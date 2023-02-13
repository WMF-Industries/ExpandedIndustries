package ExpandedIndustries.world.blocks.power;

import mindustry.world.blocks.power.NuclearReactor;

public class NuclearReactor {
    private Liquid coolant;
    private float coolantAmount;
    private Liquid outputLiquid;
    private float outputAmount;
    private Liquid inputLiquid;
    private float inputAmount;
    private float maxInput;
    private float maxOutput;
    private float coolantLiquid; // custom float for coolant liquid

    public NuclearReactor(Liquid coolant, float maxInput, float maxOutput, float coolantLiquid) {
        this.coolant = coolant;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
        this.coolantLiquid = coolantLiquid;
    }

    public void update(Liquid inputLiquid, float inputAmount, float heat) {
        this.inputLiquid = inputLiquid;
        this.inputAmount = inputAmount;

        float maxUsed = Math.min(heat / coolant.heatCapacity * coolantAmount, coolantAmount * coolantLiquid);
        outputAmount = Math.min(maxOutput, maxUsed / outputLiquid.heatCapacity);

        // update coolant amount
        coolantAmount += inputAmount - outputAmount;

        if (coolantAmount < 0) {
            coolantAmount = 0;
        }
    }

    public float getOutputAmount() {
        return outputAmount;
    }
}

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
