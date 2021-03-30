package shittysituations.stupidsurvival;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shittysituations.stupidsurvival.events.*;


public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager manager = this.getServer().getPluginManager();

        // Register all events
        manager.registerEvents(new ExplodingBlockEvent(), this); // 1 in 100 blocks will explode
        manager.registerEvents(new SpawnInvisibleMob(), this); // 1 in 50 mobs will be completely invisible
        manager.registerEvents(new VillagerDeathEvent(), this); // on Villager death spawn 5 zombies
        manager.registerEvents(new PhantomSpawnEvent(), this); // on Phantom spawn play enderdragon death sound
        manager.registerEvents(new BedRTPEvent(), this); // on Player enter bed Teleport them randomly
        manager.registerEvents(new CreeperBlockEvent(), this); // on block break play creeper sound
        manager.registerEvents(new CreeperExplodeEvent(), this); // on Creeper explode spawn cave spiders
        manager.registerEvents(new HardcoreInventoryEvent(), this); // on player death clear items
        manager.registerEvents(new ChestOpenEvent(), this);
        manager.registerEvents(new SheepDeathEvent(), this);
        manager.registerEvents(new PigDeathEvent(), this);
        manager.registerEvents(new LlamaDeathEvent(), this);
        manager.registerEvents(new IronGolemHitEvent(), this);
        manager.registerEvents(new IlayEvent(), this);
        // manager.registerEvents(new TreeFellerClone(), this); // <- TreeFeller shit
        manager.registerEvents(new CowDeathEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
