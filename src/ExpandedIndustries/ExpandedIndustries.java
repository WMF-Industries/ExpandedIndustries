package ExpandedIndustries;

import ExpandedIndustries.content.*;
import arc.Core;
import arc.Events;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.net.ServerGroup;

public class ExpandedIndustries extends Mod{
    public ExpandedIndustries(){
        Log.info("Loaded EI constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading EI content.");

        loadAllContent();
        
        Log.info("Expanded Industries initialized successfully :D");
    }

    private void loadAllContent() {
        EIItems.load();
        EILiquids.load();
        EIFx.load();
        EIStatusEffects.load();
        EIBullets.load();
        EIUnits.load();
        EIBlocks.load();
        EIWeathers.load();
        EITechTree.load();
        EILoadouts.load();

        Log.info("Loading content complete!");
    }
}
