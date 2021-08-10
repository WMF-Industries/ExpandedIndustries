package expandedindustries.entities.bullet;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import arc.math.*;

public class DrainBulletType extends BulletType{
  public float drainStrength = 0.5f;
  
      public SapBulletType(){
        speed = 0f;
        despawnEffect = Fx.none;
        pierce = true;
        collides = false;
        hitSize = 0f;
        hittable = false;
        hitEffect = Fx.hitLiquid;
        lightOpacity = 0.6f;
        impact = true;
    }

  
      @Override
    public void init(Bullet b){
        super.init(b);

        Healthc target = Damage.linecast(b, b.x, b.y, b.rotation(), length);
        b.data = target;

        if(target != null){
            float result = Math.max(Math.min(target.health(), damage), 0);

            if(b.owner instanceof Healthc h){
                h.heal(result * drainStrength);
            }
        }
              if(target instanceof Hitboxc hit){
            hit.collision(b, hit.x(), hit.y());
            b.collision(hit, hit.x(), hit.y());
        }else if(target instanceof Building tile){
            if(tile.collide(b)){
                tile.collision(b);
                hit(b, tile.x, tile.y);
            }
        }else{
            b.data = new Vec2().trns(b.rotation(), length).add(b.x, b.y);
        }
    }
}
