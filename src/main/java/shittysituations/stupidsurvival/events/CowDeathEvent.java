package shittysituations.stupidsurvival.events;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import shittysituations.stupidsurvival.mob.DeathCowMob;

import java.util.Random;

public class CowDeathEvent implements Listener {

    @EventHandler
    public void onCowDeath(EntityDeathEvent event){
        if(!(event.getEntity().getType().equals(EntityType.COW))) return;

        Random random = new Random();
        if(!(random.nextInt(30) + 1 == 1)) return;

        Entity cow = event.getEntity();
        Location location = cow.getLocation();

        DeathCowMob deathCow = new DeathCowMob(location);
        WorldServer worldServer = ((CraftWorld) cow.getWorld()).getHandle();
        worldServer.addEntity(deathCow);
    }
}
