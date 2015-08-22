import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private static final double BRIGHTENING_FACTOR = 0.05;
    private static final int maxNumber = 24;
    private static final int maxColor = 255;
    private int c;

    // default constructor
    public BlusterCritter() {
        super();
        c = (int) (Math.random() * maxNumber);
    }

    // constructor with parameter courage
    public BlusterCritter(int courage) {
        super();
        c = courage;
    }

    // override the method
    public void processActors(ArrayList<Actor> actors)
    {
        int n = 0;
        // count all critter
        for (Actor actor : actors) {
            if (actor instanceof Critter) {
                n++;
            }
        }
        // if the number of Critter bigger than the courage
        // darker it
        if (n >= c) {
            Color color = getColor();
            int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
        }
        else {
            Color color = getColor();
            int red = Math.min((int) (color.getRed() * (1 + BRIGHTENING_FACTOR)), maxColor);
            int green = Math.min((int) (color.getGreen() * (1 + BRIGHTENING_FACTOR)), maxColor);
            int blue = Math.min((int) (color.getBlue() * (1 + BRIGHTENING_FACTOR)), maxColor);
            setColor(new Color(red, green, blue));
        }
    }

    // override the method
    public ArrayList<Actor> getActors()
    {
        // Neighbors within one step
        ArrayList<Actor> actorInTwoStep = getGrid().getNeighbors(getLocation());
        ArrayList<Location> hasCheck = getGrid().getValidAdjacentLocations(getLocation());
        ArrayList<Location> adjacentLocations = getGrid().getValidAdjacentLocations(getLocation());
        // location checked list
        hasCheck.add(getLocation());
        for (Location loc : adjacentLocations) {
            for (Actor actor: getGrid().getNeighbors(loc)) {
                // the location has check
                if (!hasCheck.contains(actor.getLocation())) {
                    actorInTwoStep.add(actor);
                    // add to checked list
                    hasCheck.add(actor.getLocation());
                }
            }  
        }
        return actorInTwoStep;
    }

}