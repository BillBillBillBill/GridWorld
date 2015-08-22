import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.*;
import java.lang.System;
import java.awt.Color;
import java.util.ArrayList;


public class Cell extends Critter {
    private int type;
    private int age;
    private int maxAge;
    private int generation;
    private Color color;
    private static int rednum = 0;
    private static int greennum = 0;
    private static int bluenum = 0;
    private static int maxnum = 100;
    
    
    private static final double DARKENING_FACTOR = 0.05;
    private static final double BRIGHTENING_FACTOR = 0.05;
    private static final Color[] COLOR_LIST = { Color.BLUE, Color.RED, Color.GREEN };

    
    public Cell() {
    	type = (int)(Math.random() * 3);
    	age = 0;
    	maxAge = 20;
    	generation = 0;
    	color = COLOR_LIST[type];
    }
    
    public Cell(int type, int generation) {
    	this.type = type;
    	
    	if (type == 0) {
    		bluenum++;
    	}
    	else if (type == 1) {
    		rednum++;
    	}
    	else if (type == 2) {
    		greennum++;
    	}
    	age = 0;
    	maxAge = 200;
    	this.color = COLOR_LIST[type];
    	this.generation = generation;
    }
    
    public void setAge(int a) {
    	age = a;
    }
    
    public void setColor(Color c) {
    	color = c;
    }
    
    public Color getColor() {
    	return color;
    }
    
    public void setType(int t) {
    	type = t;
    }
    
    public int getType() {
    	return type;
    }
    
    public int getGeneration() {
    	return generation;
    }
    
    public void processCell(Cell a) {
    	int t1 = getType();
    	int t2 = a.getType();
    	if (t1 == 0 && t2 == 1) {
    		a.removeSelfFromGrid();
    	}
    	else if (t1 == 0 && t2 == 2) {
    		removeSelfFromGrid();
    	}
    	else if (t1 == 1 && t2 == 2) {
    		a.removeSelfFromGrid();
    	}
    	else if (t1 == 1 && t2 == 0) {
    		removeSelfFromGrid();
    	}
    	else if (t1 == 2 && t2 == 0) {
    		a.removeSelfFromGrid();
    	}
    	else if (t1 == 2 && t2 == 1) {
    		removeSelfFromGrid();
    	}

    	
    }
    
    public void act() {
    	// after each act, the age increase
    	age++;
    	if (age > maxAge) {
        	if (type == 0) {
        		bluenum--;
        	}
        	else if (type == 1) {
        		rednum--;
        	}
        	else if (type == 2) {
        		greennum--;
        	}
    		removeSelfFromGrid();
    	}
    	// if it is old ,it can't move and get dark
    	if (isOld()) {
    		Color c = getColor();
    		int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
    		int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
    		int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
    		setColor(new Color(red, green, blue));
    		return;
    	}
    	super.act();
    }
    
    public void getBrighter() {
		Color c = getColor();
		int red = Math.min((int) (c.getRed() * (1 + BRIGHTENING_FACTOR)), 255);
		int green = Math.min((int) (c.getGreen() * (1 + BRIGHTENING_FACTOR)), 255);
		int blue = Math.min((int) (c.getBlue() * (1 + BRIGHTENING_FACTOR)), 255);
		setColor(new Color(red, green, blue));
    }
    
    public void createNewCell(int type, int generation, Location loc) {

    	if (type == 0 && bluenum > maxnum + (int)(Math.random() * 15) ||
    		type == 1 && rednum > maxnum + (int)(Math.random() * 15)  ||
    		type == 2 && greennum > maxnum + (int)(Math.random() * 15)
    		)
    	{
    		return;
    	}

    	Cell newCell = new Cell(type, generation+1);
    	Grid<Actor> gr = getGrid();
    	if (gr.isValid(loc) && gr.get(loc) == null) {
    		newCell.putSelfInGrid(gr, loc);
    	}
    }
    
    public boolean isOld() {
    	return age > maxAge;
    }
    
    public boolean testAngle() {
		Location location1 = getLocation();
		Location location2 = location1.getAdjacentLocation(getDirection());
		Grid<Actor> gr = getGrid();
		if (gr.isValid(location2) && gr.get(location2) instanceof Cell) {
			Cell anotherCell = (Cell) gr.get(location2);
			Location a = location2.getAdjacentLocation(anotherCell.getDirection());
			return  a.equals(location1);
		}
		return false;
    }
    
    public void processActors(ArrayList<Actor> actors) {
    	Grid<Actor> gr = getGrid();
    	int n = actors.size();
    	if (n == 0) {
    		return;
    	}
  
//    	int r = (int) (Math.random() * n);
//        Actor actor = actors.get(r);
//        
        for (Actor actor : actors) {
            if (actor instanceof Cell) {
            	Cell anotherCell = (Cell) actor;
            	int otherCellType = anotherCell.getType();
            	// if it meet the die cell, clear it, and get brighter
            	
//            	if (getType() == 2 && anotherCell.isOld()) {
//            		getBrighter();
//            		maxAge++;
//            		anotherCell.removeSelfFromGrid();
//            	}
            	// the type is same, 
            	if (getType() == otherCellType) {
            		if (testAngle()) {
                		ArrayList<Location> locationList = gr.getEmptyAdjacentLocations(getLocation());
                		Location insertLocation;
                		if (locationList.size() != 0) {
                			int r = (int)(Math.random() * locationList.size());
                			insertLocation = locationList.get(r);
                			createNewCell(getType(), getGeneration(), insertLocation);
                		}
            		}

            	}
            	else {
                	int t1 = getType();
                	int t2 = anotherCell.getType();
                	if (gr.get(getLocation()) != null && gr.get(anotherCell.getLocation()) != null ) {
                	if (t1 == 0 && t2 == 1) {
                		Location a = getLocation();
                		int direction = a.getDirectionToward(anotherCell.getLocation());
                		anotherCell.setDirection(direction);
                	}
                	else if (t1 == 0 && t2 == 2) {
                		Location a = anotherCell.getLocation();
                		int direction = a.getDirectionToward(getLocation());
                		setDirection(direction);
                	}
                	else if (t1 == 1 && t2 == 2) {
                		Location a = getLocation();
                		int direction = a.getDirectionToward(anotherCell.getLocation());
                		anotherCell.setDirection(direction);
                	}
                	else if (t1 == 1 && t2 == 0) {
                		Location a = anotherCell.getLocation();
                		int direction = a.getDirectionToward(getLocation());
                		setDirection(direction);
                	}
                	else if (t1 == 2 && t2 == 0) {
                		Location a = getLocation();
                		int direction = a.getDirectionToward(anotherCell.getLocation());
                		anotherCell.setDirection(direction);
                	}
                	else if (t1 == 2 && t2 == 1) {
                		Location a = anotherCell.getLocation();
                		int direction = a.getDirectionToward(getLocation());
                		setDirection(direction);
                	}
                	}
            	}
            	
            }
        }

    }
    
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    
    
    
}
