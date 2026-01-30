package ExpandedIndustries.world.blocks.distribution;

import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.distribution.*;

public class CustomItemBridge extends BufferedItemBridge{
    /** Amount of ticks it takes an item to go through the buffer */
    public float bufferSpeed = 40f;

    public CustomItemBridge(String name){
        super(name);
    }

    public class CustomItemBridgeBuild extends BufferedItemBridgeBuild{
        ItemBuffer buffer = new ItemBuffer(bufferCapacity);

        @Override
        public void updateTransport(Building other){
            if(buffer.accepts() && items.total() > 0){
                buffer.accept(items.take());
            }

            Item item = buffer.poll(bufferSpeed / timeScale);
            if(timer.get(timerAccept, speed) && item != null && other.acceptItem(this, item)){
                moved = true;
                other.handleItem(this, item);
                buffer.remove();
            }
        }
    }
}
