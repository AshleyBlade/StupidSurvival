package shittysituations.stupidsurvival.mob;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class IlayMob extends EntityVillager {

    public IlayMob(Location location) {
        super(EntityTypes.VILLAGER, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.RED + "Ilay"));
        this.setCustomNameVisible(true);
        this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(50);
        this.setHealth(40);

        this.goalSelector.a(1, new PathfinderGoalAvoidTarget<EntityPlayer>(this, EntityPlayer.class, 15, 1.0D, 1.0D));
        this.goalSelector.a(2, new PathfinderGoalPanic(this, 1.35D));
        this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 0.6D));
        this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
    }
}
