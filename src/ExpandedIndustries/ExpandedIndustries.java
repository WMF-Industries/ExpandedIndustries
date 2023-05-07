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
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Core.app.post(() -> Core.app.post(() -> {
                Vars.defaultServers.add(new ServerGroup("[white][gold]Expanded Industries Server[][]", new String[]{"phoenix-network.dev:4000"}));
                Log.info("Added EI server to the list");
            }));
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading EI content.");

        loadAllContent();
        
        Log.info("EI loaded.");
    }

    private void loadAllContent() {
        EIItems.load();
        EILiquids.load();
        EIFx.load();
        EIStatusEffects.load();
        EIBullets.load();
        EIUnits.load();
        EIBlocks.load();
        EITechTree.load();

        Log.info("Loading content complete.");
    }
}
