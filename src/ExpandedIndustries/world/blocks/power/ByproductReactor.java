//TODO make this shit work
/*package ExpandedIndustries.world.blocks.power;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.util.Nullable;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class ByproductReactor extends NuclearReactor {
    public @Nullable ItemStack outputItem;
    public @Nullable Liquid outputLiquid;
    public @Nullable LiquidStack[] outputLiquids;
    public @Nullable LiquidStack coolantLiquid;
    public Effect craftEffect = Fx.mineBig;
    public Color heatColor = Pal.turretHeat;

    public ByproductReactor(String name){
        super(name);
        lightRadius = 125f;
    }

    @Override
    public void setStats(){
        super.setStats();

        if(outputItem != null){
            stats.add(Stat.output, StatValues.items(itemDuration, outputItem));
        }

        if(outputLiquid != null){
            stats.add(Stat.output, StatValues.liquid(outputLiquid, coolantLiquid.amount * 60f, true));
        }
    }

    @Override
    public boolean outputsItems(){
        return outputItem != null;
    }

    @Override
    public void setBars(){
        super.setBars();

        if(outputLiquid != null){
            addLiquidBar(outputLiquid);
        }
    }

    @Override
    public void init() {
        if (outputLiquid != null) {
            outputsLiquid = true;
            hasLiquids = true;
        }
    }

    public class ByproductReactorBuild extends NuclearReactorBuild {
        public float warmup;
        @Override
        public void consume(){
            boolean valid = canConsume();
            super.consume();

            if(outputItem != null && hasItems && valid){
                for(int i = 0; i < outputItem.amount; i++){
                    offload(outputItem.item);
                }

                craftEffect.at(x, y, outputItem.item.color);
            }
        }


        @Override
        public void updateTile(){
            if(enabled) enabled = shouldConsume();
            super.updateTile();
            dumpOutputs();
            warmup = Mathf.lerpDelta(warmup, (canConsume() && enabled && outputItem != null) ? 1f : 0f, 0.035f);

            if(heat > 0){
                float maxUsed = Math.min(coolantLiquid.amount, heat / coolantPower);
                heat -= maxUsed * coolantPower;
                liquids.remove(coolantLiquid.liquid, maxUsed);
            }

            if(outputLiquids != null){
                for(var output : outputLiquids){
                    handleLiquid(this, output.liquid, Math.min(output.amount = Math.min(coolantLiquid.amount, heat / coolantPower), liquidCapacity - liquids.get(output.liquid)));
                }
            }
        }

        @Override
        public void draw(){
            super.draw();
            if(warmup > 0.001f){
                Draw.blend(Blending.additive);
                Draw.color(heatColor, warmup * Mathf.absin(3f, 1f));
                Draw.rect(topRegion, x, y);
                Draw.blend();
                Draw.reset();
            }
        }

        public void dumpOutputs(){
            if(hasItems && outputItem != null && timer(timerDump, dumpTime / timeScale)){
                dump(outputItem.item);
            }
            if(hasLiquids && outputLiquid != null){
                dumpLiquid(outputLiquid);
            }
        }
    }
}*/
