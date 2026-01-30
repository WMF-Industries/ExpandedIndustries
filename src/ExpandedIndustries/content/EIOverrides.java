package ExpandedIndustries.content;

import ExpandedIndustries.entities.bullet.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;
import static mindustry.content.Blocks.*;
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
        ((LiquidTurret) tsunami).ammoTypes.put(reurium, new LiquidBulletType(reurium){{
            damage = 0.6f;
            knockback = 1.7f;
            statusDuration = 600;
            ammoMultiplier = 0.6f;
            reloadMultiplier = 0.75f;
            lifetime = 47.5f;
            speed = 4f;

            status = EIStatusEffects.sticky;
        }});

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
}
