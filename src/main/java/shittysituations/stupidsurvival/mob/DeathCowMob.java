package shittysituations.stupidsurvival.mob;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shittysituations.stupidsurvival.pathfinder.PathfinderGoalChaseEntity;

public class DeathCowMob extends EntityCow {

    public DeathCowMob(Location location) {
        super(EntityTypes.COW, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.RED + "Death Cow"));
        this.setCustomNameVisible(true);
        this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(100);
        this.setHealth(14);
        this.setFireTicks(this.getMaxFireTicks());

        LivingEntity cow = (LivingEntity) this;
        cow.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 100, false, false));
        while(cow.getType().isAlive()){
            cow.playEffect(EntityEffect.LOVE_HEARTS);
        }

        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class, false));
        this.goalSelector.a(0, new PathfinderGoalChaseEntity(this, 1.2D, 15f));
        this.goalSelector.a(1, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(3, new PathfinderGoalEatTile(this));
    }


    @Override
    public void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
    }
}
