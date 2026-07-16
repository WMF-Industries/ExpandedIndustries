package ExpandedIndustries.ui;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;

import java.nio.*;

import static mindustry.Vars.*;

/** Utility for drawing weird custom effects in the worst way possible */
public class CustomDraw{
    static Seq<PeriodicRunnable> effects = new Seq<>();
    static{
        netClient.addBinaryPacketHandler("ei-ce", CustomDrawEffects::effect);
        Events.run(EventType.Trigger.update, CustomDraw::tick);
        Events.run(EventType.Trigger.draw, CustomDraw::draw);
    }

    public static void addInstance(PeriodicRunnable task){
        effects.add(task);
    }

    static void tick(){
        var it = effects.iterator();
        while(it.hasNext()){
            PeriodicRunnable task = it.next();
            if((task.lifetime -= Time.delta) <= 0){
                it.remove();
                continue;
            }

            task.tick();
        }
    }

    static void draw(){
        effects.each(PeriodicRunnable::draw);
    }

    public static class PeriodicRunnable{
        public float lifetime;

        public PeriodicRunnable(float lifetime){
            this.lifetime = lifetime;
        }

        public void draw(){}
        public void tick(){}
    }

    public static class PeriodicWorldLabel extends PeriodicRunnable{
        public Vec2 pos = new Vec2(0, 0), vel = new Vec2(0 ,0);
        public Color textColor = Color.white.cpy();
        public String text = "null";
        public float scale = 0.5f;

        public PeriodicWorldLabel(float lifetime){
            super(lifetime);
        }

        @Override
        public void draw(){
            Draw.z(Layer.overlayUI);
            float z = Drawf.text();

            Font font = Fonts.def;
            GlyphLayout layout = Pools.obtain(GlyphLayout.class, GlyphLayout::new);

            boolean ints = font.usesIntegerPositions();
            font.setUseIntegerPositions(false);
            font.getData().setScale(scale);
            layout.setText(font, text);

            font.setColor(textColor);
            font.draw(text, pos.x, pos.y, 0, Align.center, false);

            Draw.reset();
            Pools.free(layout);
            font.getData().setScale(1f);
            font.setColor(Color.white);
            font.setUseIntegerPositions(ints);

            Draw.z(z);
        }
    }

    public static class CustomDrawEffects{
        static final byte criticalId = 1;

        static final float div = tilesize * 2f;
        public static void criticalHitEffect(float x, float y, float vx, float vy, float hitbox){
            final float hs = hitbox / div, clx = Mathf.clamp(vx, -hs, hs), cly = Mathf.clamp(vy, -hs, hs);
            criticalHitEffect(x, y, clx, cly);
        }

        private static void criticalHitEffect(float x, float y, float vx, float vy){
            Call.clientBinaryPacketUnreliable("ei-ce",
                ByteBuffer.allocate(17).put(criticalId).putFloat(x).putFloat(y).putFloat(vx).putFloat(vy).array()
            );

            if(headless) return;

            addInstance(
                new PeriodicWorldLabel(90f){
                    {
                        text = Core.bundle.get("ei-crit", "Critical Hit!");
                        textColor = Color.scarlet.cpy();
                        pos.set(x, y);
                        vel.set(vx, vy);
                        scale = 0.3f;
                    }
                    @Override
                    public void tick(){
                        textColor.sub(0, 0, 0.03f, 0.02f);
                        pos.add(
                            vel.x *= 0.7f,
                            vel.y *= 0.85f
                        );
                    }
                }
            );
        }

        public static void effect(byte[] array){
            try{
                ByteBuffer ref = ByteBuffer.wrap(array);
                switch(ref.get()){
                    case criticalId: criticalHitEffect(ref.getFloat(), ref.getFloat(), ref.getFloat(), ref.getFloat());
                    default: break;
                }
            }catch(Throwable ignored){}
        }
    }
}
