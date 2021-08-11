package expandedindustries.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import mindustry.ai.types.*;
import mindustry.annotations.Annotations.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import expandedindustries.entities.bullet*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class ExpandedUnits implements ContentList{
   @Override
   public void load(){
       
      schaus = new UnitType("schaus"){{
        defaultController = SuicdeAI;
      
        flying = true;
        drag = 0.05f;
        speed = 2.6f;
        rotateSpeed = 15f;
        accel = 0.1f;
        range = 130f;
        health = 400;
        buildSpeed = 0.5f;
        engineOffset = 6.5f;
        hitSize = 9f;
        lowAltitude = true;
        abilities.add(new RepairFieldAbility(5f, 60f * 8, 50f));
      
        weapons.add(new Weapon(){{
            top = false;
            mirror = false;
            y = 0f;
            x = 0f;
            shots = 1
            reload = 22.5f;
            ejectEffect = Fx.none;
            recoil = 2f;
            shootSound = Sounds.missile;
            shots = 1;
        
            bullet = new DrainBulletType(){{
                lifetime = 60f;
                speed = 2f;
                damage = 15;
                drainStrength = 0.8f;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 5;
                frontColor = Pal.sapBullet;
                backColor = Pal.sapBulletBack;
                collidesTeam = false;
                sprite = "shell"
                trailColor = Pal.sapBulletBack;
            }};
        }});
      }};
   }
}
