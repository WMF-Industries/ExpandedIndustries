package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.type.*;

public class EIItems {
     public static Item
     peridotium, starium, lumium, stariumAlloy, enrichedPeridotium, thermiteCompound, ice;

    public static void load(){
        peridotium = new Item("peridotium", Color.valueOf("45bb49")){{
            explosiveness = 0.75f;
            radioactivity = 1.4f;
            flammability = 0.2f;
            hardness = 3;
        }};

        starium = new Item("starium", Color.valueOf("4c4379")){{
            hardness = 4;
            cost = 1.6f;
        }};

        lumium = new Item("lumium", Color.valueOf("AAFF00")){{
            charge = 2.7f;
            radioactivity = 0.4f;
        }};

        stariumAlloy = new Item("starium-alloy", Color.valueOf("4c4590")){{
            charge = 1.55f;
            cost = 1.9f;
        }};

        enrichedPeridotium = new Item("enriched-peridotium", Color.valueOf("3E920B")){{
            radioactivity = 1.7f;
            explosiveness = 0.3f;
            cost = 1.5f;
        }};

        thermiteCompound = new Item("thermite-compound", Color.valueOf("9E9E9E")){{
            flammability = 2.1f;
            explosiveness = 0.05f;
        }}; // titanium, lead and light oil to create

        ice = new Item("ice", Color.valueOf("C2BFFBFF"));
    }
}
