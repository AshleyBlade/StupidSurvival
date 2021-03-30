package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class ExplodingBlockEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Random random = new Random();

        if(!(random.nextInt(100) + 1 == 1)) return;

        Location destroyedBlock = event.getBlock().getLocation();
        World world = destroyedBlock.getWorld();
        world.createExplosion(destroyedBlock, 2, false, false);
    }
}
