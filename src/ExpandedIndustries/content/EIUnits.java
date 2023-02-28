package ExpandedIndustries.content;

import ExpandedIndustries.entities.bullet.LifestealBulletType;
import arc.graphics.*;
import mindustry.ai.UnitCommand;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.EnergyFieldAbility;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.abilities.StatusFieldAbility;
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
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.power.PowerGenerator;

import static mindustry.Vars.tilePayload;
import static mindustry.Vars.tilesize;
import static mindustry.gen.Sounds.laserblast;
import static ExpandedIndustries.content.EIBullets.*;

public class EIUnits {
    public static UnitType
    stormer, rusher, escapade, natorin, terrand,

    breadnight, toastnight,

    centurion, alturion,

    pygmy, schaus, ageronia,

    SmolBoi, MediumBoi, LargeBoi, PayloadBoi,

    piece, guardian,
    //Overkill Content;
    starnight;
    public static void load() {

        stormer = new UnitType("stormer") {{
            constructor = LegsUnit::create;
            health = 150f;
            buildSpeed = 1.75f;
            armor = 1f;
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
                        reload = 60f;
                        recoil = 1.2f;
                        shootSound = Sounds.laser;
                        bullet = new LaserBulletType() {{
                            damage = 35;
                            healPercent = 20f;
                            length = 100f;
                            pierce = true;
                            pierceCap = 7;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }}
            );
        }};
        rusher = new UnitType("rusher") {{
            constructor = LegsUnit::create;
            allowLegStep = true;
            health = 410f;
            buildSpeed = 3f;
            armor = 4f;
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
                            pierceCap = 9;
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
            health = 820;
            armor = 9;
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
                            pierceCap = 12;
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
            health = 6920;
            armor = 16;
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
                           damage = 48;
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

            health = 18100;
            hitSize = 88;
            armor = 24;
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
                    new Weapon(){{
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
                            length = 18 * tilesize;
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
                        bullet = new LifestealBulletType(4, 4, 1.25f){{
                            lifetime = 30;
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
                            x = 9;
                            reload = 45;
                            rotate = false;
                            mirror = true;
                            top = false;
                            alternate = true;
                            shake = 0.4f;
                            shoot.shotDelay = 3.5f;
                            shoot.shots = 3;
                            shootSound = Sounds.shootBig;
                            bullet = new LifestealBulletType(4, 16, 0.5f) {{
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
            accel = 0.18f;
            speed = 1.9f;
            hitSize = 12;
            rotateSpeed = 6.6f;
            buildSpeed = 0.5f;
            health = 225;
            armor = 2.5f;
            engineSize = 2.5f;
            engineOffset = 8f;
            itemCapacity = 50;
            range = 50f;
            isEnemy = false;
            outlines = false;

            ammoType = new PowerAmmoType(750);

            mineTier = 3;
            mineSpeed = 6.75f;
        }};
        alturion = new UnitType("alturion") {{
            constructor = UnitEntity::create;
            controller = u -> new MinerAI();

            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            drag = 0.06f;
            accel = 0.18f;
            speed = 1.7f;
            hitSize = 16;
            rotateSpeed = 5.7f;
            buildSpeed = 1f;
            health = 650;
            armor = 2.5f;
            engineSize = 2.5f;
            engineOffset = 12f;
            range = 50f;
            itemCapacity = 85;
            isEnemy = false;
            outlines = false;

            ammoType = new PowerAmmoType(1000);

            mineTier = 4;
            mineSpeed = 14f;
        }};
        pygmy = new UnitType("pygmy") {{
            constructor = UnitEntity::create;

            flying = true;
            lowAltitude = true;
            speed = 2.6f;
            drag = 0.01f;
            accel = 0.08f;
            engineOffset = 6.5f;
            circleTarget = true;
            hitSize = 7f;
            itemCapacity = 5;
            health = 230;
            armor = 2;
            range = 135;

            weapons.add(new Weapon() {{
                top = false;
                x = y = recoil = 0;
                reload = 45f;
                mirror = false;
                bullet = new LifestealBulletType(3, 9, 1.5f) {{
                    smokeEffect = shootEffect = Fx.none;
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
                    fragBullet = new LifestealBulletType(3, 7, 0.5f) {{
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
            lowAltitude = true;
            speed = 2.1f;
            drag = 0.016f;
            accel = 0.08f;
            engineOffset = 7.75f;
            circleTarget = true;
            faceTarget = false;
            hitSize = 11.5f;
            itemCapacity = 40;
            health = 670;
            armor = 7;
            range = 80f;

            weapons.add(new Weapon(){{
                reload = 10;
                inaccuracy = 360;
                shootCone = 360;
                mirror = false;
                recoil = 0;
                top = false;
                x = y = 0;
                bullet = new LifestealBulletType(4, 65, 5.5f) {{
                    smokeEffect = shootEffect = Fx.none;
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
        ageronia = new UnitType("ageronia"){{
            constructor = UnitEntity::create;

            health = 720;
            armor = 6;
            speed = 2.2f;
            drag = 0.019f;
            accel = 0.075f;
            flying = true;
            lowAltitude = true;
            forceMultiTarget = true;
            hitSize = 11.5f;
            itemCapacity = 40;
            range = 220;
            weapons.add(new Weapon(){{
                top = false;
                mirror = false;
                rotate = false;
                x = y = 0;
                shoot.shots = 2;
                shootY = 0;
                reload = 50;
                shootCone = 200;
                bullet = new LifestealBulletType(5f, 45, 2.75f, "circle-bullet"){{
                    smokeEffect = shootEffect = Fx.none;
                    height = width = 4;
                    lifetime = 44;
                    pierce = true;
                    pierceCap = 5;
                    pierceBuilding = true;
                    homingPower = 0.1f;
                    homingRange = 50;
                    keepVelocity = false;
                    frontColor = hitColor = Color.valueOf("bf92f9");
                    backColor = trailColor = Color.valueOf("6d56bf");
                    trailLength = 7;
                    trailWidth = 3.5f;
                    hitEffect = new MultiEffect(
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
                    bulletInterval = 11.25f;
                    intervalRandomSpread = 20f;
                    intervalBullets = 2;
                    intervalAngle = 180f;
                    intervalSpread = 300f;
                    intervalBullet = new LifestealBulletType(3.2f, 25f, 1.25f, "circle-bullet"){{
                        lifetime = 22.5f;
                        shrinkY = shrinkX = 0;
                        weaveScale = 1;
                        weaveMag = 10;
                        frontColor = hitColor = Color.valueOf("bf92f9");
                        backColor = trailColor = Color.valueOf("6d56bf");
                        trailLength = 7;
                        trailWidth = 3.5f;
                        homingPower = 0.7f;
                        homingRange = 72;
                        hitEffect = new MultiEffect(
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
                    }};
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

            weapons.add(new Weapon() {
                {
                    reload = 24;
                    mirror = false;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    top = false;
                    bullet = SuicideBulletType;
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

            weapons.add(new Weapon() {
                {
                    reload = 24;
                    mirror = false;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    top = false;
                    bullet = SuicideBulletType;
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
            weapons.add(new Weapon() {
                {
                    reload = 24;
                    mirror = false;
                    shootCone = 180;
                    x = 0;
                    y = 0;
                    shootSound = Sounds.explosion;
                    recoil = 0;
                    top = false;
                    bullet = SuicideBulletType;
                }
            });
        }};
        PayloadBoi = new UnitType("payload-boi"){{
            constructor = PayloadUnit::create;

            health = 5300;
            speed = 3.35f;
            flying = true;
            rotateSpeed = 3.3f;
            drag = 0.1f;
            accel = 0.2f;
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

            weapons.add(new Weapon(){{
                reload = 12f;
                x = 0;
                y = 0.5f;
                top = false;
                mirror = false;
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
        guardian = new UnitType("guardian"){{
            hidden = true;
            aiController = BuilderAI::new;
            constructor = UnitEntity::create;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 8.85f;
            mineTier = 4;
            buildBeamOffset = 4.75f;
            buildSpeed = 1.75f;
            drag = 0.05f;
            speed = 2.35f;
            rotateSpeed = 22.5f;
            accel = 0.2f;
            itemCapacity = 110;
            health = 490f;
            engineOffset = 8f;
            hitSize = 12f;
            alwaysUnlocked = true;
            outlineRadius = 3;

            weapons.add(new Weapon("small-mount-weapon"){{
                reload = 30f;
                x = 5.35f;
                y = -4f;
                top = true;
                mirror = true;
                ejectEffect = Fx.casing1;

                bullet = new BasicBulletType(3f, 21){{
                    shoot.shots = 2;
                    homingPower = 0.7f;
                    homingRange = 3 * tilesize;
                    width = 5f;
                    height = 7f;
                    lifetime = 45f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    buildingDamageMultiplier = 0.01f;}};
            }});
        }};
        starnight = new UnitType("starnight"){{
            constructor = UnitEntity::create;
            hidden = true;

            health = 42900;
            armor = 37;
            speed = 0.6f;
            accel = 0.04f;
            drag = 0.018f;
            flying = true;
            engineOffset = 46f;
            engineSize = 7.8f;
            faceTarget = false;
            hitSize = 66f;
            payloadCapacity = (5.5f * 5.5f) * tilePayload;
            buildSpeed = 6.5f;
            drawShields = false;
            lowAltitude = true;
            buildBeamOffset = 43;
            ammoCapacity = 3;

            abilities.add(
                    new ForceFieldAbility(320f, 12f, 10950f, 120f),
                    new UnitSpawnAbility(UnitTypes.poly, 1200f, 0, -27.125f),
                    new EnergyFieldAbility(5f, 6f, 180f){{
                        maxTargets = 65;
                        healPercent = 0.25f;
                        statusDuration = 20f;
                        status = StatusEffects.disarmed;
                        color = Color.valueOf("6cf5d7");
                    }},
                    new StatusFieldAbility(StatusEffects.overdrive, 0f, 60f * 120f, 220f)
            );
        }};
    }}