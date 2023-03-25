package ExpandedIndustries.content;

import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class EIFx {

    public static Effect reu, overload;

    public static void load(){
        reu = new Effect(42f, e -> {
            color(EILiquids.reurium.color);

            randLenVectors(e.id, 1, 2f + e.fin() * 3f, (x, y) -> {
                Fill.square(e.x + x, e.y + y, e.fout());
            });
        });
        overload = new Effect(22, e -> {
            color(Pal.redDust);
            stroke(e.fout() * 3f);
            Lines.circle(e.x, e.y, 4f + e.finpow() * e.rotation);
        });
    }
}
