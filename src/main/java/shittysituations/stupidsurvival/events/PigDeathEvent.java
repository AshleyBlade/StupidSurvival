package shittysituations.stupidsurvival.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class PigDeathEvent implements Listener {

    @EventHandler
    public void onPigDeath(EntityDeathEvent event){
        if(!(event.getEntity().getType().equals(EntityType.PIG))) return; // if not a pig return

        if(event.getEntity().getKiller() == null) return; // If killer is null return
        LivingEntity killer = event.getEntity().getKiller(); // Store the killer

        Random random = new Random(); // create new random
        if(!(random.nextInt(50) + 1 == 1)) return; // if random number isn't 1 return -> 1 in 70

        Entity pig = event.getEntity(); // store the dead entity
        Location location = pig.getLocation(); // store the entity's location
        World world = pig.getWorld(); // store the entity's world

        Piglin piglin = (Piglin) world.spawnEntity(location, EntityType.PIGLIN); // spawn a piglin as Piglin at the entity's location
        PiglinBrute piglinBrute = (PiglinBrute) world.spawnEntity(location, EntityType.PIGLIN_BRUTE); // spawn a piglin brute as PiglinBrute at the entity's location

        piglin.isAdult(); // make the piglin an adult
        piglin.damage(piglin.getHealth() / 2); // make the piglin have half health
        piglin.setTarget(killer); // set the piglin's target as the killer
        piglin.setCustomName("George"); // set the piglin's name to George

        piglinBrute.damage(piglinBrute.getHealth() / 2); // make the piglin brute have half health
        piglinBrute.setTarget(killer); // set the piglin brutes target as the killer
        piglinBrute.setCustomName("Daddy Pig"); // set the piglin brute's name to Daddy Pig

        killer.sendMessage(ChatColor.RED + "You fucked up."); // send a message to the player.
    }
}
