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

public class OverheatSolarGenerator extends PowerGenerator {
    public Color coolColor = new Color(1, 1, 1, 0f);
    public Color hotColor = Color.valueOf("ff9575a3");
    public float heating = 0.01f;
    public float smokeThreshold = 0.3f;
    public float coolantPower = 0.5f;
    public TextureRegion topRegion;

    public OverheatSolarGenerator(String name){
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
    public void setStats(){
        super.setStats();
        stats.remove(generationType);
        stats.add(generationType, powerProduction * 60.0f, StatUnit.powerSecond);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("heat", (OverheatSolarGeneratorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat));
    }

    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(name + "-top");
    }

    public class OverheatSolarGeneratorBuild extends GeneratorBuild {
        public float heat;
        @Override
        public void updateTile(){
            productionEfficiency = enabled ?
                    state.rules.solarMultiplier * Mathf.maxZero(Attribute.light.env() +
                            (state.rules.lighting ?
                                    1f - state.rules.ambientLight.a :
                                    1f
                            )) : 0f;

            heat += productionEfficiency * heating * Math.min(delta(), 4f);

            if(heat > 0){
                float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
                heat -= maxUsed * coolantPower;
                liquids.remove(liquids.current(), maxUsed);
            }

            if(heat > smokeThreshold){
                float smoke = 1.0f + (heat - smokeThreshold) / (1f - smokeThreshold); //ranges from 1.0 to 2.0
                if(Mathf.chance(smoke / 20.0 * delta())){
                    Fx.reactorsmoke.at(x + Mathf.range(size * tilesize / 2f),
                            y + Mathf.range(size * tilesize / 2f));
                }
            }

            heat = Mathf.clamp(heat);

            if(heat >= 0.999f){
                kill();
            }
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
    }
}
