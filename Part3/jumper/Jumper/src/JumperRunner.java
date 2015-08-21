import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower; 
import info.gridworld.actor.Rock; 
import info.gridworld.grid.Grid; 
import java.awt.Color;

public final class JumperRunner
{
    private JumperRunner() {
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Flower a = new Flower();
        Rock   b = new Rock();
        world.add(new Location(3, 3), alice);
        world.add(new Location(1, 3), a);
        world.add(new Location(3, 4), b);
        world.show();
    }
}
