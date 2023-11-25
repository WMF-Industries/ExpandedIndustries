package ExpandedIndustries.world.blocks.environment;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.PixmapRegion;
import arc.math.Mathf;
import arc.math.geom.Point2;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.graphics.MultiPacker;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.OverlayFloor;
import mindustry.world.blocks.environment.Prop;

import static mindustry.Vars.tilesize;

public class PropOre extends OreBlock{
    public static final Point2[] offsets = {
            new Point2(-2, -2),
            new Point2(-2, -1),
            new Point2(-2, 0),
            new Point2(-2, 1),
            new Point2(-2, 2),
            new Point2(-1, -2),
            new Point2(0, -2),
            new Point2(1, -2),
            new Point2(2, -2),
            new Point2(-1, -1),
            new Point2(-1, 0),
            new Point2(-1, 1),
            new Point2(-1, 2),
            new Point2(0, -1),
            new Point2(1, -1),
            new Point2(2, -1),
            new Point2(0, 0),
            new Point2(0, 1),
            new Point2(0, 2),
            new Point2(1, 0),
            new Point2(2, 0),
            new Point2(1, 1),
            new Point2(1, 2),
            new Point2(2, 1),
            new Point2(2, 2),
    };

    public Block parent = Blocks.air;

    static{
        for(var p : offsets){
            p.sub(1, 1);
        }
    }

    public PropOre(String name, Item ore){
        super(name);
        this.localizedName = ore.localizedName;
        this.itemDrop = ore;
        this.variants = 3;
        this.mapColor.set(ore.color);
        this.useColor = true;
    }

    public boolean checkAdjacent(Tile tile){
        for(var point : offsets){
            Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
            if(other == null || other.overlay() != this){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateRender(Tile tile){
        return checkAdjacent(tile);
    }

    @Override
    public void renderUpdate(UpdateRenderState state){
        Tile tile = Vars.world.tile(state.tile.x - 1, state.tile.y - 1);
        if(tile.block() == Blocks.air || (tile.block() instanceof Prop && tile.block() != parent)){
            tile.setNet(parent);
        }
    }
}
