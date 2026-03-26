package ExpandedIndustries.ai;

import ExpandedIndustries.ai.types.*;
import mindustry.ai.*;

public class EICommands{
    public static final UnitCommand

    healUnitsCommand = new UnitCommand("heal-units", "modeSurvival", u -> new FieldMedicAI());
}