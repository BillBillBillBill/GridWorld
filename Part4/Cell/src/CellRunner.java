import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public class CellRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 4), new Cell(0, 1));
        world.add(new Location(3, 6), new Cell(0, 1));
        world.add(new Location(1, 1), new Cell(0, 1));
        world.add(new Location(2, 3), new Cell(1, 1));
        world.add(new Location(1, 3), new Cell(1, 1));
        world.add(new Location(1, 4), new Cell(1, 1));
//        world.add(new Location(1, 5), new Cell(1, 1));
//        world.add(new Location(1, 6), new Cell(1, 1));
        world.add(new Location(7, 7), new Cell(2, 1));
        world.add(new Location(5, 4), new Cell(2, 1));
        world.add(new Location(5, 6), new Cell(2, 1));
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(6, 7), new Rock());
        world.add(new Location(4, 9), new Rock());
        world.add(new Location(9, 2), new Rock());
        world.show();
    }
}
