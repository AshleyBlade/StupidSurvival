package shittysituations.stupidsurvival.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.Random;

public class LlamaDeathEvent implements Listener {

    private ArrayList<Creeper> creepers = new ArrayList<>();
    private WanderingTrader trader;
    public int traderSpawn = 2;

    @EventHandler
    public void onLlamaDeath(EntityDeathEvent event) {
        if(!(event.getEntity().getType().equals(EntityType.LLAMA))) return; // if entity isn't a llama return

        Random random = new Random(); // create new random
        if(!(random.nextInt(20) + 1 == 1)) return; // if random number isn' t 1 return

        Entity llama = event.getEntity(); // store the entity
        Location llamaLocation = llama.getLocation(); // store the location fo the entity
        World world = llama.getWorld(); // store the world of the entity

        trader = (WanderingTrader) world.spawnEntity(llamaLocation, EntityType.WANDERING_TRADER); // spawn a wandering trader in the world

        if(!(event.getEntity().getKiller().equals(EntityType.PLAYER))) return; // check if the killer is a player
        Player player = event.getEntity().getKiller(); // store the player
        player.sendMessage("Wow, a trader!"); // send the message to the player
    }

    @EventHandler
    public void onTraderRightClick(PlayerInteractEntityEvent event){
        // if this doesn't work check if wandertrader then store the event entity as WanderingTrader and compaer with previously created one.
        if(!(event.getRightClicked().equals(trader))) return; // if the entity that was clicked is not equal to trader

        Location location = trader.getLocation(); // store the location of the trader
        World world = trader.getWorld(); // store the world of the trader

        trader.remove(); // remove the trader from the world

        for(int i = 0; i < traderSpawn; i++){ // run the amount of times defined above -> traderSpawn
            Creeper creeper = (Creeper) world.spawnEntity(location, EntityType.CREEPER); // spawn creeper every iteration
            creepers.add(creeper);
        }

        Player player = event.getPlayer();

        for (Creeper creeper : creepers) { // For every creeper
            creeper.ignite(); // ignite
            creeper.setTarget(player);
        }

        player.sendMessage(ChatColor.RED + "Sike, time to run");

        event.setCancelled(true); // Cancel default event -> if the menu still opens move this.
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event){
        boolean correct = false;
        for(Creeper creeper : creepers){ // iterate through creeper arraylist
            if(!(event.getEntity().equals(creeper))) continue;// if the creeper is not equal
            correct = true;
        }
        if(!correct) return;

        Creeper creeper = (Creeper) event.getEntity();
        if(!(creeper.getTarget() instanceof Player)) return;

        creepers.remove(creeper);
    }
}
