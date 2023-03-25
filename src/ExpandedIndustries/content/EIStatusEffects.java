package ExpandedIndustries.content;

import arc.graphics.*;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.type.*;

import static arc.math.Angles.randLenVectors;
import static mindustry.content.StatusEffects.burning;
import static mindustry.content.StatusEffects.freezing;

public class EIStatusEffects {
    public static StatusEffect lockdown, sticky;

    public static void load(){
        lockdown = new StatusEffect("lockdown"){{
            speedMultiplier = 0.6f;
            reloadMultiplier = 0.7f;
            buildSpeedMultiplier = 0.5f;
            effectChance = 0.05f;
            color = Color.clear;
            effect = Fx.none;
        }};
        sticky = new StatusEffect("sticky"){{
            speedMultiplier = 0.4f;
            reloadMultiplier = 0.6f;
            damageMultiplier = 0.7f;
            color = Color.valueOf("FFAF5FE8");
            effect = EIFx.sticky;

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
