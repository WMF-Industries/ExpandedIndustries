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


public class EITechTree extends TechTree{
    public static void addNode(UnlockableContent target, Runnable change){
        Reflect.set(TechTree.class, "context", target.techNode);
        change.run();
    }

    public static void mergeNode(UnlockableContent source, UnlockableContent destination){
        addNode(destination, () ->
            mergeNode(source)
        );
    }

    public static void mergeNode(UnlockableContent source){
        context().children.addAll(
            source.techNode.children.copy()
        );
        source.techNode.children.clear();
    }

    public static void moveNode(UnlockableContent target, UnlockableContent destination){
        addNode(destination, () ->
            moveNode(target)
        );
    }

    public static void moveNode(UnlockableContent target){
        target.techNode.parent.children.remove(target.techNode);
        context().children.add(target.techNode);
    }

    public static void load(){
        //serpulo
        addNode(Blocks.coreShard, () ->
            node(coreFrag)
        );
        addNode(Blocks.coalCentrifuge, () ->
            node(oilCrystallizer)
        );
        addNode(Blocks.titaniumConveyor, () -> {
            node(titaniumBridge, () ->
                node(stariumBridge, () ->
                    moveNode(Blocks.phaseConveyor)
                )
            );
            node(stariumConveyor, () ->
                node(stariumJunction)
            );
        });
        addNode(Blocks.plastaniumConveyor, () ->
            node(stariumAlloyConveyor)
        );
        addNode(Blocks.phaseConveyor, () ->
            node(stariumAlloyBridge)
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
            node(precisionDrill);
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
                stariumSynthesizer,
                Seq.with(
                    new Objectives.Research(Items.titanium)
                ),
                () -> {}
            )
        );
        addNode(stariumSynthesizer, () ->
            node(
                peridotiumSynthesizer,
                Seq.with(
                    new Objectives.Research(Items.thorium)
                ),
                () -> {}
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
        addNode(Blocks.graphitePress, () ->
            node(
                peridotiumEnricher,
                Seq.with(
                    new Objectives.Research(peridotium)
                ),
                () -> {}
            )
        );
        addNode(Blocks.plastaniumCompressor, () ->
            node(plastaniumCondenser)
        );
        addNode(Blocks.graphitePress, () ->
            node(
                freezer,
                Seq.with(
                    new Objectives.Research(Liquids.water)
                ),
                () -> {}
            )
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
            node(steamTurbine)
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
            node(planetaryOverdrive, () ->
                node(planetaryMender)
            )
        );
        addNode(Blocks.copperWall, () ->
            node(graphiteWall, () ->
                node(largeGraphiteWall)
            )
        );
        addNode(Blocks.plastaniumWall, () ->
            node(stariumWall, () ->
                node(largeStariumWall)
            )
        );
        addNode(Blocks.duo, () ->
            node(anado, () ->
                node(deuse)
            )
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
            node(groundFactory, () ->
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
            node(airFactory)
        );

        addNode(UnitTypes.mono, () ->
            node(centurion, () ->
                node(alturion)
            )
        );
        addNode(UnitTypes.dagger, () ->
            node(requer, () ->
                node(convoy)
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
            node(creo);
        });
        addNode(UnitTypes.flare, () -> {
            node(pygmy, () ->
                node(schaus, () ->
                    node(ageronia)
                )
            );
            node(SmolBoi, () ->
                node(MediumBoi, () ->
                    node(LargeBoi, () ->
                        node(PayloadBoi)
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
            nodeProduce(ice, () -> {});
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
