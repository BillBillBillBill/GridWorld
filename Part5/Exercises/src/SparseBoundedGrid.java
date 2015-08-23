
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * SparseBoundedGrid, LinkedList version
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<LinkedList> occupantArray; // the array storing the grid elements
    private int rowNum;
    private int colNum;
    
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rowNum = rows;
        colNum = cols;
        initOccupantArray();
    }

    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        return colNum;
    }
    
    private void initOccupantArray() {
        occupantArray = new ArrayList<LinkedList>();
    	for	(int i = 0; i < rowNum; i++) {
    		occupantArray.add(new LinkedList<OccupantInCol>());
    	}
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < rowNum
                && 0 <= loc.getCol() && loc.getCol() < colNum;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        for (int r = 0; r < occupantArray.size(); r++) {
            LinkedList<OccupantInCol> occupantCol = occupantArray.get(r);
            for (OccupantInCol object : occupantCol) {
                Location loc = new Location(r, object.getCol());
                theLocations.add(loc);
            }
        }

        // Look at all grid locations.
        // for (int r = 0; r < getNumRows(); r++)
        // {
        //     for (int c = 0; c < getNumCols(); c++)
        //     {
        //         // If there's an object at this location, put it in the array.
        //         Location loc = new Location(r, c);
        //         if (get(loc) != null)
        //             theLocations.add(loc);
        //     }
        // }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        LinkedList<OccupantInCol> occupantCol = occupantArray.get(loc.getRow());
        for (OccupantInCol object: occupantCol) {
        	if (object.getCol() == loc.getCol()) {
        		return (E) object.getObject();
        	}
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        if (oldOccupant != null)
        {
            LinkedList<OccupantInCol> occupantCol = occupantArray.get(loc.getRow());
        	for (OccupantInCol object: occupantCol) {
        		if (object.getCol() == loc.getCol()) {
        			object.setObject(obj);
        			break;
        		}
        	}
        }
        else
        {
        	(occupantArray.get(loc.getRow())).add(new OccupantInCol(loc.getCol(), obj));
        }
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantInCol> occupantCol = occupantArray.get(loc.getRow());

        for (OccupantInCol object: occupantCol) {
    		if (object.getCol() == loc.getCol()) {
    			occupantCol.remove(object);
    			break;
    		}
    	}
        return r;
    }
}
