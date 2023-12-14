package ExpandedIndustries.content;

import ExpandedIndustries.ai.EICommands;
import ExpandedIndustries.ai.types.CircleTargetFlyingAI;
import ExpandedIndustries.entities.bullet.LifestealBulletType;
import ExpandedIndustries.entities.bullet.abilities.OverloadAbility;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.FlyingFollowAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Effect;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;

import static ExpandedIndustries.content.EIStatusEffects.frozen;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.tilePayload;
import static mindustry.Vars.tilesize;
import static mindustry.content.Fx.none;
import static mindustry.gen.Sounds.laserblast;

public class EIUnits {
    public static UnitType
    agrid, xerad, escapade, natorin, terrand,
    requer, convoy,
    centurion, alturion,
    pygmy, schaus, ageronia,
    creo,
    piece, guardian,
    //Overkill Content;
    starnight;
    public static void load() {

        agrid = new UnitType("agrid") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = true;
            outlines = false;

            health = 160f;
            armor = 0f;
            hitSize = 12f;
            speed = 0.7f;
            rotateSpeed = 2.5f;
            buildSpeed = 1.15f;

            legCount = 6;
            legLength = 15f;
            legBaseOffset = 2.5f;
            shadowElevation = 0.2f;

            ammoType = new PowerAmmoType(750);

            weapons.add(
                    new Weapon("ei-agrid-weapon") {{
                        top = false;

                        shake = 2f;
                        shootY = 4f;
                        x = 6.5f;
                        reload = 60f;
                        recoil = 1.2f;

                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType() {{
                            pierce = true;

                            damage = 35;
                            pierceCap = 3;
                            healPercent = 20f;
                            length = 100f;

                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }}
            );
        }};
        xerad = new UnitType("xerad") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            legRegion = UnitTypes.atrax.legRegion;
            legBaseRegion = UnitTypes.atrax.legBaseRegion;
            jointRegion = UnitTypes.atrax.jointRegion;
            footRegion = UnitTypes.atrax.footRegion;

            allowLegStep = hovering = true;
            outlines = false;

            health = 370f;
            armor = 2f;
            hitSize = 20f;
            speed = 0.6f;
            rotateSpeed = 2.5f;
            buildSpeed = 1.6f;

            legCount = 8;
            legLength = 25f;
            legBaseOffset = 2.5f;
            legExtension = 1;
            shadowElevation = 0.3f;

            ammoType = new PowerAmmoType(1000);

            weapons.add(
                    new Weapon("ei-xerad-weapon") {{
                        top = rotate = false;

                        reload = 75f;
                        recoil = 1.2f;
                        shake = 2f;
                        x = 11;
                        shootY = 4f;

                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType() {{
                            pierce = true;

                            damage = 40;
                            pierceCap = 4;
                            length = 160f;
                            healPercent = 7.5f;
                            buildingDamageMultiplier = 0.75f;

                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }},
                    new Weapon("ei-xerad-artillery") {{
                        top = rotate = true;
                        mirror = false;

                        x = 0;
                        y = -5;
                        reload = 150;
                        shake = 2.7f;

                        shootSound = Sounds.artillery;

                        bullet = new ArtilleryBulletType(4, 15) {{
                            collidesTeam = false;

                            splashDamage = 45;
                            splashDamageRadius = 12;
                            height = width = 8;
                            lifetime = 52;
                        }};
                    }});
        }};
        escapade = new UnitType("escapade") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = allowLegStep = true;
            outlines = false;

            health = 770;
            armor = 5;
            hitSize = 32;
            speed = 0.5f;
            rotateSpeed = 2.2f;
            buildSpeed = 2f;
            legSplashRange = legSplashDamage = 40;

            shadowElevation = 0.45f;
            legCount = 8;
            legLength = 60;
            legBaseOffset = legExtension = 0;
            lightRadius = 50;

            ammoType = new PowerAmmoType(1500);

            weapons.add(
                    new Weapon("ei-escapade-weapon") {{
                        mirror = true;
                        rotate = top = false;

                        x = 16;
                        y = 2;
                        reload = 40;

                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType() {{
                            pierce = true;

                            damage = 130;
                            pierceCap = 7;
                            buildingDamageMultiplier = 0.5f;
                            healPercent = 3f;
                            length = 160f;

                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }},
                    new Weapon("ei-escapade-artillery") {{
                        top = mirror = rotate = true;

                        x = 6;
                        y = -7;
                        reload = 180;
                        rotateSpeed = 1.6f;
                        shake = 4;

                        shootSound = Sounds.artillery;

                        bullet = new ArtilleryBulletType(4.5f, 60) {{
                            collidesTeam = collidesAir = targetAir = false;

                            lifetime = 60;
                            splashDamage = 120;
                            splashDamageRadius = 16;
                            height = width = 15;
                        }};
                    }}
            );
        }};
        natorin = new UnitType("natorin") {{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = allowLegStep = true;
            outlines = false;

            health = 6600;
            armor = 8;
            hitSize = 68;
            speed = 0.45f;
            rotateSpeed = 2.2f;
            buildSpeed = 2.35f;
            legSplashDamage = 70;
            legSplashRange = 60;

            shadowElevation = 0.7f;
            legCount = 8;
            legLength = 80;

            ammoType = new PowerAmmoType(3500);

            weapons.add(new Weapon("ei-natorin-weapon") {{
                rotate = continuous = mirror = top = true;
                alternate = false;

                reload = 240;
                rotateSpeed = 1.7f;
                shake = 4;
                x = 18;
                y = 4;
                shootY = 18;

                shootSound = Sounds.beam;

                    bullet = new ContinuousLaserBulletType() {{
                        pierceArmor = collidesTeam = true;

                        damage = 48;
                        healPercent = 4.5f;
                        lifetime = 180;
                        length = 240;
                        width = 18;
                        shake = 1.7f;

                        colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                        drawSize = 240;
                    }};
                }}
            );
        }};
        terrand = new UnitType("terrand"){{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = allowLegStep = true;

            health = 17200;
            armor = 13;
            hitSize = 88;
            itemCapacity = 180;
            speed = 0.4f;
            rotateSpeed = 1.45f;
            legSplashDamage = 130;
            legSplashRange = 85;

            legCount = 10;
            legLength = 80;
            legBaseOffset = 32;
            legExtension = 2;
            legSpeed = 0.02f;
            outlineRadius = 4;

            ammoType = new PowerAmmoType(7500);

            weapons.add(
                new Weapon(){{
                    mirror = top = false;

                    shoot.firstShotDelay = 85;
                    reload = 360;
                    x = 0;
                    y = 2.5f;
                    recoil = 0;

                    chargeSound = Sounds.lasercharge;
                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = shoot.firstShotDelay;
                    shootSound = laserblast;

                    bullet = new ArtilleryBulletType(5, 45, "circle-bullet"){{
                        collides = absorbable = hittable = false;
                        scaleLife = true;

                        lifetime = 60;
                        buildingDamageMultiplier = 0.4444444444444444f;
                        width = height = 25;
                        shrinkX = shrinkY = 0;

                        chargeEffect = new MultiEffect(
                            new Effect(80f,55f,e -> {
                                color(Pal.heal);
                                stroke(e.fin() * 2f);
                                Lines.circle(e.x, e.y, 2f + e.fout() * 100f);

                                Fill.circle(e.x, e.y, e.fin() * 20);

                                randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
                                    Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
                                    Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Pal.heal, 0.7f);
                                });

                                color();

                                Fill.circle(e.x, e.y, e.fin() * 10);
                                Drawf.light(e.x, e.y, e.fin() * 20f, Pal.heal, 0.7f);
                            }).followParent(true).rotWithParent(true),
                            new ParticleEffect(){{
                                randLength = true;

                                sizeFrom = 10;
                                sizeTo = 2;
                                baseLength = 2;
                                length = 5;
                                lifetime = 80;
                                color(Pal.heal);
                            }},
                            new ParticleEffect(){{
                                randLength = true;

                                startDelay = 30;
                                sizeFrom = 10;
                                sizeTo = 2;
                                baseLength = 2;
                                length = 5;
                                lifetime = 50;
                                color(Pal.heal);
                            }}
                        );
                        trailLength = 25;
                        trailWidth = 13;
                        frontColor = Color.valueOf("ffffff");
                        backColor = trailColor = Color.valueOf("83f793");
                        lightColor = Color.valueOf("83f793");

                        lightning = 1;
                        lightningLength = 2;
                        lightningCone = 0;
                        lightningType = new BasicBulletType(0, 45, "circle-bullet"){{
                            collides = hittable = false;
                            despawnHit = true;

                            buildingDamageMultiplier = 0.444f;
                            lifetime = 195;
                            width = height = 30;
                            shrinkX = shrinkY = 0;

                            frontColor = Color.valueOf("ffffff");
                            backColor = Color.valueOf("83f793");
                            lightColor = Color.valueOf("83f793");
                            despawnEffect = new MultiEffect(
                                    new WaveEffect(){{
                                        sizeFrom = 28;
                                        sizeTo = 28;
                                        lifetime = 20;
                                        colorTo = Color.valueOf("83f793");
                                    }},
                                    new ParticleEffect(){{
                                        sizeFrom = 8;
                                        sizeTo = 4;
                                        lifetime = 15;
                                        colorTo = Color.valueOf("83f793");
                                    }}
                            );
                        }};

                        fragBullets = 60;
                        fragLifeMin = 0;
                        fragBullet = new BulletType(0, 45){{
                            collides = absorbable = hittable = false;
                            despawnHit = true;

                            buildingDamageMultiplier = 0.444f;
                            lifetime = 180;
                            width = height = 0;

                            despawnEffect = hitEffect = none;
                            despawnSound = Sounds.shockBlast;

                            lightning = 1;
                            lightningLength = 2;
                            lightningCone = 0;
                            lightningType = new BasicBulletType(4, 45, "circle-bullet"){{
                                pierceArmor = pierce = true;
                                collidesTeam = true;
                                despawnHit = true;
                                status = StatusEffects.electrified;

                                healPercent = 0.05f;
                                pierceCap = 2;
                                lifetime = 20;
                                homingPower = 0.4f;
                                homingRange = 60;
                                statusDuration = 240;
                                buildingDamageMultiplier = 0.444f;
                                width = height = 14;

                                frontColor = Color.valueOf("ffffff");
                                backColor = trailColor = Color.valueOf("83f793");
                                lightColor = Color.valueOf("83f793");
                                trailLength = 14;
                                trailWidth = 10;
                                hitEffect = despawnEffect = new MultiEffect(
                                    new WaveEffect(){{
                                        sizeFrom = 14;
                                        sizeTo = 14;
                                        lifetime = 20;
                                        colorTo = Color.valueOf("83f793");
                                    }},
                                    new ParticleEffect(){{
                                        sizeFrom = 4;
                                        sizeTo = 2;
                                        lifetime = 15;
                                        colorTo = Color.valueOf("83f793");
                                    }}
                                );
                            }};
                        }};
                    }};
                }},
                new Weapon("ei-terrand-shotgun"){{
                    top = mirror = true;
                    rotate = alternate = false;

                    reload =  60;
                    x =  38.75f;
                    y =  0;
                    shootX =  -6;
                    shootY =  38;
                    inaccuracy =  1;
                    shoot.shotDelay =  1;
                    recoil = 1;

                    shootSound = Sounds.artillery;

                    bullet = new ArtilleryBulletType(3, 80){{
                        lifetime = 100;
                        height = 10;
                        width = 10;
                        fragBullet = new ShrapnelBulletType(){{
                            damage = 120;
                            speed = 0;
                            length = 80;
                            width = 50;

                            hitSound = despawnSound = Sounds.shotgun;
                            hitEffect = Fx.hitLancer;
                        }};
                    }};
                }},
                new Weapon("ei-terrand-weapon"){{
                    autoTarget = top = rotate = continuous = mirror = true;
                    alternate = controllable = false;

                    x = 15;
                    y = -5;
                    reload = 180;
                    rotateSpeed = 1.1f;
                    shootY = 5;
                    shake = 4;

                    shootSound = Sounds.beam;

                    bullet = new ContinuousLaserBulletType(){{
                        collidesTeam = true;

                        damage = 7.5f;
                        buildingDamageMultiplier = 0.6f;
                        lifetime = 360;
                        healPercent = 4.5f;
                        length = 120;
                        width = 8;

                        colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                        drawSize = 125;
                    }};
                }}
            );
        }};
        requer = new UnitType("requer"){{
            constructor = MechUnit::create;

            outlines = false;

            health = 210;
            armor = 1;
            hitSize = 8;
            speed = 0.55f;
            rotateSpeed = 1.72f;

            abilities.add(
                    new OverloadAbility(600, 7.5f*tilesize)
            );

            weapons.add(
                    new Weapon("ei-requer-laser"){{
                        mirror = alternate = true;
                        rotate = top = false;

                        x = 8;
                        reload = 60;
                        shake = 0.4f;

                        shootSound = Sounds.laser;

                        bullet = new LaserBulletType(){{
                            collidesTeam = false;

                            damage = 9;
                            lifetime = 30;
                            length = 18 * tilesize;
                        }};
                    }},
                    new Weapon("ei-requer-weapon"){{
                        autoTarget = rotate = top = mirror = alternate = true;
                        controllable = false;

                        x = 3.25f;
                        y = 1.5f;
                        reload = 15;

                        bullet = new LifestealBulletType(4, 4, 0.75f){{
                            lifetime = 30;
                        }};
                    }}
            );
        }};
        convoy = new UnitType("convoy"){{
            constructor = MechUnit::create;

            outlines = false;

            health = 620;
            armor = 5;
            hitSize = 12;
            speed = 0.6f;
            rotateSpeed = 1.72f;

            abilities.add(
                    new OverloadAbility(600, 7.5f*tilesize)
            );

            weapons.add(
                    new Weapon("ei-convoy-weapon"){{
                        mirror = alternate = true;
                        rotate = top = false;

                        x = 9;
                        reload = 45;
                        shake = 0.4f;
                        shoot.shotDelay = 3.5f;
                        shoot.shots = 3;

                        shootSound = Sounds.shootBig;

                        bullet = new LifestealBulletType(4, 24, 0.5f) {{
                            lifetime = 50;
                            width = 7;
                            height = 10;
                        }};
                    }}
            );
        }};
        centurion = new UnitType("centurion") {{
            constructor = UnitEntity::create;
            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            isEnemy = outlines = false;

            health = 225;
            armor = 2.5f;
            hitSize = 12;
            speed = 1.9f;
            rotateSpeed = 6.6f;
            buildSpeed = 0.5f;
            mineSpeed = 6.75f;
            mineTier = 3;
            drag = 0.06f;
            accel = 0.18f;
            itemCapacity = 50;
            range = 50f;

            engineSize = 2.5f;
            engineOffset = 8f;

            abilities.add(new RepairFieldAbility(12f, 60f * 15, 75f));
        }};
        alturion = new UnitType("alturion") {{
            constructor = UnitEntity::create;
            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            isEnemy = outlines = false;

            health = 650;
            armor = 2.5f;
            hitSize = 16;
            speed = 1.7f;
            rotateSpeed = 5.7f;
            buildSpeed = 1f;
            mineSpeed = 14f;
            mineTier = 4;
            drag = 0.06f;
            accel = 0.18f;
            itemCapacity = 85;
            range = 50f;

            engineSize = 2.5f;
            engineOffset = 12f;

            abilities.add(new RepairFieldAbility(18f, 60f * 10, 75f));
        }};
        pygmy = new UnitType("pygmy") {{
            constructor = UnitEntity::create;
            aiController = CircleTargetFlyingAI::new;

            flying = lowAltitude = circleTarget = true;

            health = 170;
            armor = 1;
            hitSize = 7f;
            speed = 2.6f;
            drag = 0.01f;
            accel = 0.08f;
            itemCapacity = 5;

            engineOffset = 6.5f;

            targetFlags = new BlockFlag[]{BlockFlag.generator, null};

            weapons.add(new Weapon() {{
                top = mirror = false;

                x = y = recoil = 0;
                reload = 45f;

                shootSound = Sounds.blaster;

                bullet = new LifestealBulletType(3, 9, 1.5f) {{
                    homingPower = 0.24f;
                    homingDelay = 3f;
                    lifetime = 45f;
                    width = 3.5f;
                    height = 5.5f;
                    shrinkY = 0;
                    buildingDamageMultiplier = 0.8f;

                    smokeEffect = shootEffect = Fx.none;
                    frontColor = Color.valueOf("bf92f9");
                    backColor = Color.valueOf("6d56bf");
                    trailLength = 6;
                    trailWidth = 1.75f;
                    trailColor = Color.valueOf("6d56bf");

                    fragRandomSpread = 40;
                    fragSpread = 5;
                    fragBullets = 2;
                    fragBullet = new LifestealBulletType(3, 7, 0.5f) {{
                        homingPower = 0.19f;
                        homingDelay = 4f;
                        lifetime = 30f;
                        width = height = 4;
                        shrinkY = 0;

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
            aiController = CircleTargetFlyingAI::new;

            flying = lowAltitude = circleTarget = true;

            health = 260;
            armor = 7;
            hitSize = 11.5f;
            speed = 2.1f;
            drag = 0.016f;
            accel = 0.08f;
            itemCapacity = 15;

            engineOffset = 7.75f;

            targetFlags = new BlockFlag[]{BlockFlag.factory, BlockFlag.battery, null};

            weapons.add(new Weapon(){{
                top = mirror = false;

                reload = 10;
                inaccuracy = 360;
                shootCone = 360;
                recoil = 0;
                x = y = 0;

                shootSound = Sounds.shockBlast;

                bullet = new LifestealBulletType(4, 65, 5.5f) {{
                    splashDamage = 15;
                    splashDamageRadius = 14;
                    homingPower = 0.12f;
                    homingDelay = 3f;
                    lifetime = 20f;
                    height = width = 7;
                    shrinkY = 0;
                    lightning = 4;
                    lightningLength = 3;
                    lightningLengthRand = 4;
                    lightningDamage = 5;
                    lightningCone = 360;
                    buildingDamageMultiplier = 0.7f;

                    smokeEffect = shootEffect = Fx.none;
                    sprite = "circle-bullet";
                    frontColor = Color.valueOf("bf92f9");
                    backColor = trailColor = lightningColor = Color.valueOf("6d56bf");
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
                }};
            }});
        }};
        ageronia = new UnitType("ageronia"){{
            constructor = UnitEntity::create;

            flying = lowAltitude = faceTarget = true;

            health = 560;
            armor = 4;
            hitSize = 16.5f;
            speed = 2.2f;
            drag = 0.019f;
            accel = 0.075f;
            itemCapacity = 35;

            engineOffset = 15.25f;
            engineSize = 3.35f;

            targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.generator, BlockFlag.battery, BlockFlag.core};

            weapons.add(new Weapon(){{
                top = mirror = rotate = false;

                x = y = 0;
                shoot.shots = 2;
                shootY = 0;
                reload = 50;
                shootCone = 200;

                shootSound = Sounds.shockBlast;

                bullet = new LifestealBulletType(5f, 45, 2.75f, "circle-bullet"){{
                    pierce = pierceBuilding = true;
                    keepVelocity = false;

                    pierceCap = 5;
                    homingPower = 0.1f;
                    homingRange = 50;
                    lifetime = 44;
                    height = width = 4;
                    buildingDamageMultiplier = 0.7f;

                    smokeEffect = shootEffect = Fx.none;
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
                        pierceCap = 2;
                        weaveScale = 1;
                        weaveMag = 10;
                        homingPower = 0.7f;
                        homingRange = 72;
                        lifetime = 22.5f;
                        shrinkY = shrinkX = 0;
                        lightning = 2;
                        lightningLength = 3;
                        lightningLengthRand = 4;
                        lightningDamage = 12;

                        frontColor = hitColor = lightningColor = Color.valueOf("bf92f9");
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
                    }};
                }};
            }});
        }};
        /*quark = new UnitType("quark"){{

        }};*/
        creo = new UnitType("creo"){{
            constructor = UnitEntity::create;
            defaultCommand = EICommands.healUnitsCommand;

            outlines = flying = faceTarget = lowAltitude = true;
            logicControllable = isEnemy = false;

            health = 110;
            hitSize = 6f;
            speed = 2.35f;
            rotateSpeed = 3.4f;
            itemCapacity = 5;

            lightRadius = 25;
            outlineRadius = 3;

            weapons.add(new RepairBeamWeapon(){{
                targetUnits = targetBuildings = true;
                outlines = top = mirror = rotate = false;

                maxRange = range = 60;
                shootCone = 5;
                beamWidth = 0.7f;
                repairSpeed = 0.4f;
                fractionRepairSpeed = 0.05f;

                x = 0;
                y = 1.5f;
            }});
        }public void init(){
            super.init();

            Seq<UnitCommand> cmds = Seq.with(commands);
                cmds.add(EICommands.healUnitsCommand);
                cmds.remove(UnitCommand.repairCommand);

            commands = cmds.toArray();
        }
        public void update(Unit unit){
            super.update(unit);
            if(unit.isPlayer() && (Vars.net.server() || !Vars.net.active())){
                unit.getPlayer().clearUnit();
            }}
        };
        piece = new UnitType("piece"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;

            lowAltitude = flying = alwaysUnlocked = true;
            isEnemy = false;

            health = 60f;
            hitSize = 6f;
            speed = 2.75f;
            drag = 0.05f;
            accel = 0.2f;
            rotateSpeed = 22.5f;
            buildSpeed = 0.25f;
            mineSpeed = 2.25f;
            mineTier = 1;
            itemCapacity = 10;

            engineOffset = 4.75f;
            outlineRadius = 2;

            weapons.add(new Weapon(){{
                top = mirror = false;

                reload = 12f;
                x = 0;
                y = 0.5f;

                ejectEffect = Fx.casing1;

                bullet = new BasicBulletType(2.5f, 7){{
                    width = 5f;
                    height = 7f;
                    lifetime = 45f;
                    buildingDamageMultiplier = 0.01f;

                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                }};
            }});
        }};
        guardian = new UnitType("guardian"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;

            hidden = lowAltitude = flying = true;
            isEnemy = false;

            health = 490f;
            hitSize = 12f;
            speed = 2.35f;
            drag = 0.05f;
            accel = 0.2f;
            rotateSpeed = 22.5f;
            buildSpeed = 1.75f;
            mineSpeed = 8.85f;
            mineTier = 3;
            itemCapacity = 110;

            buildBeamOffset = 4.75f;
            engineOffset = 8f;
            outlineRadius = 3;

            weapons.add(new Weapon("small-mount-weapon"){{
                top = mirror = true;

                reload = 30f;
                x = 5.35f;
                y = -4f;

                ejectEffect = Fx.casing1;

                bullet = new BasicBulletType(3f, 21){{
                    shoot.shots = 2;
                    homingPower = 0.7f;
                    homingRange = 3 * tilesize;
                    width = 5f;
                    height = 7f;
                    lifetime = 45f;
                    buildingDamageMultiplier = 0.01f;

                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                }};
            }});
        }};
        starnight = new UnitType("starnight"){{
            constructor = UnitEntity::create;
            aiController = FlyingFollowAI::new;

            hidden = flying = lowAltitude = faceTarget = true;
            drawShields = false;

            health = 30000;
            armor = 19;
            hitSize = 66f;
            speed = 0.6f;
            accel = 0.04f;
            drag = 0.018f;
            buildSpeed = 6.5f;
            payloadCapacity = (5.5f * 5.5f) * tilePayload;

            engineOffset = 46f;
            engineSize = 7.8f;
            buildBeamOffset = 43;

            ammoType = new PowerAmmoType(12500);

            abilities.add(
                    new ForceFieldAbility(15*tilesize, 6, 3000, 180, 16, 45),
                    new EnergyFieldAbility(15f, 12f, 12.5f*tilesize){{
                        maxTargets = 65;
                        healPercent = 0.05f;
                        statusDuration = 20f;
                        status = StatusEffects.electrified;

                        color = Color.valueOf("6cf5d7");
                    }},
                    new OverloadAbility(300, 15*tilesize){{
                        statusDuration = 360;
                    }}
            );
        }};
    }}