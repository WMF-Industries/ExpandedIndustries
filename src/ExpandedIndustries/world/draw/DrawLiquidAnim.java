package ExpandedIndustries.world.draw;

import arc.math.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.draw.*;

public class DrawLiquidAnim extends DrawLiquidTile{
    public DrawLiquidAnim(Liquid liquid, float padding){
        super(liquid, padding);
    }

    public DrawLiquidAnim(Liquid liquid){
        super(liquid);
    }

    public DrawLiquidAnim(){
        super();
    }

    @Override
    public void draw(Building build){
        Liquid drawn = drawLiquid != null ? drawLiquid : build.liquids.current();
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y, padLeft, padRight, padTop, padBottom, drawn, Mathf.clamp(build.edelta()));
    }
}
