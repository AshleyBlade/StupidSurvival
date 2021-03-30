package shittysituations.stupidsurvival.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HardcoreInventoryEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.getDrops().clear();
    }
}
