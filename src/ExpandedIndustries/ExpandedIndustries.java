package ExpandedIndustries;

import arc.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.mod.*;

import ExpandedIndustries.content.*;
import ExpandedIndustries.utilities.*;

public class ExpandedIndustries extends Mod{
    long last = -1;

    public ExpandedIndustries(){
        Log.infoTag("[EI]", "Initializing...");

        Events.on(EventType.ContentInitEvent.class, e -> EIOverrides.apply());
        Events.on(EventType.ClientLoadEvent.class, e -> EISettings.create());
    }

    @Override
    public void loadContent(){
        logTime("[EI]", "Loading content...");

        EIItems.load();
        logTime("[EI]", "Items loaded!");
        EIFx.load();
        logTime("[EI]", "Effects loaded!");
        EIStatusEffects.load();
        logTime("[EI]", "Statuses loaded!");
        EILiquids.load();
        logTime("[EI]", "Liquids loaded!");
        EIBulletTypes.load();
        logTime("[EI]", "Bullets loaded!");
        EIUnits.load();
        logTime("[EI]", "Units loaded!");
        EIBlocks.load();
        logTime("[EI]", "Blocks loaded!");
        EITechTree.load();
        logTime("[EI]", "Tech Tree loaded!");
        EILoadouts.load();
        logTime("[EI]", "Loadouts loaded!");

        Log.infoTag("[EI]", "Content loaded!");
    }

    public void logTime(String tag, String text){
        Log.infoTag(tag, text + (last > 0 ? " (" + Time.timeSinceNanos(last) / Time.nanosPerMilli + "ms)" : ""));
        last = Time.nanos();
    }
}
