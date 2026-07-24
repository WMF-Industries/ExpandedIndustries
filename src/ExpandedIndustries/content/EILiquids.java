package ExpandedIndustries.content;

import arc.graphics.*;
import mindustry.content.StatusEffects;
import mindustry.type.*;

public class EILiquids{
    public static Liquid liquidOxygen, steam, lightOil, heavyOil, reurium;

    public static void load(){
        liquidOxygen = new Liquid("liquid-oxygen", Color.valueOf("83c7ff")){{
            coolant = true;

            temperature = 0.1f;
            explosiveness = 1.2f;
            heatCapacity = 1.3f;
            viscosity = 0.9f;

            effect = EIStatusEffects.brittle;

            lightColor = Color.valueOf("83c7ff80");
        }};

        steam = new Liquid("steam", Color.valueOf("83c7ff")){{
            gas = true;
            coolant = false;

            temperature = 0.875f;
            viscosity = 1.2f;

            gasColor = Color.valueOf("FDFCFC");
        }};

        lightOil = new Liquid("light-oil", Color.valueOf("BDB88B")){{
            coolant = true;

            flammability = 0.4f;
            explosiveness = 0.6f;
            temperature = 0.4f;
            heatCapacity = 2.4f;
            viscosity = 0.91f;

            effect = StatusEffects.tarred;
        }};

        heavyOil = new Liquid("heavy-oil", Color.valueOf("C9C447")){{
            coolant = false;

            flammability = 0.3f;
            explosiveness = 0.15f;
            heatCapacity = 1.1f;
            viscosity = 0.65f;

            effect = StatusEffects.tarred;
        }};

        reurium = new Liquid("reurium", EIPal.reuColor){{
            coolant = false;

            flammability = 1.6f;
            explosiveness = 1.4f;
            viscosity = 0.45f;

            effect = EIStatusEffects.sticky;
        }};
    }
}
