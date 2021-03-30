package shittysituations.stupidsurvival.events;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class CowDeathEvent implements Listener {

    @EventHandler
    public void onCowDeath(EntityDeathEvent event){
        if(!(event.getEntity().getType().equals(EntityType.COW))) return;
        Entity cow = event.getEntity();
        World world = cow.getWorld();

    }
}
