package shittysituations.stupidsurvival.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class ChestOpenEvent implements Listener {

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event){
        if(!(event.hasBlock())) return; // check if the event has a block involved
        if(!(event.getClickedBlock().getType() == Material.CHEST)) return; // check if the clicked block was a chest

        Random random = new Random(); // create new random
        if(!(random.nextInt(50) + 1 == 1)) return; // check if the random int equals 1 -> 1 in 30

        Player player = event.getPlayer(); // store the player
        Block chest = event.getClickedBlock(); // store the chest
        World world = chest.getWorld(); // store the chest world
        Location location = new Location(world, chest.getX(), chest.getY() + 1, chest.getZ()); // create the chest location

        world.spawnEntity(location, EntityType.ENDERMITE); // spawn endermite ontop of chest
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1); // player a sound to the player
    }
}
