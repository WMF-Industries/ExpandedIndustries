package ExpandedIndustries.content;

import arc.Events;
import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.game.EventType;
import mindustry.type.StatusEffect;

import static mindustry.Vars.state;
import static mindustry.content.StatusEffects.*;

public class EIStatusEffects {
    public static StatusEffect lockdown, overload, sticky, frozen, warm;

    public static void load() {
        lockdown = new StatusEffect("lockdown") {{
            speedMultiplier = 0.6f;
            reloadMultiplier = 0.7f;
            buildSpeedMultiplier = 0.5f;
            effectChance = 0.05f;
            color = Color.clear;
            effect = Fx.none;
        }};
        overload = new StatusEffect("overload") {{
            speedMultiplier = 0.2f;
            disarm = true;
        }};
        sticky = new StatusEffect("sticky") {{
            speedMultiplier = 0.4f;
            reloadMultiplier = 0.6f;
            damageMultiplier = 0.7f;
            color = Color.valueOf("FFAF5FE8");
            effect = EIFx.reu;

            init(() -> {
                opposite(freezing, frozen);
                affinity(burning, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    Fx.burning.at(unit.x + Mathf.range(unit.bounds() / 2f), unit.y + Mathf.range(unit.bounds() / 2f));
                    result.set(burning, Math.min(time + result.time, 450f));
                });
            });
        }};
        frozen = new StatusEffect("frozen") {{
            speedMultiplier = 0;
            reloadMultiplier = 0;
            damage = 0.11f;
            permanent = true;
            init(() -> opposite(warm, burning, melting));
        }};
        warm = new StatusEffect("warm");
    }
}
