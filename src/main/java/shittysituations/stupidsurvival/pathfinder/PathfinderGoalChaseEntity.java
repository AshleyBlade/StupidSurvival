package shittysituations.stupidsurvival.pathfinder;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.EnumSet;

public class PathfinderGoalChaseEntity extends PathfinderGoal {

    private final EntityInsentient entity; // entity
    private EntityLiving target;

    private final double speed; // entity speed
    private final float distance; // distance between entity and Target

    // Coords
    private double x;
    private double y;
    private double z;

    // Custom entity -> Finds target (set by targetSelector) -> pathfinds to target -> runs code when at target

    public PathfinderGoalChaseEntity(EntityInsentient entity, double speed, float distance){
        this.entity = entity;
        this.speed = speed;
        this.distance = distance;
        this.a(EnumSet.of(Type.MOVE, Type.LOOK));
    }

    @Override
    public boolean a() { // runs every tick || gets and sets the entity's target location
        // Starts pathfinding goal if it is true
        this.target = this.entity.getGoalTarget(); // Get the goal target from the entity Entity
        if (this.target == null) {// if target == null return false
            return false;
        } else{// Follow target

            // Fuck knows
            Vec3D vec = RandomPositionGenerator.a((EntityCreature) entity, 16, 7, this.target.getPositionVector());
            if(vec == null) // Checks if target? is in the air
                return false;

            this.x = this.target.locX(); // Target's X
            this.y = this.target.locY(); // Target's Y
            this.z = this.target.locZ(); // Target's Z

            return true; // <-- runs c()
        }
    }

    public void c() { // runs after a() || set's the entities navigation to the target
        // navigates entity to target!
        this.entity.getNavigation().a(this.x, this.y, this.z, this.speed);
    }

    public boolean b() { // runs after c() || checks if the entity is close to the target

        // if false runs d -> if true continue navigating to target
        if(!(this.entity.getNavigation().m() && this.target.h(this.entity) < (double) (this.distance * this.distance))) return false;

        return true; // runs a > c > b again
    }

    // Run custom function here
    public void d() { // runs if b = false || if entity is close to target run this

        World world = entity.getWorld().getWorld();
        Location location = new Location(world, entity.locX(), entity.locY(), entity.locZ());
        world.createExplosion(location, 2, true, false);

        this.target = null;
        entity.die();
    }
}
