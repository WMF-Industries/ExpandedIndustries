package ExpandedIndustries.content;

import ExpandedIndustries.ai.*;
import ExpandedIndustries.ai.types.*;
import ExpandedIndustries.entities.bullet.*;
import ExpandedIndustries.entities.bullet.abilities.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Interp;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.Vars.*;
import static mindustry.gen.Sounds.*;

public class EIUnits{
    public static UnitType
    agrid, xerad, escapade, natorin, terrand, // specialist supports
    requer, convoy, // random..
    centurion, alturion, // miners
    luma, vera, kora, astra, // payload
    pygmy, schaus, ageronia, // hit-and-run
    creo, // healers
    piece, delta, // core
    starnight; // hidden

    public static void load(){
        agrid = new UnitType("agrid"){{
            constructor = LegsUnit::create;
            groundLayer = Layer.legUnit;

            hovering = true;

            health = 90f;
            hitSize = 11.5f;
            speed = 0.65f;
            rotateSpeed = 1.37f;

            legCount = 6;
            legLength = 13f;
            legBaseOffset = 2.5f;
            legExtension = -3;
            shadowElevation = 0.2f;

            weapons.add(
                new Weapon("ei-agrid-weapon"){{
                    top = false;

                    shake = 2f;
                    shootY = 4f;
                    x = 6f;
                    reload = 60f;
                    recoil = 1.2f;

                    shootSound = Sounds.shootLaser;

                    bullet = new LaserBulletType(){{
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

            allowLegStep = hovering = true;

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

            weapons.add(
                    new Weapon("ei-xerad-weapon") {{
                        top = rotate = false;

                        reload = 75f;
                        recoil = 1.2f;
                        shake = 2f;
                        x = 9.5f;
                        y = 3;
                        shootY = 4f;

                        shootSound = shootLaser;

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
                        rotateSpeed = 2.4f;
                        mirror = false;

                        x = 0;
                        y = -3.5f;
                        reload = 150;
                        shake = 2.7f;

                        shootSound = shootArtillery;

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

            weapons.add(
                    new Weapon("ei-escapade-weapon") {{
                        mirror = true;
                        rotate = top = false;

                        x = 16;
                        y = 2;
                        reload = 40;

                        shootSound = shootLaser;

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

                        shootSound = shootArtillery;

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

            weapons.add(new Weapon("ei-natorin-weapon") {{
                rotate = continuous = mirror = top = true;
                alternate = false;

                reload = 240;
                rotateSpeed = 1.7f;
                shake = 4;
                x = 18;
                y = 4;
                shootY = 18;

                shootSound = beamPlasma;

                    bullet = new ContinuousLaserBulletType() {{
                        pierceArmor = collidesTeam = true;

                        damage = 48;
                        healPercent = 4.5f;
                        lifetime = 180;
                        length = 240;
                        width = 4;
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
            legBaseOffset = 28;
            legGroupSize = 2;
            legExtension = 2;
            legSpeed = 0.02f;
            legForwardScl = 1.2f;
            outlineRadius = 4;

            weapons.add(
                new Weapon(){{
                    mirror = top = false;

                    shoot.firstShotDelay = 85;
                    reload = 360;
                    x = 0;
                    y = 2.5f;
                    recoil = 0;

                    chargeSound = chargeLancer;
                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = shoot.firstShotDelay;
                    shootSound = explosionPlasmaSmall;

                    bullet = new ArtilleryBulletType(5, 45, "circle-bullet"){{
                        collides = absorbable = hittable = false;
                        scaleLife = true;

                        lifetime = 60;
                        buildingDamageMultiplier = 0.4444444444444444f;
                        width = height = 25;
                        shrinkX = shrinkY = 0;

                        chargeEffect = new MultiEffect(
                            Fx.greenLaserCharge,
                            new ParticleEffect(){{
                                randLength = true;

                                sizeFrom = 10;
                                sizeTo = 2;
                                baseLength = 2;
                                length = 5;
                                lifetime = 80;
                                colorFrom = colorTo = Pal.heal;
                            }},
                            new ParticleEffect(){{
                                randLength = true;

                                startDelay = 30;
                                sizeFrom = 10;
                                sizeTo = 2;
                                baseLength = 2;
                                length = 5;
                                lifetime = 50;
                                colorFrom = colorTo = Pal.heal;
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

                            despawnEffect = hitEffect = Fx.none;
                            despawnSound = Sounds.shockBullet;

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

                    shootSound = shootArtillery;

                    bullet = new ArtilleryBulletType(3, 80){{
                        lifetime = 100;
                        height = 10;
                        width = 10;
                        fragBullet = new ShrapnelBulletType(){{
                            damage = 120;
                            speed = 0;
                            length = 80;
                            width = 50;

                            hitSound = despawnSound = Sounds.shootFuse;
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

                    shootSound = beamPlasma;

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
                new StatusAbility(600, 7.5f * tilesize)
            );

            weapons.add(
                new Weapon("ei-requer-laser"){{
                    mirror = alternate = true;
                    rotate = top = false;

                    x = 8;
                    reload = 60;
                    shake = 0.4f;

                    shootSound = shootLaser;

                    bullet = new LaserBulletType(){{
                        collidesTeam = false;

                        damage = 9;
                        lifetime = 30;
                        lifesteal = 1.5f;
                        length = 18 * tilesize;
                    }};
                }},
                new Weapon("ei-requer-weapon"){{
                    autoTarget = rotate = top = mirror = alternate = true;
                    controllable = false;

                    x = 3.25f;
                    y = 1.5f;
                    reload = 15;

                    bullet = new PulseBulletType(4, 4){{
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
                new StatusAbility(600, 7.5f*tilesize)
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

                    shootSound = shootSalvo;

                    bullet = new PulseBulletType(4f, 19f){{
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
            aiController = CircleTargetAI::new;

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

                shootSound = shootAlpha;

                bullet = new BasicBulletType(3, 9){{
                    homingPower = 0.24f;
                    homingDelay = 3f;
                    lifetime = 45f;
                    lifesteal = 1.5f;
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
                    fragBullet = new BasicBulletType(3, 7){{
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
            aiController = CircleTargetAI::new;

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

                shootSound = Sounds.shockBullet;

                bullet = new BasicBulletType(4, 65){{
                    splashDamage = 15;
                    splashDamageRadius = 14;
                    homingPower = 0.12f;
                    homingDelay = 3f;
                    lifetime = 20f;
                    lifesteal = 5.5f;
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

                shootSound = shockBullet; 

                bullet = new BasicBulletType(5f, 45, "circle-bullet"){{
                    pierce = pierceBuilding = true;
                    keepVelocity = false;

                    pierceCap = 5;
                    homingPower = 0.1f;
                    homingRange = 50;
                    lifetime = 44;
                    lifesteal = 2.75f;
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
                    intervalBullet = new BasicBulletType(3.2f, 25, "circle-bullet"){{
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
        luma = new UnitType("luma"){{
            constructor = PayloadUnit::create;
            aiController = SuicideAI::new;

            flying = faceTarget = true;

            health = 210;
            hitSize = 8f;
            speed = 1.9f;
            rotateSpeed = 3.4f;
            itemCapacity = 50;
            drag = 0.099f;
            accel = 0.4f;
            payloadCapacity = tilesize * tilesize;

            engineOffset = 6f;
            engineSize = 2.2f;

            lightRadius = 50;
        }};
        vera = new UnitType("vera"){{
            constructor = PayloadUnit::create;
            aiController = SuicideAI::new;

            flying = faceTarget = true;

            health = 280;
            hitSize = 12f;
            speed = 1.8f;
            rotateSpeed = 5;
            itemCapacity = 110;
            payloadCapacity =  2f * 2f * tilesize * tilesize;

            engineSize = 0;
            setEnginesMirror(
                    new UnitEngine(3f, -8f, 2.6f, -40f)
            );

            lightRadius = 50;
        }};
        kora = new UnitType("kora"){{
            constructor = PayloadUnit::create;
            aiController = SuicideAI::new;

            flying = faceTarget = true;

            health = 470;
            hitSize = 20;
            speed = 1.6f;
            rotateSpeed = 3;
            itemCapacity = 200;
            payloadCapacity =  3f * 3f * tilesize * tilesize;

            engineOffset = 16f;
            engineSize = 3.2f;
            setEnginesMirror(
                new UnitEngine(11.4f, -13.5f, 3f, -50f)
            );
            lightRadius = 50;
        }};
        astra = new UnitType("astra"){{
            constructor = PayloadUnit::create;

            flying = true;
            isEnemy = false; //huh?

            health = 5300;
            hitSize = 7.4f * tilesize;
            speed = 2.2f;
            rotateSpeed = 1.2f;
            drag = 0.098f;
            accel = 0.06f;
            itemCapacity = 280;
            payloadCapacity = 4.5f * 4.5f * tilesize * tilesize;

            engineSize = 6.5f;
            engineOffset = 32;
            setEnginesMirror(
                new UnitEngine(26f, -30f, 4.8f, -50f),
                new UnitEngine(30f, -24f, 4.6f, -50f),
                new UnitEngine(34f, -18f, 4.4f, -50f)
            );

            abilities.add(new UnitSpawnAbility(luma, 900, 0, 2));
        }};
        creo = new UnitType("creo"){
            {
                constructor = UnitEntity::create;
                aiController = FieldMedicAI::new;

                outlines = flying = faceTarget = lowAltitude = true;
                logicControllable = playerControllable = isEnemy = false;

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

                    shootCone = 15f;
                    beamWidth = 0.7f;
                    repairSpeed = 0.4f;
                    fractionRepairSpeed = 0.05f;

                    x = 0;
                    y = 1.5f;

                    bullet = new BulletType(){{
                        maxRange = range = 60;
                    }};
                }});
            }

            @Override
            public void init(){
                super.init();

                commands.add(EICommands.healUnitsCommand);
                commands.remove(UnitCommand.repairCommand);
            }
        };
        piece = new UnitType("piece"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;

            lowAltitude = flying = alwaysUnlocked = true;
            isEnemy = false;

            health = 80f;
            hitSize = 6f;
            speed = 2.75f;
            drag = 0.05f;
            accel = 0.2f;
            rotateSpeed = 22.5f;
            buildSpeed = 0.25f;
            mineSpeed = 3f;
            mineTier = 1;
            itemCapacity = 10;

            engineOffset = 4.75f;
            outlineRadius = 2;

            weapons.add(new Weapon(){{
                top = mirror = false;

                reload = 15f;
                x = 0;
                y = 0.5f;

                ejectEffect = Fx.casing1;
                shootSound = Sounds.beamPlasmaSmall;
                initialShootSound = Sounds.shootBeamPlasmaSmall;

                bullet = new BasicBulletType(2.5f, 7){{
                    keepVelocity = false;

                    lifetime = 45f;
                    width = 1.5f;
                    height = 4.5f;
                    homingPower = 0.02f;
                    buildingDamageMultiplier = 0.01f;

                    trailWidth = 1.2f;
                    trailLength = 3;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    shootEffect = Fx.shootSmallColor;
                    smokeEffect = Fx.hitLaserColor;
                    backColor = trailColor = Pal.yellowBoltFront;
                    hitColor = Pal.yellowBoltFront;
                    frontColor = Color.white;
                    lightColor = Pal.yellowBoltFront;
                }};
            }});
        }};
        delta = new UnitType("delta"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;

            lowAltitude = flying = true;
            isEnemy = false;

            health = 390f;
            armor = 3f;
            hitSize = 13.5f;
            speed = 3.7f;
            drag = 0.05f;
            rotateSpeed = 19f;
            accel = 0.16f;
            fogRadius = 0f;
            buildSpeed = 1.5f;
            mineSpeed = 10.25f;
            mineTier = 3;
            itemCapacity = 95;


            engineSize = 3.2f;
            buildBeamOffset = mineBeamOffset = engineOffset = 8.1f;
            setEnginesMirror(
                new UnitEngine(
                    -7.2f,
                    -6.4f,
                    2.4f,
                    -50f
                )
            );

            weapons.add(
                new Weapon(){{
                    mirror = false;

                    x = 0f;
                    y = 5.2f;
                    reload = 75f;
                    range = 270f;

                    shootSound = Sounds.shootMissileLarge;
                    shootSoundVolume = 0.5f;

                    bullet = new BulletType(){{
                        keepVelocity = false;

                        shake = 2f;
                        speed = 0f;

                        shootEffect = new MultiEffect(
                            Fx.shootBigColor,
                            new Effect(9, e -> {
                                color(Color.white, e.color, e.fin());
                                stroke(0.7f + e.fout());
                                Lines.square(e.x, e.y, e.fin() * 5f, e.rotation + 45f);

                                Drawf.light(e.x, e.y, 14f, e.color, e.fout() * 0.7f);
                            }),
                            new WaveEffect(){{
                                colorFrom = colorTo = Pal.surgeAmmoFront;
                                sizeTo = 10f;
                                lifetime = 8f;
                                strokeFrom = 3f;
                            }}
                        );
                        smokeEffect = Fx.shootBigSmoke2;

                        spawnUnit = new MissileUnitType("delta-missile"){{
                            lowAltitude = true;

                            speed = 5f;
                            maxRange = 6f;
                            lifetime = 54f;
                            health = 30;

                            engineSize = 1.75f;
                            engineLayer = Layer.effect;
                            trailColor = engineColor = Pal.surgeAmmoFront;
                            parts.add(
                                new FlarePart(){{
                                    followRotation = true;

                                    progress = PartProgress.life.slope().curve(Interp.pow2In);
                                    color1 = Pal.surgeAmmoFront;
                                    color2 = Pal.surgeAmmoBack;
                                    radius = 0f;
                                    radiusTo = 15f;
                                    stroke = 3f;
                                    rotation = 45f;
                                    y = -4.25f;
                                }}
                            );

                            weapons.add(new Weapon(){{
                                shootOnDeath = true;
                                mirror = false;

                                shootCone = 360f;
                                reload = 1f;

                                shootSound = Sounds.none;
                                shootOnDeathEffect = Fx.massiveExplosion;

                                bullet = new ExplosionBulletType(55f, 25f){{
                                    buildingDamageMultiplier = 0.01f;
                                    shootEffect = new MultiEffect(
                                        new WrapEffect(Fx.dynamicSpikes, Pal.surgeAmmoFront, 24f),
                                        new WaveEffect(){{
                                            colorFrom = colorTo = Pal.surgeAmmoFront;
                                            sizeTo = 40f;
                                            lifetime = 12f;
                                            strokeFrom = 4f;
                                        }}
                                    );
                                }};
                            }});
                        }};
                    }};
                }}
            );
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

            abilities.add(
                new ForceFieldAbility(15f * tilesize, 6, 3000, 180, 16, 45),
                new EnergyFieldAbility(15f, 12f, 12.5f * tilesize){{
                    maxTargets = 65;
                    healPercent = 0.05f;
                    statusDuration = 20f;
                    status = StatusEffects.electrified;

                    color = Color.valueOf("6cf5d7");
                }},
                new StatusAbility(300, 15f * tilesize){{
                    statusDuration = 360;
                }}
            );
        }};
    }}
