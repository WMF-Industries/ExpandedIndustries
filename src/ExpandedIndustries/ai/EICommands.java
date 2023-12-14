package ExpandedIndustries.ai;

import ExpandedIndustries.ai.types.FieldMedicAI;
import mindustry.ai.UnitCommand;

public class EICommands{
    public static final UnitCommand
        healUnitsCommand = new UnitCommand("heal", "modeSurvival", u -> new FieldMedicAI());
}