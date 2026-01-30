package ExpandedIndustries.entities.bullet;

import arc.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;

public class LifestealBulletType extends BasicBulletType{
    static final EventType.UnitDamageEvent bulletDamageEvent = new EventType.UnitDamageEvent();

    /** Multiplier of damage done that's converted to health */
    public float intensity = 0.75f;

    public LifestealBulletType(float speed, float damage, float intensity, String bulletSprite){
        this.speed = speed;
        this.damage = damage;
        this.intensity = intensity;
        this.sprite = bulletSprite;
    }

    public LifestealBulletType(float speed, float damage, float intensity){
        this(speed, damage, intensity, "bullet");
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        boolean wasDead = entity instanceof Unit u && u.dead;
        float armor = 0, targetHealth = 0, healing = 0;

        if(entity instanceof Healthc h){
            targetHealth = h.health();
            if(entity instanceof Building build)
                armor = build.block.armor;

            if(pierceArmor)
                h.damagePierce(b.damage);
            else h.damage(b.damage);
        }

        if(entity instanceof Unit unit){
            armor = unit.type.armor;

            Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
            if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(status, statusDuration);

            Events.fire(bulletDamageEvent.set(unit, b));
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead){
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
        }

        handlePierce(b, health, entity.x(), entity.y());

        healing = Math.max(0.5f, (Math.min(targetHealth, Damage.applyArmor(b.damage(), armor)) * intensity));
        if(b.owner() instanceof Healthc o && healing > 0)
            o.heal(healing);
    }
}