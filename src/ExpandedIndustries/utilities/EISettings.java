package ExpandedIndustries.utilities;

import mindustry.gen.*;

import static mindustry.Vars.*;

public class EISettings{
    public static void create(){
        ui.settings.addCategory("@ei-category", Icon.logic, t -> {
            t.checkPref("ei-replaceroot", false);
        });
    }
}
