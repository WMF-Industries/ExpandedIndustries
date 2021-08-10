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
    }
