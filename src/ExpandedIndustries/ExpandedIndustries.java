package ExpandedIndustries;

import ExpandedIndustries.content.*;
import arc.util.Log;
import mindustry.mod.Mod;

public class ExpandedIndustries extends Mod{
    public ExpandedIndustries(){
        Log.info("Loaded EI constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading EI content.");
        
        loadAllContent();
        
        Log.info("EI loaded.");
    }
    
    // Load our content in a separate method here in case
    // we need to add additional logic to loadContent()
    private void loadAllContent() {
        EIItems.load();
        EILiquids.load();
        EIFx.load();
        EIStatusEffects.load();
        EIBullets.load();
        EIUnits.load();
        EIBlocks.load();
        EITechTree.load();

        Log.info("Initialization complete.");
    }
}
