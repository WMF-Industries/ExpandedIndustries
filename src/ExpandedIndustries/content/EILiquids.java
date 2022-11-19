package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.type.*;

public class EILiquids {
    public static Liquid lox, steam, lightOil, heavyOil, reurium;

    public static void load(){
        lox = new Liquid("lox", Color.valueOf("83c7ff")){{
            temperature = 0.1f;
            heatCapacity = 1.3f;
            lightColor = Color.valueOf("83c7ff80");
        }};
        steam = new Liquid("steam", Color.valueOf("83c7ff")){{
            gas = true;
            temperature = 0.75f;
            coolant = false;
            gasColor = Color.valueOf("FDFCFC");
        }};
        lightOil = new Liquid("light-oil", Color.valueOf("BDB88B")){{
            flammability = 3.7f;
            temperature = 0.35f;
            heatCapacity = 0.8f;
        }};
        heavyOil = new Liquid("heavy-oil", Color.valueOf("C9C447")){{
            flammability = 2.2f;
            temperature = 0.35f;
            heatCapacity = 1.4f;
        }};
        reurium = new Liquid("reurium", Color.valueOf("a45cdc")){{
            flammability = 1.6f;
            coolant = false;
        }};
    }
}
