package ExpandedIndustries.utilities;

import arc.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.type.*;

import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.storage.*;

import static arc.Core.*;
import static mindustry.Vars.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.UnitTypes.*;

import static ExpandedIndustries.content.EIBlocks.*;
import static ExpandedIndustries.content.EIItems.*;
import static ExpandedIndustries.content.EILiquids.*;
import static ExpandedIndustries.content.EIBulletTypes.*;

public class EIOverrides{
    static ObjectSet<String> modBlacklist = ObjectSet.with(
        "EI"
    );
    final static Bits blockBlacklist = assign(
        wave, tsunami
    );

    public static void apply(){
        Log.info("[EI] Overriding content stats.");
        // manual overrides
        addBullet(tsunami, reurium, tsunamiReuriumBullet);
        addBullet(hail, itemIce, hailIceBullet);
        addBullet(ripple, itemIce, rippleIceBullet);

        ice.itemDrop = itemIce;

        if(settings.getBool("ei-replaceroot", false)){
            ((CoreBlock) coreShard).isFirstTier = false;
            coreShard.alwaysUnlocked = false;
            coreShard.researchCost = ItemStack.with(copper, 1200, lead, 900);
            coreShard.techNode.setupRequirements(coreShard.researchCost);

            alpha.alwaysUnlocked = false;

            Planets.serpulo.defaultCore = coreFrag;

            // this is a terrible way to do this, but i don't think there's any other way (that doesn't involve replacing the sector's map)
            Events.on(EventType.SectorLaunchEvent.class, e -> {
                if(e.sector == Planets.serpulo.getStartSector())
                    state.rules.defaultTeam.core().tile.setNet(coreFrag, state.rules.defaultTeam, 0);
            });
        }

        // automatic overrides
        for(Block b : content.blocks()){
            if(blockBlacklist.get(b.id))
                continue;

            if(b instanceof LiquidTurret t){
                if(!t.ammoTypes.containsKey(reurium)){
                    t.ammoTypes.put(reurium, baseReuriumBullet);
                    return;
                }
            }
        }

        Log.info("[EI] Content stat overrides finished!");
    }

    public static Bits assign(Block... args){
        Bits ret = new Bits(content.blocks().size);

        for(Block b : content.blocks())
            if(b.isModded() && modBlacklist.contains(b.minfo.mod.name))
                ret.set(b.id, true);
        for(Block b : args)
            ret.set(b.id, true);

        return ret;
    }

    public static void addBullet(Block block, Object resource, BulletType bullet){
        if(block instanceof ItemTurret t && resource instanceof Item i){
            t.ammoTypes.put(i, bullet);
            return;
        }
        
        if(block instanceof LiquidTurret t && resource instanceof Liquid l)
            t.ammoTypes.put(l, bullet);
    }
}
