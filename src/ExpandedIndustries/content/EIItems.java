package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.content.Planets;
import mindustry.type.*;

public class EIItems{
    public static Item

    peridotium, starium, lumium, stariumAlloy, enrichedPeridotium, thermiteCompound, itemIce;

    public static void load(){
        itemIce = new Item("ice", Color.valueOf("C2BFFBFF")){{
            lowPriority = true;

            shownPlanets.add(Planets.serpulo);

            hardness = 2;
            cost = 0.3f;
        }};

        starium = new Item("starium", Color.valueOf("4c4379")){{
            shownPlanets.add(Planets.serpulo);

            hardness = 4;
            cost = 1.6f;
        }};

        peridotium = new Item("peridotium", Color.valueOf("45bb49")){{
            shownPlanets.add(Planets.serpulo);

            explosiveness = 0.75f;
            radioactivity = 1.4f;
            flammability = 0.2f;
            hardness = 3;
        }};

        thermiteCompound = new Item("thermite-compound", Color.valueOf("9E9E9E")){{
            shownPlanets.add(Planets.serpulo);

            flammability = 2.1f;
            explosiveness = 0.05f;
        }}; // titanium, lead and light oil to create

        enrichedPeridotium = new Item("enriched-peridotium", Color.valueOf("3E920B")){{
            shownPlanets.add(Planets.serpulo);

            radioactivity = 1.7f;
            explosiveness = 0.3f;
            cost = 1.5f;
        }};

        stariumAlloy = new Item("starium-alloy", Color.valueOf("4c4590")){{
            shownPlanets.add(Planets.serpulo);

            charge = 1.55f;
            cost = 1.9f;
        }};

        lumium = new Item("lumium", Color.valueOf("AAFF00")){{
            shownPlanets.add(Planets.serpulo);

            charge = 2.7f;
            radioactivity = 0.4f;
        }};
    }
}
