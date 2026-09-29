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

    /// Moves the target after the current context node, then executes the given code with the target as context
    public static void moveNode(UnlockableContent target, Runnable change){
        moveNode(target);
        addNode(target, change);
    }

    /// Adds objectives to the target node
    public static void addObjectives(UnlockableContent target, Objectives.Objective... objectives){
        target.techNode.objectives.add(objectives);
    }

    public static Seq<Objectives.Objective> completeSectors(SectorPreset... sectors){
        Seq<Objectives.Objective> objectives = new Seq<>();
        for(SectorPreset sector : sectors)
            objectives.add(new Objectives.SectorComplete(sector));
        return objectives;
    }

    public static Seq<Objectives.Objective> onSectors(SectorPreset... sectors){
        Seq<Objectives.Objective> objectives = new Seq<>();
        for(SectorPreset sector : sectors)
            objectives.add(new Objectives.OnSector(sector));
        return objectives;
    }

    public static Seq<Objectives.Objective> researchContent(UnlockableContent... content){
        Seq<Objectives.Objective> objectives = new Seq<>();
        for(UnlockableContent con : content)
            objectives.add(new Objectives.Research(con));
        return objectives;
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
        addNode(Blocks.cryofluidMixer, () -> {
            node(
                cryofluidStirrer,
                completeSectors(SectorPresets.windsweptIslands),
                () -> node(
                    cryofluidPlant,
                    completeSectors(SectorPresets.perilousHarbor),
                    () -> {}
                )
            );
            node(
                oxygenLiquefier,
                completeSectors(SectorPresets.saltFlats),
                () -> {}
            );
        });
        addNode(Blocks.pneumaticDrill, () ->
            node(
                electricDrill,
                completeSectors(SectorPresets.windsweptIslands),
                () -> {}
            )
        );
        addNode(Blocks.blastDrill, ()-> {
            node(
                hammerDrill,
                researchContent(Liquids.cryofluid),
                () -> {}
            );
            node(pressurizedDrill);
        });
        addNode(Blocks.siliconCrucible, () ->
            node(
                siliconFabricator,
                completeSectors(SectorPresets.facility32m),
                () -> {}
            )
        );
        addNode(Blocks.surgeSmelter, () -> {
            node(
                lumiumSmelter,
                researchContent(
                    starium,
                    Items.thorium,
                    Blocks.impactReactor
                ),
                () -> {}
            );
            node(
                stariumRefiner,
                researchContent(
                    starium,
                    Items.surgeAlloy
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
                researchContent(Items.titanium),
                () -> node(
                    molecularReassembler,
                    researchContent(Items.thorium),
                    () -> {}
                )
            )
        );
        addNode(Blocks.multiPress, () ->
            node(
                graphiteCompressor,
                researchContent(steam),
                () -> {}
            )
        );
        addNode(Blocks.graphitePress, () -> {
            node(
                peridotiumEnricher,
                researchContent(peridotium),
                () -> {}
            );
            node(
                freezer,
                researchContent(
                    Liquids.water,
                    Blocks.combustionGenerator
                ),
                () -> {}
            );
        });
        addNode(Blocks.plastaniumCompressor, () ->
            node(plastaniumCondenser)
        );
        addNode(Blocks.oilExtractor, () ->
            node(
                oilPurifier,
                researchContent(Liquids.oil),
                () -> {
                    node(
                        oilRefiner,
                        researchContent(heavyOil),
                        () -> {}
                    );
                    node(
                        thermiteMixer,
                        researchContent(lightOil),
                        () -> {}
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
                researchContent(Liquids.cryofluid),
                () -> {}
            )
        );
        addNode(Blocks.impactReactor, () ->
            node(
                lumiumReactor,
                researchContent(
                    lumium,
                    liquidOxygen
                ),
                () -> {}
            )
        );
        addNode(Blocks.mendProjector, () ->
            node(
                sectorMender,
                completeSectors(SectorPresets.littoralShipyard),
                () -> {}
            )
        );
        addNode(Blocks.overdriveDome, () ->
            node(
                sectorOverdrive,
                completeSectors(SectorPresets.littoralShipyard),
                () -> {}
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
            node(
                enforcer,
                researchContent(Items.thorium),
                () -> node(
                    cavern,
                    Seq.withArrays(
                        researchContent(
                            Items.plastanium,
                            Items.phaseFabric
                        ),
                        completeSectors(
                            SectorPresets.overgrowth,
                            SectorPresets.impact0078
                        )
                    ),
                    () -> node(
                        underglow,
                        completeSectors(SectorPresets.mycelialBastion),
                        () -> {}
                    )
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
                    node(demand, () ->
                        node(entail, () ->
                            node(warrant)
                        )
                    )
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
            node(exilis, () ->
                node(machaon, () ->
                    node(ageronia, () ->
                        node(monarch, () ->
                            node(alexandrae)
                        )
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
            node(
                reinforcedSolarPanel,
                onSectors(SectorPresets.aegis),
                () -> {}
            )
        );
        addNode(Blocks.armoredDuct, () ->
            node(
                tungstenConveyor,
                onSectors(SectorPresets.peaks),
                () -> moveNode(Blocks.surgeConveyor)
            )
        );
        addNode(Blocks.largePlasmaBore, () ->
            node(
                hugePlasmaBore,
                onSectors(SectorPresets.crossroads),
                () -> {}
            )
        );
    }
}
