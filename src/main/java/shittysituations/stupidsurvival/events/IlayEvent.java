package shittysituations.stupidsurvival.events;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;
import shittysituations.stupidsurvival.mob.IlayMob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IlayEvent implements Listener {

    public ArrayList<ItemStack> stolenItems = new ArrayList<>();

    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent event){ // when a player enters a bed
        Random random = new Random(); // create new random
        if(!(random.nextInt(100) + 1 == 1)) return; // if random int isn't 1 return

        Player player = event.getPlayer(); // store player as a Player

        IlayMob ilay = new IlayMob(player.getLocation()); // store mob as an IlayMob
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle(); // Get the server's world
        world.addEntity(ilay); // add IlayMob to world

        Collections.addAll(stolenItems, event.getPlayer().getInventory().getContents()); // Add all items to an ArrayList

        player.getInventory().clear(); // clear the player's inventory
    }

    @EventHandler
    public void onIlayDamage(EntityDamageEvent event){ // when an entity is damaged
        if(!(event.getEntity() instanceof Villager)) return; // if entity isn't villager
        if(event.getEntity().getCustomName() == null) return; // if entity doesn't have custom name
        if(!event.getEntity().getCustomName().contains("Ilay")) return; // if custom name doesn't contain Ilay

        Random random = new Random(); // create new random
        event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack( // drop 1-3 gold nuggets in the world
                Material.GOLD_NUGGET, random.nextInt(3) + 1));
    }

    @EventHandler
    public void onIlayDeath(EntityDeathEvent event){ // when an entity dies
        if (!(event.getEntity() instanceof Villager)) return; // if entity isn't a villager
        if (event.getEntity().getCustomName() == null) return; // if entity doesn't have a custom name
        if (!event.getEntity().getCustomName().contains("Ilay")) return; // if custom name doesn't contain Ilay

        for(ItemStack item : stolenItems){ // iterate through stolenItems
            if(item != null) { // if item is not null
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), item); // drop item in world
            }
        }

        stolenItems.clear();
    }
}
