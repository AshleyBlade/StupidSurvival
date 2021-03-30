package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class SheepDeathEvent implements Listener {

    private final int sheepSpawn = 5;

    @EventHandler
    public void onSheepDeath(EntityDeathEvent event){
        if(!(event.getEntity().getType().equals(EntityType.SHEEP))) return; // If entity isn't sheep return

        if(event.getEntity().getKiller() == null) return; // If killer is null return
        LivingEntity killer = event.getEntity().getKiller(); // Store the killer

        Random random = new Random(); // create new random
        if(!(random.nextInt(20) + 1 == 1)) return; // if this random number isn't equal to 1 return -> 1 in 40;

        Entity sheep = event.getEntity(); // store sheep
        World world = sheep.getWorld(); // get sheep's world
        Location location = sheep.getLocation(); // get sheep's location

        for(int i = 0; i < sheepSpawn; i++){ // run the below code for sheepSpawn amount of times
            Wolf wolf = (Wolf) world.spawnEntity(location, EntityType.WOLF); // spawn wolf where sheep died -> store wolf as wolf
            wolf.setTarget(killer); // set the target to the killer
            wolf.setAngry(true); // make the wolf angry
            wolf.setCustomName("Shaun's Avenger"); // set the name of the wolf
            wolf.setCustomNameVisible(true); // Shows name at all times
        }
    }
}
