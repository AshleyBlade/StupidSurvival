package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Random;

public class CreeperExplodeEvent implements Listener {

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if(!(event.getEntity().getType().equals(EntityType.CREEPER))) return;

        Random random = new Random();
        if(!(random.nextInt(50) + 1 == 1)) return;
        Entity creeper = event.getEntity();
        Location creeperLoc = creeper.getLocation();
        World world = creeperLoc.getWorld();
        world.spawnEntity(creeperLoc, EntityType.CAVE_SPIDER);
        world.spawnEntity(creeperLoc, EntityType.CAVE_SPIDER);
    }
}
