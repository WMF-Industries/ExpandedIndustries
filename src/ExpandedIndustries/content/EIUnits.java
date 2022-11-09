package ExpandedIndustries.content;

import ExpandedIndustries.entities.bullet.LifestealBulletType;
import arc.graphics.*;
import mindustry.ai.UnitCommand;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.graphics.*;
import mindustry.ai.types.*;
import mindustry.entities.bullet.*;
import mindustry.type.ammo.*;

import static mindustry.gen.Sounds.laserblast;
import static mindustry.gen.Sounds.none;

public class EIUnits {
    public static UnitType
    stormer, rusher, escapade, natorin, terrand,

    breadnight, toastnight,

    centurion, alturion,

    pygmy, schaus, ageronia,

    SmolBoi, MediumBoi, LargeBoi, PayloadBoi,

    piece;
    public static void load() {

        stormer = new UnitType("stormer") {{
            constructor = LegsUnit::create;
            health = 230f;
            buildSpeed = 1.75f;
            armor = 2.5f;
            rotateSpeed = 2.5f;
            legCount = 6;
            legLength = 15f;
            legBaseOffset = 2.5f;
            shadowElevation = 0.2f;
            hovering = true;
            groundLayer = Layer.legUnit;
            outlines = false;

            ammoType = new PowerAmmoType(750);

            speed = 0.7f;
            hitSize = 12f;

            weapons.add(
                    new Weapon("ei-stormer-weapon") {{
                        top = false;
                        range = 100f;
                        shake = 2f;
                        shootY = 4f;
                        x = 6.5f;
                        reload = 75f;
                        recoil = 1.2f;
                        shootSound = Sounds.laser;
                        bullet = new LaserBulletType() {{
                            damage = 25;
                            healPercent = 20f;
                            length = 100f;
                            pierce = true;
                            pierceCap = 5;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }}
            );
        }};
        rusher = new UnitType("rusher") {{
            constructor = LegsUnit::create;
            allowLegStep = true;
            health = 470f;
            buildSpeed = 3f;
            armor = 10f;
            rotateSpeed = 2.5f;
            legCount = 8;
            legLength = 25f;
            legBaseOffset = 2.5f;
            legExtension = 1;
            shadowElevation = 0.3f;
            hovering = true;
            groundLayer = Layer.legUnit;
            outlines = false;

            ammoType = new PowerAmmoType(1000);

            speed = 0.6f;
            hitSize = 20f;

            weapons.add(
                    new Weapon("ei-rusher-weapon") {{
                        top = false;
                        rotate = false;
                        shake = 2f;
                        range = 160f;
                        shootY = 4f;
                        x = 11;
                        reload = 75f;
                        recoil = 1.2f;
                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType() {{
                            damage = 40;
                            healPercent = 7.5f;
                            buildingDamageMultiplier = 0.75f;
                            length = 160f;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                            pierce = true;
                            pierceCap = 7;
                        }};
                    }},
                    new Weapon("ei-rusher-artillery") {{
                        top = true;
                        shake = 2.7f;
                        x = 0;
                        y = -5;
                        reload = 160;
                        range = 240f;
                        rotate = true;
                        mirror = false;
                        shootSound = Sounds.artillery;
                        bullet = new ArtilleryBulletType() {{
                            splashDamage = 65;
                            splashDamageRadius = 15;
                            height = width = 8;
                            collidesTeam = false;
                            lifetime = 70;
                            speed = 4;
                        }};
                    }});
        }};
        escapade = new UnitType("escapade") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = true;
            allowLegStep = true;
            hitSize = 32;
            speed = 0.5f;
            rotateSpeed = 2.2f;
            shadowElevation = 0.45f;
            health = 1020;
            armor = 15;
            buildSpeed = 3.5f;
            legCount = 8;
            legLength = 60;
            legSplashDamage = 60;
            legSplashRange = 40;
            legBaseOffset = 0;
            legExtension = 0;
            lightRadius = 50;
            outlines = false;

            ammoType = new PowerAmmoType(1500);

            weapons.add(
                    new Weapon("ei-escapade-weapon") {{
                        x = 16;
                        y = 2;
                        rotate = false;
                        top = false;
                        mirror = true;
                        reload = 40;
                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType() {{
                            damage = 190;
                            healPercent = 3f;
                            buildingDamageMultiplier = 0.50f;
                            length = 200f;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                            pierce = true;
                            pierceCap = 9;
                        }};
                    }},
                    new Weapon("ei-escapade-artillery") {{
                        x = 6;
                        y = -7;
                        reload = 180;
                        rotate = true;
                        rotateSpeed = 1.6f;
                        mirror = true;
                        top = true;
                        shake = 4;

                        bullet = new ArtilleryBulletType() {{
                            splashDamage = 275;
                            splashDamageRadius = 24;
                            height = width = 15;
                            collidesTeam = false;
                            lifetime = 55;
                            speed = 5.5f;
                        }};
                    }}
            );
        }};
        natorin = new UnitType("natorin") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = true;
            hitSize = 68;
            drawShields = false;
            allowLegStep = true;
            shadowElevation = 0.7f;
            health = 7840;
            armor = 20;
            speed = 0.45f;
            rotateSpeed = 2.2f;
            buildSpeed = 5;
            legCount = 8;
            legLength = 80;
            legSplashDamage = 70;
            legSplashRange = 60;
            outlines = false;

            ammoType = new PowerAmmoType(4750);

            weapons.add(new Weapon("ei-natorin-weapon") {{
                    x = 18;
                    y = 4;
                    reload = 100;
                    rotate = true;
                    rotateSpeed = 1.7f;
                    continuous = true;
                    mirror = true;
                    shootSound = Sounds.beam;
                    top = true;
                    shootY = 18;
                    shake = 4;
                    alternate = false;

                       bullet = new ContinuousLaserBulletType() {{
                           colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                           damage = 32;
                           collidesTeam = true;
                           healPercent = 4.5f;
                           length = 240;
                           width = 18;
                           drawSize = 240;
                           lifetime = 170;
                           shake = 1.7f;
                       }};
                    }}
            );
        }};
        terrand = new UnitType("terrand"){{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            health = 21200;
            hitSize = 88;
            armor = 30;
            itemCapacity = 180;
            speed = 0.4f;
            legCount = 10;
            legLength = 80;
            legBaseOffset = 32;
            legExtension = 2;
            legSpeed = 0.02f;
            legSplashDamage = 130;
            legSplashRange = 85;
            allowLegStep = true;
            rotateSpeed = 1.45f;
            outlineRadius = 4;

            weapons.add(
                    new Weapon("ei-terrand"){{
                        shoot.firstShotDelay = 90;
                        reload = 390;

                        chargeSound = Sounds.lasercharge;
                        shootStatus = StatusEffects.unmoving;
                        shootStatusDuration = shoot.firstShotDelay;
                        x = y = 0;

                        shootSound = laserblast;
                        mirror = false;
                        top = false;
                        recoil = 0;
                        bullet = new EmpBulletType(){{
                            chargeEffect = Fx.greenLaserCharge;
                            speed = 5;
                            sprite = "circle-bullet";
                            scaleLife = true;
                            lightOpacity = 0.7f;
                            unitDamageScl = 1.2f;
                            healPercent = 0.09f;
                            damage = 150;
                            lifetime = 60;
                            radius = 120f;
                            width = height = 15;
                            shrinkX = shrinkY = 0;
                            collides = false;
                            status = StatusEffects.electrified;
                            statusDuration = 450;
                            frontColor = Color.valueOf("ffffff");
                            backColor = Color.valueOf("83f793");
                            lightColor = Color.valueOf("83f793");
                            hitColor = Color.valueOf("83f793");
                            hitEffect = despawnEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        sizeFrom = 90;
                                        sizeTo = 90;
                                        lifetime = 45;
                                        colorFrom = Color.valueOf("a5f5af");
                                        colorTo = Color.valueOf("ffffff");
                                    }},
                                    new WaveEffect() {{
                                        sizeFrom = 0;
                                        sizeTo = 90;
                                        lifetime = 30;
                                        colorFrom = Color.valueOf("a5f5af");
                                        colorTo = Color.valueOf("ffffff");
                                    }},
                                    new ParticleEffect() {{
                                        length = 0;
                                        lifetime = 30;
                                        particles = 1;
                                        sizeFrom = 5;
                                        sizeTo = 0;
                                        colorFrom = Color.valueOf("a5f5af");
                                        colorTo = Color.valueOf("ffffff");
                                    }}
                            );
                            lightning = 1;
                            lightningLength = 2;
                            lightningCone = 0;
                            lightningType = new EmpBulletType() {
                                {
                                    speed = 0;
                                    sprite = "circle-bullet";
                                    scaleLife = true;
                                    lightOpacity = 0.7f;
                                    unitDamageScl = 1.2f;
                                    healPercent = 0.09f;
                                    damage = 150;
                                    lifetime = 30;
                                    radius = 120f;
                                    width = height = 15;
                                    shrinkX = shrinkY = 0;
                                    collides = false;
                                    status = StatusEffects.electrified;
                                    statusDuration = 450;
                                    frontColor = Color.valueOf("ffffff");
                                    backColor = Color.valueOf("83f793");
                                    lightColor = Color.valueOf("83f793");
                                    hitColor = Color.valueOf("83f793");
                                    hitEffect = despawnEffect = new MultiEffect(
                                            new WaveEffect() {{
                                                sizeFrom = 90;
                                                sizeTo = 90;
                                                lifetime = 45;
                                                colorFrom = Color.valueOf("a5f5af");
                                                colorTo = Color.valueOf("ffffff");
                                            }},
                                            new WaveEffect() {{
                                                sizeFrom = 0;
                                                sizeTo = 90;
                                                lifetime = 30;
                                                colorFrom = Color.valueOf("a5f5af");
                                                colorTo = Color.valueOf("ffffff");
                                            }},
                                            new ParticleEffect() {{
                                                length = 0;
                                                lifetime = 30;
                                                particles = 1;
                                                sizeFrom = 5;
                                                sizeTo = 0;
                                                colorFrom = Color.valueOf("a5f5af");
                                                colorTo = Color.valueOf("ffffff");
                                            }}
                                    );
                                    lightning = 1;
                                    lightningLength = 2;
                                    lightningCone = 0;

                                    lightningType = new EmpBulletType() {
                                        {
                                            speed = 0;
                                            sprite = "circle-bullet";
                                            scaleLife = true;
                                            lightOpacity = 0.7f;
                                            unitDamageScl = 1.2f;
                                            healPercent = 0.09f;
                                            damage = 150;
                                            lifetime = 30;
                                            radius = 120f;
                                            width = height = 15;
                                            shrinkX = shrinkY = 0;
                                            collides = false;
                                            status = StatusEffects.electrified;
                                            statusDuration = 450;
                                            frontColor = Color.valueOf("ffffff");
                                            backColor = Color.valueOf("83f793");
                                            lightColor = Color.valueOf("83f793");
                                            hitColor = Color.valueOf("83f793");
                                            hitEffect = despawnEffect = new MultiEffect(
                                                    new WaveEffect() {{
                                                        sizeFrom = 90;
                                                        sizeTo = 90;
                                                        lifetime = 45;
                                                        colorFrom = Color.valueOf("a5f5af");
                                                        colorTo = Color.valueOf("ffffff");
                                                    }},
                                                    new WaveEffect() {{
                                                        sizeFrom = 0;
                                                        sizeTo = 90;
                                                        lifetime = 30;
                                                        colorFrom = Color.valueOf("a5f5af");
                                                        colorTo = Color.valueOf("ffffff");
                                                    }},
                                                    new ParticleEffect() {{
                                                        length = 0;
                                                        lifetime = 30;
                                                        particles = 1;
                                                        sizeFrom = 5;
                                                        sizeTo = 0;
                                                        colorFrom = Color.valueOf("a5f5af");
                                                        colorTo = Color.valueOf("ffffff");
                                                    }}
                                            );
                                            lightning = 1;
                                            lightningLength = 2;
                                            lightningCone = 0;
                                            lightningType = new EmpBulletType() {
                                                {
                                                    speed = 0;
                                                    sprite = "circle-bullet";
                                                    scaleLife = true;
                                                    lightOpacity = 0.7f;
                                                    unitDamageScl = 1.2f;
                                                    healPercent = 0.09f;
                                                    damage = 150;
                                                    lifetime = 30;
                                                    radius = 120f;
                                                    width = height = 15;
                                                    shrinkX = shrinkY = 0;
                                                    collides = false;
                                                    status = StatusEffects.electrified;
                                                    statusDuration = 450;
                                                    frontColor = Color.valueOf("ffffff");
                                                    backColor = Color.valueOf("83f793");
                                                    lightColor = Color.valueOf("83f793");
                                                    hitColor = Color.valueOf("83f793");
                                                    hitEffect = despawnEffect = new MultiEffect(
                                                            new WaveEffect() {{
                                                                sizeFrom = 90;
                                                                sizeTo = 90;
                                                                lifetime = 45;
                                                                colorFrom = Color.valueOf("a5f5af");
                                                                colorTo = Color.valueOf("ffffff");
                                                            }},
                                                            new WaveEffect() {{
                                                                sizeFrom = 0;
                                                                sizeTo = 90;
                                                                lifetime = 30;
                                                                colorFrom = Color.valueOf("a5f5af");
                                                                colorTo = Color.valueOf("ffffff");
                                                            }},
                                                            new ParticleEffect() {{
                                                                length = 0;
                                                                lifetime = 30;
                                                                particles = 1;
                                                                sizeFrom = 5;
                                                                sizeTo = 0;
                                                                colorFrom = Color.valueOf("a5f5af");
                                                                colorTo = Color.valueOf("ffffff");
                                                            }}
                                                    );
                                                    lightning = 1;
                                                    lightningLength = 2;
                                                    lightningCone = 0;
                                                    lightningType = new EmpBulletType() {
                                                        {
                                                            speed = 0;
                                                            sprite = "circle-bullet";
                                                            scaleLife = true;
                                                            lightOpacity = 0.7f;
                                                            unitDamageScl = 1.2f;
                                                            healPercent = 0.09f;
                                                            damage = 150;
                                                            lifetime = 30;
                                                            radius = 120f;
                                                            width = height = 15;
                                                            shrinkX = shrinkY = 0;
                                                            collides = false;
                                                            status = StatusEffects.electrified;
                                                            statusDuration = 450;
                                                            frontColor = Color.valueOf("ffffff");
                                                            backColor = Color.valueOf("83f793");
                                                            lightColor = Color.valueOf("83f793");
                                                            hitColor = Color.valueOf("83f793");
                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                    new WaveEffect() {{
                                                                        sizeFrom = 90;
                                                                        sizeTo = 90;
                                                                        lifetime = 45;
                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                        colorTo = Color.valueOf("ffffff");
                                                                    }},
                                                                    new WaveEffect() {{
                                                                        sizeFrom = 0;
                                                                        sizeTo = 90;
                                                                        lifetime = 30;
                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                        colorTo = Color.valueOf("ffffff");
                                                                    }},
                                                                    new ParticleEffect() {{
                                                                        length = 0;
                                                                        lifetime = 30;
                                                                        particles = 1;
                                                                        sizeFrom = 5;
                                                                        sizeTo = 0;
                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                        colorTo = Color.valueOf("ffffff");
                                                                    }}
                                                            );
                                                            lightning = 1;
                                                            lightningLength = 2;
                                                            lightningCone = 0;
                                                            lightningType = new EmpBulletType() {
                                                                {
                                                                    speed = 0;
                                                                    sprite = "circle-bullet";
                                                                    scaleLife = true;
                                                                    lightOpacity = 0.7f;
                                                                    unitDamageScl = 1.2f;
                                                                    healPercent = 0.09f;
                                                                    damage = 150;
                                                                    lifetime = 30;
                                                                    radius = 120f;
                                                                    width = height = 15;
                                                                    shrinkX = shrinkY = 0;
                                                                    collides = false;
                                                                    status = StatusEffects.electrified;
                                                                    statusDuration = 450;
                                                                    frontColor = Color.valueOf("ffffff");
                                                                    backColor = Color.valueOf("83f793");
                                                                    lightColor = Color.valueOf("83f793");
                                                                    hitColor = Color.valueOf("83f793");
                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                            new WaveEffect() {{
                                                                                sizeFrom = 90;
                                                                                sizeTo = 90;
                                                                                lifetime = 45;
                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                colorTo = Color.valueOf("ffffff");
                                                                            }},
                                                                            new WaveEffect() {{
                                                                                sizeFrom = 0;
                                                                                sizeTo = 90;
                                                                                lifetime = 30;
                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                colorTo = Color.valueOf("ffffff");
                                                                            }},
                                                                            new ParticleEffect() {{
                                                                                length = 0;
                                                                                lifetime = 30;
                                                                                particles = 1;
                                                                                sizeFrom = 5;
                                                                                sizeTo = 0;
                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                colorTo = Color.valueOf("ffffff");
                                                                            }}
                                                                    );
                                                                    lightning = 1;
                                                                    lightningLength = 2;
                                                                    lightningCone = 0;
                                                                    lightningType = new EmpBulletType() {
                                                                        {
                                                                            speed = 0;
                                                                            sprite = "circle-bullet";
                                                                            scaleLife = true;
                                                                            lightOpacity = 0.7f;
                                                                            unitDamageScl = 1.2f;
                                                                            healPercent = 0.09f;
                                                                            damage = 150;
                                                                            lifetime = 30;
                                                                            radius = 120f;
                                                                            width = height = 15;
                                                                            shrinkX = shrinkY = 0;
                                                                            collides = false;
                                                                            status = StatusEffects.electrified;
                                                                            statusDuration = 450;
                                                                            frontColor = Color.valueOf("ffffff");
                                                                            backColor = Color.valueOf("83f793");
                                                                            lightColor = Color.valueOf("83f793");
                                                                            hitColor = Color.valueOf("83f793");
                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                    new WaveEffect() {{
                                                                                        sizeFrom = 90;
                                                                                        sizeTo = 90;
                                                                                        lifetime = 45;
                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                    }},
                                                                                    new WaveEffect() {{
                                                                                        sizeFrom = 0;
                                                                                        sizeTo = 90;
                                                                                        lifetime = 30;
                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                    }},
                                                                                    new ParticleEffect() {{
                                                                                        length = 0;
                                                                                        lifetime = 30;
                                                                                        particles = 1;
                                                                                        sizeFrom = 5;
                                                                                        sizeTo = 0;
                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                    }}
                                                                            );
                                                                            lightning = 1;
                                                                            lightningLength = 2;
                                                                            lightningCone = 0;
                                                                            lightningType = new EmpBulletType() {
                                                                                {
                                                                                    speed = 0;
                                                                                    sprite = "circle-bullet";
                                                                                    scaleLife = true;
                                                                                    lightOpacity = 0.7f;
                                                                                    unitDamageScl = 1.2f;
                                                                                    healPercent = 0.09f;
                                                                                    damage = 150;
                                                                                    lifetime = 30;
                                                                                    radius = 120f;
                                                                                    width = height = 15;
                                                                                    shrinkX = shrinkY = 0;
                                                                                    collides = false;
                                                                                    status = StatusEffects.electrified;
                                                                                    statusDuration = 450;
                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                    backColor = Color.valueOf("83f793");
                                                                                    lightColor = Color.valueOf("83f793");
                                                                                    hitColor = Color.valueOf("83f793");
                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                            new WaveEffect() {{
                                                                                                sizeFrom = 90;
                                                                                                sizeTo = 90;
                                                                                                lifetime = 45;
                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                            }},
                                                                                            new WaveEffect() {{
                                                                                                sizeFrom = 0;
                                                                                                sizeTo = 90;
                                                                                                lifetime = 30;
                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                            }},
                                                                                            new ParticleEffect() {{
                                                                                                length = 0;
                                                                                                lifetime = 30;
                                                                                                particles = 1;
                                                                                                sizeFrom = 5;
                                                                                                sizeTo = 0;
                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                            }}
                                                                                    );
                                                                                    lightning = 1;
                                                                                    lightningLength = 2;
                                                                                    lightningCone = 0;
                                                                                    lightningType = new EmpBulletType() {
                                                                                        {
                                                                                            speed = 0;
                                                                                            sprite = "circle-bullet";
                                                                                            scaleLife = true;
                                                                                            lightOpacity = 0.7f;
                                                                                            unitDamageScl = 1.2f;
                                                                                            healPercent = 0.09f;
                                                                                            damage = 150;
                                                                                            lifetime = 30;
                                                                                            radius = 120f;
                                                                                            width = height = 15;
                                                                                            shrinkX = shrinkY = 0;
                                                                                            collides = false;
                                                                                            status = StatusEffects.electrified;
                                                                                            statusDuration = 450;
                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                            backColor = Color.valueOf("83f793");
                                                                                            lightColor = Color.valueOf("83f793");
                                                                                            hitColor = Color.valueOf("83f793");
                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                    new WaveEffect() {{
                                                                                                        sizeFrom = 90;
                                                                                                        sizeTo = 90;
                                                                                                        lifetime = 45;
                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                    }},
                                                                                                    new WaveEffect() {{
                                                                                                        sizeFrom = 0;
                                                                                                        sizeTo = 90;
                                                                                                        lifetime = 30;
                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                    }},
                                                                                                    new ParticleEffect() {{
                                                                                                        length = 0;
                                                                                                        lifetime = 30;
                                                                                                        particles = 1;
                                                                                                        sizeFrom = 5;
                                                                                                        sizeTo = 0;
                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                    }}
                                                                                            );
                                                                                            lightning = 1;
                                                                                            lightningLength = 2;
                                                                                            lightningCone = 0;
                                                                                            lightningType = new EmpBulletType() {
                                                                                                {
                                                                                                    speed = 0;
                                                                                                    sprite = "circle-bullet";
                                                                                                    scaleLife = true;
                                                                                                    lightOpacity = 0.7f;
                                                                                                    unitDamageScl = 1.2f;
                                                                                                    healPercent = 0.09f;
                                                                                                    damage = 150;
                                                                                                    lifetime = 30;
                                                                                                    radius = 120f;
                                                                                                    width = height = 15;
                                                                                                    shrinkX = shrinkY = 0;
                                                                                                    collides = false;
                                                                                                    status = StatusEffects.electrified;
                                                                                                    statusDuration = 450;
                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                            new WaveEffect() {{
                                                                                                                sizeFrom = 90;
                                                                                                                sizeTo = 90;
                                                                                                                lifetime = 45;
                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                            }},
                                                                                                            new WaveEffect() {{
                                                                                                                sizeFrom = 0;
                                                                                                                sizeTo = 90;
                                                                                                                lifetime = 30;
                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                            }},
                                                                                                            new ParticleEffect() {{
                                                                                                                length = 0;
                                                                                                                lifetime = 30;
                                                                                                                particles = 1;
                                                                                                                sizeFrom = 5;
                                                                                                                sizeTo = 0;
                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                            }},
                                                                                                            new ParticleEffect() {{
                                                                                                                startDelay(30);
                                                                                                                line = true;
                                                                                                                lenFrom = 4;
                                                                                                                lenTo = 5;
                                                                                                                strokeFrom = 0;
                                                                                                                strokeTo = 10;
                                                                                                                particles = 12;
                                                                                                                colorFrom = Color.valueOf("3bf550");
                                                                                                            }}
                                                                                                    );
                                                                                                    lightning = 10;
                                                                                                    lightningDamage = 15;
                                                                                                    lightningLength = 4;
                                                                                                    lightningLengthRand = 6;
                                                                                                    fragBullets = 4;
                                                                                                    fragBullet = new EmpBulletType() {
                                                                                                        {
                                                                                                            speed = 5;
                                                                                                            sprite = "circle-bullet";
                                                                                                            scaleLife = true;
                                                                                                            lightOpacity = 0.7f;
                                                                                                            unitDamageScl = 1.2f;
                                                                                                            healPercent = 0.09f;
                                                                                                            damage = 150;
                                                                                                            lifetime = 7.5f;
                                                                                                            radius = 120f;
                                                                                                            width = height = 15;
                                                                                                            shrinkX = shrinkY = 0;
                                                                                                            collides = false;
                                                                                                            status = StatusEffects.electrified;
                                                                                                            statusDuration = 450;
                                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                                            backColor = Color.valueOf("83f793");
                                                                                                            lightColor = Color.valueOf("83f793");
                                                                                                            hitColor = Color.valueOf("83f793");
                                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                                    new WaveEffect() {{
                                                                                                                        sizeFrom = 90;
                                                                                                                        sizeTo = 90;
                                                                                                                        lifetime = 45;
                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                    }},
                                                                                                                    new WaveEffect() {{
                                                                                                                        sizeFrom = 0;
                                                                                                                        sizeTo = 90;
                                                                                                                        lifetime = 30;
                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                    }},
                                                                                                                    new ParticleEffect() {{
                                                                                                                        length = 0;
                                                                                                                        lifetime = 30;
                                                                                                                        particles = 1;
                                                                                                                        sizeFrom = 5;
                                                                                                                        sizeTo = 0;
                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                    }}
                                                                                                            );
                                                                                                            lightning = 1;
                                                                                                            lightningLength = 2;
                                                                                                            lightningCone = 0;
                                                                                                            lightningType = new EmpBulletType() {
                                                                                                                {
                                                                                                                    speed = 0;
                                                                                                                    sprite = "circle-bullet";
                                                                                                                    scaleLife = true;
                                                                                                                    lightOpacity = 0.7f;
                                                                                                                    unitDamageScl = 1.2f;
                                                                                                                    healPercent = 0.09f;
                                                                                                                    damage = 150;
                                                                                                                    lifetime = 30;
                                                                                                                    radius = 120f;
                                                                                                                    width = height = 15;
                                                                                                                    shrinkX = shrinkY = 0;
                                                                                                                    collides = false;
                                                                                                                    status = StatusEffects.electrified;
                                                                                                                    statusDuration = 450;
                                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                                            new WaveEffect() {{
                                                                                                                                sizeFrom = 90;
                                                                                                                                sizeTo = 90;
                                                                                                                                lifetime = 45;
                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                            }},
                                                                                                                            new WaveEffect() {{
                                                                                                                                sizeFrom = 0;
                                                                                                                                sizeTo = 90;
                                                                                                                                lifetime = 30;
                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                            }},
                                                                                                                            new ParticleEffect() {{
                                                                                                                                length = 0;
                                                                                                                                lifetime = 30;
                                                                                                                                particles = 1;
                                                                                                                                sizeFrom = 5;
                                                                                                                                sizeTo = 0;
                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                            }}
                                                                                                                    );
                                                                                                                    lightning = 1;
                                                                                                                    lightningLength = 2;
                                                                                                                    lightningCone = 0;
                                                                                                                    lightningType = new EmpBulletType() {
                                                                                                                        {
                                                                                                                            speed = 0;
                                                                                                                            sprite = "circle-bullet";
                                                                                                                            scaleLife = true;
                                                                                                                            lightOpacity = 0.7f;
                                                                                                                            unitDamageScl = 1.2f;
                                                                                                                            healPercent = 0.09f;
                                                                                                                            damage = 150;
                                                                                                                            lifetime = 30;
                                                                                                                            radius = 120f;
                                                                                                                            width = height = 15;
                                                                                                                            shrinkX = shrinkY = 0;
                                                                                                                            collides = false;
                                                                                                                            status = StatusEffects.electrified;
                                                                                                                            statusDuration = 450;
                                                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                                                            backColor = Color.valueOf("83f793");
                                                                                                                            lightColor = Color.valueOf("83f793");
                                                                                                                            hitColor = Color.valueOf("83f793");
                                                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                    new WaveEffect() {{
                                                                                                                                        sizeFrom = 90;
                                                                                                                                        sizeTo = 90;
                                                                                                                                        lifetime = 45;
                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                    }},
                                                                                                                                    new WaveEffect() {{
                                                                                                                                        sizeFrom = 0;
                                                                                                                                        sizeTo = 90;
                                                                                                                                        lifetime = 30;
                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                    }},
                                                                                                                                    new ParticleEffect() {{
                                                                                                                                        length = 0;
                                                                                                                                        lifetime = 30;
                                                                                                                                        particles = 1;
                                                                                                                                        sizeFrom = 5;
                                                                                                                                        sizeTo = 0;
                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                    }}
                                                                                                                            );
                                                                                                                            lightning = 1;
                                                                                                                            lightningLength = 2;
                                                                                                                            lightningCone = 0;
                                                                                                                            lightningType = new EmpBulletType() {
                                                                                                                                {
                                                                                                                                    speed = 0;
                                                                                                                                    sprite = "circle-bullet";
                                                                                                                                    scaleLife = true;
                                                                                                                                    lightOpacity = 0.7f;
                                                                                                                                    unitDamageScl = 1.2f;
                                                                                                                                    healPercent = 0.09f;
                                                                                                                                    damage = 150;
                                                                                                                                    lifetime = 30;
                                                                                                                                    radius = 120f;
                                                                                                                                    width = height = 15;
                                                                                                                                    shrinkX = shrinkY = 0;
                                                                                                                                    collides = false;
                                                                                                                                    status = StatusEffects.electrified;
                                                                                                                                    statusDuration = 450;
                                                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                            new WaveEffect() {{
                                                                                                                                                sizeFrom = 90;
                                                                                                                                                sizeTo = 90;
                                                                                                                                                lifetime = 45;
                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                            }},
                                                                                                                                            new WaveEffect() {{
                                                                                                                                                sizeFrom = 0;
                                                                                                                                                sizeTo = 90;
                                                                                                                                                lifetime = 30;
                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                            }},
                                                                                                                                            new ParticleEffect() {{
                                                                                                                                                length = 0;
                                                                                                                                                lifetime = 30;
                                                                                                                                                particles = 1;
                                                                                                                                                sizeFrom = 5;
                                                                                                                                                sizeTo = 0;
                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                            }}
                                                                                                                                    );
                                                                                                                                    lightning = 1;
                                                                                                                                    lightningLength = 2;
                                                                                                                                    lightningCone = 0;
                                                                                                                                    lightningType = new EmpBulletType() {
                                                                                                                                        {
                                                                                                                                            speed = 0;
                                                                                                                                            sprite = "circle-bullet";
                                                                                                                                            scaleLife = true;
                                                                                                                                            lightOpacity = 0.7f;
                                                                                                                                            unitDamageScl = 1.2f;
                                                                                                                                            healPercent = 0.09f;
                                                                                                                                            damage = 150;
                                                                                                                                            lifetime = 30;
                                                                                                                                            radius = 120f;
                                                                                                                                            width = height = 15;
                                                                                                                                            shrinkX = shrinkY = 0;
                                                                                                                                            collides = false;
                                                                                                                                            status = StatusEffects.electrified;
                                                                                                                                            statusDuration = 450;
                                                                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                                                                            backColor = Color.valueOf("83f793");
                                                                                                                                            lightColor = Color.valueOf("83f793");
                                                                                                                                            hitColor = Color.valueOf("83f793");
                                                                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                        sizeFrom = 90;
                                                                                                                                                        sizeTo = 90;
                                                                                                                                                        lifetime = 45;
                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                    }},
                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                        sizeFrom = 0;
                                                                                                                                                        sizeTo = 90;
                                                                                                                                                        lifetime = 30;
                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                    }},
                                                                                                                                                    new ParticleEffect() {{
                                                                                                                                                        length = 0;
                                                                                                                                                        lifetime = 30;
                                                                                                                                                        particles = 1;
                                                                                                                                                        sizeFrom = 5;
                                                                                                                                                        sizeTo = 0;
                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                    }}
                                                                                                                                            );
                                                                                                                                            lightning = 1;
                                                                                                                                            lightningLength = 2;
                                                                                                                                            lightningCone = 0;
                                                                                                                                            lightningType = new EmpBulletType() {
                                                                                                                                                {
                                                                                                                                                    speed = 0;
                                                                                                                                                    sprite = "circle-bullet";
                                                                                                                                                    scaleLife = true;
                                                                                                                                                    lightOpacity = 0.7f;
                                                                                                                                                    unitDamageScl = 1.2f;
                                                                                                                                                    healPercent = 0.09f;
                                                                                                                                                    damage = 150;
                                                                                                                                                    lifetime = 30;
                                                                                                                                                    radius = 120f;
                                                                                                                                                    width = height = 15;
                                                                                                                                                    shrinkX = shrinkY = 0;
                                                                                                                                                    collides = false;
                                                                                                                                                    status = StatusEffects.electrified;
                                                                                                                                                    statusDuration = 450;
                                                                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                sizeFrom = 90;
                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                lifetime = 45;
                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                            }},
                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                sizeFrom = 0;
                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                lifetime = 30;
                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                            }},
                                                                                                                                                            new ParticleEffect() {{
                                                                                                                                                                length = 0;
                                                                                                                                                                lifetime = 30;
                                                                                                                                                                particles = 1;
                                                                                                                                                                sizeFrom = 5;
                                                                                                                                                                sizeTo = 0;
                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                            }}
                                                                                                                                                    );
                                                                                                                                                    lightning = 1;
                                                                                                                                                    lightningLength = 2;
                                                                                                                                                    lightningCone = 0;
                                                                                                                                                    lightningType = new EmpBulletType() {
                                                                                                                                                        {
                                                                                                                                                            speed = 0;
                                                                                                                                                            sprite = "circle-bullet";
                                                                                                                                                            scaleLife = true;
                                                                                                                                                            lightOpacity = 0.7f;
                                                                                                                                                            unitDamageScl = 1.2f;
                                                                                                                                                            healPercent = 0.09f;
                                                                                                                                                            damage = 150;
                                                                                                                                                            lifetime = 30;
                                                                                                                                                            radius = 120f;
                                                                                                                                                            width = height = 15;
                                                                                                                                                            shrinkX = shrinkY = 0;
                                                                                                                                                            collides = false;
                                                                                                                                                            status = StatusEffects.electrified;
                                                                                                                                                            statusDuration = 450;
                                                                                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                                                                                            backColor = Color.valueOf("83f793");
                                                                                                                                                            lightColor = Color.valueOf("83f793");
                                                                                                                                                            hitColor = Color.valueOf("83f793");
                                                                                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                                        sizeFrom = 90;
                                                                                                                                                                        sizeTo = 90;
                                                                                                                                                                        lifetime = 45;
                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                    }},
                                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                                        sizeFrom = 0;
                                                                                                                                                                        sizeTo = 90;
                                                                                                                                                                        lifetime = 30;
                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                    }},
                                                                                                                                                                    new ParticleEffect() {{
                                                                                                                                                                        length = 0;
                                                                                                                                                                        lifetime = 30;
                                                                                                                                                                        particles = 1;
                                                                                                                                                                        sizeFrom = 5;
                                                                                                                                                                        sizeTo = 0;
                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                    }}
                                                                                                                                                            );
                                                                                                                                                            lightning = 1;
                                                                                                                                                            lightningLength = 2;
                                                                                                                                                            lightningCone = 0;
                                                                                                                                                            lightningType = new EmpBulletType() {
                                                                                                                                                                {
                                                                                                                                                                    speed = 0;
                                                                                                                                                                    sprite = "circle-bullet";
                                                                                                                                                                    scaleLife = true;
                                                                                                                                                                    lightOpacity = 0.7f;
                                                                                                                                                                    unitDamageScl = 1.2f;
                                                                                                                                                                    healPercent = 0.09f;
                                                                                                                                                                    damage = 150;
                                                                                                                                                                    lifetime = 30;
                                                                                                                                                                    radius = 120f;
                                                                                                                                                                    width = height = 15;
                                                                                                                                                                    shrinkX = shrinkY = 0;
                                                                                                                                                                    collides = false;
                                                                                                                                                                    status = StatusEffects.electrified;
                                                                                                                                                                    statusDuration = 450;
                                                                                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                                sizeFrom = 90;
                                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                                lifetime = 45;
                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                            }},
                                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                                sizeFrom = 0;
                                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                                lifetime = 30;
                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                            }},
                                                                                                                                                                            new ParticleEffect() {{
                                                                                                                                                                                length = 0;
                                                                                                                                                                                lifetime = 30;
                                                                                                                                                                                particles = 1;
                                                                                                                                                                                sizeFrom = 5;
                                                                                                                                                                                sizeTo = 0;
                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                            }}
                                                                                                                                                                    );
                                                                                                                                                                    lightning = 1;
                                                                                                                                                                    lightningLength = 2;
                                                                                                                                                                    lightningCone = 0;
                                                                                                                                                                    lightningType = new EmpBulletType() {
                                                                                                                                                                        {
                                                                                                                                                                            speed = 0;
                                                                                                                                                                            sprite = "circle-bullet";
                                                                                                                                                                            scaleLife = true;
                                                                                                                                                                            lightOpacity = 0.7f;
                                                                                                                                                                            unitDamageScl = 1.2f;
                                                                                                                                                                            healPercent = 0.09f;
                                                                                                                                                                            damage = 150;
                                                                                                                                                                            lifetime = 30;
                                                                                                                                                                            radius = 120f;
                                                                                                                                                                            width = height = 15;
                                                                                                                                                                            shrinkX = shrinkY = 0;
                                                                                                                                                                            collides = false;
                                                                                                                                                                            status = StatusEffects.electrified;
                                                                                                                                                                            statusDuration = 450;
                                                                                                                                                                            frontColor = Color.valueOf("ffffff");
                                                                                                                                                                            backColor = Color.valueOf("83f793");
                                                                                                                                                                            lightColor = Color.valueOf("83f793");
                                                                                                                                                                            hitColor = Color.valueOf("83f793");
                                                                                                                                                                            hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                                                        sizeFrom = 90;
                                                                                                                                                                                        sizeTo = 90;
                                                                                                                                                                                        lifetime = 45;
                                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                    }},
                                                                                                                                                                                    new WaveEffect() {{
                                                                                                                                                                                        sizeFrom = 0;
                                                                                                                                                                                        sizeTo = 90;
                                                                                                                                                                                        lifetime = 30;
                                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                    }},
                                                                                                                                                                                    new ParticleEffect() {{
                                                                                                                                                                                        length = 0;
                                                                                                                                                                                        lifetime = 30;
                                                                                                                                                                                        particles = 1;
                                                                                                                                                                                        sizeFrom = 5;
                                                                                                                                                                                        sizeTo = 0;
                                                                                                                                                                                        colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                        colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                    }}
                                                                                                                                                                            );
                                                                                                                                                                            lightning = 1;
                                                                                                                                                                            lightningLength = 2;
                                                                                                                                                                            lightningCone = 0;
                                                                                                                                                                            lightningType = new EmpBulletType() {
                                                                                                                                                                                {
                                                                                                                                                                                    speed = 0;
                                                                                                                                                                                    sprite = "circle-bullet";
                                                                                                                                                                                    scaleLife = true;
                                                                                                                                                                                    lightOpacity = 0.7f;
                                                                                                                                                                                    unitDamageScl = 1.2f;
                                                                                                                                                                                    healPercent = 0.09f;
                                                                                                                                                                                    damage = 150;
                                                                                                                                                                                    lifetime = 30;
                                                                                                                                                                                    radius = 120f;
                                                                                                                                                                                    width = height = 15;
                                                                                                                                                                                    shrinkX = shrinkY = 0;
                                                                                                                                                                                    collides = false;
                                                                                                                                                                                    status = StatusEffects.electrified;
                                                                                                                                                                                    statusDuration = 450;
                                                                                                                                                                                    frontColor = Color.valueOf("ffffff");
                                                                                                                                                                                    backColor = Color.valueOf("83f793");
                                                                                                                                                                                    lightColor = Color.valueOf("83f793");
                                                                                                                                                                                    hitColor = Color.valueOf("83f793");
                                                                                                                                                                                    hitEffect = despawnEffect = new MultiEffect(
                                                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                                                sizeFrom = 90;
                                                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                                                lifetime = 45;
                                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                            }},
                                                                                                                                                                                            new WaveEffect() {{
                                                                                                                                                                                                sizeFrom = 0;
                                                                                                                                                                                                sizeTo = 90;
                                                                                                                                                                                                lifetime = 30;
                                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                            }},
                                                                                                                                                                                            new ParticleEffect() {{
                                                                                                                                                                                                length = 0;
                                                                                                                                                                                                lifetime = 30;
                                                                                                                                                                                                particles = 1;
                                                                                                                                                                                                sizeFrom = 5;
                                                                                                                                                                                                sizeTo = 0;
                                                                                                                                                                                                colorFrom = Color.valueOf("a5f5af");
                                                                                                                                                                                                colorTo = Color.valueOf("ffffff");
                                                                                                                                                                                            }},
                                                                                                                                                                                            new ParticleEffect() {{
                                                                                                                                                                                                startDelay(30);
                                                                                                                                                                                                line = true;
                                                                                                                                                                                                lenFrom = 4;
                                                                                                                                                                                                lenTo = 5;
                                                                                                                                                                                                strokeFrom = 0;
                                                                                                                                                                                                strokeTo = 10;
                                                                                                                                                                                                particles = 12;
                                                                                                                                                                                                colorFrom = Color.valueOf("3bf550");
                                                                                                                                                                                            }}
                                                                                                                                                                                    );
                                                                                                                                                                                    lightning = 10;
                                                                                                                                                                                    lightningDamage = 15;
                                                                                                                                                                                    lightningLength = 4;
                                                                                                                                                                                    lightningLengthRand = 6;
                                                                                                                                                                                }
                                                                                                                                                                            };
                                                                                                                                                                        }
                                                                                                                                                                    };
                                                                                                                                                                }
                                                                                                                                                            };
                                                                                                                                                        }
                                                                                                                                                    };
                                                                                                                                                }
                                                                                                                                            };
                                                                                                                                        }
                                                                                                                                    };
                                                                                                                                }
                                                                                                                            };
                                                                                                                        }
                                                                                                                    };
                                                                                                                }
                                                                                                            };
                                                                                                        }
                                                                                                    };
                                                                                                }
                                                                                            };
                                                                                        }
                                                                                    };
                                                                                }
                                                                            };
                                                                        }
                                                                    };
                                                                }
                                                            };
                                                        }
                                                    };
                                                }
                                            };
                                        }
                                    };
                                }
                            };
                        }
                    };
                }},
                new Weapon("ei-terrand-shotgun"){{
                    top = true;
                    mirror =  true;
                    rotate =  false;
                    alternate =  false;
                    reload =  30;
                    x =  38.75f;
                    y =  0;
                    shootX =  -6;
                    shootY =  38;
                    inaccuracy =  1;
                    shoot.shotDelay =  1;
                    shootSound = Sounds.artillery;
                    recoil = 1;
                    bullet = new ArtilleryBulletType(3, 80){{
                        lifetime = 100;
                        height = 10;
                        width = 10;
                        fragBullet = new ShrapnelBulletType(){{
                            hitSound = despawnSound = Sounds.shotgun;
                            damage = 120;
                            length = 80;
                            width = 50;
                            speed = 0;
                            hitEffect = Fx.hitLancer;
                        }};
                    }};
                }},
                new Weapon("ei-terrand-weapon"){{
                    controllable = false;
                    autoTarget = true;
                    top = true;
                    x = 15;
                    y = -5;
                    reload = 100;
                    rotate = true;
                    rotateSpeed = 1.1f;
                    continuous = true;
                    mirror = true;
                    shootSound = Sounds.beam;
                    shootY = 5;
                    shake = 4;
                    alternate = false;
                    bullet = new ContinuousLaserBulletType(){{
                        damage = 7.5f;
                        collidesTeam = true;
                        colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                        healPercent = 4.5f;
                        length = 120;
                        drawSize = 125;
                        lifetime = 360;
                        width = 8;
                    }};
                }}
            );
        }};
        breadnight = new UnitType("breadnight"){{
            constructor = MechUnit::create;

            hitSize = 8;
            canBoost = true;
            health = 210;
            armor = 1;
            speed = 0.55f;
            rotateSpeed = 1.72f;
            outlines = false;

            weapons.add(
                    new Weapon("ei-breadnight-laser"){{
                        x = 8;
                        reload = 60;
                        rotate = false;
                        mirror = true;
                        top = false;
                        alternate = true;
                        shake = 0.4f;
                        shootSound = Sounds.laser;
                        bullet = new LaserBulletType(){{
                            damage = 9;
                            lifetime = 30;
                            length = 18;
                            collidesTeam = false;
                        }};
                    }},
                    new Weapon("ei-breadnight-weapon"){{
                        controllable = false;
                        autoTarget = true;
                        x = 3.25f;
                        y = 1.5f;
                        rotate = true;
                        top = true;
                        mirror = true;
                        alternate = true;
                        reload = 15;
                        bullet = new BasicBulletType(4, 4){{
                            lifetime = 25;
                        }};
                    }}
            );
        }};
        toastnight = new UnitType("toastnight"){{
            constructor = MechUnit::create;

            hitSize = 12;
            health = 620;
            armor = 5;
            speed = 0.6f;
            rotateSpeed = 1.72f;
            outlines = false;

            weapons.add(
                    new Weapon("ei-toastnight-weapon"){{
                            x = 8;
                            reload = 45;
                            rotate = false;
                            mirror = true;
                            top = false;
                            alternate = true;
                            shake = 0.4f;
                            shoot.shotDelay = 3.5f;
                            shoot.shots = 3;
                            shootSound = Sounds.shootBig;
                            bullet = new BasicBulletType(4, 16) {{
                                lifetime = 50;
                                width = 7;
                                height = 10;
                            }};
                    }}
            );
        }};
        centurion = new UnitType("centurion") {{
            constructor = UnitEntity::create;
            controller = u -> new MinerAI();

            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.7f;
            hitSize = 12;
            rotateSpeed = 3.5f;
            buildSpeed = 0.5f;
            health = 100;
            armor = 2.5f;
            engineSize = 2.5f;
            engineOffset = 8f;
            itemCapacity = 40;
            range = 50f;
            isEnemy = false;
            outlines = false;

            ammoType = new PowerAmmoType(750);

            mineTier = 3;
            mineSpeed = 5.5f;
        }};
        alturion = new UnitType("alturion") {{
            constructor = UnitEntity::create;
            controller = u -> new MinerAI();

            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;
            hitSize = 16;
            rotateSpeed = 3.8f;
            buildSpeed = 1f;
            health = 650;
            armor = 2.5f;
            engineSize = 2.5f;
            engineOffset = 12f;
            range = 50f;
            itemCapacity = 60;
            isEnemy = false;
            outlines = false;

            ammoType = new PowerAmmoType(1000);

            mineTier = 4;
            mineSpeed = 12.75f;
        }};
        pygmy = new UnitType("pygmy") {{
            constructor = UnitEntity::create;

            flying = true;
            speed = 2.25f;
            drag = 0.01f;
            accel = 0.08f;
            engineOffset = 6.5f;
            circleTarget = true;
            hitSize = 7f;
            itemCapacity = 5;
            outlines = false;

            weapons.add(new Weapon("ei-pygmy-full") {{
                top = false;
                x = y = recoil = 0;
                reload = 45f;
                mirror = false;
                bullet = new LifestealBulletType(3, 5, 1.5f) {{
                    shrinkY = 0;
                    homingPower = 0.24f;
                    homingDelay = 3f;
                    width = 3.5f;
                    height = 5.5f;
                    lifetime = 45f;
                    frontColor = Color.valueOf("bf92f9");
                    backColor = Color.valueOf("6d56bf");
                    trailLength = 6;
                    trailWidth = 1.75f;
                    trailColor = Color.valueOf("6d56bf");
                    fragRandomSpread = 40;
                    fragSpread = 5;
                    fragBullets = 2;
                    fragBullet = new LifestealBulletType(3, 5, 0.5f) {{
                        shrinkY = 0;
                        homingPower = 0.19f;
                        homingDelay = 4f;
                        width = height = 4;
                        lifetime = 30f;
                        frontColor = Color.valueOf("bf92f9");
                        backColor = Color.valueOf("6d56bf");
                        trailLength = 6;
                        trailWidth = 1.75f;
                        trailColor = Color.valueOf("6d56bf");
                    }};
                }};
            }});
        }};
        schaus = new UnitType("schaus"){{
            constructor = UnitEntity::create;

            flying = true;
            speed = 1.75f;
            drag = 0.016f;
            accel = 0.08f;
            engineOffset = 7.75f;
            circleTarget = true;
            faceTarget = false;
            hitSize = 11.5f;
            itemCapacity = 40;
            outlines = false;

            weapons.add(new Weapon("ei-schaus-full"){{
                reload = 10;
                inaccuracy = 360;
                shootCone = 360;
                mirror = false;
                recoil = 0;
                top = false;
                x = y = 0;
                bullet = new LifestealBulletType(4, 55, 5.5f) {{
                    splashDamage = 15;
                    splashDamageRadius = 14;
                    shrinkY = 0;
                    homingPower = 0.12f;
                    homingDelay = 3f;
                    height = width = 7;
                    sprite = "circle-bullet";
                    lifetime = 20f;
                    frontColor = Color.valueOf("bf92f9");
                    backColor = trailColor = Color.valueOf("6d56bf");
                    trailLength = 7;
                    trailWidth = 3.5f;
                    hitEffect = despawnEffect = new MultiEffect(
                            new WaveEffect(){{
                                sizeFrom = 14;
                                sizeTo = 14;
                                lifetime = 20;
                                colorTo = Color.valueOf("6d56bf");
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 4;
                                sizeTo = 2;
                                lifetime = 15;
                                colorTo = Color.valueOf("6d56bf");
                            }}
                    );
                    lightning = 2;
                    lightningLength = 3;
                    lightningLengthRand = 4;
                    lightningDamage = 5;
                    lightningCone = 360;
                    lightningColor = Color.valueOf("bf92f9");
                }};
            }});
        }};
        SmolBoi = new UnitType("small-boi"){{
            constructor = UnitEntity::create;

            flying = true;
            health = 210;
            speed = 1.9f;
            rotateSpeed = 3;
            lightRadius = 50;
            faceTarget = true;
            itemCapacity =  50;
            outlines = false;
            hitSize = 6f;

            weapons.add(new Weapon("ei-small-boi") {
                {
                    reload = 24;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    recoil = 0;
                    top = false;
                    bullet = new BombBulletType() {
                        {
                            shootEffect = despawnEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        sizeFrom = 120;
                                        sizeTo = 0;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }},
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                    }}
                            );
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                        particles = 6;
                                    }},
                                    new WaveEffect() {{
                                        sizeFrom = 0;
                                        sizeTo = 2;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }}
                            );
                            splashDamageRadius = 120;
                            instantDisappear = true;
                            killShooter = true;
                            hittable = false;
                            splashDamage = 75;
                            damage = 30;
                            collidesAir = true;
                            lifetime = 10;
                            speed = 1;
                        }
                    };
                }
            });
        }};
        MediumBoi = new UnitType("medium-boi"){{
            constructor = UnitEntity::create;

            flying = true;
            health = 210;
            speed = 1.8f;
            engineOffset = 6.25f;
            rotateSpeed = 3;
            lightRadius = 50;
            faceTarget = true;
            itemCapacity =  50;
            outlines = false;
            hitSize = 12f;

            weapons.add(new Weapon("ei-medium-boi") {
                {
                    reload = 24;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    recoil = 0;
                    top = false;
                    bullet = new BombBulletType() {
                        {
                            shootEffect = despawnEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        sizeFrom = 120;
                                        sizeTo = 0;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }},
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                    }}
                            );
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                        particles = 6;
                                    }},
                                    new WaveEffect() {{
                                        sizeFrom = 0;
                                        sizeTo = 2;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }}
                            );
                            splashDamageRadius = 120;
                            instantDisappear = true;
                            killShooter = true;
                            hittable = false;
                            splashDamage = 75;
                            damage = 30;
                            collidesAir = true;
                            lifetime = 10;
                            speed = 1;
                        }
                    };
                }
            });
        }};
        LargeBoi = new UnitType("large-boi"){{
            constructor = UnitEntity::create;

            flying = true;
            health = 210;
            speed = 1.8f;
            engineOffset = 11.25f;
            engineSize = 2.75f;
            rotateSpeed = 3;
            lightRadius = 50;
            faceTarget = true;
            itemCapacity =  50;
            outlines = false;
            hitSize = 20;

            abilities.add(new UnitSpawnAbility(SmolBoi, 1050, 0, 2));
            weapons.add(new Weapon("ei-large-boi") {
                {
                    reload = 24;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    recoil = 0;
                    top = false;
                    bullet = new BombBulletType() {
                        {
                            shootEffect = despawnEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        sizeFrom = 120;
                                        sizeTo = 0;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }},
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                    }}
                            );
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        region = "circle";
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                        particles = 6;
                                    }},
                                    new WaveEffect() {{
                                        sizeFrom = 0;
                                        sizeTo = 2;
                                        colorFrom = Color.valueOf("ffe266");
                                        colorTo = Color.valueOf("eec44f");
                                    }}
                            );
                            splashDamageRadius = 120;
                            instantDisappear = true;
                            killShooter = true;
                            hittable = false;
                            splashDamage = 75;
                            damage = 30;
                            collidesAir = true;
                            lifetime = 10;
                            speed = 1;
                        }
                    };
                }
            });
        }};
        PayloadBoi = new UnitType("payload-boi"){{
            constructor = PayloadUnit::create;

            health = 5300;
            speed = 3.35f;
            flying = true;
            rotateSpeed = 3.3f;
            drag = 0.04f;
            accel = 0.07f;
            engineSize = 4.5f;
            engineOffset = 16;
            itemCapacity = 340;
            payloadCapacity = 800;
            outlines = false;
            hitSize = 40f;
        }};
        piece = new UnitType("piece"){{
            aiController = BuilderAI::new;
            constructor = UnitEntity::create;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 2.25f;
            mineTier = 1;
            buildSpeed = 0.25f;
            drag = 0.05f;
            speed = 2.75f;
            rotateSpeed = 22.5f;
            accel = 0.2f;
            itemCapacity = 10;
            health = 60f;
            engineOffset = 4.75f;
            hitSize = 6f;
            alwaysUnlocked = true;
            outlineRadius = 2;

            weapons.add(new Weapon("ei-piece"){{
                reload = 12f;
                x = 0;
                y = 0.5f;
                top = false;
                mirror = false;
                recoil = 0;
                ejectEffect = Fx.casing1;

                bullet = new BasicBulletType(2.5f, 7){{
                    width = 5f;
                    height = 7f;
                    lifetime = 45f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    buildingDamageMultiplier = 0.01f;}};
            }});
        }};
    }}