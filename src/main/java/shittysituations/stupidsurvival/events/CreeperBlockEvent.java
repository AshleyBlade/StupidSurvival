package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class CreeperBlockEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){ // play sound on block break
        Random random = new Random();

        Location blockLoc = event.getBlock().getLocation();
        World world = blockLoc.getWorld();

        if(!(random.nextInt(200) + 1 == 1)) {
            world.playSound(blockLoc, Sound.ENTITY_CREEPER_PRIMED, 1, 1);
        } else if(!(random.nextInt(200) + 1 == 1)){
            Creeper creeper = (Creeper) world.spawnEntity(event.getPlayer().getLocation(), EntityType.CREEPER);
            creeper.ignite();
            creeper.setTarget(event.getPlayer());
        }
    }
}
