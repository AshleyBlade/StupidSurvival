package shittysituations.stupidsurvival.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class SpawnInvisibleMob implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        if(!(event.getEntity() instanceof LivingEntity)) return;
        Random random = new Random();
        if(!(random.nextInt(50) + 1 == 1)) return;
        LivingEntity mob = (LivingEntity) event.getEntity();
        mob.setInvisible(true);
        mob.setCustomName(ChatColor.RED + mob.getType().toString());
        // getLogger().info("A " + mob.getType().toString() +" has spawned invisible at: " + mob.getLocation().getX() + " " + mob.getLocation().getY() + " " + mob.getLocation().getZ());
    }
}
