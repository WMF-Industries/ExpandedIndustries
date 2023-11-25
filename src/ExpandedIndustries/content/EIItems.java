package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.type.*;

public class EIItems {
     public static Item starium, peridotium, lumium, stariumAlloy, enhancedPeridotium, ice, solidFuel, neorium, teranite, xenite, ion, amethyst;

    public static void load(){
        starium = new Item("starium", Color.valueOf("4c4379")){{
            hardness = 4;
        }};

        peridotium = new Item("peridotium", Color.valueOf("45bb49")){{
            explosiveness = 2.2f;
            radioactivity = 1.1f;
            flammability = 1.3f;
            hardness = 3;
        }};

        lumium = new Item("lumium", Color.valueOf("AAFF00")){{
            charge = 2.8f;
            explosiveness = 1.2f;
        }};

        stariumAlloy = new Item("starium-alloy", Color.valueOf("4c4590")){{
            charge = 2.35f;
            cost = 1.3f;
        }};

        enhancedPeridotium = new Item("enhanced-peridotium", Color.valueOf("3E920B")){{
            flammability = 0.5f;
            radioactivity = 1.7f;
            explosiveness = 0.4f;
        }};

        ice = new Item("ice", Color.valueOf("C2BFFBFF"));

        solidFuel = new Item("solid-fuel", Color.valueOf("9E9E9E")){{
            flammability = 2.75f;
            explosiveness = 1.3f;
        }};

        neorium = new Item("neorium"){{
            hardness = 1;
        }};

        teranite = new Item("teranite", Color.valueOf("30b4c0")){{
            hardness = 2;
        }};

        xenite = new Item("xenite"){{
            hardness = 1;
        }};

        ion = new Item("ion");
        amethyst = new Item("amethyst"){{
            hardness = Integer.MAX_VALUE;
        }};
    }
}
