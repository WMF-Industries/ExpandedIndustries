package ExpandedIndustries.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.EnumSet;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Layer;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static ExpandedIndustries.content.EIUnits.*;
import static mindustry.Vars.*;
import static mindustry.content.Fx.none;
import static mindustry.type.ItemStack.with;

public class EIBlocks {

    public static Block
            //environment
            flower, grassWater, orePeridotium, oreStarium, oreNeorium, oreTeranite, liquidReurium,
    //distribution
    stariumConveyor, starlightJunction, massStariumConveyor, titaniumBridge, stariumBridge,
            alloyBridge, titaniumBridgeConduit, stariumBridgeConduit, stariumConduit,
    //storage
    crate,
    //production
    cryofluidPlant, cryofluidStirrer, oxygenLiquifier, coalLiquifier, oilCrystaliser,
            electricDrill, precisionDrill, hammerDrill, siliconFabricator,
            lumiumSmelter, metaglassFabricator, peridotiumSynthesizer, stariumSynthesizer,
            stariumRefiner, graphiteCompressor, peridotiumEnhancer, scrapper, plastaniumCondenser,
            freezer, oilPurifier, heavyOilRefinery, fuelAssembler,
    //power
    steamTurbine, peridotiumGenerator, peridotiumReactor, lumiumReactor,
    //logic
    controllerProcessor, armProcessor, threadripperProcessor,
    //other
    coreFrag, coreExtensio, microPad, planetaryMender, planetaryOverdrive, hardenedUnloader, advancedUnloader,
    //defense
    stariumWall, largeStariumWall, graphiteWall, largeGraphiteWall, anado, deuse,
            exagonArtillery, slowRay, fastRay, piercer, enforcer, renoit,
    //factories & recons
    groundFactory, airFactory, starruneReconstructor, eraniteReconstructor, ultraReconstructor,
            terraReconstructor,
    //overkill content
    overkillAssembler;

    public static void load() {
        grassWater = new ShallowLiquid("grass-water") {{
            speedMultiplier = 0.7f;
            variants = 3;
            liquidDrop = Liquids.water;
            liquidMultiplier = 0.75f;
            cacheLayer = CacheLayer.water;
            status = StatusEffects.wet;
            statusDuration = 90f;
            albedo = 0.3f;
            supportsOverlay = true;
        }};
        flower = new OverlayFloor("flowers") {{
            variants = 3;
            attributes.set(Attribute.water, 0.2f);
        }};
        orePeridotium = new OreBlock("peridotium-ore", EIItems.peridotium) {{
            oreDefault = true;
            oreThreshold = 0.864f;
            oreScale = 24.904762f;
        }};
        oreStarium = new OreBlock("starium-ore", EIItems.starium) {{
            oreDefault = true;
            oreThreshold = 0.912f;
            oreScale = 25.380953f;
        }};
        liquidReurium = new Floor("liquid-reurium"){{
            speedMultiplier = 0.05f;
            variants = 0;
            liquidDrop = EILiquids.reurium;
            liquidMultiplier = 0.35f;
            isLiquid = true;
            status = EIStatusEffects.sticky;
            statusDuration = 900f;
            drownTime = 600f;
            albedo = 0.9f;
            supportsOverlay = false;
            cacheLayer = CacheLayer.tar;
        }};
        stariumConveyor = new Conveyor("starium-conveyor") {{
            requirements(Category.distribution, with(EIItems.starium, 2, Items.titanium, 1, Items.copper, 1));
            health = 100;
            speed = 0.15f;
            displayedSpeed = 21f;
        }};
        massStariumConveyor = new StackConveyor("starium-alloy-conveyor") {{
            requirements(Category.distribution, with(EIItems.starium, 3, EIItems.stariumAlloy, 1, Items.silicon, 1));
            health = 120;
            speed = 4f / 60f;
            itemCapacity = 15;
        }};
        starlightJunction = new Junction("starlight-junction") {{
            requirements(Category.distribution, with(EIItems.starium, 3, Items.titanium, 2, Items.copper, 2));
            speed = 22.5f;
            capacity = 8;
            health = 80;
        }};
        titaniumBridge = new BufferedItemBridge("titanium-bridge") {{
            requirements(Category.distribution, with(Items.copper, 8, Items.lead, 6, Items.titanium, 2));
            fadeIn = moveArrows = true;
            range = 7;
            speed = 111f;
            arrowSpacing = 6f;
            bufferCapacity = 22;
        }};
        stariumBridge = new BufferedItemBridge("starium-bridge") {{
            requirements(Category.distribution, with(Items.copper, 10, Items.titanium, 4, EIItems.starium, 4));
            fadeIn = moveArrows = true;
            range = 10;
            speed = 148f;
            arrowSpacing = 6f;
            bufferCapacity = 30;
        }};
        alloyBridge = new ItemBridge("starium-alloy-bridge") {{
            requirements(Category.distribution, with(Items.copper, 12, Items.silicon, 6, EIItems.stariumAlloy, 10));
            range = 20;
            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
            hasPower = true;
            pulse = true;
            envEnabled |= Env.space;
            consumePower(0.3f);
        }};
        stariumConduit = new Conduit("starium-conduit") {{
            requirements(Category.liquid, with(EIItems.starium, 4, Items.titanium, 2, Items.metaglass, 1));
            liquidCapacity = 24f;
            liquidPressure = 1.6f;
            health = 110;
        }};
        titaniumBridgeConduit = new LiquidBridge("titanium-bridge-conduit") {{
            requirements(Category.liquid, with(Items.titanium, 4, Items.metaglass, 8));
            fadeIn = moveArrows = false;
            arrowSpacing = 6f;
            range = 7;
            hasPower = false;
        }};
        stariumBridgeConduit = new LiquidBridge("starium-bridge-conduit") {{
            requirements(Category.liquid, with(EIItems.starium, 4, Items.metaglass, 8));
            fadeIn = moveArrows = false;
            arrowSpacing = 6f;
            range = 10;
            hasPower = false;
        }};
        crate = new StorageBlock("crate") {{
            requirements(Category.effect, with(Items.copper, 50, Items.lead, 50));
            size = 1;
            itemCapacity = 75;
            health = 70;
        }};
        cryofluidPlant = new GenericCrafter("cryofluid-plant") {{
            requirements(Category.crafting, with(Items.copper, 230, Items.silicon, 110, Items.titanium, 60, EIItems.starium, 20));
            outputLiquid = new LiquidStack(Liquids.cryofluid, 1f);
            size = 4;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid) {{
                drawLiquidLight = true;
            }}, new DrawDefault(), new DrawRegion("-top"));
            liquidCapacity = 120f;
            craftTime = 90;

            consumePower(2f);
            consumeItem(Items.titanium, 2);
            consumeLiquid(Liquids.water, 1f);
        }};
        cryofluidStirrer = new GenericCrafter("cryofluid-stirrer") {{
            requirements(Category.crafting, with(Items.copper, 160, Items.silicon, 70, Items.titanium, 80));
            outputLiquid = new LiquidStack(Liquids.cryofluid, 30f / 60f);
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid) {{
                drawLiquidLight = true;
            }}, new DrawDefault(), new DrawRegion("-top"));
            liquidCapacity = 60f;
            craftTime = 90;

            consumePower(1.5f);
            consumeItem(Items.titanium, 1);
            consumeLiquid(Liquids.water, 30f / 60f);
        }};
        oxygenLiquifier = new GenericCrafter("oxygen-liquifier") {{
            requirements(Category.crafting, with(Items.copper, 170, Items.silicon, 60, Items.titanium, 70, EIItems.starium, 10));
            outputLiquid = new LiquidStack(EILiquids.lox, 15f / 60f);
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(EILiquids.lox) {{
                drawLiquidLight = true;
            }}, new DrawDefault(), new DrawRegion("-top"));
            liquidCapacity = 30f;
            craftTime = 240;

            consumePower(1.75f);
            consumeItem(EIItems.ice);
            consumeLiquid(Liquids.water, 7.5f / 60f);
        }};
        coalLiquifier = new GenericCrafter("coal-liquifier") {{
            requirements(Category.crafting, with(Items.copper, 170, Items.silicon, 60, Items.titanium, 70, EIItems.starium, 10));
            outputLiquid = new LiquidStack(Liquids.oil, 6f / 60f);
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.oil) {{
                drawLiquidLight = true;
            }}, new DrawDefault(), new DrawRegion("-top"));
            liquidCapacity = 30f;
            craftTime = 15f;

            consumePower(2f);
            consumeItem(Items.coal);
            consumeLiquid(Liquids.water, 12f / 60f);
        }};
        oilCrystaliser = new GenericCrafter("oil-crystaliser") {{
            requirements(Category.crafting, with(Items.copper, 70, Items.titanium, 20, Items.graphite, 40, EIItems.starium, 30));
            craftEffect = Fx.coalSmeltsmoke;
            outputItem = new ItemStack(Items.coal, 5);
            craftTime = 45f;
            size = 3;
            hasPower = hasItems = hasLiquids = true;
            rotateDraw = false;

            consumeLiquid(Liquids.oil, 0.5f);
            consumePower(1.2f);
        }};
        electricDrill = new Drill("electric-drill") {{
            requirements(Category.production, with(Items.graphite, 50, Items.titanium, 20, Items.silicon, 10));
            tier = 4;
            drillTime = 160;
            size = 2;

            consumePower(1.25f);
            hasLiquids = false;
            hasPower = hasItems = true;
        }};
        precisionDrill = new Drill("precision-drill") {{
            requirements(Category.production, with(Items.graphite, 165, Items.silicon, 80, Items.titanium, 75, Items.thorium, 65));
            drillTime = 120;
            size = 4;
            drawRim = true;
            tier = 5;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.03f;
            drillEffect = Fx.mineHuge;
            rotateSpeed = 6f;
            warmupSpeed = 0.01f;
            itemCapacity = 20;

            consumeLiquid(Liquids.water, 0.4f);
        }};
        hammerDrill = new Drill("hammer-drill") {{
            requirements(Category.production, with(Items.lead, 265, Items.silicon, 160, Items.titanium, 70, Items.thorium, 95));
            drillTime = 250;
            size = 5;
            drawRim = true;
            hasPower = true;
            tier = 5;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.03f;
            drillEffect = Fx.mineHuge;
            rotateSpeed = 6f;
            warmupSpeed = 0.01f;
            itemCapacity = 20;

            liquidBoostIntensity = 2f;

            consumePower(5f);
            consumeLiquid(Liquids.cryofluid, 0.1f).boost();
        }};
        siliconFabricator = new AttributeCrafter("silicon-fabricator") {{
            requirements(Category.crafting, with(Items.titanium, 170, Items.metaglass, 90, Items.plastanium, 55, Items.silicon, 90, Items.phaseFabric, 10));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.silicon, 16);
            craftTime = 90f;
            size = 4;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 30;
            boostScale = 0.15f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(Items.coal, 6, Items.sand, 8, Items.blastCompound, 1));
            consumePower(5f);
        }};
        lumiumSmelter = new GenericCrafter("lumium-smelter") {{
            requirements(Category.crafting, with(Items.copper, 165, Items.silicon, 80, Items.lead, 80, Items.thorium, 30, EIItems.starium, 25));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(EIItems.lumium, 1);
            craftTime = 90f;
            size = 4;
            hasPower = true;
            itemCapacity = 40;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(3.5f);
            consumeItems(with(Items.thorium, 10, EIItems.starium, 10));
        }};
        metaglassFabricator = new AttributeCrafter("metaglass-fabricator") {{
            requirements(Category.crafting, with(Items.copper, 125, Items.graphite, 90, Items.titanium, 75, EIItems.starium, 60));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Items.metaglass, 10);
            craftTime = 90f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 40;
            boostScale = 0.15f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(Items.sand, 10, Items.lead, 10, Items.pyratite, 1));
            consumePower(1.5f);
        }};
        peridotiumSynthesizer = new GenericCrafter("peridotium-synthesizer") {{
            requirements(Category.crafting, with(Items.copper, 260, Items.silicon, 90, Items.titanium, 80, Items.thorium, 50));
            craftEffect = Fx.blockCrash;
            outputItem = new ItemStack(EIItems.peridotium, 1);
            craftTime = 120f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(Items.thorium, 10));
            consumePower(1.2f);
        }};
        stariumSynthesizer = new GenericCrafter("starium-synthesizer") {{
            requirements(Category.crafting, with(Items.copper, 260, Items.silicon, 90, Items.titanium, 80, Items.thorium, 50));
            craftEffect = Fx.blockCrash;
            outputItem = new ItemStack(EIItems.starium, 5);
            craftTime = 120f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(Items.titanium, 10, Items.silicon, 5));
            consumePower(1.2f);
        }};
        stariumRefiner = new GenericCrafter("starium-refiner") {{
            requirements(Category.crafting, with(Items.copper, 265, Items.silicon, 130, Items.lead, 95, Items.thorium, 30, Items.surgeAlloy, 15, EIItems.starium, 25));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(EIItems.stariumAlloy, 1);
            craftTime = 45f;
            size = 3;
            hasPower = true;
            itemCapacity = 40;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(0.85f);
            consumeItems(with(Items.surgeAlloy, 1, EIItems.starium, 15));
        }};
        graphiteCompressor = new GenericCrafter("graphite-compressor") {{
            requirements(Category.crafting, with(Items.copper, 235, Items.graphite, 130, Items.titanium, 70, Items.silicon, 50, EIItems.starium, 25));

            craftEffect = Fx.steam;
            outputItem = new ItemStack(Items.graphite, 7);
            craftTime = 45f;
            itemCapacity = 40;
            liquidCapacity = 160;
            size = 4;
            hasItems = true;
            hasLiquids = true;
            hasPower = false;

            consumeItem(Items.coal, 5);
            consumeLiquid(EILiquids.steam, 0.125f);
        }};
        peridotiumEnhancer = new GenericCrafter("peridotium-enhancer") {{
            requirements(Category.crafting, with(Items.copper, 130, Items.graphite, 60, Items.silicon, 40, Items.titanium, 25, EIItems.starium, 15));

            craftEffect = Fx.pulverizeMedium;
            outputItem = new ItemStack(EIItems.enhancedPeridotium, 1);
            craftTime = 180f;
            itemCapacity = 40;
            size = 2;
            hasItems = true;

            consumePower(0.5f);
            consumeItem(EIItems.peridotium, 3);
        }};
        scrapper = new GenericCrafter("scrapper") {{
            requirements(Category.crafting, with(Items.copper, 130, Items.lead, 60, Items.graphite, 20, Items.silicon, 5));

            craftEffect = Fx.pulverizeMedium;
            outputItem = new ItemStack(Items.scrap, 1);
            craftTime = 30f;
            itemCapacity = 15;
            size = 2;
            hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 2.25f;
            }}, new DrawRegion("-top"));
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.025f;

            consumePower(1f);
            consumeItems(with(Items.copper, 2, Items.sand, 3));
        }};
        plastaniumCondenser = new GenericCrafter("plastanium-condenser") {{
            requirements(Category.crafting, with(Items.silicon, 110, Items.lead, 115, Items.graphite, 70, Items.titanium, 80, Items.plastanium, 40));
            hasItems = true;
            liquidCapacity = 60f;
            craftTime = 45f;
            outputItem = new ItemStack(Items.plastanium, 2);
            size = 3;
            health = 420;
            hasPower = hasLiquids = true;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());

            consumeLiquid(Liquids.oil, 0.5f);
            consumePower(3.5f);
            consumeItem(Items.titanium, 3);
        }};
        freezer = new GenericCrafter("freezer") {{
            requirements(Category.crafting, with(Items.lead, 75, Items.metaglass, 30, Items.silicon, 15));

            craftEffect = Fx.pulverizeSmall;
            outputItem = new ItemStack(EIItems.ice, 1);
            craftTime = 75f;
            size = 2;
            hasItems = true;

            consumeLiquid(Liquids.water, 2);
            consumePower(0.5f);
        }};
        oilPurifier = new GenericCrafter("oil-purifier"){{
            requirements(Category.crafting, with(Items.copper, 170, Items.metaglass, 70, Items.silicon, 60, Items.titanium, 60, Items.thorium, 30));
            size = 3;

            researchCostMultiplier = 1.2f;
            craftTime = 10f;
            rotate = true;
            invertFlip = true;

            liquidCapacity = 45f;

            consumeLiquid(Liquids.oil, 15f / 60f);
            consumePower(3f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 3.35f;
            }}, new DrawRegion("-top"));

            ambientSound = Sounds.spark;
            ambientSoundVolume = 0.08f;

            regionRotated1 = 3;
            outputLiquids = LiquidStack.with(EILiquids.heavyOil, 4f / 60, EILiquids.lightOil, 11f / 60);
            liquidOutputDirections = new int[]{1, 3};
        }};
        heavyOilRefinery = new GenericCrafter("heavy-oil-refinery"){
            {
                requirements(Category.crafting, with(Items.lead, 270, Items.metaglass, 90, Items.silicon, 70, Items.titanium, 30, Items.thorium, 45));
                size = 3;

                researchCostMultiplier = 1.2f;
                craftTime = 90f;

                liquidCapacity = 45f;

                consumeLiquid(EILiquids.heavyOil, 12f / 60f);
                consumePower(2f);

                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(EILiquids.lightOil), new DrawRegion("-rotator") {{
                    spinSprite = true;
                    rotateSpeed = 1.7f;
                }}, new DrawDefault());

                ambientSound = Sounds.spark;
                ambientSoundVolume = 0.08f;

                regionRotated1 = 3;
                outputLiquids = LiquidStack.with(EILiquids.lightOil, 8f / 60);
                outputItem = new ItemStack(Items.scrap, 1);
            }};
        fuelAssembler = new GenericCrafter("fuel-assembler"){{
            requirements(Category.crafting, with(Items.lead, 310, Items.metaglass, 110, Items.silicon, 90, Items.titanium, 35, Items.thorium, 45));
            size = 4;

            researchCostMultiplier = 1.2f;
            craftTime = 30f;

            liquidCapacity = 20f;

            consumeLiquid(EILiquids.lightOil, 7.5f / 60f);
            consumePower(2.5f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(EILiquids.lightOil), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 2.1f;
            }}, new DrawDefault());

            ambientSound = Sounds.spark;
            ambientSoundVolume = 0.08f;
            outputItem = new ItemStack(EIItems.solidFuel, 1);
        }};
        steamTurbine = new ConsumeGenerator("steam-turbine") {{
            requirements(Category.power, with(Items.copper, 370, Items.lead, 220, Items.silicon, 240, Items.graphite, 130, Items.titanium, 170));
            powerProduction = 15f;
            consumeLiquid(Liquids.water, 15f / 60f);
            itemDuration = 180;
            consume(new ConsumeItemFlammable(0.75f));
            explodeOnFull = false;
            outputLiquid = new LiquidStack(EILiquids.steam, 15f / 60f);
            hasLiquids = hasItems = hasPower = true;
            itemCapacity = 15;
            size = 3;
            health = 925;
            generateEffect = Fx.generatespark;

            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.06f;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawWarmupRegion(),
                    new DrawRegion("-turbine") {{
                        rotateSpeed = 2f;
                    }},
                    new DrawLiquidRegion(Liquids.water),
                    new DrawLiquidRegion(EILiquids.steam)
            );
        }};
        peridotiumGenerator = new ConsumeGenerator("peridotium-generator") {{
            requirements(Category.power, with(Items.lead, 630, Items.silicon, 170, Items.phaseFabric, 100, Items.plastanium, 180, Items.thorium, 80, EIItems.enhancedPeridotium, 30));
            size = 5;
            health = 2420;
            powerProduction = 35f;
            itemDuration = 60 * 14f;
            envEnabled = Env.any;
            generateEffect = Fx.generatespark;

            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
            consume(new ConsumeItemRadioactive());
            consumeLiquid(Liquids.cryofluid, 0.05f);
        }};
        peridotiumReactor = new NuclearReactor("peridotium-reactor"){{
            requirements(Category.power, with(Items.copper, 650, Items.graphite, 340, Items.titanium, 340, Items.silicon, 300, Items.thorium, 200, EIItems.starium, 175));
            size = 4;
            health = 1740;
            powerProduction = 80;
            itemCapacity = 20;
            itemDuration = 60 * 7f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            heating = 0.02f;
            explosionRadius = 25 * tilesize;
            explosionDamage = health * itemCapacity;

            fuelItem = EIItems.peridotium;
            consumeItem(EIItems.peridotium);
            consumeLiquid(Liquids.water, 0.5f).update(false);
        }};
        lumiumReactor = new ImpactReactor("lumium-reactor") {{
            requirements(Category.power, with(Items.lead, 1250, Items.silicon, 750, Items.titanium, 700, Items.surgeAlloy, 650, Items.plastanium, 450, EIItems.stariumAlloy, 250));
            size = 5;
            health = 4500;
            powerProduction = 652.5f;
            itemDuration = 240f;
            ambientSound = Sounds.pulse;
            ambientSoundVolume = 0.5f;

            consumePower(52.5f);
            consumeItem(EIItems.lumium);
            consumeLiquid(EILiquids.lox, 1);
        }};
        controllerProcessor = new LogicBlock("controller-processor") {{
            requirements(Category.logic, with(Items.copper, 120, Items.lead, 160, Items.silicon, 90));

            instructionsPerTick = 6;
            consumePower(0.25f);
            hasPower = true;

            size = 1;
            range = 100;
        }};
        armProcessor = new LogicBlock("arm-processor") {{
            requirements(Category.logic, with(Items.lead, 430, Items.silicon, 170, Items.graphite, 120, Items.thorium, 90));

            instructionsPerTick = 12;
            consumePower(0.5f);
            hasPower = true;

            size = 2;
            range = 225;
        }};
        threadripperProcessor = new LogicBlock("threadripper-processor") {{
            requirements(Category.logic, with(Items.lead, 650, Items.silicon, 210, Items.thorium, 95, Items.surgeAlloy, 70));

            consumeLiquid(Liquids.cryofluid, 0.15f);
            hasLiquids = true;

            instructionsPerTick = 50;
            consumePower(2.25f);
            hasPower = true;

            range = 550;
            size = 3;
        }};
        coreFrag = new CoreBlock("core-frag") {{
            requirements(Category.effect, with(Items.copper, 250, Items.lead, 125));

            isFirstTier = true;
            unitType = piece;
            health = 600;
            itemCapacity = 1000;
            size = 2;

            unitCapModifier = 4;
        }};
        coreExtensio = new CoreBlock("core-extensio") {{
            requirements(Category.effect, with(Items.copper, 18000, Items.lead, 18000, Items.silicon, 10500, Items.thorium, 7000, Items.surgeAlloy, 1025));

            unitType = UnitTypes.mega;
            health = 12000;
            itemCapacity = 35000;
            size = 6;

            unitCapModifier = 48;
        }};
        microPad = new LaunchPad("micro-pad") {{
            requirements(Category.effect, BuildVisibility.campaignOnly, with(Items.copper, 175, Items.silicon, 70, Items.lead, 100, Items.titanium, 75));
            size = 2;
            itemCapacity = 50;
            launchTime = 750f;
            hasPower = true;
            consumePower(2f);
        }};
        planetaryMender = new MendProjector("planetary-mender"){{
            requirements(Category.effect, with(Items.copper, 9425, Items.lead, 8750, Items.silicon, 5230, Items.titanium, 4300, Items.thorium, 4230, Items.surgeAlloy, 1235));
            size = 5;
            reload = 450;
            range = 100 * 100 * tilesize;
            healPercent = 25f;
            phaseBoost = 25f;
            hasPower = true;
            consumePower(160f);
            consumeItem(Items.surgeAlloy, 3).boost();
        }};
        planetaryOverdrive = new OverdriveProjector("planetary-overdrive"){{
            requirements(Category.effect, with(Items.copper, 8425, Items.lead, 6350, Items.silicon, 5430, Items.titanium, 4100, Items.thorium, 3950, EIItems.stariumAlloy, 535, EIItems.enhancedPeridotium, 430));
            size = 5;
            range = 100 * 100 * tilesize;
            hasPower = true;
            speedBoost = 4f;
            hasBoost = false;
            useTime = 60f;
            consumePower(240f);
            consumeItems(with(Items.phaseFabric, 1, Items.silicon, 2));
        }};
        hardenedUnloader = new Unloader("hardened-unloader"){{
            requirements(Category.effect, with(Items.titanium, 90, Items.silicon, 55));
            speed = 60f / 20f;
            group = BlockGroup.transportation;
            health = 120;
        }};
        advancedUnloader = new Unloader("advanced-unloader"){{
            requirements(Category.effect, with(Items.titanium, 225, Items.silicon, 130, Items.graphite, 65));
            speed = 60f / 40f;
            group = BlockGroup.transportation;
            health = 180;
            conductivePower = true;
            hasPower = true;
        }};
        stariumWall = new Wall("starium-wall") {{
            requirements(Category.defense, with(EIItems.stariumAlloy, 6, Items.plastanium, 2));
            health = 1950;
            insulated = true;
            flashHit = true;
            chanceDeflect = 25;
            absorbLasers = true;
            schematicPriority = 10;
            envDisabled |= Env.scorching;
        }};
        largeStariumWall = new Wall("large-starium-wall") {{
            requirements(Category.defense, ItemStack.mult(stariumWall.requirements, 4));
            health = 1950 * 4;
            size = 2;
            insulated = true;
            flashHit = true;
            chanceDeflect = 25;
            absorbLasers = true;
            schematicPriority = 10;
            envDisabled |= Env.scorching;
        }};
        graphiteWall = new Wall("graphite-wall") {{
            requirements(Category.defense, with(Items.graphite, 6));
            health = 400;
            envDisabled |= Env.scorching;
        }};
        largeGraphiteWall = new Wall("large-graphite-wall") {{
            requirements(Category.defense, ItemStack.mult(graphiteWall.requirements, 4));
            health = 400 * 4;
            size = 2;
            envDisabled |= Env.scorching;
        }};
        anado = new ItemTurret("anado") {{
            requirements(Category.turret, with(Items.copper, 45, Items.lead, 20));
            ammo(
                    Items.scrap, new BasicBulletType(2f, 8) {{
                        width = 6f;
                        height = 8f;
                        lifetime = 60f;
                        fragBullets = 4;
                        reloadMultiplier = 0.66f;
                        fragBullet = new BasicBulletType(1f, 5) {{
                            splashDamage = 3f;
                            splashDamageRadius = 40f;
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
                        fragBullets = 3;
                        fragBullet = new BasicBulletType(1f, 4) {{
                            splashDamage = 2f;
                            splashDamageRadius = 40f;
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
                        fragBullets = 6;
                        fragBullet = new BasicBulletType(1f, 7) {{
                            splashDamage = 6f;
                            splashDamageRadius = 40f;
                            height = 5f;
                            width = 3f;
                            lifetime = 30f;
                        }};
                    }}
            );

            reload = 20f;
            range = 120;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 320;
            inaccuracy = 1f;
            rotateSpeed = 7.2f;
            coolant = consumeCoolant(0.1f);

            limitRange();
        }};
        deuse = new ItemTurret("deuse") {{
            requirements(Category.turret, with(Items.lead, 200, Items.copper, 100, Items.graphite, 80, Items.titanium, 50));
            ammo(
                    Items.scrap, new BasicBulletType(3f, 14) {{
                        width = 6f;
                        height = 8f;
                        lifetime = 60f;
                        fragBullets = 4;
                        reloadMultiplier = 0.66f;
                        fragBullet = new BasicBulletType(1.5f, 5) {{
                            splashDamage = 6f;
                            splashDamageRadius = 45f;
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
                        fragBullets = 3;
                        fragBullet = new BasicBulletType(1.75f, 4) {{
                            splashDamage = 7f;
                            splashDamageRadius = 50f;
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
                        fragBullets = 6;
                        fragBullet = new BasicBulletType(2.125f, 7) {{
                            splashDamage = 12f;
                            splashDamageRadius = 60f;
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
                        fragBullets = 8;
                        reloadMultiplier = 1.2f;
                        fragBullet = new BasicBulletType(2.25f, 13) {{
                            splashDamage = 16f;
                            splashDamageRadius = 60f;
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

            ammoUseEffect = Fx.casing2;
            health = 510;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(0.3f);
        }};
        exagonArtillery = new ItemTurret("exagon-altillery") {{
            requirements(Category.turret, with(Items.copper, 370, Items.silicon, 280, Items.titanium, 210, Items.plastanium, 170, Items.surgeAlloy, 120, EIItems.starium, 70));
            ammo(
                    Items.surgeAlloy, new ArtilleryBulletType(5f, 275) {{
                        knockback = 0.8f;
                        lifetime = 120f;
                        width = height = 12f;
                        buildingDamageMultiplier = 3f;
                        collidesTiles = false;
                        splashDamageRadius = 20f;
                        splashDamage = 125f;
                        ammoMultiplier = 3f;
                        lightning = 3;
                        lightningLength = 15;
                        lightningDamage = 15f;
                    }},
                    EIItems.stariumAlloy, new ArtilleryBulletType(5f, 465) {{
                        knockback = 0.8f;
                        lifetime = 120f;
                        width = height = 12f;
                        collidesTiles = false;
                        buildingDamageMultiplier = 4.25f;
                        splashDamageRadius = 35f;
                        splashDamage = 200f;
                        reloadMultiplier = 1.2f;
                        ammoMultiplier = 5f;
                        lightning = 4;
                        lightningLength = 25;
                        lightningDamage = 15f;
                        fragBullets = 3;
                        fragBullet = new BasicBulletType(3.25f, 125) {{
                            lifetime = 15f;
                            width = height = 6f;
                            collidesTiles = false;
                            splashDamageRadius = 45f;
                            splashDamage = 55f;
                            lightning = 2;
                            lightningLength = 10;
                            lightningDamage = 15f;
                        }};
                    }}
            );

            targetAir = false;
            reload = 210f;
            recoil = 4.7f;
            size = 3;
            range = 600f;
            shoot.shots = 3;
            shoot.shotDelay = 3f;
            health = 920;
            shake = 2.3f;
            shootSound = Sounds.bang;
            consumePower(1.25f);
            coolant = consumeCoolant(0.5f);
            limitRange(0f);
        }};
        slowRay = new PowerTurret("slowray") {{
            requirements(Category.turret, with(Items.copper, 560, Items.lead, 510, Items.silicon, 270, Items.titanium, 350, Items.thorium, 260, EIItems.starium, 300, Items.surgeAlloy, 75));
            range = 230;
            size = 3;
            recoil = 2.7f;
            reload = 330f;
            health = 1650;
            shootSound = Sounds.plasmaboom;
            consumePower(9.5f);


            shoot.firstShotDelay = 75f;
            chargeSound = Sounds.lasercharge;
            moveWhileCharging = false;
            accurateDelay = true;
            shootType = new EmpBulletType() {
                {
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
                                startDelay(30f);
                                sizeFrom = 0;
                                sizeTo = 15;
                                lifetime = 30f;
                                colorFrom = Color.valueOf("3bf550");
                                colorTo = Color.valueOf("3bf550");
                            }},
                            new WaveEffect() {{
                                startDelay(45f);
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
                    speed = 5;
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
        }};
        fastRay = new PowerTurret("fastray") {{
            requirements(Category.turret, with(Items.copper, 1250, Items.graphite, 780, Items.silicon, 620, Items.plastanium, 430, Items.phaseFabric, 210, EIItems.stariumAlloy, 140));
            consumePower(20);
            range = 210;

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
                    status = EIStatusEffects.lockdown;
                    statusDuration = 360;
                    damage = 25;
                    speed = width = height = 0;
                    lifetime = 150;
                    collides = absorbable = hittable = false;
                    radius = 120;
                    timeDuration = 0;
                    lightning = 2;
                    lightningDamage = 8;
                    lightningLength = 4;
                    lightningLengthRand = 24;
                    lightningColor = hitColor = Color.valueOf("eb8778");
                    despawnHit = true;
                    hitEffect = despawnEffect = applyEffect = none;
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
                            }},
                            despawnEffect = hitPowerEffect = applyEffect = none
                    );
                }};
            }};
        }};
        piercer = new PowerTurret("piercer") {{
            requirements(Category.turret, with(Items.copper, 270, Items.lead, 210, Items.silicon, 190, Items.titanium, 130, Items.thorium, 75, EIItems.starium, 70));
            range = 200f;

            shoot.firstShotDelay = 40f;
            chargeSound = Sounds.lasercharge2;
            moveWhileCharging = false;
            accurateDelay = true;

            recoil = 2f;
            reload = 120f;
            shake = 2f;
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
                lifetime = 24f;
                particles = 9;
            }};
            smokeEffect = none;
            heatColor = Color.red;
            size = 3;
            health = 2000;
            targetAir = false;
            moveWhileCharging = false;
            accurateDelay = false;
            shootSound = Sounds.shotgun;
            coolant = consumeCoolant(0.2f);

            consumePower(9.5f);

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
                width = height = 12f;
                lifetime = 25f;
                pierceBuilding = true;
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
                    damage = 135;
                    length = 85;
                }};
            }};
        }};
        enforcer = new PowerTurret("enforcer") {{
            requirements(Category.turret, with(Items.copper, 270, Items.lead, 210, Items.silicon, 130, Items.titanium, 70, Items.thorium, 40));
            health = 720;
            size = 3;
            reload = 105f;
            shootSound = Sounds.plasmaboom;
            range = 200;
            recoil = 2.7f;
            consumePower(7.5f);

            shoot.firstShotDelay = 75f;
            chargeSound = Sounds.lasercharge;
            moveWhileCharging = false;
            accurateDelay = true;
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
                spin = 2f;
                scaleLife = true;
                status = StatusEffects.electrified;
                statusDuration = 450;
                lightOpacity = 0.7f;
                unitDamageScl = 1.2f;
                healPercent = 20f;
                sprite = "circle-bullet";
                damage = 75;
                lifetime = 60;
                speed = 5;
                radius = 90f;
                width = height = 15;
                shrinkX = shrinkY = 0;
                frontColor = Color.valueOf("ffffff");
                backColor = Color.valueOf("83f793");
                lightColor = Color.valueOf("83f793");
                hitColor = Color.valueOf("83f793");
                hitEffect = despawnEffect = new MultiEffect(
                        new WaveEffect() {{
                            sizeFrom = 90;
                            sizeTo = 80;
                            colorFrom = Color.valueOf("84f491");
                            colorTo = Color.valueOf("84f491");
                        }},
                        new WaveEffect() {{
                            startDelay(15);
                            sizeFrom = 70;
                            sizeTo = 80;
                            colorFrom = Color.valueOf("84f491");
                            colorTo = Color.valueOf("84f491");
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
                lightning = 1;
                lightningLength = 1;
                lightningType = new BasicBulletType(0, 0) {{
                    sprite = "circle-bullet";
                    backColor = Color.valueOf("3bf550");
                    frontColor = Color.valueOf("ffffff");
                    lightColor = Color.valueOf("83f793");
                    width = height = 15;
                    shrinkX = shrinkY = 0;
                    rotationOffset = 0;
                    spin = 0f;
                    lifetime = 30;
                    collides = false;
                    lightning = 3;
                    lightningLength = 5;
                    lightningDamage = 5f;
                }};
            }};
        }};
        renoit = new LiquidTurret("renoit"){{
            requirements(Category.turret, with(Items.metaglass, 235, Items.lead, 520, Items.titanium, 170, Items.thorium, 100, Items.silicon, 110, Items.plastanium, 70));
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
            liquidCapacity = 40f;
            shootEffect = Fx.shootLiquid;
            range = 240f;
            health = 1725;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};
        groundFactory = new UnitFactory("ground-factory"){{
            requirements(Category.units, with(Items.copper, 90, Items.titanium, 80, Items.silicon, 120));
            plans = Seq.with(
                    new UnitPlan(stormer, 60f * 10, with(Items.silicon, 30, Items.titanium, 10)),
                    new UnitPlan(breadnight, 60f * 15, with(Items.silicon, 25, Items.graphite, 20))
            );
            size = 3;
            consumePower(1.2f);
        }};
        airFactory = new UnitFactory("air-factory"){{
            requirements(Category.units, with(Items.copper, 90, Items.titanium, 80, Items.silicon, 120));
            plans = Seq.with(
                    new UnitPlan(EIUnits.pygmy, 60f * 15, with(Items.silicon, 25, Items.graphite, 10)),
                    new UnitPlan(EIUnits.centurion, 60f * 25, with(Items.silicon, 30, Items.titanium, 20, Items.lead, 15)),
                    new UnitPlan(EIUnits.SmolBoi, 60f * 10, with(Items.silicon, 30, Items.titanium, 15))
            );
            size = 3;
            consumePower(1.2f);
        }};
        starruneReconstructor = new Reconstructor("starrune-reconstructor"){{
            requirements(Category.units, with(Items.copper, 200, Items.lead, 120, Items.silicon, 90, Items.graphite, 60));

            size = 3;
            consumePower(3f);
            consumeItems(with(Items.silicon, 40, Items.graphite, 40, EIItems.starium, 10));

            constructTime = 60f * 10f;

            upgrades.addAll(
                    new UnitType[]{EIUnits.stormer, EIUnits.rusher},
                    new UnitType[]{EIUnits.pygmy, EIUnits.schaus},
                    new UnitType[]{EIUnits.SmolBoi, EIUnits.MediumBoi},
                    new UnitType[]{centurion, alturion},
                    new UnitType[]{breadnight, toastnight}
            );
        }};
        eraniteReconstructor = new Reconstructor("eranite-reconstructor"){{
            requirements(Category.units, with(Items.copper, 200, Items.lead, 120, Items.silicon, 90, Items.metaglass, 70, EIItems.starium, 125));

            size = 5;
            consumePower(3f);
            consumeItems(with(Items.silicon, 180, Items.graphite, 70, Items.titanium, 60, EIItems.starium, 50));

            constructTime = 60f * 20f;

            upgrades.addAll(
                    new UnitType[]{EIUnits.rusher, EIUnits.escapade},
                    new UnitType[]{EIUnits.schaus, EIUnits.ageronia},
                    new UnitType[]{EIUnits.MediumBoi, EIUnits.LargeBoi}
            );
        }};
        ultraReconstructor = new Reconstructor("ultra-reconstructor"){{
            requirements(Category.units, with(Items.copper, 900, Items.lead, 1200, Items.silicon, 905, Items.metaglass, 230, Items.plastanium, 475, Items.phaseFabric, 100, EIItems.starium, 435));

            size = 9;
            consumePower(3f);
            consumeItems(with(Items.silicon, 650, Items.plastanium, 700, Items.graphite, 555, EIItems.enhancedPeridotium, 75));
            consumeLiquid(Liquids.cryofluid, 1.25f);

            constructTime = 60f * 25f;

            upgrades.addAll(
                    new UnitType[]{EIUnits.escapade, EIUnits.natorin},
                    new UnitType[]{LargeBoi, PayloadBoi}
            );
        }};
        terraReconstructor = new Reconstructor("terra-reconstructor"){{
            requirements(Category.units, with(Items.copper, 1900, Items.lead, 1200, Items.silicon, 1105, Items.metaglass, 730, Items.plastanium, 675, Items.phaseFabric, 500, EIItems.starium, 435, Items.surgeAlloy, 500));

            size = 11;
            consumePower(3f);
            consumeItems(with(Items.silicon, 970, Items.plastanium, 830, Items.graphite, 655, EIItems.enhancedPeridotium, 275, EIItems.stariumAlloy, 175));
            consumeLiquid(EILiquids.lox, 3f);

            constructTime = 60f * 25f;

            upgrades.addAll(
                    new UnitType[]{EIUnits.natorin, EIUnits.terrand}
            );
        }};
        /*overkillAssembler = new UnitAssembler("overkill-assembler"){{
            requirements(Category.units, with(Items.copper, 2650, Items.titanium, 1200, Items.silicon, 1100, EIItems.starium, 900, Items.thorium, 775, Items.plastanium, 700, Items.surgeAlloy, 650, Items.phaseFabric, 450, EIItems. stariumAlloy, 335));
            size = 11;
            plans.add(
                    new AssemblerUnitPlan(starnight, 60f * 90f, PayloadStack.list(UnitTypes.oct, 1, Blocks.thoriumWallLarge, 24, Blocks.forceProjector, 4, Blocks.overdriveProjector, 2))
            );
            areaSize = 20;

            consumePower(5.75f);
            consumeLiquid(EILiquids.heavyOil, 18f / 60f);
        }};*/
    }
}