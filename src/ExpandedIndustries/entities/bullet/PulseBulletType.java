package ExpandedIndustries.entities.bullet;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.util.*;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;

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
    public Func<UnlockableContent, Float> multipliers = entity -> 1f;
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
        Unit unit = null;
        float damage = b.damage;

        if(entity instanceof Unit u){
            unit = u;
            wasDead = u.dead;
            damage *= multipliers.get(u.type);
        }

        if(!net.client() && Mathf.chance(criticalHitChance)){
            damage *= criticalMultiplier;

            criticalHitEffect(b.x, b.y, b.vel.x, b.vel.y);
        }

        if(entity instanceof Healthc h){
            wasDead = h.dead();

            if(!wasDead){
                if(unit == null && entity instanceof Building build)
                    damage *= multipliers.get(build.block);

                float shield = entity instanceof Shieldc s ? Math.max(s.shield(), 0f) : 0f;
                if(maxDamageFraction > 0){
                    float cap = h.maxHealth() * maxDamageFraction + shield;
                    damage = Math.min(damage, cap);
                    //cap health to effective health for handlePierce to handle it properly
                    health = Math.min(health, cap);
                }else{
                    health += shield;
                }
                if(lifesteal > 0f && b.owner instanceof Healthc o){
                    float result = Math.max(Math.min(h.health(), damage), 0);
                    o.heal(result * lifesteal);
                }
                if(pierceArmor){
                    h.damagePierce(damage);
                }else if(armorMultiplier != 1){
                    h.damageArmorMult(damage, armorMultiplier);
                }else{
                    h.damage(damage);
                }

                afterDamage.get(h);
            }
        }

        if(unit != null){
            if(!unit.dead){
                Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
                if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
                unit.impulse(Tmp.v3);
                unit.apply(status, statusDuration);
            }

            if(!wasDead){
                Events.fire(bulletDamageEvent.set(unit, b));
                if(unit.dead)
                    Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
            }
        }

        if(!wasDead)
            handlePierce(b, health, entity.x(), entity.y());
    }
}
