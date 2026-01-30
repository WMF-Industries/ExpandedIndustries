package ExpandedIndustries.content;

import mindustry.entities.bullet.*;

import static ExpandedIndustries.content.EILiquids.*;
import static ExpandedIndustries.content.EIStatusEffects.*;

public class EIBulletTypes{
    public static BulletType baseReuriumBullet;

    public static void load(){
        baseReuriumBullet = new LiquidBulletType(reurium){{
            damage = 0.4f;
            knockback = 1.5f;
            statusDuration = 450f;
            lifetime = 60f;
            speed = 3f;

            status = EIStatusEffects.sticky;
        }};
    }
}
