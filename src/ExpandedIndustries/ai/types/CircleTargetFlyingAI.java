package ExpandedIndustries.ai.types;

import mindustry.ai.types.FlyingAI;

public class CircleTargetFlyingAI extends FlyingAI {
    @Override
    public void updateMovement(){
        unloadPayloads();

        if(target != null && unit.hasWeapons()){
                unit.lookAt(target);
                circleAttack(120f);
        }
    }
}
