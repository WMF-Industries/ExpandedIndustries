package ExpandedIndustries.content;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.type.StatusEffect;

import static mindustry.content.StatusEffects.burning;
import static mindustry.content.StatusEffects.freezing;

public class EIStatusEffects {
    public static StatusEffect lockdown, overload, sticky;

    public static void load(){
        lockdown = new StatusEffect("lockdown"){{
            speedMultiplier = 0.6f;
            reloadMultiplier = 0.7f;
            buildSpeedMultiplier = 0.5f;
            effectChance = 0.05f;
            color = Color.clear;
            effect = Fx.none;
        }};
        overload = new StatusEffect("overload"){{
            speedMultiplier = 0.2f;
            disarm = true;
        }};
        sticky = new StatusEffect("sticky"){{
            speedMultiplier = 0.4f;
            reloadMultiplier = 0.6f;
            damageMultiplier = 0.7f;
            color = Color.valueOf("FFAF5FE8");
            effect = EIFx.reu;

            init(() -> {
                opposite(freezing);
                affinity(burning, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    Fx.burning.at(unit.x + Mathf.range(unit.bounds() / 2f), unit.y + Mathf.range(unit.bounds() / 2f));
                    result.set(burning, Math.min(time + result.time, 450f));
                });
            });
        }};
    }
}
