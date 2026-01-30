package ExpandedIndustries.world.blocks.power;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.ui.*;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class CooledSolarGenerator extends SolarGenerator{
    public Color coolColor = new Color(1, 1, 1, 0f);
    public Color hotColor = Color.valueOf("ff9575a3");
    public float heating = 0.01f;
    public float smokeThreshold = 0.3f;
    public float coolantPower = 0.5f;
    public TextureRegion topRegion;

    public CooledSolarGenerator(String name){
        super(name);
        rebuildable = false;
        hasLiquids = true;

        powerProduction = 0.4f;
        liquidCapacity = 30;
        schematicPriority = -5;

        flags = EnumSet.of(BlockFlag.generator);
        envEnabled = Env.any;
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("heat", (CooledSolarGeneratorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat));
    }

    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(name + "-top");
    }

    public class CooledSolarGeneratorBuild extends SolarGeneratorBuild{
        public float heat;

        @Override
        public void updateTile(){
            super.updateTile();

            heat += productionEfficiency * heating * Math.min(delta(), 4f);
            if(heat > 0){
                float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
                heat -= maxUsed * coolantPower;

                if(Mathf.chance((liquids.currentAmount() / liquidCapacity) / 10f * delta()))
                    Fx.steam.at(x + fxOffset(4f), y + fxOffset(4f), Mathf.random(359));

                liquids.remove(liquids.current(), maxUsed);

                if(heat > smokeThreshold){
                    float smoke = 1.0f + (heat - smokeThreshold) / (1f - smokeThreshold);
                    if(Mathf.chance(smoke / 20f * delta()))
                        Fx.reactorsmoke.at(x + fxOffset(2f), y + fxOffset(2f));
                }

                if(heat >= 0.999f)
                    kill();
            }

            heat = Mathf.clamp(heat);
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.heat) return heat;
            return super.sense(sensor);
        }

        @Override
        public void draw(){
            super.draw();

            Draw.color(coolColor, hotColor, heat);
            Fill.rect(x, y, size * tilesize, size * tilesize);

            Draw.color(liquids.current().color);
            Draw.alpha(liquids.currentAmount() / liquidCapacity);
            Draw.rect(topRegion, x, y);

            Draw.reset();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
        }

        float fxOffset(float data){
            return Mathf.range(size * tilesize / data);
        }
    }
}
