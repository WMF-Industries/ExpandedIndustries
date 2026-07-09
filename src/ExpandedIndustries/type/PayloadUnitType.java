package ExpandedIndustries.type;

import arc.graphics.g2d.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.draw.*;

import static mindustry.Vars.*;

// this whole class is kinda cheesy
public class PayloadUnitType extends UnitType{
    public PayloadUnitType(String name){
        super(name);
    }

    @Override
    public void update(Unit unit){
        super.update(unit);

        //TODO: find a better way to do this
        if(unit instanceof PayloadUnit u){
            for(Payload p : u.payloads){
                if(p instanceof BuildPayload b){
                    if(b.build.block.updateInUnits){
                        if(u.stack != null && u.stack.amount > 0){
                            int requested = b.build.acceptStack(u.stack.item, u.stack.amount, u);
                            if(requested > 0){
                                b.build.handleStack(u.stack.item, requested, u);
                                u.stack.amount -= requested;
                            }
                        }

                        // don't update them twice
                        if(!state.rules.unitPayloadUpdate && !b.build.block.alwaysUpdateInUnits){
                            b.build.tile = emptyTile;
                            b.build.updatePayload(u, null);
                        }
                    }
                }
            }
        }
    }

    @Override
    public <T extends Unit & Payloadc> void drawPayload(T unit){
        if(unit.hasPayload()){
            float prev = Draw.z();

            Seq<Payload> p = unit.payloads();
            Payload pay = p.first();

            setZ(pay, prev - 0.02f);
            pay.set(unit.x, unit.y, unit.rotation);
            pay.draw();

            // currently limited to drawing up to 9 blocks
            int max = Math.min(p.size, 9);
            float offset = pay.size() / 2;
            for(int i = 1; i < max; i++){
                pay = p.get(i);

                setZ(pay, prev - 0.02f);
                setOffsets(unit.x, unit.y, offset + pay.size() / 2, i);

                pay.set(tmp[0], tmp[1], unit.rotation);
                pay.draw();
            }

            Draw.z(prev);
        }
    }

    public void setZ(Payload p, float def){
        if(p.content() instanceof Turret t && t.drawer instanceof DrawTurret d)
            def = d.shadowLayer;
        Draw.z(def);
    }

    public static float[] tmp = new float[2];
    public void setOffsets(float x, float y, float offset, int iter){
        switch(iter){
            case 1 -> { tmp[0] = x - offset; tmp[1] = y; }
            case 2 -> { tmp[0] = x; tmp[1] = y + offset; }
            case 3 -> { tmp[0] = x + offset; tmp[1] = y; }
            case 4 -> { tmp[0] = x; tmp[1] = y - offset; }
            case 5 -> { tmp[0] = x + offset; tmp[1] = y + offset; }
            case 6 -> { tmp[0] = x - offset; tmp[1] = y + offset; }
            case 7 -> { tmp[0] = x + offset; tmp[1] = y - offset; }
            case 8 -> { tmp[0] = x - offset; tmp[1] = y - offset; }
        }
    }
}
