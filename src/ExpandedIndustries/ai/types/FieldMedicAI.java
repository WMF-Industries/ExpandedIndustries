package ExpandedIndustries.ai.types;

import arc.math.geom.Vec2;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.meta.BlockFlag;


public class FieldMedicAI extends AIController{
    public static float retreatDelay = Time.toSeconds * 3f;
    private float retreatTimer, updateTimer;

    @Override
    public void updateTargeting() {
        if((updateTimer += Time.delta) >= 10){
            updateTimer = 0;
            target = Units.closest(unit.team, unit.x, unit.y, Unit::damaged);
            if(target == null)target = Units.findDamagedTile(unit.team, unit.x, unit.y);
        }
    }

    @Override
    public void updateMovement(){
        if(target != null){
            moveTo(target, unit.type.range * 0.9f, 5, true, null);
            if(!unit.within(target, unit.range())){
                unit.controlWeapons(false);
            }else{
                unit.controlWeapons(true);
                unit.aim(target);
            }
            unit.lookAt(target);
            retreatTimer = 0f;
        }else{
            unit.controlWeapons(false);
            if((retreatTimer += Time.delta) >= retreatDelay || unit.damaged()){
                var retreatTarget = targetFlag(unit.x, unit.y, BlockFlag.repair, false);
                if(retreatTarget == null)retreatTarget = unit.closestCore();
                if(retreatTarget != null)moveTo(retreatTarget, unit.range() * 0.5f, 5);
            }
        }
    }
}
