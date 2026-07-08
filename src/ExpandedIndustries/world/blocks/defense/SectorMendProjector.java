package ExpandedIndustries.world.blocks.defense;

import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/// MendProjector, but covering the entire map in an optimized way
public class SectorMendProjector extends Block{
    public final int timerUse = timers++;
    public Color baseColor = Color.valueOf("84f491");
    public Color phaseColor = baseColor;
    public TextureRegion topRegion;
    public float reload = 250f;
    public float healPercent = 12f;
    public float healAmount = 50f;
    public float phaseBoost = 12f;
    public float useTime = 400f;
    public Sound mendSound = Sounds.healWave;
    public float mendSoundVolume = 0.5f;

    public SectorMendProjector(String name){
        super(name);
        solid = update = hasPower = hasItems = emitLight = suppressable = true;

        group = BlockGroup.projectors;
        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.blockRepair);

        lightRadius = 50f;
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

        stats.add(Stat.repairTime, (int)(100f / healPercent * reload / 60f), StatUnit.seconds);
        if(findConsumer(c -> c instanceof ConsumeItems) instanceof ConsumeItems cons){
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters(
                "{0}" + StatUnit.timesSpeed.localized(),
                stats.timePeriod, (phaseBoost + healPercent) / healPercent, 0,
                cons.items)
            );
        }
    }

    public class SectorMendBuild extends Building{
        public float heat, charge = Mathf.random(reload), phaseHeat, smoothEfficiency;
        public boolean any;

        @Override
        public void updateTile(){
            boolean canHeal = !checkSuppression();

            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 && canHeal ? 1f : 0f, 0.08f);
            charge += heat * delta();

            phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);

            if(optionalEfficiency > 0 && timer(timerUse, useTime / timeScale) && canHeal){
                consume();
            }

            if(charge >= reload && canHeal){
                charge = 0f;

                any = false;

                float healing = (healAmount + phaseHeat * phaseBoost) * efficiency;
                team.data().buildings.each(b -> b.damaged() && !b.isHealSuppressed(), other -> {
                    other.heal(healing);
                    other.recentlyHealed();
                    Fx.healBlockFull.at(other.x, other.y, other.block.size, baseColor, other.block);
                    any = true;
                });

                if(any){
                    mendSound.at(this, 1f + Mathf.range(0.1f), mendSoundVolume);
                }
            }
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.progress) return Mathf.clamp(charge / reload);
            return super.sense(sensor);
        }

        @Override
        public void drawCached(){
            super.draw();
        }

        @Override
        public void draw(){
            super.draw();

            if(!Lod.l2) return;

            float f = 1f - (Time.time / 100f) % 1f;
            Draw.color(baseColor, phaseColor, phaseHeat);
            Draw.alpha(heat * Mathf.absin(Time.time, 50f / Mathf.PI2, 1f) * 0.5f);
            Draw.rect(topRegion, x, y);
            Draw.alpha(1f);
            Lines.stroke((2f * f + 0.2f) * heat);
            Lines.square(x, y, Math.min(1f + (1f - f) * size * tilesize / 2f, size * tilesize/2f));

            Draw.reset();
        }

        @Override
        public void drawLight(){
            Drawf.light(x, y, lightRadius * smoothEfficiency, baseColor, 0.7f * smoothEfficiency);
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
