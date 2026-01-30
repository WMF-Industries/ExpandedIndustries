package ExpandedIndustries.entities.bullet.abilities;

import ExpandedIndustries.content.*;
import arc.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.type.*;

public class StatusAbility extends Ability{
    private static final Seq<Unit> targets = new Seq<>();
    public boolean selfBoost = false;

    public float reload = 100, range = 60, x, y, timer;
    public StatusEffect status = StatusEffects.none, teamStatus = StatusEffects.none;
    public float statusDuration = 60f * 15f, teamStatusDuration = 60f * 10.5f;
    public Effect activeEffect = EIFx.overload;

    public StatusAbility(float reload, float range){
        this.reload = reload;
        this.range = range;
    }

    @Override
    public String localized(){
        return Core.bundle.format("ability.ei-status-ability", range / Vars.tilesize, statusDuration / 60, reload / 60);
    }

    @Override
    public void update(Unit unit){
        if((timer += Time.delta) >= reload){
            Tmp.v1.trns(unit.rotation - 90, x, y).add(unit.x, unit.y);
            float rx = Tmp.v1.x, ry = Tmp.v1.y;

            targets.clear();
            Units.nearby(null, rx, ry, range, other -> {
                if(other != unit && other.targetable(unit.team) && (other.team != unit.team || other.damaged()))
                    targets.add(other);
            });

            if(targets.isEmpty()){
                if(selfBoost)
                    unit.apply(teamStatus, teamStatusDuration);
            }else{
                targets.each(u -> u.apply(u.team == unit.team ? teamStatus : status, u.team == unit.team ? teamStatusDuration : statusDuration));

                activeEffect.at(unit, range);
                timer = 0f;
            }
        }
    }
}