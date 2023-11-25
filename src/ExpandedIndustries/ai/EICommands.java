package ExpandedIndustries.ai;

import ExpandedIndustries.ai.types.RepairUnitAI;
import mindustry.ai.UnitCommand;

public class EICommands{
    public static final UnitCommand
        healUnitsCommand = new UnitCommand("heal-units", "units", u -> new RepairUnitAI());
}