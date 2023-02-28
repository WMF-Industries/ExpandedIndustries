package ExpandedIndustries.content;

import arc.graphics.Color;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
public class EIBullets{
    public static BulletType
        SuicideBulletType;
    public static void load() {
        SuicideBulletType = new BombBulletType() {
            {
                shootEffect = despawnEffect = new MultiEffect(
                        new WaveEffect() {{
                            sizeFrom = 120;
                            sizeTo = 0;
                            colorFrom = Color.valueOf("ffe266");
                            colorTo = Color.valueOf("eec44f");
                        }},
                        new ParticleEffect() {{
                            region = "circle";
                            sizeFrom = 2;
                            sizeTo = 0;
                        }}
                );
                hitEffect = new MultiEffect(
                        new ParticleEffect() {{
                            region = "circle";
                            sizeFrom = 2;
                            sizeTo = 0;
                            particles = 6;
                        }},
                        new WaveEffect() {{
                            sizeFrom = 0;
                            sizeTo = 2;
                            colorFrom = Color.valueOf("ffe266");
                            colorTo = Color.valueOf("eec44f");
                        }}
                );
                splashDamageRadius = 120;
                instantDisappear = true;
                killShooter = true;
                hittable = false;
                splashDamage = 35;
                damage = 30;
                buildingDamageMultiplier = 0.5f;
                collidesAir = true;
                lifetime = 10;
                speed = 1;
            }
        };
    }
}
