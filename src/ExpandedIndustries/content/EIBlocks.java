package ExpandedIndustries.content;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import ExpandedIndustries.world.blocks.power.*;
import ExpandedIndustries.world.blocks.distribution.*;

import static ExpandedIndustries.content.EIItems.*;
import static ExpandedIndustries.content.EILiquids.*;
import static ExpandedIndustries.content.EIStatusEffects.*;
import static ExpandedIndustries.content.EIUnits.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.StatusEffects.*;
import static mindustry.type.ItemStack.*;

public class EIBlocks{
    public static Block
    //environment
    flower, orePeridotium, oreStarium,
    grassWater, liquidReurium,

    //distribution - serpulo
    stariumConveyor, stariumAlloyConveyor, stariumJunction, titaniumBridge, stariumBridge, stariumAlloyBridge,
    stariumConduit, titaniumBridgeConduit, stariumBridgeConduit,

    //distribution - erekir
    tungstenConveyor,

    //storage - serpulo
    crate,
    layeredUnloader, microUnloader,

    //extraction - serpulo
    electricDrill, precisionDrill, hammerDrill,

    //extraction - erekir
    hugePlasmaBore,

    //production (vanilla res) - serpulo
    graphiteCompressor, siliconFabricator, metaglassFabricator, plastaniumCondenser,
    cryofluidStirrer, cryofluidPlant, oilCrystallizer, coalLiquifier, scrapper,

    //production (custom res) - serpulo
    peridotiumSynthesizer, stariumSynthesizer, stariumRefiner, peridotiumEnricher, lumiumSmelter,
    oilPurifier, oilRefiner, thermiteMixer, freezer, oxygenLiquefier,

    //power - serpulo
    steamTurbine, peridotiumReactor, peridotiumGenerator, lumiumReactor,

    //power - erekir
    reinforcedSolarPanel,

    //logic - serpulo
    poweredMicroProcessor, poweredLogicProcessor, poweredHyperProcessor, //todo: rework and implement

    //other - serpulo
    coreFrag, coreQuadrant,
    planetaryMender, planetaryOverdrive,

    //defense - serpulo
    graphiteWall, largeGraphiteWall, stariumWall, largeStariumWall,
    anado, deuse, enforcer,
    hexagon, renoit, piercer,
    cavern, underglow, raven, region,

    //factories & recons - serpulo
    groundFactory, airFactory,
    starruneReconstructor, eraniteReconstructor, ultraReconstructor, terraReconstructor;

    public static void load(){
        grassWater = new Floor("grass-water"){{
            shallow = supportsOverlay = isLiquid = placeableOn = true;

            liquidDrop = water;
            status = wet;

            liquidMultiplier = 0.75f;
            speedMultiplier = 0.7f;
            statusDuration = 90f;
            drownTime = 0;

            variants = 4;
            albedo = 0.9f;

            cacheLayer = CacheLayer.water;
        }};
        liquidReurium = new Floor("liquid-reurium") {{
            isLiquid = true;
            supportsOverlay = false;

            liquidDrop = reurium;
            status = sticky;

            speedMultiplier = 0.05f;
            liquidMultiplier = 0.35f;
            statusDuration = 900f;
            drownTime = 600f;

            variants = 0;
            albedo = 0.9f;
            cacheLayer = CacheLayer.tar;
            mapColor = Color.valueOf("c179d2");
        }};
        flower = new OverlayFloor("flowers") {{
            variants = 3;

            attributes.set(Attribute.water, 0.2f);
        }};
        orePeridotium = new OreBlock("peridotium-ore", EIItems.peridotium) {{
            oreDefault = true;

            oreThreshold = 0.864f;
            oreScale = 24.9047f;
        }};
        oreStarium = new OreBlock("starium-ore", EIItems.starium) {{
            oreDefault = true;

            oreThreshold = 0.893f;
            oreScale = 25.1f;
        }};

        stariumConveyor = new Conveyor("starium-conveyor"){{
            requirements(Category.distribution, with(lead, 2, titanium, 2, starium, 1));

            speed = 9f / 60f;
            displayedSpeed = 20;
            health = 60;
        }};
        stariumAlloyConveyor = new StackConveyor("starium-alloy-conveyor"){{
            requirements(Category.distribution, with(silicon, 2, plastanium, 1, starium, 1));

            speed = 5f / 60f;
            itemCapacity = 20;
            health = 105;
        }};
        stariumJunction = new Junction("starium-junction"){{
            requirements(Category.distribution, with(copper, 4, titanium, 3, starium, 3));

            speed = 17.5f;
            itemCapacity = 10;
        }};
        titaniumBridge = new BufferedItemBridge("titanium-bridge"){{
            requirements(Category.distribution, with(copper, 6, lead, 6, titanium, 4));

            fadeIn = moveArrows = true;

            range = 7;
            bufferCapacity = 22;

            arrowSpacing = 6f;
        }};
        stariumBridge = new CustomItemBridge("starium-bridge"){{
            requirements(Category.distribution, with(copper, 8, titanium, 6, starium, 2));

            fadeIn = moveArrows = true;

            range = 9;
            bufferCapacity = 40;
            speed = 2.5f;
            displayedSpeed = 20f;

            arrowSpacing = 6f;
        }};
        stariumAlloyBridge = new ItemBridge("starium-alloy-bridge"){{
            requirements(Category.distribution, with(titanium, 8, silicon, 8, stariumAlloy, 4));
            consumePower(0.25f);

            hasPower = pulse = true;

            range = 18;

            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
        }};
        stariumConduit = new Conduit("starium-conduit"){{
            requirements(Category.distribution, with(metaglass, 3, starium, 1));

            liquidCapacity = 24f;
            liquidPressure = 1.6f;
            health = 105;
        }};
        titaniumBridgeConduit = new LiquidBridge("titanium-bridge-conduit"){{
            requirements(Category.distribution, with(metaglass, 6, graphite, 4, titanium, 2));

            fadeIn = moveArrows = hasPower = false;

            health = 90;
            range = 7;

            arrowSpacing = 6f;
        }};
        stariumBridgeConduit = new LiquidBridge("starium-bridge-conduit"){{
            requirements(Category.distribution, with(metaglass, 8, graphite, 6, starium, 2));

            fadeIn = moveArrows = hasPower = false;

            health = 105;
            range = 9;

            arrowSpacing = 6f;
        }};

        tungstenConveyor = new StackConveyor("tungsten-conveyor"){{
            requirements(Category.distribution, with(graphite, 4, tungsten, 2));

            outputRouter = false;

            health = 140;
            speed = 6f / 60f;
            itemCapacity = 5;
        }};

        crate = new StorageBlock("crate"){{
            requirements(Category.effect, with(copper, 50, lead, 50));

            itemCapacity = 50;
            buildCostMultiplier = 0.75f;
        }};
        microUnloader = new Unloader("micro-unloader"){{
            requirements(Category.effect, with(copper, 20, lead, 5, graphite, 5));

            speed = 15f;
        }};
        layeredUnloader = new Unloader("layered-unloader"){{
            requirements(Category.effect, with(titanium, 50, silicon, 60, graphite, 10));

            speed = 3f;
        }};

        electricDrill = new Drill("electric-drill"){{
            requirements(Category.production, with(lead, 35, titanium, 15, silicon, 10));
            consumePower(1.75f);

            hasPower = true;
            hasLiquids = false;

            size = 2;
            tier = 4;
            liquidBoostIntensity = 1f;
            drillTime = 67.5f;
        }};
        precisionDrill = new Drill("precision-drill"){{
            requirements(Category.production, with(lead, 120, metaglass, 80, titanium, 80, thorium, 20));
            consumeLiquid(water, 0.4f);

            drawRim = true;

            size = 4;
            tier = 5;
            drillTime = 80f;
            liquidBoostIntensity = 1f;
            rotateSpeed = 6f;
            itemCapacity = 20;
            warmupSpeed = 0.01f;
            updateEffectChance = 0.03f;

            drillEffect = Fx.mineHuge;
            updateEffect = Fx.pulverizeRed;
        }};
        hammerDrill = new Drill("hammer-drill"){{
            requirements(Category.production, with(lead, 265, silicon, 160, titanium, 70, thorium, 95));
            consumeLiquid(cryofluid, 0.2f).boost();
            consumePower(6f);

            hasPower = drawRim = true;

            size = 5;
            tier = 5;
            drillTime = 150f;
            rotateSpeed = 7f;
            itemCapacity = 60;
            warmupSpeed = 0.03f;
            liquidBoostIntensity = 2f;
            updateEffectChance = 0.03f;

            drillEffect = Fx.mineHuge;
            updateEffect = Fx.pulverizeRed;
        }};

        hugePlasmaBore = new BeamDrill("huge-plasma-bore"){{
            requirements(Category.production, with(silicon, 260, oxide, 90, thorium, 160, tungsten, 270));
            consumeLiquids(LiquidStack.with(nitrogen, 4f / 60f, cyanogen, 2f / 60f)).boost();
            consumeLiquid(hydrogen, 4f / 60f);
            consumePower(2f);

            drillTime = 60f;
            tier = 5;
            size = 4;
            range = 8;
            itemCapacity = 30;
            optionalBoostIntensity = 2.5f;

            fogRadius = 4;
            laserWidth = 0.7f;
        }};

        graphiteCompressor = new GenericCrafter("graphite-compressor"){{
            requirements(Category.crafting, with(lead, 210, graphite, 70, titanium, 190, silicon, 90, thorium, 120));
            consumeItem(coal, 8);
            consumeLiquid(EILiquids.steam, 0.2f);
            consumePower(3f);

            hasItems = hasLiquids = hasPower = true;

            size = 4;
            craftTime = 45;
            itemCapacity = 30;
            liquidCapacity = 100;
            outputItem = new ItemStack(graphite, 4);

            craftEffect = Fx.steam;
        }};
        siliconFabricator = new AttributeCrafter("silicon-fabricator"){{
            requirements(Category.crafting, with(metaglass, 230, titanium, 140, plastanium, 70, silicon, 160));
            consumeItems(with(graphite, 4, sand, 16, lead, 4));
            consumePower(4.45f);

            hasPower = hasItems = true;
            hasLiquids = false;

            size = 4;
            craftTime = 90f;
            itemCapacity = 60;
            boostScale = 0.15f;
            outputItem = new ItemStack(silicon, 16);

            craftEffect = Fx.smeltsmoke;
            ambientSound = Sounds.loopSmelter;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(
                    Color.valueOf("ffef99")
                )
            );
        }};
        metaglassFabricator = new AttributeCrafter("metaglass-fabricator"){{
            requirements(Category.crafting, with(titanium, 110, graphite, 75, lead, 95, plastanium, 20));
            consumeItems(with(lead, 3, sand, 3, graphite, 2));
            consumePower(1.4f);

            hasPower = true;
            hasLiquids = false;

            size = 3;
            craftTime = 60f;
            itemCapacity = 40;
            boostScale = 0.15f;
            ambientSoundVolume = 0.07f;
            outputItem = new ItemStack(metaglass, 8);

            ambientSound = Sounds.loopSmelter;
            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(
                    Color.valueOf("ffef99")
                )
            );
        }};
        plastaniumCondenser = new AttributeCrafter("plastanium-condenser"){{
            requirements(Category.crafting, with(lead, 210, silicon, 90, metaglass, 70, titanium, 90, plastanium, 15));
            consumeItem(titanium, 4);
            consumeLiquid(oil, 0.6f);
            consumePower(4.5f);

            hasItems = hasPower = hasLiquids = true;

            size = 3;
            health = 530;
            craftTime = 60f;
            liquidCapacity = 180f;
            attribute = Attribute.oil;
            outputItem = new ItemStack(plastanium, 3);

            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFade()
            );
        }};
        cryofluidStirrer = new GenericCrafter("cryofluid-stirrer"){{
            requirements(Category.crafting, with(lead, 130, silicon, 70, titanium, 50, metaglass, 20));
            consumeItem(titanium, 1);
            consumeLiquid(water, 0.5f);
            consumePower(2f);

            hasPower = hasItems = hasLiquids = solid = outputsLiquid = true;
            rotate = false;

            size = 3;
            craftTime = 90;
            liquidCapacity = 120f;
            outputLiquid = new LiquidStack(cryofluid, 0.5f);

            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(water),
                new DrawLiquidTile(cryofluid){{
                    drawLiquidLight = true;
                }},
                new DrawDefault(),
                new DrawRegion("-top")
            );
        }};
        cryofluidPlant = new GenericCrafter("cryofluid-plant"){{
            requirements(Category.crafting, with(titanium, 210, silicon, 120, metaglass, 90, thorium, 40));
            consumeItem(titanium, 3);
            consumeLiquid(water, 1f);
            consumePower(3.5f);

            hasPower = hasItems = hasLiquids = solid = outputsLiquid = true;
            rotate = false;

            size = 4;
            liquidCapacity = 120f;
            craftTime = 90;
            outputLiquid = new LiquidStack(cryofluid, 1f);

            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(water),
                new DrawLiquidTile(cryofluid){{
                    drawLiquidLight = true;
                }},
                new DrawDefault(),
                new DrawRegion("-top")
            );
        }};
        oilCrystallizer = new GenericCrafter("oil-crystallizer"){{
            requirements(Category.crafting, with(copper, 120, silicon, 70, titanium, 110, Items.plastanium, 20));
            consumeLiquid(Liquids.oil, 0.6f);
            consumePower(1.5f);

            hasPower = hasItems = hasLiquids = true;
            rotateDraw = false;

            size = 3;
            craftTime = 30f;
            outputItem = new ItemStack(Items.coal, 3);

            craftEffect = Fx.coalSmeltsmoke;
        }};

        cavern = new PowerTurret("cavern"){{
            requirements(Category.turret, with(Items.lead, 570, Items.silicon, 490, Items.titanium, 470, Items.plastanium, 380, Items.phaseFabric, 350, EIItems.stariumAlloy, 220));
            consumePower(15f);
            consumeCoolant(0.25f);

            moveWhileCharging = false;

            range = 230;
            size = 3;
            recoil = 2.7f;
            reload = 345f;
            health = 1650;
            coolantMultiplier = 1.2f;
            shoot.firstShotDelay = 75f;

            chargeSound = Sounds.chargeLancer;
            shootSound = Sounds.explosionPlasmaSmall;

            shootType = new EmpBulletType(){{
                scaleLife = despawnHit = true;
                collides = false;

                speed = 5f;
                damage = 150f;
                unitDamageScl = 1.2f;
                healPercent = 0.09f;
                status = StatusEffects.electrified;
                statusDuration = 450f;
                radius = 88.5f;
                lifetime = 30f;
                width = height = 15f;
                shrinkX = shrinkY = 0f;

                sprite = "circle-bullet";
                chargeEffect = new MultiEffect(
                    new ParticleEffect(){{
                        line = true;
                        lenFrom = 5;
                        lenTo = 2;
                        strokeFrom = 2;
                        strokeTo = 1;
                        colorFrom = Color.valueOf("a5f5af");
                        colorTo = Color.valueOf("ffffff");
                        length = 24;
                        baseLength = 24;
                        lifetime = 70f;
                        particles = 9;
                    }},
                    new WaveEffect(){{
                        sprite = "circle-bullet";
                        frontColor = Color.valueOf("ffffff");
                        backColor = Color.valueOf("83f793");
                        sizeFrom = 0;
                        sizeTo = 7.5f;
                        lifetime = 75;
                    }}
                );
                lightOpacity = 0.7f;
                frontColor = Color.valueOf("ffffff");
                backColor = lightColor = hitColor = Color.valueOf("83f793");
                hitEffect = Fx.none;
                despawnEffect = EIFx.cavernFx;

                fragBullets = 1;
                fragOffsetMin = fragOffsetMax = 0f;
                fragBullet = new BasicBulletType(0f, 0f, "circle-bullet"){{
                    collides = false;

                    lifetime = 300f;
                    intervalDelay = 30f;
                    bulletInterval = 30f;
                    intervalBullets = 1;

                    intervalBullet = new EmpBulletType(){{
                        despawnHit = instantDisappear = true;
                        collides = false;

                        speed = 0f;
                        damage = 150f;
                        unitDamageScl = 1.2f;
                        healPercent = 0.09f;
                        status = StatusEffects.electrified;
                        statusDuration = 450f;
                        radius = 88.5f;
                        lifetime = 1f;
                        width = height = 1f;
                        hitEffect = Fx.none;
                        despawnEffect = EIFx.cavernFx;

                        sprite = "white";
                        frontColor = backColor = Color.clear;
                    }};

                    lightOpacity = 0.7f;
                    frontColor = Color.valueOf("ffffff");
                    backColor = lightColor = hitColor = Color.valueOf("83f793");
                    despawnEffect = new MultiEffect(
                        EIFx.cavernFx,
                        new ParticleEffect(){{
                            startDelay(5);
                            line = true;

                            lifetime = 15f;
                            lenFrom = 4;
                            lenTo = 5;
                            strokeFrom = 0;
                            strokeTo = 6;
                            particles = 12;
                            colorFrom = Color.valueOf("3bf550");
                        }},
                        new ParticleEffect(){{
                            startDelay(15);
                            line = true;

                            lifetime = 20f;
                            lenFrom = 5;
                            lenTo = 0;
                            strokeFrom = 6;
                            strokeTo = 0;
                            particles = 12;
                            colorFrom = Color.valueOf("3bf550");
                        }},
                        new WaveEffect(){{
                            startDelay(20);

                            sizeFrom = 20;
                            sizeTo = 0;
                            lifetime = 5;
                            colorFrom = Color.valueOf("a5f5af");
                            colorTo = Color.valueOf("ffffff");
                        }}
                    );
                }};
            }};
        }};

        //Todo: Finish this
        lumiumSmelter = new GenericCrafter("lumium-smelter") {{
            hasPower = true;

            size = 4;
            craftTime = 75f;
            itemCapacity = 20;
            outputItem = new ItemStack(EIItems.lumium, 1);

            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(7.5f);
            consumeItems(with(Items.thorium, 5, Items.coal, 3, EIItems.starium, 2));
            requirements(Category.crafting, with(Items.copper, 270, Items.silicon, 230, Items.titanium, 210, Items.thorium, 70, Items.plastanium, 60, EIItems.starium, 60));
        }};
        peridotiumSynthesizer = new GenericCrafter("peridotium-synthesizer") {{
            hasPower = true;
            hasLiquids = false;

            size = 3;
            craftTime = 90f;
            itemCapacity = 30;
            ambientSoundVolume = 0.07f;
            outputItem = new ItemStack(EIItems.peridotium, 1);

            craftEffect = Fx.blockCrash;
            ambientSound = Sounds.loopSmelter;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));

            consumeItems(with(Items.thorium, 5));
            consumePower(4.5f);
            requirements(Category.crafting, with(Items.copper, 260, Items.silicon, 90, Items.titanium, 80, Items.thorium, 50));
        }};
        stariumSynthesizer = new GenericCrafter("starium-synthesizer") {{
            hasPower = true;
            hasLiquids = false;

            size = 3;
            itemCapacity = 50;
            craftTime = 90f;
            ambientSoundVolume = 0.07f;
            outputItem = new ItemStack(EIItems.starium, 5);

            craftEffect = Fx.blockCrash;
            ambientSound = Sounds.loopSmelter;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));

            consumeItems(with(Items.titanium, 10, Items.silicon, 5));
            consumePower(6f);
            requirements(Category.crafting, with(Items.lead, 310, Items.silicon, 170, Items.titanium, 120));
        }};
        stariumRefiner = new GenericCrafter("starium-refiner") {{
            hasPower = true;

            size = 3;
            craftTime = 45f;
            itemCapacity = 50;
            outputItem = new ItemStack(EIItems.stariumAlloy, 1);

            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(4.5f);
            consumeItems(with(Items.surgeAlloy, 1, EIItems.starium, 10));
            requirements(Category.crafting, with(Items.lead, 330, Items.silicon, 240, Items.titanium, 200, Items.plastanium, 120, Items.surgeAlloy, 70, EIItems.starium, 40));
        }};
        peridotiumEnricher = new GenericCrafter("peridotium-enricher") {{
            hasItems = true;

            size = 2;
            craftTime = 150f;
            itemCapacity = 15;
            outputItem = new ItemStack(enrichedPeridotium, 1);

            craftEffect = Fx.pulverizeMedium;

            consumePower(0.75f);
            consumeItem(EIItems.peridotium, 3);
            requirements(Category.crafting, with(Items.copper, 80, Items.lead, 65, Items.silicon, 50, Items.titanium, 25));
        }};
        scrapper = new GenericCrafter("scrapper") {{
            hasItems = true;

            size = 2;
            craftTime = 30f;
            itemCapacity = 15;
            ambientSoundVolume = 0.025f;
            outputItem = new ItemStack(Items.scrap, 1);

            craftEffect = Fx.pulverizeMedium;
            ambientSound = Sounds.loopGrind;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 2.25f;
            }}, new DrawRegion("-top"));

            consumePower(1f);
            consumeItems(with(Items.copper, 2, Items.sand, 3));
            requirements(Category.crafting, with(Items.lead, 70, Items.graphite, 30, Items.silicon, 10));
        }};
        freezer = new GenericCrafter("freezer") {{
            hasItems = true;

            size = 2;
            craftTime = 90f;
            outputItem = new ItemStack(EIItems.ice, 1);

            craftEffect = Fx.pulverizeSmall;

            consumeLiquid(Liquids.water, 0.75f);
            consumePower(2.5f);
            requirements(Category.crafting, with(Items.lead, 75, Items.metaglass, 30, Items.silicon, 15));
        }};
        oilPurifier = new GenericCrafter("oil-purifier"){{
            rotate = invertFlip = true;

            size = 3;
            craftTime = 10f;
            regionRotated1 = 3;
            liquidCapacity = 45f;
            researchCostMultiplier = 1.2f;
            ambientSoundVolume = 0.08f;
            liquidOutputDirections = new int[]{1, 3};
            outputLiquids = LiquidStack.with(EILiquids.heavyOil, 4f / 60, EILiquids.lightOil, 11f / 60);

            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 3.35f;
            }}, new DrawRegion("-top"));

            consumeLiquid(Liquids.oil, 15f / 60f);
            consumePower(4f);
            requirements(Category.crafting, with(Items.copper, 220, Items.silicon, 160, Items.graphite, 130, Items.metaglass, 80, Items.titanium, 40));
        }};
        oilRefiner = new GenericCrafter("oil-refiner"){{
            size = 3;
            craftTime = 90f;
            regionRotated1 = 3;
            liquidCapacity = 45f;
            ambientSoundVolume = 0.08f;
            researchCostMultiplier = 1.2f;
            outputItem = new ItemStack(Items.scrap, 1);
            outputLiquids = LiquidStack.with(EILiquids.lightOil, 8f / 60);

            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(EILiquids.lightOil), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 1.7f;
            }}, new DrawDefault());

            consumeLiquid(EILiquids.heavyOil, 12f / 60f);
            consumePower(5.5f);
            requirements(Category.crafting, with(Items.lead, 220, Items.silicon, 170, Items.metaglass, 110, Items.titanium, 90, Items.plastanium, 35));
        }};
        thermiteMixer = new GenericCrafter("thermite-mixer"){{
            size = 4;
            craftTime = 30f;
            liquidCapacity = 20f;
            researchCostMultiplier = 1.2f;
            ambientSoundVolume = 0.08f;
            outputItem = new ItemStack(thermiteCompound, 1);

            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(EILiquids.lightOil), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 2.1f;
            }}, new DrawDefault());

            requirements(Category.crafting, with(Items.lead, 310, Items.silicon, 180, Items.metaglass, 125, Items.titanium, 85, Items.plastanium, 20));
            consumeLiquid(EILiquids.lightOil, 7.5f / 60f);
            consumePower(2.5f);
        }};
        steamTurbine = new ConsumeGenerator("steam-turbine") {{
            hasLiquids = hasItems = hasPower = true;
            explodeOnFull = false;

            powerProduction = 15f;
            itemDuration = 150;
            itemCapacity = 20;
            size = 3;
            health = 925;
            ambientSoundVolume = 0.06f;

            ambientSound = Sounds.loopSteam;
            generateEffect = Fx.generatespark;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawWarmupRegion(),
                new DrawRegion("-turbine") {{
                    rotateSpeed = 2f;
                }},
                new DrawLiquidRegion(Liquids.water),
                new DrawLiquidRegion(EILiquids.steam)
            );

            consumeLiquid(Liquids.water, 15f / 60f);
            consume(new ConsumeItemFlammable(0.75f));
            outputLiquid = new LiquidStack(EILiquids.steam, 15f / 60f);
            requirements(Category.power, with(Items.copper, 270, Items.silicon, 190, Items.graphite, 155, Items.titanium, 90, EIItems.starium, 40));
        }};
        peridotiumGenerator = new ConsumeGenerator("peridotium-generator") {{
            size = 5;
            health = 2420;
            powerProduction = 35f;
            itemDuration = 840f;
            envEnabled = Env.any;

            generateEffect = Fx.generatespark;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());

            requirements(Category.power, with(Items.lead, 420, Items.silicon, 270, Items.metaglass, 235, Items.titanium, 185, Items.thorium, 130, Items.plastanium, 70, EIItems.stariumAlloy, 45));
            consume(new ConsumeItemRadioactive());
            consumeLiquid(Liquids.cryofluid, 0.05f);
        }};
        peridotiumReactor = new NuclearReactor("peridotium-reactor"){{
            size = 4;
            health = 1740;
            powerProduction = 80;
            itemCapacity = 20;
            itemDuration = 420f;
            ambientSoundVolume = 0.24f;
            heating = 0.02f;
            explosionRadius = 25;
            explosionDamage = 27500;

            ambientSound = Sounds.loopHum;

            fuelItem = EIItems.peridotium;
            consumeLiquid(Liquids.water, 0.5f);
            consumeItem(EIItems.peridotium);
            requirements(Category.power, with(Items.lead, 410, Items.silicon, 330, Items.graphite, 310, Items.titanium, 170, Items.thorium, 120, EIItems.starium, 90));
        }};
        lumiumReactor = new ImpactReactor("lumium-reactor") {{
            size = 5;
            health = 4500;
            powerProduction = 652.5f;
            itemDuration = 240f;
            ambientSoundVolume = 0.5f;

            ambientSound = Sounds.loopPulse;

            consumePower(52.5f);
            consumeItem(EIItems.lumium);
            consumeLiquid(liquidOxygen, 1);
            requirements(Category.power, with(Items.lead, 1250, Items.silicon, 750, Items.titanium, 700, Items.plastanium, 625, EIItems.enrichedPeridotium, 335, EIItems.stariumAlloy, 270));
        }};
        reinforcedSolarPanel = new CooledSolarGenerator("reinforced-solar-panel") {{
            size = 2;

            consumeLiquid(Liquids.water, heating / coolantPower).update(false);
            requirements(Category.power, with(Items.beryllium, 60, Items.tungsten, 110, Items.silicon, 70));
        }};
        coreFrag = new CoreBlock("core-frag") {{
            isFirstTier = true;

            unitType = piece;
            health = 500;
            itemCapacity = 1000;
            size = 2;
            unitCapModifier = 4;

            requirements(Category.effect, with(Items.copper, 250, Items.lead, 125));
        }};
        planetaryMender = new MendProjector("planetary-mender"){{
            hasPower = true;

            size = 5;
            reload = 450;
            range = 8000f;
            healPercent = 25f;
            phaseBoost = 25f;

            consumePower(250f);
            consumeItem(Items.surgeAlloy, 3).boost();
            requirements(Category.effect, with(Items.copper, 9425, Items.lead, 8750, Items.silicon, 5230, Items.titanium, 4300, Items.thorium, 4230, Items.surgeAlloy, 1235));
        }};
        planetaryOverdrive = new OverdriveProjector("planetary-overdrive"){{
            hasPower = true;
            hasBoost = false;

            size = 5;
            range = 8000f;
            speedBoost = 4f;
            useTime = 60f;

            consumePower(240f);
            consumeItems(with(Items.phaseFabric, 1, Items.silicon, 2));
            requirements(Category.effect, with(Items.copper, 8425, Items.lead, 6350, Items.silicon, 5430, Items.titanium, 4100, Items.thorium, 3950, EIItems.stariumAlloy, 535, EIItems.enrichedPeridotium, 430));
        }};
        stariumWall = new Wall("starium-wall") {{
            absorbLasers = insulated = flashHit = true;

            health = 1950;
            chanceDeflect = 25;
            schematicPriority = 10;
            envDisabled |= Env.scorching;

            requirements(Category.defense, with(EIItems.stariumAlloy, 6, Items.plastanium, 2));
        }};
        largeStariumWall = new Wall("large-starium-wall") {{
            absorbLasers = insulated = flashHit = true;

            health = 1950 * 4;
            size = 2;
            chanceDeflect = 25;
            schematicPriority = 10;
            envDisabled |= Env.scorching;

            requirements(Category.defense, ItemStack.mult(stariumWall.requirements, 4));
        }};
        graphiteWall = new Wall("graphite-wall") {{
            health = 400;
            envDisabled |= Env.scorching;

            requirements(Category.defense, with(Items.graphite, 6));
        }};
        largeGraphiteWall = new Wall("large-graphite-wall") {{
            health = 400 * 4;
            size = 2;
            envDisabled |= Env.scorching;

            requirements(Category.defense, ItemStack.mult(graphiteWall.requirements, 4));
        }};
        anado = new ItemTurret("anado") {{
            ammo(
                Items.scrap, new BasicBulletType(2f, 8) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    splashDamage = 3f;
                    splashDamageRadius = 16f;
                    reloadMultiplier = 0.66f;
                    fragBullets = 4;
                    fragBullet = new BasicBulletType(1f, 5) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }},
                Items.lead, new BasicBulletType(2f, 7) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 2;
                    splashDamage = 2f;
                    splashDamageRadius = 24f;
                    fragBullets = 3;
                    fragBullet = new BasicBulletType(1f, 4) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }},
                Items.metaglass, new BasicBulletType(3f, 14) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 75f;
                    ammoMultiplier = 4;
                    splashDamage = 6f;
                    splashDamageRadius = 32f;
                    fragBullets = 6;
                    fragBullet = new BasicBulletType(1f, 7) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 30f;
                    }};
                }}
            );

            reload = 20f;
            range = 120;
            shootCone = 15f;
            health = 320;
            inaccuracy = 1f;
            rotateSpeed = 7.2f;

            ammoUseEffect = Fx.casing1;

            coolant = consumeCoolant(0.1f);
            limitRange();
            requirements(Category.turret, with(Items.lead, 45, Items.copper, 15));
        }};
        deuse = new ItemTurret("deuse") {{
            ammo(
                Items.scrap, new BasicBulletType(3f, 14) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    reloadMultiplier = 0.66f;
                    splashDamage = 6f;
                    splashDamageRadius = 24f;
                    fragBullets = 4;
                    fragBullet = new BasicBulletType(1.5f, 5) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }},
                Items.lead, new BasicBulletType(3f, 13) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 2f;
                    splashDamage = 7f;
                    splashDamageRadius = 32f;
                    fragBullets = 3;
                    fragBullet = new BasicBulletType(1.75f, 4) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }},
                Items.metaglass, new BasicBulletType(4.25f, 21) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 4f;
                    splashDamage = 12f;
                    splashDamageRadius = 40f;
                    fragBullets = 6;
                    fragBullet = new BasicBulletType(2.125f, 7) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }},
                Items.plastanium, new BasicBulletType(4.5f, 29) {{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 5f;
                    reloadMultiplier = 1.2f;
                    splashDamage = 16f;
                    splashDamageRadius = 48f;
                    fragBullets = 8;
                    fragBullet = new BasicBulletType(2.25f, 13) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                    }};
                }}
            );

            size = 2;
            rotateSpeed = 7.7f;
            range = 210f;
            reload = 60f;
            ammoEjectBack = 3f;
            recoil = 1.7f;
            shake = 2f;
            shoot.shots = 6;
            shoot.shotDelay = 5f;
            health = 510;

            shootSound = Sounds.shootSalvo;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(0.3f);
            limitRange();
            requirements(Category.turret, with(Items.lead, 130, Items.copper, 70, Items.silicon, 60, Items.titanium, 40));
        }};
        hexagon = new ItemTurret("hexagon") {{
            ammo(
                Items.surgeAlloy, new ArtilleryBulletType(5f, 42) {{
                    collidesTiles = collidesAir = false;

                    knockback = 0.8f;
                    lifetime = 120f;
                    width = height = 12f;
                    buildingDamageMultiplier = 10f;
                    splashDamageRadius = 20f;
                    splashDamage = 18f;
                    ammoMultiplier = 3f;
                    lightning = 3;
                    lightningLength = 7;
                    lightningDamage = 2.5f;
                }},
                EIItems.stariumAlloy, new ArtilleryBulletType(5f, 60) {{
                    collidesTiles = collidesAir = false;

                    knockback = 0.8f;
                    lifetime = 120f;
                    width = height = 12f;
                    buildingDamageMultiplier = 10f;
                    splashDamageRadius = 36f;
                    splashDamage = 20f;
                    reloadMultiplier = 1.2f;
                    ammoMultiplier = 5f;
                    lightning = 4;
                    lightningLength = 10;
                    lightningDamage = 5f;
                    fragBullets = 3;
                    fragBullet = new BasicBulletType(3.25f, 6f) {{
                        collidesTiles = collidesAir = false;

                        buildingDamageMultiplier = 10f;
                        lifetime = 15f;
                        width = height = 6f;
                        splashDamageRadius = 45f;
                        splashDamage = 4f;
                        lightning = 2;
                        lightningLength = 10;
                        lightningDamage = 2.5f;
                    }};
                }}
            );

            targetAir = false;

            reload = 210f;
            recoil = 4.7f;
            size = 3;
            range = 600f;
            shoot.shots = 3;
            shoot.shotDelay = 5f;
            health = 920;
            shake = 2.3f;

            shootSound = Sounds.shootRipple;

            coolant = consumeCoolant(0.5f);
            consumePower(2.5f);
            limitRange(0f);
            requirements(Category.turret, with(Items.copper, 360, Items.lead, 290, Items.silicon, 220, Items.titanium, 160, Items.plastanium, 130, Items.surgeAlloy, 90));
        }};
        underglow = new PowerTurret("underglow") {{
            range = 210;
            coolantMultiplier = 1.1f;
            recoil = 7;
            reload = 75;
            size = 4;

            shootEffect = new MultiEffect(
                new ParticleEffect(){{
                    length = sizeTo = 0;
                    particles = 1;
                    offset = 25;
                    region = "ei-shootspike";
                    lifetime = 40;
                    sizeFrom = 35;
                    interp = Interp.pow2In;
                    colorFrom = colorTo = Color.valueOf("eb8778");
                }},
                new ParticleEffect(){{
                    length = sizeTo = 0;
                    particles = 1;
                    offset = -25;
                    region = "ei-shootspike";
                    lifetime = 40;
                    sizeFrom = 35;
                    interp = Interp.pow2In;
                    colorFrom = colorTo = Color.valueOf("eb8778");
                }}
            );
            smokeEffect = new ParticleEffect(){{
                length = 20;
                particles = 15;
                cone = 35;
                interp = Interp.pow3In;
                sizeInterp = Interp.pow2In;
                lifetime = 30;
                colorFrom = Color.valueOf("a2a2a2");
                colorTo = Color.valueOf("454545");
                sizeFrom = 3;
            }};

            consumeCoolant(0.5f);
            consumePower(20);
            requirements(Category.turret, with(Items.copper, 1250, Items.graphite, 780, Items.silicon, 620, Items.plastanium, 430, Items.phaseFabric, 210, EIItems.stariumAlloy, 140));

            shootType = new EmpBulletType(){{
                damage = 40;
                splashDamage = 50;
                radius = splashDamageRadius = 120;
                speed = 3;
                lifetime = 70;
                timeDuration = shrinkY = 0;
                width = height = 14;
                sprite = "circle-bullet";
                backColor = trailColor = hitColor = Color.valueOf("eb8778");
                trailWidth = 7;
                trailLength = 15;
                despawnHit = true;
                fragBullets = 25;
                fragLifeMin = 0;
                fragBullet = new EmpBulletType(){{
                    despawnHit = true;
                    collides = absorbable = hittable = false;

                    status = EIStatusEffects.lockdown;
                    statusDuration = 360;
                    damage = 25;
                    speed = width = height = 0;
                    lifetime = 150;
                    radius = 120;
                    timeDuration = 0;
                    lightning = 2;
                    lightningDamage = 8;
                    lightningLength = 4;
                    lightningLengthRand = 24;

                    lightningColor = hitColor = Color.valueOf("eb8778");
                    hitEffect = despawnEffect = applyEffect = Fx.none;
                    hitPowerEffect = new MultiEffect(
                        new ParticleEffect(){{
                            length = 25;
                            line = true;
                            particles = 2;
                            lifetime = 20;
                            interp = Interp.pow3Out;
                            sizeInterp = Interp.pow2In;
                            lenFrom = 6;
                            lenTo = 0;
                            strokeFrom = 2;
                            strokeTo = 1;
                            colorFrom = colorTo = Color.valueOf("eb8778");
                        }},
                        new WaveEffect(){{
                            lifetime = 15;
                            interp = Interp.pow3In;
                            sizeFrom = sizeTo = 4;
                            strokeFrom = 2;
                            strokeTo = 0;
                            colorFrom = colorTo = Color.valueOf("eb8778");
                        }}
                    );
                    hitEffect = new MultiEffect(
                        new WaveEffect(){{
                            lifetime = 170;
                            interp = Interp.pow10In;
                            sizeFrom = sizeTo = 80;
                            strokeFrom = 4;
                            strokeTo = 0;
                            colorFrom = colorTo = Color.valueOf("eb8778");
                        }},
                        new ParticleEffect(){{
                            length = 0;
                            particles = 1;
                            lifetime = 170;
                            interp = Interp.pow10In;
                            sizeFrom = 7;
                            sizeTo = 0;
                            colorFrom = colorTo = Color.valueOf("eb8778");
                        }},
                        new ParticleEffect(){{
                            length = 0;
                            particles = 1;
                            lifetime = 10;
                            sizeFrom = 80;
                            sizeTo = 0;
                            colorFrom = colorTo = Color.valueOf("eb8778");
                        }}
                    );
                }};
            }};
        }};
        piercer = new PowerTurret("piercer") {{
            moveWhileCharging = targetAir = accurateDelay = false;

            range = 200f;
            shoot.firstShotDelay = 40f;
            recoil = 2f;
            reload = 120f;
            shake = 2f;
            size = 3;
            health = 2000;
            coolantMultiplier = 1.6f;

            heatColor = Color.red;
            smokeEffect = Fx.none;
            chargeSound = Sounds.chargeCorvus;
            shootSound = Sounds.shootFuse;
            shootEffect = new ParticleEffect() {{
                line = true;
                lenFrom = 5;
                lenTo = 3;
                strokeFrom = 2;
                strokeTo = 1;
                colorFrom = Color.valueOf("a9d8ff");
                colorTo = Color.valueOf("ffffffaa");
                length = 24;
                baseLength = 24;
                lifetime = 35f;
                particles = 9;
            }};

            consumeCoolant(0.2f);
            consumePower(9.5f);
            requirements(Category.turret, with(Items.lead, 290, Items.silicon, 220, Items.titanium, 190, Items.thorium, 120, EIItems.starium, 70));

            shootType = new BasicBulletType(8f, 370) {{
                chargeEffect = new MultiEffect(
                    new ParticleEffect() {{
                        line = true;
                        lenFrom = 5;
                        lenTo = 3;
                        strokeFrom = 20;
                        strokeTo = 0;
                        particles = 12;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("a9d8ff");
                        colorTo = Color.valueOf("a9d8ff");
                    }},
                    new WaveEffect() {{
                        sizeFrom = 0;
                        sizeTo = 8;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("ffffff");
                        colorTo = Color.valueOf("a9d8ff");
                    }}
                );
                pierceBuilding = true;
                collidesAir = false;

                status = StatusEffects.slow;
                statusDuration = 300;
                width = height = 12f;
                lifetime = 25f;
                pierceCap = 10;

                frontColor = Color.valueOf("ffffff");
                backColor = Color.valueOf("a9d8ff");
                hitColor = Color.valueOf("a9d8ff");
                lightColor = Color.valueOf("a9d8ff");
                trailColor = Color.valueOf("a9d8ff");
                trailWidth = 6;
                trailLength = 20;
                hitEffect = new ParticleEffect() {{
                    line = true;
                    strokeFrom = 4;
                    strokeTo = 3;
                    colorFrom = Color.valueOf("a9d8ff");
                    colorTo = Color.valueOf("ffffff");
                    particles = 8;
                    lifetime = 50f;
                }};

                fragBullets = 4;
                fragBullet = new LaserBulletType() {{
                    collidesAir = false;

                    damage = 135;
                    length = 85;
                    status = StatusEffects.slow;
                    statusDuration = 300;
                }};
            }};
        }};
        enforcer = new PowerTurret("enforcer") {{
            moveWhileCharging = false;

            health = 720;
            size = 3;
            reload = 105f;
            shoot.firstShotDelay = 60f;
            range = 200;
            recoil = 2.7f;


            chargeSound = Sounds.chargeVela;
            shootSound = Sounds.shootBeamPlasma;

            consumePower(7.5f);
            requirements(Category.turret, with(Items.copper, 270, Items.lead, 210, Items.silicon, 170, Items.titanium, 120, Items.thorium, 90, EIItems.enrichedPeridotium, 30));

            shootType = new EmpBulletType() {{
                chargeEffect = new MultiEffect(
                    new WaveEffect() {{
                        sizeFrom = 0;
                        sizeTo = 30;
                        lifetime = 30f;
                        colorFrom = Color.valueOf("84f491");
                        colorTo = Color.valueOf("84f491");
                    }},
                    new WaveEffect() {{
                        startDelay(15f);
                        sizeFrom = 0;
                        sizeTo = 15;
                        lifetime = 30f;
                        colorFrom = Color.valueOf("3bf550");
                        colorTo = Color.valueOf("3bf550");
                    }},
                    new WaveEffect() {{
                        sprite = "circle-bullet";
                        sizeFrom = 0;
                        sizeTo = 15;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("84f491");
                        colorTo = Color.valueOf("84f491");
                    }},
                    new WaveEffect() {{
                        sprite = "circle-bullet";
                        sizeFrom = 0;
                        sizeTo = 9;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("3bf550");
                        colorTo = Color.valueOf("3bf550");
                    }}
                );
                scaleLife = true;

                spin = 2f;
                status = StatusEffects.electrified;
                statusDuration = 450;
                unitDamageScl = 1.25f;
                healPercent = 20f;
                damage = 70;
                lifetime = 60;
                speed = 5;
                radius = 90f;
                width = height = 15;
                shrinkX = shrinkY = 0;

                lightOpacity = 0.7f;
                sprite = "circle-bullet";
                frontColor = Color.valueOf("ffffff");
                backColor = Color.valueOf("83f793");
                lightColor = Color.valueOf("83f793");
                hitColor = Color.valueOf("83f793");
                hitEffect = despawnEffect = EIFx.cavernFx;

                lightning = 1;
                lightningLength = 1;
                lightningType = new BasicBulletType(0, 0) {{
                    collides = false;

                    width = height = 15;
                    shrinkX = shrinkY = 0;
                    rotationOffset = 0;
                    spin = 0f;
                    lifetime = 30;
                    lightning = 3;
                    lightningLength = 5;
                    lightningDamage = 5f;

                    sprite = "circle-bullet";
                    backColor = Color.valueOf("3bf550");
                    frontColor = Color.valueOf("ffffff");
                    lightColor = Color.valueOf("83f793");
                }};
            }};
        }};
        renoit = new LiquidTurret("renoit"){{
            requirements(Category.turret, with(Items.lead, 420, Items.metaglass, 330, Items.titanium, 270, Items.thorium, 140, Items.plastanium, 60));
            ammo(
                Liquids.water, new LiquidBulletType(Liquids.water){{
                    lifetime = 49f;
                    speed = 5f;
                    knockback = 1.7f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                    damage = 0.4f;
                    layer = Layer.bullet - 2f;
                }},
                Liquids.slag,  new LiquidBulletType(Liquids.slag){{
                    lifetime = 49f;
                    speed = 5f;
                    knockback = 1.3f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    damage = 9.5f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                }},
                Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid){{
                    lifetime = 49f;
                    speed = 5f;
                    knockback = 1.3f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                    damage = 0.4f;
                }},
                Liquids.oil, new LiquidBulletType(Liquids.oil){{
                    lifetime = 49f;
                    speed = 5f;
                    knockback = 1.3f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                    damage = 0.4f;
                    layer = Layer.bullet - 2f;
                }},
                EILiquids.reurium, new LiquidBulletType(EILiquids.reurium){{
                    lifetime = 49f;
                    speed = 5f;
                    knockback = 1.5f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.6f;
                    statusDuration = 60f * 8f;
                    damage = 0.6f;
                    layer = Layer.bullet - 2f;
                    reloadMultiplier = 0.75f;
                    status = EIStatusEffects.sticky;
                }}
            );

            shoot = new ShootAlternate(){{
                shots = 1;
                barrels = 2;
                spread = 7.5f;
                shotDelay = 0f;
            }};

            shootX = 0f;
            size = 4;
            reload = 3f;
            shoot.shots = 4;
            velocityRnd = 0.1f;
            inaccuracy = 4f;
            recoil = 1f;
            shootCone = 45f;
            liquidCapacity = 120f;
            range = 240f;
            health = 1725;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);

            shootEffect = Fx.shootLiquid;
        }};
        groundFactory = new UnitFactory("ground-factory"){{
            size = 3;

            consumePower(1.25f);
            requirements(Category.units, with(Items.copper, 90, Items.silicon, 70, Items.titanium, 50));

            plans = Seq.with(
                new UnitPlan(agrid, 60f * 10, with(Items.silicon, 30, Items.titanium, 10)),
                new UnitPlan(requer, 60f * 15, with(Items.silicon, 25, Items.graphite, 20))
            );
        }};
        airFactory = new UnitFactory("air-factory"){{
            size = 3;

            consumePower(1.25f);
            requirements(Category.units, with(Items.copper, 90, Items.silicon, 70, Items.titanium, 50));

            plans = Seq.with(
                new UnitPlan(pygmy, 60f * 15, with(Items.silicon, 25, Items.graphite, 10)),
                new UnitPlan(SmolBoi, 60 * 20, with(Items.silicon, 10, Items.titanium, 30)),
                new UnitPlan(creo, 60f * 25, with(Items.silicon, 25, Items.titanium, 20))
            );
        }};
        starruneReconstructor = new Reconstructor("starrune-reconstructor"){{
            size = 3;
            constructTime = 60f * 10f;

            consumePower(3f);
            consumeItems(with(Items.silicon, 40, Items.graphite, 40, Items.titanium, 20));
            requirements(Category.units, with(Items.copper, 200, Items.lead, 120, Items.silicon, 90, Items.graphite, 70));

            upgrades.addAll(
                new UnitType[]{agrid, xerad},
                new UnitType[]{requer, convoy},
                new UnitType[]{pygmy, schaus},
                new UnitType[]{SmolBoi, MediumBoi},
                new UnitType[]{UnitTypes.mono, centurion}
            );
        }};
        eraniteReconstructor = new Reconstructor("eranite-reconstructor"){{
            size = 5;
            constructTime = 60f * 20f;

            consumePower(6f);
            consumeItems(with(Items.silicon, 130, Items.titanium, 80, Items.metaglass, 40, EIItems.starium, 30));
            requirements(Category.units, with(Items.lead, 650, Items.silicon, 450, Items.titanium, 350, Items.thorium, 650, EIItems.starium, 250));

            upgrades.addAll(
                new UnitType[]{xerad, escapade},
                new UnitType[]{schaus, ageronia},
                new UnitType[]{MediumBoi, LargeBoi},
                new UnitType[]{centurion, alturion}
            );
        }};
        ultraReconstructor = new Reconstructor("ultra-reconstructor"){{
            size = 9;
            constructTime = 60f * 25f;

            consumePower(13f);
            consumeItems(with(Items.silicon, 850, Items.titanium, 750, Items.plastanium, 650, EIItems.starium, 550));
            consumeLiquid(Liquids.cryofluid, 1f);
            requirements(Category.units, with(Items.lead, 2000, Items.silicon, 1000, Items.titanium, 2000, Items.thorium, 750, Items.plastanium, 450, EIItems.enrichedPeridotium, 600, EIItems.starium, 400));

            upgrades.addAll(
                new UnitType[]{escapade, natorin},
                new UnitType[]{LargeBoi, PayloadBoi}
            );
        }};
        terraReconstructor = new Reconstructor("terra-reconstructor"){{
            constructTime = 60f * 25f;
            size = 11;

            consumePower(25f);
            consumeItems(with(Items.silicon, 1200, Items.plastanium, 750, EIItems.stariumAlloy, 400, EIItems.enrichedPeridotium, 270));
            consumeLiquid(liquidOxygen, 3f);
            requirements(Category.units, with(Items.lead, 4000, Items.silicon, 3000, Items.thorium, 1000, Items.plastanium, 600, Items.phaseFabric, 600, EIItems.stariumAlloy, 475));

            upgrades.addAll(
                new UnitType[]{natorin, terrand}
            );
        }};
    }
}