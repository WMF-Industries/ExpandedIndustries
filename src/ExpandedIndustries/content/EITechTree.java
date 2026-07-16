package ExpandedIndustries.content;

import arc.func.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.type.*;

import static ExpandedIndustries.content.EIBlocks.*;
import static ExpandedIndustries.content.EIItems.*;
import static ExpandedIndustries.content.EILiquids.*;
import static ExpandedIndustries.content.EIUnits.*;
import static arc.Core.*;


public class EITechTree extends TechTree{
    /// Changes the root node of the given planet to the given content
    public static void changeRoot(Planet planet, UnlockableContent root){
        TechNode old = planet.techTree;

        TechNode rootNode = nodeRoot(old.name, root, old.requiresUnlock, () -> {});
        rootNode.children.add(old);
        rootNode.children.addAll(old.children);
        rootNode.children.each(n -> n.parent = rootNode);

        old.name = null;
        old.requiresUnlock = true;
        old.children.clear();

        TechTree.roots.remove(old);
        planet.techTree = rootNode;
    }

    /// Sets the given target as context, then runs given code
    public static void addNode(UnlockableContent target, Runnable change){
        Reflect.set(TechTree.class, "context", target.techNode);
        change.run();
    }

    /// Moves the target after the specified destination node
    public static void moveNode(UnlockableContent target, UnlockableContent destination){
        addNode(destination, () ->
            moveNode(target)
        );
    }

    /// Moves the target after the current context node
    public static void moveNode(UnlockableContent target){
        target.techNode.parent.children.remove(target.techNode);
        context().children.add(target.techNode);
        target.techNode.parent = context();
    }

    /// Adds objectives to the target node
    public static void addObjectives(UnlockableContent target, Objectives.Objective... objectives){
        target.techNode.objectives.add(objectives);
    }

    /// Moves the target after the current context node, then executes the given code with the target as context
    public static void moveNode(UnlockableContent target, Runnable change){
        moveNode(target);
        addNode(target, change);
    }

    public static void load(){
        //serpulo
        if(settings.getBool("ei-replaceroot", false)){
            changeRoot(Planets.serpulo, coreFrag);
            moveNode(Blocks.coreFoundation, Blocks.coreShard);
            addObjectives(Blocks.coreShard, new Objectives.OnSector(SectorPresets.crateredBattleground));
        }else{
            addNode(Blocks.coreShard, () ->
                node(coreFrag)
            );
        }

        addNode(Blocks.coreNucleus, () ->
            node(coreQuadrant)
        );
        addNode(Blocks.coalCentrifuge, () ->
            node(oilCrystallizer)
        );
        addNode(Blocks.titaniumConveyor, () -> {
            node(titaniumBridge, () ->
                node(stariumBridge, () ->
                    moveNode(Blocks.phaseConveyor, () ->
                        node(stariumAlloyBridge)
                    )
                )
            );
            node(stariumConveyor, () ->
                node(stariumJunction)
            );
        });
        addNode(Blocks.plastaniumConveyor, () ->
            node(stariumAlloyConveyor)
        );
        addNode(Blocks.pulseConduit, () ->
            node(stariumConduit)
        );
        addNode(Blocks.bridgeConduit, () ->
            node(titaniumBridgeConduit, () ->
                node(stariumBridgeConduit)
            )
        );
        addNode(Blocks.itemBridge, () ->
            node(crate, () -> {
                moveNode(Blocks.container);
                node(microUnloader, () ->
                    moveNode(Blocks.unloader)
                );
            })
        );
        addNode(Blocks.cryofluidMixer, () ->
            node(cryofluidStirrer, () ->
                node(cryofluidPlant)
            )
        );
        addNode(Blocks.pneumaticDrill, () ->
            node(electricDrill)
        );
        addNode(Blocks.blastDrill, ()-> {
            node(
                hammerDrill,
                Seq.with(
                    new Objectives.Research(Liquids.cryofluid)
                ),
                () -> {}
            );
            node(pressurizedDrill);
        });
        addNode(Blocks.siliconCrucible, () ->
            node(siliconFabricator)
        );
        addNode(Blocks.surgeSmelter, () -> {
            node(
                lumiumSmelter,
                Seq.with(
                    new Objectives.Research(starium),
                    new Objectives.Research(Items.thorium)
                ),
                () -> {}
            );
            node(
                stariumRefiner,
                Seq.with(
                    new Objectives.Research(starium),
                    new Objectives.Research(Items.surgeAlloy)
                ),
                () -> {}
            );
        });
        addNode(Blocks.kiln, () -> {
            node(metaglassFabricator);
            node(scrapper, () -> {
                moveNode(Blocks.pulverizer);
                moveNode(Blocks.melter);
            });
        });
        addNode(Blocks.siliconSmelter, () ->
            node(
                mixingFoundry,
                Seq.with(
                    new Objectives.Research(Items.titanium)
                ),
                () -> node(
                    molecularReassembler,
                    Seq.with(
                        new Objectives.Research(Items.thorium)
                    ),
                    () -> {}
                )
            )
        );
        addNode(Blocks.multiPress, () ->
            node(
                graphiteCompressor,
                Seq.with(
                    new Objectives.Research(steam)
                ),
                () -> {}
            )
        );
        addNode(Blocks.graphitePress, () -> {
            node(
                peridotiumEnricher,
                Seq.with(
                    new Objectives.Research(peridotium)
                ),
                () -> {}
            );
            node(
                freezer,
                Seq.with(
                    new Objectives.Research(Liquids.water),
                    new Objectives.Research(Blocks.combustionGenerator)
                ),
                () -> {}
            );
        });
        addNode(Blocks.plastaniumCompressor, () ->
            node(plastaniumCondenser)
        );
        addNode(Blocks.oilExtractor, () ->
            node(oilPurifier,
                Seq.with(
                    new Objectives.Research(Liquids.oil)
                ),
                () -> {
                    node(
                        oilRefiner,
                        Seq.with(
                            new Objectives.Research(heavyOil)
                        ), () -> {
                        }
                    );
                    node(
                        thermiteMixer,
                        Seq.with(
                            new Objectives.Research(lightOil)
                        ),
                        () -> {
                        }
                    );
                }
            )
        );
        addNode(Blocks.thoriumReactor, () ->
            node(peridotiumReactor)
        );
        addNode(Blocks.steamGenerator, () ->
            node(steamTurbine, () ->
                moveNode(Blocks.differentialGenerator)
            )
        );
        addNode(Blocks.rtgGenerator, () ->
            node(
                peridotiumGenerator,
                Seq.with(
                    new Objectives.Research(Liquids.cryofluid)
                ),
                () -> {}
            )
        );
        addNode(Blocks.impactReactor, () ->
            node(
                lumiumReactor,
                Seq.with(
                    new Objectives.Research(liquidOxygen)
                ),
                () -> {}
            )
        );
        addNode(Blocks.overdriveDome, () ->
            node(sectorOverdrive, () ->
                node(sectorMender)
            )
        );
        addNode(Blocks.duo, () ->
            node(iceWall, () -> {
                moveNode(Blocks.copperWall);
                node(largeIceWall);
            })
        );
        addNode(Blocks.copperWall, () ->
            node(graphiteWall, () -> {
                moveNode(Blocks.titaniumWall);
                node(largeGraphiteWall);
            })
        );
        addNode(Blocks.plastaniumWall, () ->
            node(stariumWall, () ->
                node(largeStariumWall)
            )
        );
        addNode(Blocks.duo, () ->
            node(anado, () -> {
                moveNode(Blocks.scorch);
                moveNode(Blocks.salvo, () ->
                    node(deuse)
                );
            })
        );
        addNode(Blocks.ripple, () ->
            node(hexagon)
        );
        addNode(Blocks.tsunami, () ->
            node(renoit)
        );
        addNode(Blocks.lancer, () -> {
            node(enforcer, () ->
                node(cavern, () ->
                    node(underglow)
                )
            );
            node(piercer);
        });
        addNode(Blocks.groundFactory, () ->
            node(industrialGroundFactory, () ->
                node(starruneReconstructor, () ->
                    node(eraniteReconstructor, () ->
                        node(ultraReconstructor, () ->
                            node(terraReconstructor)
                        )
                    )
                )
            )
        );
        addNode(Blocks.airFactory, () ->
            node(industrialAirFactory)
        );

        addNode(UnitTypes.mono, () ->
            node(centurion, () ->
                node(alturion)
            )
        );
        addNode(UnitTypes.dagger, () ->
            node(requer, () ->
                node(convoy, () ->
                    node(demand)
                )
            )
        );
        addNode(UnitTypes.nova, () -> {
            node(agrid, () ->
                node(xerad, () ->
                    node(escapade, () ->
                        node(natorin, () ->
                            node(terrand)
                        )
                    )
                )
            );
            node(creo, () ->
                node(fingo, () ->
                    node(perficio)
                )
            );
        });
        addNode(UnitTypes.flare, () -> {
            node(pygmy, () ->
                node(schaus, () ->
                    node(ageronia, () ->
                        node(monarch)
                    )
                )
            );
            node(luma, () ->
                node(vera, () ->
                    node(kora, () ->
                        node(astra, () ->
                            node(brilliance)
                        )
                    )
                )
            );
        });

        addNode(Items.titanium, () ->
            nodeProduce(starium, () ->
                nodeProduce(stariumAlloy, () -> {})
            )
        );
        addNode(Items.thorium, () -> {
            nodeProduce(peridotium, () ->
                nodeProduce(enrichedPeridotium, () -> {})
            );
            nodeProduce(lumium, () -> {});
        });
        addNode(Liquids.oil, () ->
            nodeProduce(heavyOil, () ->
                nodeProduce(lightOil, () ->
                    nodeProduce(thermiteCompound, () -> {})
                )
            )
        );
        addNode(Liquids.cryofluid, () ->
            nodeProduce(liquidOxygen, () -> {})
        );
        addNode(Liquids.water, () -> {
            nodeProduce(itemIce, () -> {});
            nodeProduce(steam, () -> {});
        });

        //erekir
        addNode(Blocks.turbineCondenser, () ->
            node(reinforcedSolarPanel)
        );
        addNode(Blocks.armoredDuct, () ->
            node(tungstenConveyor, () ->
                moveNode(Blocks.surgeConveyor)
            )
        );
        addNode(Blocks.largePlasmaBore, () ->
            node(hugePlasmaBore)
        );
    }
}
