package shittysituations.stupidsurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.Random;

public class BedRTPEvent implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEnterBed(PlayerBedLeaveEvent event){

        if(!(random.nextInt(100) + 1 == 1)) return; // if random = 1 out of 1000 then

        Player player = event.getPlayer(); // store player

        Location randomLoc = randomLocation(player); // Store randomlocation
        player.teleport(randomLoc);
    }

    private Location randomLocation(Player player){
        World world = player.getWorld(); // Get the world the player is currently in
        int x = randomCoord(); // Store result of randomCoord
        int z = randomCoord(); // Store result of randomCoord
        Block block = world.getHighestBlockAt(x, z); // Get the block at the random x and z coords
        int blockY = block.getY(); // Get the Y level of the block at the x, and y coords. Add one to be one block above the found block

        boolean safetyCheck = false; // Initialise safetyCheck
        while(!safetyCheck){
            if(block.getType() == Material.LAVA | block.getType() == Material.CACTUS | block.getType() == Material.FIRE){ // If the block is hazardous
                Bukkit.getLogger().info("An unsafe block has been found! " + block.getType().toString());
                x = randomCoord();
                z = randomCoord();
                block = world.getHighestBlockAt(x, z);
                blockY = block.getY();

                safetyCheck = false; // Set safetyCheck to false -> run this code again
            } else{
                Bukkit.getLogger().info("This block is safe: " + block.getType().toString() + ", teleporting player...");
                safetyCheck = true; // Set safetyCheck to true -> teleport the player to the found block
            }
        }
        return new Location(world, x, blockY + 1, z);
    }

    private int randomCoord(){
        return (random.nextInt(1000) * (random.nextBoolean() ? -1 : 1)); // Return the result of the nextInt method
    }
}
