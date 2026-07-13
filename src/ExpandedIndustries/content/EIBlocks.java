package ExpandedIndustries.content;

import ExpandedIndustries.world.blocks.defense.*;
import ExpandedIndustries.world.draw.*;
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
    electricDrill, pressurizedDrill, hammerDrill,

    //extraction - erekir
    hugePlasmaBore,

    //production (vanilla res) - serpulo
    graphiteCompressor, siliconFabricator, metaglassFabricator, plastaniumCondenser,
    cryofluidStirrer, cryofluidPlant, oilCrystallizer, coalLiquifier, scrapper,

    //production (custom res) - serpulo
    mixingFoundry, molecularReassembler, stariumRefiner, peridotiumEnricher, lumiumSmelter,
    freezer, oxygenLiquefier, oilPurifier, oilRefiner, thermiteMixer,

    //power - serpulo
    steamTurbine, peridotiumReactor, peridotiumGenerator, lumiumReactor,

    //power - erekir
    reinforcedSolarPanel,

    //logic - serpulo
    poweredMicroProcessor, poweredLogicProcessor, poweredHyperProcessor, //todo: rework and implement

    //other - serpulo
    coreFrag, coreQuadrant,
    sectorMender, sectorOverdrive,

    //defense - serpulo
    iceWall, largeIceWall, graphiteWall, largeGraphiteWall, stariumWall, largeStariumWall,
    anado, deuse, enforcer,
    hexagon, renoit, piercer,
    cavern, underglow, raven, region,

    //factories & recons - serpulo
    industrialGroundFactory, industrialAirFactory,
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
        orePeridotium = new OreBlock("peridotium-ore", peridotium) {{
            oreDefault = true;

            oreThreshold = 0.864f;
            oreScale = 24.9047f;
        }};
        oreStarium = new OreBlock("starium-ore", starium) {{
            oreDefault = true;

            oreThreshold = 0.893f;
            oreScale = 25.1f;
        }};

        microUnloader = new Unloader("micro-unloader"){{
            requirements(Category.distribution, with(copper, 22, lead, 5, graphite, 5));

            speed = 15f;
        }};
        titaniumBridge = new BufferedItemBridge("titanium-bridge"){{
            requirements(Category.distribution, with(copper, 6, lead, 6, titanium, 4));

            fadeIn = moveArrows = true;

            range = 7;
            bufferCapacity = 22;

            arrowSpacing = 6f;
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
        stariumBridge = new CustomItemBridge("starium-bridge"){{
            requirements(Category.distribution, with(copper, 8, titanium, 6, starium, 2));

            fadeIn = moveArrows = true;

            range = 9;
            bufferCapacity = 40;
            speed = 2.5f;
            displayedSpeed = 20f;

            arrowSpacing = 6f;
        }};
        layeredUnloader = new Unloader("layered-unloader"){{
            requirements(Category.distribution, with(titanium, 50, silicon, 60, graphite, 10));

            speed = 3f;
        }};
        stariumAlloyBridge = new ItemBridge("starium-alloy-bridge"){{
            requirements(Category.distribution, with(titanium, 8, silicon, 8, stariumAlloy, 4));
            consumePower(0.25f);

            hasPower = pulse = true;

            range = 18;

            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
        }};
        titaniumBridgeConduit = new LiquidBridge("titanium-bridge-conduit"){{
            requirements(Category.liquid, with(metaglass, 6, graphite, 4, titanium, 2));

            fadeIn = moveArrows = hasPower = false;

            health = 90;
            range = 7;

            arrowSpacing = 6f;
        }};
        stariumConduit = new Conduit("starium-conduit"){{
            requirements(Category.liquid, with(metaglass, 3, starium, 1));

            liquidCapacity = 24f;
            liquidPressure = 1.6f;
            health = 105;
        }};
        stariumBridgeConduit = new LiquidBridge("starium-bridge-conduit"){{
            requirements(Category.liquid, with(metaglass, 8, graphite, 6, starium, 2));

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

        coreFrag = new CoreBlock("core-frag"){{
            requirements(Category.effect, with(copper, 250, lead, 125));

            isFirstTier = alwaysUnlocked = true;

            unitType = piece;
            health = 500;
            itemCapacity = 1000;
            size = 2;
            unitCapModifier = 4;
        }};
        crate = new StorageBlock("crate"){{
            requirements(Category.effect, with(copper, 50, lead, 50));

            itemCapacity = 50;
            buildCostMultiplier = 0.75f;
            researchCost = with(copper, 500, lead, 500);
        }};
        coreQuadrant = new CoreBlock("core-quadrant"){{
            requirements(Category.effect, with(copper, 12000, lead, 9500, silicon, 8000, thorium, 6500, plastanium, 4200));

            unitType = delta;
            health = 12500;
            armor = 5f;
            itemCapacity = 24000;
            size = 6;
            unitCapModifier = 48;
            researchCostMultiplier = 0.15f;
            buildCostMultiplier = 0.6f;

            thrusterLength = 40/4f;
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
        pressurizedDrill = new Drill("pressurized-drill"){{
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
            consumeLiquid(steam, 0.2f);
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
            requirements(Category.crafting, with(lead, 120, titanium, 210, silicon, 120, metaglass, 90, thorium, 40));
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
            requirements(Category.crafting, with(lead, 180, metaglass, 70, graphite, 110, titanium, 100, plastanium, 20));
            consumeLiquid(oil, 0.6f);
            consumePower(1.1f);

            hasPower = hasItems = hasLiquids = true;
            rotateDraw = false;

            size = 3;
            craftTime = 30f;
            outputItem = new ItemStack(coal, 3);

            craftEffect = Fx.coalSmeltsmoke;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawLiquidRegion(){{
                    drawLiquid = oil;
                }}
            );
        }};
        scrapper = new GenericCrafter("scrapper"){{
            requirements(Category.crafting, with(lead, 70, copper, 60, graphite, 30));
            consumeItems(with(copper, 2, sand, 3));
            consumePower(0.75f);

            hasItems = true;

            size = 2;
            craftTime = 30f;
            itemCapacity = 15;
            ambientSoundVolume = 0.025f;
            outputItem = new ItemStack(scrap, 1);

            craftEffect = Fx.pulverizeMedium;
            ambientSound = Sounds.loopGrind;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator"){{
                spinSprite = true;
                rotateSpeed = 2.25f;
            }}, new DrawRegion("-top"));
        }};

        mixingFoundry = new GenericCrafter("mixing-foundry"){{
            requirements(Category.crafting, with(lead, 310, silicon, 170, titanium, 120, metaglass, 40));
            consumeItems(with(titanium, 9, silicon, 3));
            consumePower(7f);

            hasPower = true;
            hasLiquids = false;

            size = 3;
            itemCapacity = 40;
            craftTime = 130f;
            ambientSoundVolume = 0.07f;
            outputItem = new ItemStack(starium, 3);

            craftEffect = Fx.blockCrash;
            ambientSound = Sounds.loopSmelter;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidAnim(slag), //what?
                new DrawDefault(),
                new DrawFlame(Color.valueOf("ffef99")){{
                    flameRadius = 4f;
                    flameRadiusIn = 2.9f;
                }}
            );
        }};
        molecularReassembler = new GenericCrafter("molecular-reassembler"){{
            requirements(Category.crafting, with(copper, 260, silicon, 90, titanium, 80, thorium, 50));
            consumeItem(thorium, 5);
            consumePower(4f);

            hasPower = true;
            hasLiquids = false;

            size = 3;
            craftTime = 340f;
            itemCapacity = 15;
            ambientSoundVolume = 0.07f;
            outputItem = new ItemStack(peridotium, 1);

            craftEffect = Fx.blockCrash;
            ambientSound = Sounds.loopSmelter;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawArcSmelt(),
                new DrawDefault()
            );
        }};
        stariumRefiner = new GenericCrafter("starium-refiner"){{
            requirements(Category.crafting, with(copper, 210, titanium, 160, silicon, 110, plastanium, 40, surgeAlloy, 50));
            consumeItems(with(surgeAlloy, 3, starium, 5));
            consumePower(4.5f);

            hasPower = true;

            size = 3;
            craftTime = 110f;
            itemCapacity = 20;
            outputItem = new ItemStack(stariumAlloy, 1);

            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame()
            );
        }};
        peridotiumEnricher = new GenericCrafter("peridotium-enricher"){{
            requirements(Category.crafting, with(copper, 80, lead, 65, silicon, 50));
            consumeItem(peridotium, 4);
            consumePower(0.75f);

            hasItems = true;

            size = 2;
            craftTime = 150f;
            itemCapacity = 20;
            outputItem = new ItemStack(enrichedPeridotium, 1);

            craftEffect = Fx.pulverizeMedium;
        }};
        lumiumSmelter = new GenericCrafter("lumium-smelter"){{
            requirements(Category.crafting, with(copper, 270, silicon, 230, titanium, 210, thorium, 70, plastanium, 60, starium, 60));
            consumeItems(with(titanium, 5, enrichedPeridotium, 2));
            consumePower(7.5f);

            hasPower = true;

            size = 4;
            craftTime = 75f;
            itemCapacity = 20;
            outputItem = new ItemStack(lumium, 1);

            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame()
            );
        }};
        freezer = new GenericCrafter("freezer"){{
            requirements(Category.crafting, with(lead, 75, metaglass, 30, silicon, 15));
            consumeLiquid(water, 0.75f);
            consumePower(2.5f);

            hasItems = true;

            size = 2;
            craftTime = 210f;
            outputItem = new ItemStack(itemIce, 3);

            craftEffect = Fx.pulverizeSmall;
        }};
        oxygenLiquefier = new GenericCrafter("oxygen-liquefier"){{
            requirements(Category.crafting, with(lead, 120, metaglass, 70, silicon, 60, titanium, 40));
            consumeLiquid(cryofluid, 0.05f);
            consumeItem(itemIce);
            consumePower(1.5f);

            size = 2;
            craftTime = 90f;

            outputLiquids = LiquidStack.with(liquidOxygen, 16f / 60f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(cryofluid),
                new DrawLiquidTile(liquidOxygen),
                new DrawDefault()
            );
        }};
        oilPurifier = new GenericCrafter("oil-purifier"){{
            requirements(Category.crafting, with(copper, 220, silicon, 160, graphite, 130, metaglass, 80, titanium, 40));
            consumeLiquid(oil, 15f / 60f);
            consumePower(4f);

            rotate = invertFlip = true;

            size = 3;
            craftTime = 10f;
            liquidCapacity = 45f;
            researchCostMultiplier = 1.2f;
            liquidOutputDirections = new int[]{1, 3};
            outputLiquids = LiquidStack.with(heavyOil, 4f / 60f, lightOil, 11f / 60f);

            regionRotated1 = 3;
            ambientSoundVolume = 0.08f;
            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(oil),
                new DrawRegion(),
                new DrawLiquidOutputs(),
                new DrawRegion("-rotator"){{
                    spinSprite = true;
                    rotateSpeed = 3.35f;
                }},
                new DrawRegion("-top")
            );
        }};
        oilRefiner = new GenericCrafter("oil-refiner"){{
            requirements(Category.crafting, with(lead, 220, silicon, 170, metaglass, 110, titanium, 90, plastanium, 35));
            consumeLiquid(heavyOil, 12f / 60f);
            consumePower(5.5f);

            size = 3;
            craftTime = 90f;
            liquidCapacity = 45f;
            researchCostMultiplier = 1.2f;
            outputItem = new ItemStack(scrap, 1);
            outputLiquids = LiquidStack.with(lightOil, 8f / 60);

            regionRotated1 = 3;
            ambientSoundVolume = 0.08f;
            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(heavyOil),
                new DrawLiquidTile(lightOil),
                new DrawRegion("-rotator"){{
                    spinSprite = true;
                    rotateSpeed = 1.7f;
                }},
                new DrawDefault()
            );
        }};
        thermiteMixer = new GenericCrafter("thermite-mixer"){{
            requirements(Category.crafting, with(lead, 310, silicon, 180, metaglass, 125, titanium, 85, plastanium, 20));
            consumeItems(with(titanium, 4, lead, 2));
            consumeLiquid(lightOil, 7.5f / 60f);
            consumePower(2.5f);

            size = 4;
            craftTime = 30f;
            liquidCapacity = 20f;
            researchCostMultiplier = 1.2f;
            ambientSoundVolume = 0.08f;
            outputItem = new ItemStack(thermiteCompound, 1);

            ambientSound = Sounds.loopElectricHum;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(lightOil),
                new DrawRegion("-rotator"){{
                    spinSprite = true;
                    rotateSpeed = 2.1f;
                }},
                new DrawDefault()
            );
        }};

        steamTurbine = new ConsumeGenerator("steam-turbine"){{
            requirements(Category.power, with(copper, 80, lead, 95, graphite, 30, silicon, 25, metaglass, 40));
            consume(new ConsumeItemFlammable(0.75f));
            consumeLiquid(water, 15f / 60f);

            hasLiquids = hasItems = hasPower = true;
            explodeOnFull = false;

            health = 925;
            size = 3;
            powerProduction = 11.5f;
            itemDuration = 120;
            itemCapacity = 20;
            outputLiquid = new LiquidStack(steam, 5f / 60f);

            ambientSoundVolume = 0.06f;
            ambientSound = Sounds.loopSteam;
            generateEffect = Fx.generatespark;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawRegion("-turbine1"){{
                    rotateSpeed = 3.5f;
                }},
                new DrawRegion("-turbine2"){{
                    rotateSpeed = -4f;
                }},
                new DrawDefault(),
                new DrawWarmupRegion(),
                new DrawLiquidRegion(water),
                new DrawLiquidRegion(steam)
            );
        }};
        peridotiumGenerator = new ConsumeGenerator("peridotium-generator"){{
            requirements(Category.power, with(lead, 420, silicon, 270, metaglass, 235, titanium, 185, thorium, 130, plastanium, 70, stariumAlloy, 45));
            consumeLiquid(cryofluid, 0.05f);
            consume(new ConsumeItemRadioactive());

            size = 5;
            health = 2420;
            powerProduction = 30f;
            itemDuration = 840f;
            envEnabled = Env.any;

            generateEffect = Fx.generatespark;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(cryofluid),
                new DrawParticles(){{
                    color = Color.valueOf("6aa95e");
                    particles = 60;
                    particleSize = 6f;
                    particleRad = 14f;
                    alpha = 0.5f;
                }},
                new DrawDefault()
            );
        }};
        peridotiumReactor = new NuclearReactor("peridotium-reactor"){{
            requirements(Category.power, with(lead, 410, silicon, 260, graphite, 170, titanium, 210, thorium, 140, plastanium, 75, starium, 90));
            consumeLiquid(water, 0.8f);
            consumeItem(peridotium);

            size = 4;
            health = 1740;
            powerProduction = 80f;
            fuelItem = peridotium;
            itemCapacity = 20;
            itemDuration = 420f;
            heating = 0.02f;
            explosionRadius = 25;
            explosionDamage = 27500;

            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.24f;
            explodeEffect = EIFx.peridotiumExplosion;
        }};
        lumiumReactor = new VariableImpactReactor("lumium-reactor"){{
            requirements(Category.power, with(lead, 1250, silicon, 750, titanium, 700, plastanium, 625, phaseFabric, 120, enrichedPeridotium, 335, stariumAlloy, 115));
            consumeItem(lumium);
            consumeLiquid(liquidOxygen, 1);
            consumePower(24f);

            size = 5;
            health = 4500;
            powerProduction = 294f;
            itemDuration = 240f;

            ambientSound = Sounds.loopPulse;
            ambientSoundVolume = 0.5f;
        }};
        reinforcedSolarPanel = new CooledSolarGenerator("reinforced-solar-panel"){{
            requirements(Category.power, with(beryllium, 90, graphite, 40, silicon, 70));
            consumeLiquid(water, heating / coolantPower).update(false);

            size = 2;
        }};
        sectorMender = new SectorMendProjector("sector-mender"){{
            requirements(Category.effect, with(copper, 9300, lead, 8700, silicon, 8200, graphite, 8350, titanium, 6500, thorium, 5000, plastanium, 1600, enrichedPeridotium, 750));
            consumeItem(surgeAlloy, 3).boost();
            consumePower(250f);

            hasPower = true;

            size = 5;
            reload = 450;
            healPercent = 25f;
            phaseBoost = 25f;
        }};
        sectorOverdrive = new SectorOverdriveProjector("sector-overdrive"){{
            requirements(Category.effect, with(copper, 8425, lead, 6350, silicon, 5430, titanium, 4100, thorium, 3950, phaseFabric, 1950, stariumAlloy, 535));
            consumeItems(with(phaseFabric, 1, silicon, 2));
            consumePower(240f);

            hasPower = true;
            hasBoost = false;

            size = 5;
            speedBoost = 4f;
            useTime = 60f;
        }};

        int largeWallMultiplier = 4;
        iceWall = new Wall("ice-wall"){{
            requirements(category.defense, with(itemIce, 10));

            health = 500;
            envDisabled |= Env.scorching;
        }};

        largeIceWall = new Wall("large-ice-wall"){{
            requirements(Category.defense, ItemStack.mult(iceWall.requirements, largeWallMultiplier));

            health = iceWall.health * largeWallMultiplier;
            size = 2;
            envDisabled |= Env.scorching;
        }};

        graphiteWall = new Wall("graphite-wall"){{
            requirements(Category.defense, with(graphite, 6));

            health = 440;
            envDisabled |= Env.scorching;
        }};
        largeGraphiteWall = new Wall("large-graphite-wall"){{
            requirements(Category.defense, ItemStack.mult(graphiteWall.requirements, largeWallMultiplier));

            health = graphiteWall.health * largeWallMultiplier;
            size = 2;
            envDisabled |= Env.scorching;
        }};
        stariumWall = new Wall("starium-wall"){{
            requirements(Category.defense, with(stariumAlloy, 2, phaseFabric, 2, plastanium, 2));

            absorbLasers = insulated = flashHit = true;

            health = 1550;
            chanceDeflect = 25;
            schematicPriority = 10;
            envDisabled |= Env.scorching;
        }};
        largeStariumWall = new Wall("large-starium-wall"){{
            requirements(Category.defense, ItemStack.mult(stariumWall.requirements, largeWallMultiplier));

            absorbLasers = insulated = flashHit = true;

            health = stariumWall.health * largeWallMultiplier;
            size = 2;
            chanceDeflect = 25;
            schematicPriority = 10;
            envDisabled |= Env.scorching;
        }};

        anado = new ItemTurret("anado"){{
            requirements(Category.turret, with(lead, 30, copper, 25));
            consumeCoolant(0.1f);

            reload = 20f;
            range = 120;
            shootCone = 15f;
            health = 320;
            inaccuracy = 1f;
            rotateSpeed = 7.2f;

            ammoUseEffect = Fx.casing1;

            ammo(
                scrap, new BasicBulletType(2f, 8){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    splashDamage = 3f;
                    splashDamageRadius = 16f;
                    reloadMultiplier = 0.66f;
                    armorMultiplier = 1.8f;

                    frontColor = Pal.scrapAmmoFront;
                    backColor = Pal.scrapAmmoBack;

                    fragBullets = 4;
                    fragBullet = new BasicBulletType(1f, 5){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 1.8f;

                        frontColor = Pal.scrapAmmoFront;
                        backColor = Pal.scrapAmmoBack;
                    }};
                }},
                lead, new BasicBulletType(2f, 7){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 2;
                    splashDamage = 2f;
                    splashDamageRadius = 24f;
                    armorMultiplier = 2f;

                    frontColor = EIPal.leadFront;
                    backColor = EIPal.leadBack;

                    fragBullets = 3;
                    fragBullet = new BasicBulletType(1f, 4){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 2f;

                        frontColor = EIPal.leadFront;
                        backColor = EIPal.leadBack;
                    }};
                }},
                metaglass, new BasicBulletType(3f, 14){{
                    width = 6f;
                    height = 8f;
                    lifetime = 75f;
                    ammoMultiplier = 4;
                    splashDamage = 6f;
                    splashDamageRadius = 28f;
                    armorMultiplier = 1.5f;
                    
                    frontColor = Pal.glassAmmoFront;
                    backColor = Pal.glassAmmoBack;

                    fragBullets = 6;
                    fragBullet = new BasicBulletType(1f, 7) {{
                        height = 5f;
                        width = 3f;
                        lifetime = 30f;
                        armorMultiplier = 1.5f;

                        frontColor = Pal.glassAmmoFront;
                        backColor = Pal.glassAmmoBack;
                    }};
                }}
            );

            limitRange();
        }};
        deuse = new ItemTurret("deuse"){{
            requirements(Category.turret, with(lead, 130, copper, 70, silicon, 60, titanium, 40));
            consumeCoolant(0.3f);

            size = 2;
            rotateSpeed = 7.7f;
            range = 210f;
            recoil = 1.7f;
            reload = 60f;
            shoot.shots = 6;
            shoot.shotDelay = 5f;
            health = 510;

            shootSound = Sounds.shootSalvo;
            ammoUseEffect = Fx.casing2;
            ammoEjectBack = 3f;
            shake = 2f;

            ammo(
                scrap, new BasicBulletType(3f, 14){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    reloadMultiplier = 0.66f;
                    splashDamage = 6f;
                    splashDamageRadius = 18f;
                    armorMultiplier = 1.8f;

                    frontColor = Pal.scrapAmmoFront;
                    backColor = Pal.scrapAmmoBack;

                    fragBullets = 4;
                    fragBullet = new BasicBulletType(1.5f, 5){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 1.8f;

                        frontColor = Pal.scrapAmmoFront;
                        backColor = Pal.scrapAmmoBack;
                    }};
                }},
                lead, new BasicBulletType(3f, 13){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 2f;
                    splashDamage = 7f;
                    splashDamageRadius = 24f;
                    armorMultiplier = 2f;

                    frontColor = EIPal.leadFront;
                    backColor = EIPal.leadBack;

                    fragBullets = 3;
                    fragBullet = new BasicBulletType(1.75f, 4){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 2f;

                        frontColor = EIPal.leadFront;
                        backColor = EIPal.leadBack;
                    }};
                }},
                metaglass, new BasicBulletType(4.25f, 16){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 4f;
                    splashDamage = 12f;
                    splashDamageRadius = 28f;
                    armorMultiplier = 1.5f;

                    frontColor = Pal.glassAmmoFront;
                    backColor = Pal.glassAmmoBack;

                    fragBullets = 6;
                    fragBullet = new BasicBulletType(2.125f, 7){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 1.5f;

                        frontColor = Pal.glassAmmoFront;
                        backColor = Pal.glassAmmoBack;
                    }};
                }},
                plastanium, new BasicBulletType(4.5f, 24){{
                    width = 6f;
                    height = 8f;
                    lifetime = 60f;
                    ammoMultiplier = 5f;
                    reloadMultiplier = 1.2f;
                    splashDamage = 16f;
                    splashDamageRadius = 36f;
                    armorMultiplier = 1.2f;

                    frontColor = Pal.plastaniumFront;
                    backColor = Pal.plastaniumBack;

                    fragBullets = 8;
                    fragBullet = new BasicBulletType(2.25f, 13){{
                        height = 5f;
                        width = 3f;
                        lifetime = 20f;
                        armorMultiplier = 1.2f;

                        frontColor = Pal.plastaniumFront;
                        backColor = Pal.plastaniumBack;
                    }};
                }}
            );

            limitRange();
        }};
        piercer = new PowerTurret("piercer"){{
            requirements(Category.turret, with(lead, 290, silicon, 220, titanium, 190, thorium, 120));
            consumeCoolant(0.2f);
            consumePower(9.5f);

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
            shootEffect = new ParticleEffect(){{
                line = true;
                lenFrom = 5;
                lenTo = 3;
                strokeFrom = 2;
                strokeTo = 1;
                colorFrom = EIPal.greenLaserBack;
                colorTo = Color.valueOf("ffffffaa");
                length = 24;
                baseLength = 24;
                lifetime = 35f;
                particles = 9;
            }};

            shootType = new BasicBulletType(8f, 210){{
                chargeEffect = new MultiEffect(
                    new ParticleEffect() {{
                        line = true;
                        lenFrom = 5;
                        lenTo = 3;
                        strokeFrom = 20;
                        strokeTo = 0;
                        particles = 12;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = EIPal.greenLaserBack;
                        colorTo = EIPal.greenLaserBack;
                    }},
                    new WaveEffect() {{
                        sizeFrom = 0;
                        sizeTo = 8;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = EIPal.greenLaserFront;
                        colorTo = EIPal.greenLaserBack;
                    }}
                );
                pierceBuilding = true;
                collidesAir = false;

                status = StatusEffects.slow;
                statusDuration = 300;
                width = height = 12f;
                lifetime = 25f;
                pierceCap = 10;

                frontColor = EIPal.greenLaserFront;
                backColor = EIPal.greenLaserBack;
                hitColor = EIPal.greenLaserBack;
                lightColor = EIPal.greenLaserBack;
                trailColor = EIPal.greenLaserBack;
                trailWidth = 6;
                trailLength = 20;
                hitEffect = new ParticleEffect(){{
                    line = true;
                    strokeFrom = 4;
                    strokeTo = 3;
                    colorFrom = EIPal.greenLaserBack;
                    colorTo = EIPal.greenLaserFront;
                    particles = 8;
                    lifetime = 50f;
                }};

                fragBullets = 4;
                fragBullet = new LaserBulletType(){{
                    collidesAir = false;

                    damage = 135;
                    pierceDamageFactor = 0.67f;
                    length = 85;
                    statusDuration = 300;
                }};
            }};
        }};
        enforcer = new PowerTurret("enforcer"){{
            requirements(Category.turret, with(copper, 270, lead, 210, silicon, 170, titanium, 120, thorium, 90));
            consumePower(7.5f);

            moveWhileCharging = false;

            health = 720;
            size = 3;
            reload = 105f;
            shoot.firstShotDelay = 60f;
            range = 200;
            recoil = 2.7f;

            chargeSound = Sounds.chargeVela;
            shootSound = Sounds.shootBeamPlasma;

            shootType = new EmpBulletType(){{
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
                frontColor = EIPal.greenLaserFront;
                backColor = EIPal.limeLaserBack;
                lightColor = EIPal.limeLaserBack;
                hitColor = EIPal.limeLaserBack;
                hitEffect = despawnEffect = EIFx.cavernFx;
                chargeEffect = new MultiEffect(
                    new WaveEffect(){{
                        sizeFrom = 0;
                        sizeTo = 30;
                        lifetime = 30f;
                        colorFrom = Color.valueOf("84f491");
                        colorTo = Color.valueOf("84f491");
                    }},
                    new WaveEffect(){{
                        startDelay(15f);
                        sizeFrom = 0;
                        sizeTo = 15;
                        lifetime = 30f;
                        colorFrom = Color.valueOf("3bf550");
                        colorTo = Color.valueOf("3bf550");
                    }},
                    new WaveEffect(){{
                        sprite = "circle-bullet";
                        sizeFrom = 0;
                        sizeTo = 15;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("84f491");
                        colorTo = Color.valueOf("84f491");
                    }},
                    new WaveEffect(){{
                        sprite = "circle-bullet";
                        sizeFrom = 0;
                        sizeTo = 9;
                        lifetime = shoot.firstShotDelay;
                        colorFrom = Color.valueOf("3bf550");
                        colorTo = Color.valueOf("3bf550");
                    }}
                );

                fragBullets = 1;
                fragOffsetMin = fragOffsetMax = 0f;
                fragBullet = new BasicBulletType(0f, 0f, "circle-bullet"){{
                    showStats = false;
                    collides = false;

                    width = height = 15;
                    shrinkX = shrinkY = 0;
                    rotationOffset = 0;
                    spin = 0f;
                    lifetime = 30;
                    lightning = 3;
                    lightningLength = 5;
                    lightningDamage = 5f;

                    backColor = Color.valueOf("3bf550");
                    frontColor = EIPal.greenLaserFront;
                    lightColor = EIPal.limeLaserBack;
                }};
            }};
        }};
        renoit = new LiquidTurret("renoit"){{
            requirements(Category.turret, with(lead, 420, metaglass, 330, titanium, 270, thorium, 140, plastanium, 60));

            health = 1725;
            shootX = 0f;
            size = 4;
            reload = 3f;
            velocityRnd = 0.1f;
            inaccuracy = 4f;
            recoil = 1f;
            shootCone = 45f;
            liquidCapacity = 120f;
            range = 240f;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
            shoot = new ShootAlternate(){{
                shots = 4;
                shotDelay = 0f;
                barrels = 2;
                spread = 7.5f;
            }};

            shootEffect = Fx.shootLiquid;

            ammo(
                water, new LiquidBulletType(water){{
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
                slag,  new LiquidBulletType(slag){{
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
                cryofluid, new LiquidBulletType(cryofluid){{
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
                oil, new LiquidBulletType(oil){{
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
                reurium, new LiquidBulletType(reurium){{
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
        }};
        cavern = new PowerTurret("cavern"){{
            requirements(Category.turret, with(lead, 570, silicon, 490, titanium, 470, plastanium, 380, phaseFabric, 350, stariumAlloy, 220));
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
                timeIncrease = 1f;
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
                        colorTo = EIPal.greenLaserFront;
                        length = 24;
                        baseLength = 24;
                        lifetime = 70f;
                        particles = 9;
                    }},
                    new WaveEffect(){{
                        sprite = "circle-bullet";
                        frontColor = EIPal.greenLaserFront;
                        backColor = EIPal.limeLaserBack;
                        sizeFrom = 0;
                        sizeTo = 7.5f;
                        lifetime = 75;
                    }}
                );
                lightOpacity = 0.7f;
                frontColor = EIPal.greenLaserFront;
                backColor = lightColor = hitColor = EIPal.limeLaserBack;
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
                        timeIncrease = 1f;
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
                    frontColor = EIPal.greenLaserFront;
                    backColor = lightColor = hitColor = EIPal.limeLaserBack;
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
                            colorTo = EIPal.greenLaserFront;
                        }}
                    );
                }};
            }};
        }};
        hexagon = new ItemTurret("hexagon"){{
            requirements(Category.turret, with(copper, 320, lead, 280, silicon, 290, titanium, 95, thorium, 110, plastanium, 90, surgeAlloy, 65));
            consumeCoolant(0.5f);
            consumePower(2.5f);

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

            ammo(
                surgeAlloy, new ArtilleryBulletType(5f, 42){{
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

                    frontColor = Pal.surgeAmmoFront;
                    backColor = Pal.surgeAmmoBack;
                }},
                stariumAlloy, new ArtilleryBulletType(5f, 60){{
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

                    frontColor = EIPal.stariumAlloyFront;
                    backColor = EIPal.stariumAlloyBack;

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

                        frontColor = EIPal.stariumAlloyFront;
                        backColor = EIPal.stariumAlloyBack;
                    }};
                }}
            );

            limitRange(0f);
        }};
        underglow = new PowerTurret("underglow"){{
            requirements(Category.turret, with(copper, 1250, graphite, 780, silicon, 620, thorium, 640, plastanium, 430, phaseFabric, 210, stariumAlloy, 175));
            consumeCoolant(0.5f);
            consumePower(20);

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

            shootType = new EmpBulletType(){{
                despawnHit = true;

                damage = 40;
                splashDamage = 50;
                radius = splashDamageRadius = 120;
                speed = 3;
                lifetime = 70;
                timeDuration = shrinkY = 0;
                width = height = 14;

                sprite = "circle-bullet";
                trailWidth = 7;
                trailLength = 15;
                backColor = trailColor = hitColor = Color.valueOf("eb8778");

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

        industrialGroundFactory = new UnitFactory("industrial-ground-factory"){{
            requirements(Category.units, with(copper, 90, silicon, 70, graphite, 50));
            consumePower(1.25f);

            size = 3;

            plans = Seq.with(
                new UnitPlan(agrid, 900f, with(silicon, 30, titanium, 10)),
                new UnitPlan(requer, 600f, with(silicon, 25, graphite, 20))
            );
        }};
        industrialAirFactory = new UnitFactory("industrial-air-factory"){{
            requirements(Category.units, with(copper, 90, silicon, 70, graphite, 50));
            consumePower(1.25f);

            size = 3;

            plans = Seq.with(
                new UnitPlan(pygmy, 900f, with(silicon, 25, graphite, 10)),
                new UnitPlan(luma, 1200f, with(silicon, 10, titanium, 30)),
                new UnitPlan(creo, 1500f, with(silicon, 25, metaglass, 20))
            );
        }};
        starruneReconstructor = new Reconstructor("starrune-reconstructor"){{
            requirements(Category.units, with(copper, 200, lead, 120, silicon, 90, graphite, 70));
            consumeItems(with(silicon, 40, graphite, 40, titanium, 20));
            consumePower(3f);

            size = 3;
            constructTime = 60f * 10f;

            upgrades.addAll(
                new UnitType[]{agrid, xerad},
                new UnitType[]{requer, convoy},
                new UnitType[]{pygmy, schaus},
                new UnitType[]{luma, vera},
                new UnitType[]{UnitTypes.mono, centurion}
            );
        }};
        eraniteReconstructor = new Reconstructor("eranite-reconstructor"){{
            requirements(Category.units, with(lead, 650, silicon, 450, titanium, 350, thorium, 650, starium, 250));
            consumeItems(with(silicon, 200, titanium, 120, metaglass, 100, starium, 20));
            consumePower(6f);

            size = 5;
            constructTime = 60f * 25f;

            upgrades.addAll(
                new UnitType[]{xerad, escapade},
                new UnitType[]{schaus, ageronia},
                new UnitType[]{vera, kora},
                new UnitType[]{centurion, alturion}
            );
        }};
        ultraReconstructor = new Reconstructor("ultra-reconstructor"){{
            requirements(Category.units, with(lead, 2000, silicon, 1000, titanium, 2000, thorium, 750, plastanium, 450, enrichedPeridotium, 600, starium, 400));
            consumeItems(with(silicon, 850, titanium, 750, plastanium, 650, starium, 550));
            consumeLiquid(cryofluid, 1f);
            consumePower(13f);

            size = 9;
            constructTime = 60f * 100f;

            upgrades.addAll(
                new UnitType[]{escapade, natorin},
                new UnitType[]{kora, astra}
            );
        }};
        terraReconstructor = new Reconstructor("terra-reconstructor"){{
            requirements(Category.units, with(lead, 4000, silicon, 3000, thorium, 1000, plastanium, 600, phaseFabric, 600, stariumAlloy, 475));
            consumeItems(with(silicon, 1200, plastanium, 750, stariumAlloy, 400, enrichedPeridotium, 270));
            consumeLiquid(liquidOxygen, 3f);
            consumePower(25f);

            constructTime = 60f * 250f;
            size = 11;

            upgrades.addAll(
                new UnitType[]{natorin, terrand},
                new UnitType[]{astra, brilliance}
            );
        }};
    }
}