package ExpandedIndustries.world.blocks.power;

import mindustry.world.blocks.power.NuclearReactor;

public class LiquidReactor extends NuclearReactor {
    public LiquidReactor(String name) {
        super(name);
    }

    @Override
    public void update(Tile tile) {
        super.update(tile);
        if (tile.entity.timer.get(timerDump, dumpTime)) {
            Liquids.outputLiquid(tile, this.liquid, this.liquidAmount, this.liquidTemperature);
            this.liquidAmount = 0f;
        }
    }

    @Override
    protected void updateCooling(Tile tile, float coolant) {
        super.updateCooling(tile, coolant);
        float heat = this.heat;
        if (heat > 0) {
            float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
            float used = liquids.remove(this.liquid, maxUsed);
            heat -= used * coolantPower;
            this.liquidAmount += used;
        }
        this.heat = heat;
    }
}
