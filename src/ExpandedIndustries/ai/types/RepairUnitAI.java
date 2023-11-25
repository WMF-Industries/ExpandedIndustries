package ExpandedIndustries.ai.types;

import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.meta.BlockFlag;


public class RepairUnitAI extends AIController {
    public static float retreatDelay = Time.toSeconds * 3f;
    private float retreatTimer;

    @Override
    public void updateTargeting() {
        target = Units.closest(unit.team, unit.x, unit.y, Unit::damaged);
    }

    @Override
    public void updateMovement(){
        if(target != null){
            if(!unit.within(target, unit.type.range)){
                moveTo(target, unit.type.range * 0.9f , 50f);
                unit.controlWeapons(true);
            }else unit.controlWeapons(false);
            unit.lookAt(target);
            retreatTimer = 0f;
        }else if((retreatTimer += Time.delta) >= retreatDelay){
            var retreatTarget = targetFlag(unit.x, unit.y, BlockFlag.repair, false);
            if(retreatTarget == null) retreatTarget = unit.closestCore();
            moveTo(retreatTarget, (unit.type.range / 2));
        }
    }
}
