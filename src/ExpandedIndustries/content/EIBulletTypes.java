package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;

import static ExpandedIndustries.content.EILiquids.*;

public class EIBulletTypes{
    public static BulletType baseLOXBullet, tsunamiLOXBullet, baseReuriumBullet, tsunamiReuriumBullet, hailIceBullet, rippleIceBullet;

    public static void load(){
        baseLOXBullet = new LiquidBulletType(liquidOxygen){{
            damage = 0f;
            knockback = 0.4f;
            statusDuration = 60f * 2;
            lifetime = 60f;
            speed = 4f;
            lifetime = 26f;
        }};
        tsunamiLOXBullet = new LiquidBulletType(liquidOxygen){{
            damage = 0.3f;
            knockback = 1.4f;
            statusDuration = 60f * 4;
            lifetime = 60f;
            speed = 3.4f;
            orbSize = 4f;
            ammoMultiplier = 0.6f;
            reloadMultiplier = 0.75f;
        }};
        baseReuriumBullet = new LiquidBulletType(reurium){{
            damage = 0.4f;
            knockback = 1.5f;
            statusDuration = 450f;
            lifetime = 60f;
            speed = 3f;
        }};
        tsunamiReuriumBullet = new LiquidBulletType(reurium){{
            damage = 0.6f;
            knockback = 1.7f;
            statusDuration = 600;
            ammoMultiplier = 0.6f;
            reloadMultiplier = 0.75f;
            lifetime = 47.5f;
            speed = 4f;
        }};

        Color iceFront = Color.valueOf("#A8AEF0"), iceBack = Color.valueOf("#736CCE");
        hailIceBullet = new ArtilleryBulletType(3f, 9f){{
            knockback = 1.1f;
            lifetime = 80f;
            width = height = 12f;
            collidesTiles = false;
            splashDamageRadius = 18f;
            splashDamage = 25f;
            status = StatusEffects.freezing;
            statusDuration = 240f;

            fragBullets = 3;
            fragBullet = new BasicBulletType(4f, 12f){{
                lifetime = 6f;
                width = height = 3f;
                status = StatusEffects.freezing;
                statusDuration = 180f;

                hitColor = backColor = iceBack;
                frontColor = iceFront;
                despawnEffect = Fx.hitBulletColor;
            }};

            hitColor = backColor = trailColor = iceBack;
            frontColor = iceFront;
            despawnEffect = Fx.hitBulletColor;
        }};
        rippleIceBullet = new ArtilleryBulletType(3f, 30){{
            hitEffect = new MultiEffect(Fx.flakExplosion, Fx.shockwaveSmaller);
            knockback = 1.1f;
            lifetime = 80f;
            width = 12f;
            height = 14f;
            collidesTiles = false;
            splashDamageRadius = 24f;
            splashDamage = 45f;
            status = StatusEffects.freezing;
            statusDuration = 300f;
            lifeScaleRandMax = 1.08f;
            lifeScaleRandMin = 0.95f;

            fragBullets = 4;
            fragBullet = new BasicBulletType(4f, 12f){{
                lifetime = 6f;
                width = height = 3.5f;
                status = StatusEffects.freezing;
                statusDuration = 210f;

                hitColor = backColor = iceBack;
                frontColor = iceFront;
                despawnEffect = Fx.hitBulletColor;
            }};

            backColor = hitColor = trailColor = iceBack;
            frontColor = iceFront;
            despawnEffect = Fx.hitBulletColor;
        }};
    }
}
