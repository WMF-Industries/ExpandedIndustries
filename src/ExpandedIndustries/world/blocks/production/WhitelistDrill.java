package ExpandedIndustries.world.blocks.production;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Prop;
import mindustry.world.blocks.production.Drill;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

import static mindustry.Vars.*;

public class WhitelistDrill extends Drill{
    public String itemDisplayPrefix = "-crystal";
    public Seq<Item> itemsWhitelisted = Seq.with(Items.copper,Items.lead);
    public WhitelistDrill(String name)
    {
        super(name);
        tier = Integer.MAX_VALUE;
    }

    @Override
    public float getDrillTime(Item item){
        return drillTime + hardnessDrillMultiplier / drillMultipliers.get(item, 1f);
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.drillTier);

        stats.remove(Stat.drillSpeed);
        stats.add(Stat.drillSpeed, 60f / drillTime * size * size, StatUnit.itemsSecond);

        if(liquidBoostIntensity != 1 && findConsumer(f -> f instanceof ConsumeLiquidBase) instanceof ConsumeLiquidBase consBase){
            stats.remove(Stat.booster);
            stats.add(Stat.booster,
                    StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(),
                            consBase.amount,
                            liquidBoostIntensity * liquidBoostIntensity, false, this::consumesLiquid)
            );
        }
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Tile tile = world.tile(x, y);
        if(tile == null) return;

        countOre(tile);

        if(returnItem != null){
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(returnItem) * returnCount, 2), x, y, valid);
            float dx = x * tilesize + offset - width/2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(returnItem.fullIcon, dx, dy - 1, s, s);
            Draw.reset();
            Draw.rect(returnItem.fullIcon, dx, dy, s, s);
        }else{
            Tile to = tile.getLinkedTilesAs(this, tempTiles).find(t -> t.drop() != null && (t.drop().hardness > tier || t.drop() == blockedItem));
            Item item = to == null ? null : to.drop();
            if(item != null){
                drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, valid);
            }
        }
    }

    @Override
    public boolean canReplace(Block other){
        return other instanceof Prop || other == null || other == Blocks.air;
    }

    @Override
    public boolean canMine(Tile tile){
        if(tile == null || tile.block().isStatic()) return false;
        Item drops = tile.drop();
        return drops != null && itemsWhitelisted.contains(drops);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
            if(canMine(other)){
                return returnCount == 25;
            }
        }
        return false;
    }

    public class WhitelistDrillBuild extends DrillBuild {
        @Override
        public void draw(){
            float s = 0.3f;
            float ts = 0.6f;

            if(drawSpinSprite){
                Drawf.spinSprite(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }else{
                Draw.rect(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }

            Draw.rect(region, x, y);
            Draw.z(Layer.blockCracks);
            drawDefaultCracks();

            Draw.z(Layer.blockAfterCracks);
            if(drawRim){
                Draw.color(heatColor);
                Draw.alpha(warmup * ts * (1f - s + Mathf.absin(Time.time, 3f, s)));
                Draw.blend(Blending.additive);
                Draw.rect(rimRegion, x, y);
                Draw.blend();
                Draw.color();
            }

            if(itemDisplayPrefix != null) {
                Draw.rect(dominantItem.name + itemDisplayPrefix, x, y);
            }
        }
    }
}
