package ExpandedIndustries.world.blocks.defense;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.Bar;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/// OverdriveProjector, but covering the entire map in an optimized way
public class SectorOverdriveProjector extends Block{
    public TextureRegion topRegion;
    public float reload = 60f;
    public float speedBoost = 1.5f;
    public float speedBoostPhase = 0.75f;
    public float useTime = 400f;
    public boolean hasBoost = true;
    public Color baseColor = Color.valueOf("feb380");
    public Color phaseColor = Color.valueOf("ffd59e");

    public SectorOverdriveProjector(String name){
        super(name);
        solid = update = hasPower = hasItems = emitLight = true;
        canOverdrive = false;

        envEnabled |= Env.space;
        group = BlockGroup.projectors;

        lightRadius = 50f;
        ambientSound = Sounds.loopCircuit;
        ambientSoundVolume = 0.13f;
    }

    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(name + "-top");
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    @Override
    public void setStats(){
        stats.timePeriod = useTime;
        super.setStats();

        stats.add(Stat.speedIncrease, "+" + (int)(speedBoost * 100f - 100) + "%");
        stats.add(Stat.productionTime, useTime / 60f, StatUnit.seconds);

        if(hasBoost && findConsumer(f -> f instanceof ConsumeItems) instanceof ConsumeItems items){
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters("+{0}%", stats.timePeriod, speedBoostPhase * 100f, 0, items.items));
        }
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("boost", (SectorOverdriveBuild entity) -> new Bar(() -> Core.bundle.format("bar.boost", Mathf.round(Math.max((entity.realBoost() * 100 - 100), 0))), () -> Pal.accent, () -> entity.realBoost() / (hasBoost ? speedBoost + speedBoostPhase : speedBoost)));
    }

    public class SectorOverdriveBuild extends Building{
        public float heat, charge = Mathf.random(reload), phaseHeat, smoothEfficiency, useProgress;

        @Override
        public void drawLight(){
            Drawf.light(x, y, lightRadius * smoothEfficiency, baseColor, 0.7f * smoothEfficiency);
        }

        @Override
        public void updateTile(){
            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 ? 1f : 0f, 0.08f);
            charge += heat * Time.delta;

            if(hasBoost){
                phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);
            }

            if(charge >= reload){
                charge = 0f;

                float boost = realBoost();
                team.data().buildings.each(b -> b.block.canOverdrive, b -> b.applyBoost(boost, reload + 1f));
            }

            if(efficiency > 0){
                useProgress += delta();
            }

            if(useProgress >= useTime){
                consume();
                useProgress %= useTime;
            }


        }

        public float realBoost(){
            return (speedBoost + phaseHeat * speedBoostPhase) * efficiency;
        }

        @Override
        public void draw(){
            super.draw();

            float f = 1f - (Time.time / 100f) % 1f;

            Draw.color(baseColor, phaseColor, phaseHeat);
            Draw.alpha(heat * Mathf.absin(Time.time, 50f / Mathf.PI2, 1f) * 0.5f);
            Draw.rect(topRegion, x, y);
            Draw.alpha(1f);
            Lines.stroke((2f * f + 0.1f) * heat);

            float r = Math.max(0f, Mathf.clamp(2f - f * 2f) * size * tilesize / 2f - f - 0.2f), w = Mathf.clamp(0.5f - f) * size * tilesize;
            Lines.beginLine();
            for(int i = 0; i < 4; i++){
                Lines.linePoint(x + Geometry.d4(i).x * r + Geometry.d4(i).y * w, y + Geometry.d4(i).y * r - Geometry.d4(i).x * w);
                if(f < 0.5f) Lines.linePoint(x + Geometry.d4(i).x * r - Geometry.d4(i).y * w, y + Geometry.d4(i).y * r + Geometry.d4(i).x * w);
            }
            Lines.endLine(true);

            Draw.reset();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
            write.f(phaseHeat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
            phaseHeat = read.f();
        }
    }
}
