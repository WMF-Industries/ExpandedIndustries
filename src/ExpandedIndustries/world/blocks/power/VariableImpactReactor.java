package ExpandedIndustries.world.blocks.power;

import ExpandedIndustries.world.blocks.*;
import arc.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.blocks.power.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

/** Impact Reactor that follows the network load
 * Saves fuel when underloaded
 * Receives damage and eventually explodes if overloaded */
public class VariableImpactReactor extends ImpactReactor{
    /// Fraction of reactor health to take as damage every tick when load goes over 100%
    public float damageFraction = 0.007f;
    /// Startup warmup speed (up to minimal power fraction)
    public float startupSpeed = 0.01f;
    /// Minimal power target in the network, in power per tick
    public float minPowerLevel = 8.33f;
    /// Minimal reactor power fraction to keep
    public float minPowerFract = 0.2f;
    /// Max load factor before the reactor explodes, -1 to disable
    public float maxPowerFactor = 2f;
    /// Load follow speed
    public float loadSpeed = 0.03f;

    public VariableImpactReactor(String name){
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("load", (VariableImpactReactorBuild entity) -> new Bar(() ->
            Core.bundle.format("bar.ei-power-load",
                Strings.fixed(entity.currentFactor * 100, 1)),
            () -> Tmp.c1.set(Pal.powerBar).lerp(Pal.turretHeat, entity.currentFactor),
            () -> entity.currentFactor));
    }

    @Override
    public void setStats(){
        super.setStats();
        // warmup speed on this reactor is unpredictable
        stats.remove(Stat.warmupTime);

        stats.add(EIStat.powerTarget, Mathf.round(minPowerLevel * 60f), StatUnit.powerUnits);
        stats.add(EIStat.minLoad, minPowerFract * 100f, StatUnit.percent);
        if(maxPowerFactor > 0)
            stats.add(EIStat.maxLoad, maxPowerFactor * 100f, StatUnit.percent);
    }

    public class VariableImpactReactorBuild extends ImpactReactorBuild{
        float loadFactor, currentFactor;

        public float consumeFactor(){
            return Mathf.clamp(currentFactor, 0.1f, 1f) * timeScale;
        }

        @Override
        public float getPowerProduction(){
            return enabled ? powerProduction * currentFactor : 0f;
        }

        public float satisfaction(){
            return power.graph.getLastScaledPowerIn() - power.graph.getLastScaledPowerOut();
        }

        @Override
        public void updateTile(){
            boolean active = false;

            if(efficiency >= 0.9999f && power.status >= 0.99f){
                active = true;
                boolean prevOut = getPowerProduction() <= consPower.requestedPower(this);

                warmup = Mathf.lerpDelta(warmup, 1f, warmupSpeed * timeScale);
                if(Mathf.equal(warmup, 1f, 0.001f)){
                    warmup = 1f;
                }

                if(!prevOut && (getPowerProduction() > consPower.requestedPower(this))){
                    Events.fire(EventType.Trigger.impactPower);
                }

                if(timer(timerUse, itemDuration / consumeFactor())){
                    consume();
                }
            }else{
                warmup = Mathf.lerpDelta(warmup, 0f, 0.01f);
                currentFactor = loadFactor = 0;
            }

            totalProgress += warmup * Time.delta;

            productionEfficiency = Mathf.pow(warmup, 5f);
            if(active && productionEfficiency > 0f){
                float satis = satisfaction(), targetSpeed = currentFactor < minPowerFract ? startupSpeed : warmupSpeed;
                loadFactor = Math.max(minPowerFract, loadFactor + (satis < minPowerLevel ? loadSpeed : satis > minPowerLevel ? -loadSpeed : 0f));
                currentFactor = Mathf.lerp(currentFactor, loadFactor * productionEfficiency, targetSpeed * productionEfficiency * timeScale);
            }

            if(damageFraction > 0f && currentFactor > 1f){
                damageContinuous(maxHealth * damageFraction);
            }

            if(maxPowerFactor > 0 && currentFactor > maxPowerFactor){
                kill();
            }
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(currentFactor);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            currentFactor = read.f();
        }
    }
}
