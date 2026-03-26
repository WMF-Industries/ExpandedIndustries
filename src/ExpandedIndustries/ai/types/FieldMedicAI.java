package ExpandedIndustries.ai.types;

import arc.math.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;


public class FieldMedicAI extends AIController{
    public static float retreatDelay = 180f;
    private float retreatTimer, updateTimer, shootCone = Float.MAX_VALUE;

    @Override
    public void init(){
        super.init();

        for(int i = 0; i < unit.type.weapons.size; i++)
            if(unit.type.weapons.get(i) instanceof RepairBeamWeapon wp && wp.shootCone < shootCone)
                shootCone = wp.shootCone;
    }

    @Override
    public void updateTargeting(){
        if((updateTimer += Time.delta) >= 5){
            updateTimer = 0;

            target = Units.closest(unit.team, unit.x, unit.y, Unit::damaged);
            if(target == null)
                target = Units.findDamagedTile(unit.team, unit.x, unit.y);

            for(WeaponMount mount : unit.mounts)
                if(mount.weapon instanceof RepairBeamWeapon)
                    mount.target = target;
        }
    }

    @Override
    public void updateMovement(){
        if(target != null){
            moveTo(target, unit.type.range * 0.85f, 5, true, null);

            unit.aim(target);
            unit.lookAt(target);
            unit.controlWeapons(true, unit.within(target, unit.range()) && Angles.within(unit.rotation, Angles.angle(unit.x, unit.y, target.x(), target.y()), shootCone));

            retreatTimer = 0f;
        }else{
            unit.controlWeapons(false, false);

            if((retreatTimer += Time.delta) >= retreatDelay || unit.damaged()){
                var retreatTarget = targetFlag(unit.x, unit.y, BlockFlag.repair, false);
                if(retreatTarget == null)
                    retreatTarget = unit.closestCore();
                if(retreatTarget != null)
                    moveTo(retreatTarget, unit.range() * 0.5f, 5);
            }
        }
    }
}
