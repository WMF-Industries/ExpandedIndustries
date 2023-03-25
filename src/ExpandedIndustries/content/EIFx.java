package ExpandedIndustries.content;

import arc.graphics.g2d.Fill;
import mindustry.entities.Effect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class EIFx {

    public static Effect sticky;

    public static void load(){
        sticky = new Effect(42f, e -> {
            color(EILiquids.reurium.color);

            randLenVectors(e.id, 1, 2f + e.fin() * 3f, (x, y) -> {
                Fill.square(e.x + x, e.y + y, e.fout());
            });
        });
    }
}
