import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


public class QuickCrab extends CrabCritter
{

    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        int stepsize = (int) (Math.random() * 2) + 1;

        for (int d : directions)
        {
            // move two step
            if (stepsize == 2) {
                Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
                Location twoSteploc = neighborLoc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(twoSteploc)) {
                    locs.add(twoSteploc);
                }
                // try to move one step
                else if (gr.isValid(neighborLoc)) {
                    locs.add(neighborLoc);
                } 
            }
            // move one step
            else {
                Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neighborLoc)) {
                    locs.add(neighborLoc);
                }
            }
        }
        return locs;
    }    
}
