package shittysituations.stupidsurvival.events;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PhantomSpawnEvent implements Listener {

    @EventHandler
    public void onPhantomSpawn(EntitySpawnEvent event){
        if(!(event.getEntity().getType().equals(EntityType.PHANTOM))) return;
        LivingEntity phantom = (LivingEntity) event.getEntity();
        World world = phantom.getLocation().getWorld();
        assert world != null;
        world.playSound(phantom.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 100, 1);
    }
}
