import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class KingCrab extends CrabCritter
{

    public void makeMove(Location loc)
    {
        Grid<Actor> gr = getGrid();
        Location location = getLocation();
        for (Actor actor: getActors()) {
            Location actorLocation = actor.getLocation();
            int direction = location.getDirectionToward(actor.getLocation());
            Location newloc = actorLocation.getAdjacentLocation(direction);
            System.out.println(direction);
            
            if (gr.isValid(newloc)) {
                Actor a = gr.get(newloc);
                if (a == null) {
                    actor.moveTo(newloc);
                } 
                
            } else {
                    actor.removeSelfFromGrid();
                }
        }
        super.makeMove(loc);
    }
    
}
