package ExpandedIndustries.entities.bullet.abilities;

import ExpandedIndustries.content.EIFx;
import ExpandedIndustries.content.EIStatusEffects;
import arc.Core;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Healthc;
import mindustry.gen.Statusc;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class OverloadAbility extends Ability {
    private static final Seq<Healthc> all = new Seq<>();
    public boolean applyToSelf = false, infiniteSelfStatus = false;

    public float reload = 100, range = 60;
    public StatusEffect status = EIStatusEffects.overload;
    public StatusEffect teamStatus = StatusEffects.overclock;
    public float statusDuration = 60f * 15f, teamStatusDuration = 60f * 10.5f;
    public float x, y;
    protected float timer, curStroke, selfStatusDuration = teamStatusDuration;
    protected boolean anyNearby = false, reapply = true;
    public Effect activeEffect = EIFx.overload;

    public OverloadAbility(float reload, float range){
        this.reload = reload;
        this.range = range;
    }

    @Override
    public String localized(){
        return Core.bundle.format("ability.ei-overload", range / Vars.tilesize, statusDuration / 60, reload / 60);
    }

    @Override
    public void update(Unit unit){

        curStroke = Mathf.lerpDelta(curStroke, anyNearby ? 1 : 0, 0.09f);

        if((timer += Time.delta) >= reload){
            Tmp.v1.trns(unit.rotation - 90, x, y).add(unit.x, unit.y);
            float rx = Tmp.v1.x, ry = Tmp.v1.y;
            anyNearby = false;

                all.clear();

                Units.nearby(null, rx, ry, range, other -> {
                    if (other != unit && other.targetable(unit.team) && (other.team != unit.team || other.damaged())) {
                        all.add(other);
                    }
                });

            all.sort(h -> h.dst2(rx, ry));
            for(int i = 0; i < all.size; i++){
                Healthc other = all.get(i);

                if(((Teamc)other).team() == unit.team){
                    anyNearby = true;
                    if(other instanceof Statusc s) {
                        s.apply(teamStatus, teamStatusDuration);
                    }
                }else{
                    anyNearby = true;
                    if(other instanceof Statusc s){
                        s.apply(status, statusDuration);
                    }
                }
            }

            if(anyNearby || (applyToSelf && reapply)){
                if(anyNearby){
                    activeEffect.at(unit, range);
                }
                if(applyToSelf){
                    if(infiniteSelfStatus){
                       selfStatusDuration = Float.MAX_VALUE;
                       reapply = false;
                    }
                    unit.apply(teamStatus, selfStatusDuration);
                }
                timer = 0f;
            }
        }
    }
}