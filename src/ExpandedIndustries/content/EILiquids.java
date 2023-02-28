package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.content.StatusEffects;
import mindustry.type.*;

public class EILiquids {
    public static Liquid lox, steam, lightOil, heavyOil, reurium;

    public static void load(){
        lox = new Liquid("lox", Color.valueOf("83c7ff")){{
            effect = StatusEffects.freezing;
            temperature = 0.1f;
            heatCapacity = 1.3f;
            lightColor = Color.valueOf("83c7ff80");
        }};
        steam = new Liquid("steam", Color.valueOf("83c7ff")){{
            gas = true;
            temperature = 0.875f;
            coolant = false;
            gasColor = Color.valueOf("FDFCFC");
        }};
        lightOil = new Liquid("light-oil", Color.valueOf("BDB88B")){{
            effect = StatusEffects.tarred;
            flammability = 3.7f;
            explosiveness = 1.8f;
            temperature = 0.35f;
            heatCapacity = 0.9f;
            viscosity = 0.945f;
            coolant = true;
        }};
        heavyOil = new Liquid("heavy-oil", Color.valueOf("C9C447")){{
            effect = StatusEffects.tarred;
            flammability = 2.2f;
            explosiveness = 1.2f;
            temperature = 0.35f;
            heatCapacity = 1.7f;
            viscosity = 0.7f;
            coolant = true;
        }};
        reurium = new Liquid("reurium", Color.valueOf("a45cdc")){{
            effect = EIStatusEffects.sticky;
            flammability = 1.6f;
            explosiveness = 1.4f;
            viscosity = 0.45f;
            coolant = false;
        }};
    }
}
