package ExpandedIndustries.ai.types;

import mindustry.ai.types.*;

public class CircleTargetAI extends FlyingAI{
    @Override
    public void updateMovement(){
        unloadPayloads();

        if(target != null && unit.hasWeapons()){
            unit.lookAt(target);
            circleAttack(120f);
        }
    }
}
