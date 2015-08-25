package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		System.out.println("hehe");
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		System.out.println("willMove:" + willMove);
		System.out.println("isEnd:" + isEnd);
		//System.out.println("next:" + next.toString());
		//System.out.println("last:" + last.toString());
		System.out.println("crossLocation empty:" + crossLocation.empty());
		if (crossLocation.empty() || isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			System.out.println("Move to next:" + next.toString());
			move();
			//increase step count when move 
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			System.out.println("gr == null");
			return null;
		}
		System.out.println("getValid" + loc.toString());
		ArrayList<Location> valid = new ArrayList<Location>();
		int directions[] = {0, 90, 180, 270};
		for (int i = 0; i < 4; i++) {
			Location newloc = loc.getAdjacentLocation(getDirection() + directions[i]);
			System.out.println(newloc.toString());
			if (gr.isValid(newloc) && (gr.get(newloc) == null || gr.get(newloc) instanceof Flower )&& !newloc.equals(last)) {
				valid.add(newloc);
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if (stepCount == 0) {
			ArrayList<Location> nextLocationList1 = getValid(getLocation());
		    nextLocationList1.add(getLocation());
			crossLocation.push(nextLocationList1);
			last = getLocation();
		}
		if (isEnd || crossLocation.empty()) {
			return false;
		}
		else {
			ArrayList<Location> topArray = crossLocation.pop();
			System.out.println("Get the top array:" + topArray.toString());
			
			// go back
			if (topArray.size() == 1 || topArray.size() == 0) {
				if (topArray.size() == 0) {
					if (crossLocation.empty()) {
						
						return false;
					}
					topArray = crossLocation.pop();
				}
				System.out.println("Go back" + topArray.get(0));
				next = topArray.get(0);
				return true;
			}
			if (topArray.size() != 0) {
				crossLocation.push(topArray);
			}
			
			int randomselectone = (int)(Math.random() * topArray.size() - 1);
			next = topArray.remove(randomselectone);
			last = getLocation();
			System.out.println("next:" + next.toString());
			System.out.println("last:" + last.toString());
			
			ArrayList<Location> nextLocationList = getValid(next);
			System.out.println("nextLocationList:" + nextLocationList.toString());
			nextLocationList.add(last);
			crossLocation.push(nextLocationList);
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		// check whether the terminal is around
		int directions[] = {0, 90, 180, 270};
		for (int i = 0; i < 4; i++) {
			Location newloc = loc.getAdjacentLocation(getDirection() + directions[i]);
			if (gr.isValid(newloc) && gr.get(newloc) != null && (gr.get(newloc) instanceof Rock) && (gr.get(newloc)).getColor().getRed() == Color.RED.getRed()) {
				isEnd = true;
				System.out.println("finish!");
				return;
			}
		
		}
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
