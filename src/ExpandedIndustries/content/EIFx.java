package ExpandedIndustries.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Font;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Position;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.effect.WrapEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Fonts;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class EIFx {

    public static Effect reu, overload, critical, cavernFx, despawnPulse, hitPulse, pointBeamHigh;

    public static void load(){
        reu = new Effect(42f, e -> {
            color(EIPal.reuColor);

            randLenVectors(e.id, 1, 2f + e.fin() * 3f, (x, y) -> {
                Fill.square(e.x + x, e.y + y, e.fout());
            });
        });
        overload = new Effect(22, e -> {
            color(Pal.redDust);
            stroke(e.fout() * 3f);
            Lines.circle(e.x, e.y, 4f + e.finpow() * e.rotation);
        });

        cavernFx = new MultiEffect(
            new WaveEffect(){{
                sizeFrom = 90;
                sizeTo = 90;
                lifetime = 45;
                colorFrom = Color.valueOf("a5f5af");
                colorTo = Color.valueOf("ffffff");
            }},
            new WaveEffect() {{
                sizeFrom = 0;
                sizeTo = 90;
                lifetime = 30;
                colorFrom = Color.valueOf("a5f5af");
                colorTo = Color.valueOf("ffffff");
            }},
            new ParticleEffect(){{
                length = 0;
                lifetime = 30;
                particles = 1;
                sizeFrom = 5;
                sizeTo = 0;
                colorFrom = Color.valueOf("a5f5af");
                colorTo = Color.valueOf("ffffff");
            }}
        );

        despawnPulse = new MultiEffect(
            new ParticleEffect(){{
                particles = 1;
                sizeFrom = 12;
                sizeTo = 6;
                lifetime = 60;
                region = "circle";
            }},
            new ParticleEffect(){{
                particles = Mathf.random(5, 20);
                sizeFrom = Mathf.random(3f, 7.5f);
                sizeTo = Mathf.random(0.5f, 2f);
                lifetime = 60;
            }}
        );
        hitPulse = new MultiEffect(
            new WaveEffect(){{
                lifetime = 90f;
                sizeFrom = 40;
                sizeTo = 40;
                colorFrom = colorTo = Color.valueOf("#76c0fc");
            }},
            new ParticleEffect(){{
                particles = Mathf.random(5, 20);
                sizeFrom = Mathf.random(3f, 7.5f);
                sizeTo = Mathf.random(0.5f, 2f);
                lifetime = 30;
            }},
            new ParticleEffect(){{
                startDelay = 30;

                particles = Mathf.random(5, 20);
                sizeFrom = Mathf.random(3f, 7.5f);
                sizeTo = Mathf.random(0.5f, 2f);
                lifetime = 30;
            }},
            new ParticleEffect(){{
                startDelay = 60;

                particles = Mathf.random(5, 20);
                sizeFrom = Mathf.random(3f, 7.5f);
                sizeTo = Mathf.random(0.5f, 2f);
                lifetime = 30;
            }}
        );

        pointBeamHigh = new Effect(25f, 300f, e -> {
            if(!(e.data instanceof Position pos)) return;

            Draw.z(Layer.flyingUnit + 0.2f);
            Draw.color(e.color, e.fout());
            Lines.stroke(1.5f);
            Lines.line(e.x, e.y, pos.getX(), pos.getY());
            Drawf.light(e.x, e.y, pos.getX(), pos.getY(), 20f, e.color, 0.6f * e.fout());
        });
    }
}
