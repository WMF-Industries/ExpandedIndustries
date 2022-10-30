package ExpandedIndustries.content;

import mindustry.entities.bullet.EmpBulletType;

public class EIBullets{

    class damageFieldType extends EmpBulletType{
        public int statusDuration = 300;
        public boolean scaleVelocity = true;
        public boolean scaleLife = true;
        public boolean absorbable = false;
        public boolean reflectable = false;
        public boolean hittable = false;
        public float healprecent = 0.07f; // 0.0f = 0%, 0.5 = 50%, 1.0 = 100%;
    }

}
//TODO - Create a customisable damageFieldType bullet for slowRay like turrets
