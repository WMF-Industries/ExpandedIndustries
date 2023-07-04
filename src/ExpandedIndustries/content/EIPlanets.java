package ExpandedIndustries.content;

import mindustry.content.Planets;
import mindustry.content.SerpuloTechTree;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.PlanetRenderer;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;

public class EIPlanets {
    public static Planet nya;

    public static void load(){
        nya = new Planet("nya", Planets.sun, 0.5f){{
            generator = new SerpuloPlanetGenerator(); //temp
            /*amount of sectors of the planet*/
            meshLoader = () -> new HexMesh(this, 7);
            defaultCore = EIBlocks.coreFrag;
            techTree = Planets.serpulo.techTree;
            allowWaves = enemyCoreSpawnReplace = allowLaunchLoadout = allowLaunchSchematics = allowWaveSimulation = allowSectorInvasion= true;
            ruleSetter = r->{
                /*Vars.state.rules u want  for the planet*/
                r.showSpawns = true;
            };
        }};

    }
}
