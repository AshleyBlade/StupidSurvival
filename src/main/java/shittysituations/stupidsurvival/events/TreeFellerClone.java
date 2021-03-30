package shittysituations.stupidsurvival.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class TreeFellerClone implements Listener {

    @EventHandler
    public void onRightClickAxe(PlayerInteractEvent event){
        if(!(event.hasBlock())) return;
        if(!(event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("Axe"))) return;
        //if(!(event.getHand().equals(EquipmentSlot.HAND)))
        Material[] logs = {Material.ACACIA_LOG, Material.BIRCH_LOG, Material.OAK_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.SPRUCE_LOG};
        boolean correct = false;
        for (Material log : logs) {
            if (!(event.getClickedBlock().getType() == log)) continue;
            correct = true;
        }
        if(!correct) return;

    }
}
