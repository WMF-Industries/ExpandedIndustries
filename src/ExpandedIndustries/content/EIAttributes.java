package ExpandedIndustries.content;

import mindustry.world.meta.*;

public class EIAttributes{
    public static Attribute

    cold, liquidHeat, conductivity;

    public static void load(){
        cold = Attribute.add("cold");
        liquidHeat = Attribute.add("liquid-heat");
        conductivity = Attribute.add("conductivity");
    }
}
