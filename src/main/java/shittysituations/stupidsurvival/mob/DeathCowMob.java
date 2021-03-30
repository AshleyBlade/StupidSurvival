package shittysituations.stupidsurvival.mob;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import shittysituations.stupidsurvival.pathfinder.PathfinderGoalChaseEntity;

public class DeathCowMob extends EntityCow {

    public DeathCowMob(Location location) {
        super(EntityTypes.COW, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.RED + "Death Cow"));
        this.setCustomNameVisible(true);
        this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(100);
        this.setHealth(100);
        this.setOnFire(1);

        this.goalSelector.a(0, new PathfinderGoalChaseEntity());
        this.goalSelector.a(0, new PathfinderGoalMeleeAttack())
    }


    @Override
    public void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
    }
}
