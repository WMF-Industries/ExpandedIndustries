package ExpandedIndustries.entities.bullet;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;

import static mindustry.Vars.*;
import static ExpandedIndustries.ui.CustomDraw.CustomDrawEffects.*;

/**
 * To add per-condition damage multipliers or after-damage events, do:
 * <pre>{@code
 *  bullet = new PulseBulletType(){{
 *      // bullet stats and stuff
 *      multipliers = entity -> {
 *          // your code
 *      }
 *      afterDamage = entity -> {
 *          // more of your code
 *      }
 *  }};
 * }</pre>
 **/
public class PulseBulletType extends BasicBulletType{
    static final EventType.UnitDamageEvent bulletDamageEvent = new EventType.UnitDamageEvent();

    /** Function used to fetch the base multiplier for different contents */
    public Func<Healthc, Float> multipliers = entity -> 1f;
    /** Function letting bullets have custom behavior when dealing damage to an entity */
    public Cons<Healthc> afterDamage = entity -> {};
    /** Chance for the bullet to deal a critical hit */
    public double criticalHitChance = 0.07f;
    /** Critical hit damage multiplier */
    public float criticalMultiplier = 2.5f;

    public PulseBulletType(float speed, float damage){
        super(speed, damage);
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        boolean wasDead = false;
        float dmg = damage;

        if(entity instanceof Unit u)
            wasDead = u.dead;

        if(!net.client() && Mathf.chance(criticalHitChance)){
            dmg *= criticalMultiplier;

            criticalHitEffect(b.x, b.y, b.vel.x, b.vel.y);
        }

        if(entity instanceof Healthc h){
            dmg *= multipliers.get(h);

            if(pierceArmor){
                h.damagePierce(dmg);
            }else h.damage(dmg);

            afterDamage.get(h);
        }

        if(entity instanceof Unit unit){
            Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
            if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(status, statusDuration);

            Events.fire(bulletDamageEvent.set(unit, b));
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead)
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));

        handlePierce(b, health, entity.x(), entity.y());
    }
}
