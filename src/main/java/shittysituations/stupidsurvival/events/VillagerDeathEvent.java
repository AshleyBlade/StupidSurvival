package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeathEvent implements Listener {

    @EventHandler
    public void onVillagerDeath(EntityDeathEvent event){
        if(!(event.getEntity().getType().equals(EntityType.VILLAGER))) return;
        if(event.getEntity().getCustomName() != null) return;

        Entity villager = event.getEntity();
        Location villagerLocation = villager.getLocation();
        World villagerWorld = villagerLocation.getWorld();
        int zombieCount = 10;
        for(int i = 0; i < zombieCount; i++){
            villagerWorld.spawnEntity(villagerLocation, EntityType.ZOMBIE);
        }
    }
}
