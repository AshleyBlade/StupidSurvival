package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class IronGolemHitEvent implements Listener {

    @EventHandler
    public void onIronGolemHit(EntityDamageByEntityEvent event){
        if(!(event.getDamager().getType().equals(EntityType.IRON_GOLEM))) return;

        Entity entity = event.getEntity();

        World world = entity.getWorld();
        Location location = entity.getLocation();

        world.strikeLightning(location);
    }
}
