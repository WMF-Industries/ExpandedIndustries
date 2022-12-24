package ExpandedIndustries.entities.bullet;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;

public class LifestealBulletType extends BulletType {
    public Color backColor = Pal.bulletYellowBack, frontColor = Pal.bulletYellow;
    public Color mixColorFrom = new Color(1f, 1f, 1f, 0f), mixColorTo = new Color(1f, 1f, 1f, 0f);
    public float lengthAdjust = maxRange % 5;
    public float width = 5f, height = 7f;
    public float length = 100f;
    public float intensity = 0.75f;
    public float shrinkX = 0f, shrinkY = 0.5f;
    public Interp shrinkInterp = Interp.linear;
    public float spin = 0, rotationOffset = 0f;
    public String sprite;
    public @Nullable String backSprite;

    public TextureRegion backRegion;
    public TextureRegion frontRegion;

    public LifestealBulletType(float speed, float damage, float intensity, String bulletSprite) {
        super(speed, damage);
        this.sprite = bulletSprite;
    }

    public LifestealBulletType(float speed, float damage, float intensity) {
        this(speed, damage, intensity, "bullet");
    }

    @Override
    public void load() {
        backRegion = Core.atlas.find(backSprite == null ? (sprite + "-back") : backSprite);
        frontRegion = Core.atlas.find(sprite);
    }

    @Override
    public void draw(Bullet b) {
        super.draw(b);
        float shrink = shrinkInterp.apply(b.fout());
        float height = this.height * ((1f - shrinkY) + shrinkY * shrink);
        float width = this.width * ((1f - shrinkX) + shrinkX * shrink);
        float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;

        Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

        Draw.mixcol(mix, mix.a);

        if (backRegion.found()) {
            Draw.color(backColor);
            Draw.rect(backRegion, b.x, b.y, width, height, b.rotation() + offset);
        }

        Draw.color(frontColor);
        Draw.rect(frontRegion, b.x, b.y, width, height, b.rotation() + offset);

        Draw.reset();
    }

    @Override
    protected float calculateRange() {
        return Math.max(length, maxRange);
    }

    @Override
    public void init(Bullet b) {
        super.init(b);

        Healthc target = Damage.linecast(b, b.x, b.y, b.rotation(), length + lengthAdjust);
        b.data = target;

        if (target != null) {
            float result = Math.max(Math.min(target.health(), damage), 0);

            if (b.owner instanceof Healthc h) {
                h.heal(result * intensity);
            }
        }

        if (target instanceof Hitboxc hit) {
            hit.collision(b, hit.x(), hit.y());
            b.collision(hit, hit.x(), hit.y());
        } else if (target instanceof Building tile) {
            if (tile.collide(b)) {
                tile.collision(b);
                hit(b, tile.x, tile.y);
            }
        } else {
            b.data = new Vec2().trns(b.rotation(), length).add(b.x, b.y);
        }
    }
}