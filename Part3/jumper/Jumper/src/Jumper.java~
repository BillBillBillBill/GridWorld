import info.gridworld.actor.Actor;  
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Jumper extends Actor
{
    //default constrctor
    public Jumper()
    {
        setColor(Color.RED);
    }

    //a constrctor with a parameter Color
    public Jumper(Color bugColor)
    {
        setColor(bugColor);
    }

    // move or turn
    public void act()
    {
        if (canMove()) {
            move(); 
        }
        else
        {
            turn();
        }
    }

    //turn 45 degree to the right
    public void turn()       
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
    //get current location
        Location loc = getLocation();
    //the next location
        Location next1 = loc.getAdjacentLocation(getDirection());
    //move two cells
        Location next2 = next1.getAdjacentLocation(getDirection());
        if (gr.isValid(next2)) {
            moveTo(next2);
        }
    }

    public boolean canMove()
    {
        Location loc = getLocation();
    //the next location
        Location next1 = loc.getAdjacentLocation(getDirection());
    //the next and next location 
        Location next2 = next1.getAdjacentLocation(getDirection());
        return canMove(next2);
    }

    // can move into empty location or onto flower
    // can not move onto any other actor 
    public boolean canMove (Location a) {
    // if the Grid isn't exist or the grid isn't valid return false
        Grid<Actor> gr = getGrid();
        if (gr == null || !gr.isValid(a)) {
            return false;
        }
    //get the neighbor on Location a
        Actor neighbor = gr.get(a);
        return (neighbor == null);
    }
}

