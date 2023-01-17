package ExpandedIndustries.content;

import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static ExpandedIndustries.content.EIBlocks.*;
import static ExpandedIndustries.content.EIUnits.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;

public class EITechTree {
    //this is stupid but seems to work
    public static TechNode context = null;

    public static void mergeNode(UnlockableContent p, Runnable c) {
        context = TechTree.all.find(t -> t.content == p);
        c.run();
    }

    public static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent block){
        node(block, () -> {});
    }

    public static void nodeProduce(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, new Seq<>(), children);
    }
    public static void load() {
        mergeNode(Blocks.coreShard, () -> {
            node(coreFrag, () -> {
            });
        });
        mergeNode(Blocks.coreNucleus, () -> {
            node(coreExtensio, () -> {
            });
        });
        mergeNode(Blocks.titaniumConveyor, () -> {
                    node(stariumConveyor);
        });
        mergeNode(Blocks.plastaniumConveyor, () -> {
            node(massStariumConveyor);
        });
        mergeNode(Blocks.junction, () -> {
            node(starlightJunction);
        });
        mergeNode(Blocks.itemBridge, () -> {
            node(titaniumBridge, () -> {
                node(stariumBridge, () -> {
                    node(alloyBridge);
                });
            });
        });
        mergeNode(Blocks.pulseConduit, () -> {
            node(stariumConduit);
        });
        mergeNode(Blocks.bridgeConduit, () -> {
            node(titaniumBridgeConduit, () -> {
                node(stariumBridgeConduit);
            });
        });
        mergeNode(Blocks.itemBridge, () -> {
            node(crate, () -> {
                node(microPad, Seq.with(new Objectives.SectorComplete(windsweptIslands)), () -> {
                });
            });
        });
        mergeNode(Blocks.cryofluidMixer, () -> {
            node(cryofluidStirrer, () -> {
                node(cryofluidPlant);
            });
        });
        mergeNode(Blocks.cryofluidMixer, () -> {
            node(oxygenLiquifier);
        });
        mergeNode(Blocks.coalCentrifuge, () -> {
            node(coalLiquifier);
        });
        mergeNode(Blocks.pneumaticDrill, () -> {
            node(electricDrill);
        });
        mergeNode(Blocks.blastDrill, ()-> {
            node(hammerDrill, Seq.with(
                    new Objectives.Research(Liquids.cryofluid)
            ), () -> {
            });
            node(precisionDrill);
        });
        mergeNode(Blocks.siliconCrucible, () -> {
            node(siliconFabricator);
        });
        mergeNode(Blocks.surgeSmelter, () -> {
            node(lumiumSmelter, Seq.with(
                    new Objectives.Research(EIItems.starium),
                    new Objectives.Research(Items.thorium)
                ), () -> {
            });
            node(stariumRefiner, Seq.with(
                    new Objectives.Research(EIItems.starium),
                    new Objectives.Research(Items.surgeAlloy)
                ), () -> {
            });
        });
        mergeNode(Blocks.kiln, () -> {
            node(metaglassFabricator);
        });
        mergeNode(Blocks.siliconCrucible, () -> {
            node(stariumSynthesizer, Seq.with(
                    new Objectives.Research(Items.titanium),
                    new Objectives.Research(Items.silicon)
                ), () -> {
            });
        });
        mergeNode(Blocks.siliconCrucible, () -> {
            node(peridotiumSynthesizer, Seq.with(
                    new Objectives.Research(Items.thorium)), () -> {
            });
        });
        mergeNode(Blocks.multiPress, () -> {
            node(graphiteCompressor, Seq.with(
                    new Objectives.Research(EILiquids.steam)), () -> {
            });
        });
        mergeNode(Blocks.graphitePress, () -> {
            node(peridotiumEnhancer, Seq.with(
                    new Objectives.Research(EIItems.peridotium)), () -> {
            });
        });
        mergeNode(Blocks.pulverizer, () -> {
            node(scrapper);
        });
        mergeNode(Blocks.plastaniumCompressor, () -> {
            node(plastaniumCondenser);
        });
        mergeNode(Blocks.cultivator, () -> {
            node(freezer);
        });
        mergeNode(Blocks.oilExtractor, () -> {
            node(oilPurifier, Seq.with(
                    new Objectives.Research(Liquids.oil)), () -> {
                node(heavyOilRefinery, Seq.with(
                        new Objectives.Research(EILiquids.heavyOil)), () -> {
                });
                node(fuelAssembler, Seq.with(
                        new Objectives.Research(EILiquids.lightOil)), () -> {
                });
            });
        });
        mergeNode(Blocks.thoriumReactor, () -> {
            node(peridotiumReactor, () -> {
            });
        });
        mergeNode(Blocks.steamGenerator, ()-> {
            node(steamTurbine, ()-> {
            });
        });
        mergeNode(Blocks.rtgGenerator, () -> {
            node(peridotiumGenerator, Seq.with(
                    new Objectives.Research(Liquids.cryofluid)), () -> {
            });
        });
        mergeNode(Blocks.impactReactor, () -> {
            node(lumiumReactor, Seq.with(
                    new Objectives.Research(EILiquids.lox)), () -> {
            });
        });
        mergeNode(Blocks.microProcessor, () -> {
            node(controllerProcessor, () -> {
                node(armProcessor, () -> {
                    node(threadripperProcessor);
                });
            });
        });
        mergeNode(Blocks.overdriveDome, () -> {
            node(planetaryOverdrive, () -> {
                node(planetaryMender);
            });
        });
        mergeNode(Blocks.copperWall, () -> {
            node(graphiteWall, () -> {
                node(largeGraphiteWall);
            });
        });
        mergeNode(Blocks.plastaniumWall, () -> {
            node(stariumWall, () -> {
                node(largeStariumWall);
            });
        });
        mergeNode(Blocks.duo, () -> {
            node(anado, () -> {
                node(deuse);
            });
        });
        mergeNode(Blocks.ripple, () -> {
            node(exagonArtillery);
        });
        mergeNode(Blocks.tsunami, () -> {
            node(renoit);
        });
        mergeNode(Blocks.lancer, () -> {
            node(piercer, () -> {
                node(enforcer, () -> {
                    node(slowRay, () -> {
                        node(fastRay, () -> {
                        });
                    });
                });
            });
        });
        mergeNode(Blocks.groundFactory, () -> {
            node(groundFactory, () -> {
                node(starruneReconstructor, () -> {
                    node(eraniteReconstructor, () -> {
                        node(ultraReconstructor, () -> {
                            node(terraReconstructor);
                        });
                    });
                });
            });
        });
        mergeNode(Blocks.airFactory, () -> {
            node(airFactory);
        });

        mergeNode(UnitTypes.mono, () -> {
            node(centurion, () -> {
                node(alturion);
            });
        });
        mergeNode(UnitTypes.dagger, () -> {
            node(breadnight, () -> {
                node(toastnight);
            });
        });
        mergeNode(UnitTypes.nova, () -> {
            node(stormer, () -> {
                node(rusher, () -> {
                    node(escapade, () -> {
                        node(natorin, () -> {
                            node(terrand);
                        });
                    });
                });
            });
        });
        mergeNode(UnitTypes.flare, () -> {
            node(pygmy, () -> {
                node(schaus, () -> {
                    node(ageronia);
                });
            });
            node(SmolBoi, () -> {
                node(MediumBoi, () -> {
                    node(LargeBoi, () -> {
                        node(PayloadBoi);
                    });
                });
            });
        });

        mergeNode(Items.titanium, () -> {
            nodeProduce(EIItems.starium, () -> {
                nodeProduce(EIItems.stariumAlloy, () -> {
                });
            });
        });
        mergeNode(Items.thorium, () -> {
            nodeProduce(EIItems.peridotium, () -> {
                nodeProduce(EIItems.enhancedPeridotium, () -> {
                });
            });
            nodeProduce(EIItems.lumium, () -> {
            });
        });
        mergeNode(Liquids.oil, () -> {
            nodeProduce(EILiquids.heavyOil, () -> {
                nodeProduce(EILiquids.lightOil, () -> {
                    nodeProduce(EIItems.solidFuel, () -> {
                    });
                });
            });
        });
        mergeNode(Liquids.cryofluid, () -> {
            nodeProduce(EILiquids.lox, () -> {
            });
        });
        mergeNode(Liquids.water, () -> {
            nodeProduce(EIItems.ice, () -> {
            });
            nodeProduce(EILiquids.steam, () -> {
            });
        });
    }
}
