package ExpandedIndustries.world.blocks.storage;

import ExpandedIndustries.content.EIStatusEffects;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.logic.Ranged;
import mindustry.type.StatusEffect;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.tilesize;

public class StatusCore extends CoreBlock{
    public StatusEffect statusEffect;
    public Color effectColor;
    public float statusDuration, applyRange, applyDelay, effectSize, effectLifetime;
    public Seq<Unit> targets = new Seq<>();

    public StatusCore(String name){
        super(name);

        statusEffect = EIStatusEffects.warm;
        effectColor = Color.orange;
        effectSize = 3f;
        applyRange = 15 * tilesize;
        statusDuration = 15;
        applyDelay = 60f;
        effectLifetime = 210f;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.range, applyRange / tilesize, StatUnit.blocks);
        stats.add(Stat.boostEffect, statusEffect.emoji() + " " + statusEffect.localizedName, statusDuration, StatUnit.seconds);
    }

    public class StatusCoreBuild extends CoreBuild implements Ranged {
        public float refresh = 0, drawProgress = 0;

        @Override
        public void updateTile() {
            if ((refresh += Time.delta) > applyDelay) {
                targets.clear();
                refresh = 0f;

                Units.nearby(team, x, y, applyRange, u -> {
                    targets.add(u);
                });

                for (var target : targets) {
                    target.apply(statusEffect, (statusDuration * 60));
                }
            }

            drawProgress += Time.delta / effectLifetime;
        }

        @Override
        public void draw(){
            super.draw();

            Draw.z(Layer.effect);
            float mod = drawProgress % 1f;
            Draw.color(effectColor);
            Lines.circle(x, y, applyRange);
            Lines.stroke(effectSize * (1f - mod));
            Lines.circle(x, y, applyRange * mod);
            Draw.reset();
        }

        @Override
        public float range() {
            return applyRange;
        }
    }
}
