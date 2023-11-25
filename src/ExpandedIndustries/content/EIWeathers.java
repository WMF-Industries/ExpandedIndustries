package ExpandedIndustries.content;

import arc.graphics.Color;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;
import mindustry.world.meta.Attribute;

public class EIWeathers {
    public static Weather hailstorm;
    public static void load() {
        hailstorm = new ParticleWeather("hailstorm"){{
            particleRegion = "particle";
            color = Color.cyan;
            yspeed = -8.7f;
            xspeed = -4.4f;
            sizeMax = 11f;
            sizeMin = 4f;
            density = 1500f;
            opacityMultiplier = 0.73f;
            attrs.set(Attribute.light, -0.20f);
            attrs.set(Attribute.water, 1.5f);

            sound = Sounds.windhowl;
            soundVol = 0f;
            soundVolOscMag = 1.5f;
            soundVolOscScl = 1100f;
            soundVolMin = 0.02f;

            status = EIStatusEffects.frozen;
        }};
    }
}
