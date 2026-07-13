package ExpandedIndustries.world.blocks.defense;

import ExpandedIndustries.content.*;
import arc.Core;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.util.io.*;
import mindustry.ui.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/** A type of wall that slowly decays <br>
 * Attributes can be assigned to increase / decrease the decay rate, where positive values increase, and negative ones decrease */
public class DecayingWall extends Wall{
    /// Secondary "health" system, the wall dies once integrity runs out
    public float integrity = 1440f;
    /// Integrity lost per tick
    public float integrityLoss = 0.1f;
    /// Minimal integrity loss multiplier per tick
    public float minLossMultiplier = 0.05f;
    /// Action to perform when this wall dies
    public Cons<DecayingWallBuild> deathAction = b -> {};
    /// Attribute fetch types
    public AttributeFilter[] attributeFilters;

    public DecayingWall(String name){
        super(name);

        solid = update = true;
    }

    public void setAttribute(Attribute attribute, float value){
        setAttribute(attribute, AttributeFilter.all, value);
    }

    public void setAttribute(Attribute attribute, AttributeFilter type, float value){
        attributes.set(attribute, value);

        if(attributeFilters == null)
            attributeFilters = new AttributeFilter[Attribute.all.length];

        for(int i = 0; i < Attribute.all.length; i++)
            if(Attribute.all[i] == attribute)
                attributeFilters[i] = type;
    }

    @Override
    public void init(){
        super.init();

        if(attributeFilters == null)
            attributeFilters = new AttributeFilter[Attribute.all.length];

        for(int i = 0; i < attributeFilters.length; i++)
            if(attributeFilters[i] == null)
                attributeFilters[i] = AttributeFilter.all;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        float loss = 1f;
        for(int i = 0; i < attributeFilters.length; i++){
            Attribute a = Attribute.all[i];

            if(attributes.get(a) == 0) continue;
            AttributeFilter t = attributeFilters[i];

            float sum = sumAttribute(a, x, y);
            if(sum > 0f && AttributeFilter.floor(t))
                loss += attributes.get(a) * sum;
        }

        drawPlaceText(Core.bundle.format(
            "bar.ei-integrity-loss",
            loss > 1f ? Color.scarlet : Color.lime,
            Math.max(minLossMultiplier, Mathf.round(loss, 0.01f))
        ), x, y, valid);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("integrity", (DecayingWallBuild entity) ->
            new Bar(
                () -> Core.bundle.get("bar.ei-integrity"),
                () -> EIPal.cyanishGray,
                () -> entity.integrityLeft / integrity
            )
        );
    }

    public class DecayingWallBuild extends WallBuild{
        private float[] attrsums = new float[Attribute.all.length];
        public float integrityLeft = integrity;

        @Override
        public void updateTile(){
            float multipliers = 1f;
            for(int i = 0; i < attributeFilters.length; i++){
                Attribute a = Attribute.all[i];

                if(attributes.get(a) == 0f) continue;
                AttributeFilter t = attributeFilters[i];

                float val = 0f;
                if(AttributeFilter.floor(t))
                    val += attrsums[i];
                if(AttributeFilter.weather(t))
                    val += state.envAttrs.get(a);

                if(val <= 0f) continue;
                multipliers += attributes.get(a) * val;
            }

            integrityLeft -= integrityLoss * Math.max(minLossMultiplier, multipliers);
            if(integrityLeft <= 0f)
                kill();
        }

        @Override
        public void heal(float amount){}

        @Override
        public boolean damaged(){
            return false;
        }

        @Override
        public void killed(){
            deathAction.get(this);
            super.killed();
        }

        @Override
        public void pickedUp(){
            attrsums = new float[Attribute.all.length];
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            for(int i = 0; i < attrsums.length; i++)
                attrsums[i] = sumAttribute(Attribute.all[i], tile.x, tile.y);
        }

        @Override
        public void read(Reads r){
            super.read(r);

            integrityLeft = r.f();
        }

        @Override
        public void write(Writes w){
            super.write(w);

            w.f(integrityLeft);
        }
    }

    public enum AttributeFilter{
        weather, floor, all;

        public static boolean floor(AttributeFilter t){
            return t == floor || t == all;
        }

        public static boolean weather(AttributeFilter t){
            return t == weather || t == all;
        }
    }
}
