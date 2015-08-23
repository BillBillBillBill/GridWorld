
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.HashMap;
import java.util.*;

/**
 * UnboundedGrid2
 */

public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private int rowNum;
    private int colNum;
    private Object[][] occupantArray; 

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        rowNum = colNum = 16;
        occupantArray = new Object[rowNum][colNum];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < rowNum; r++)
        {
            for (int c = 0; c < colNum; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public void enlargeGrid(int newrow, int newcol) {
        Object[][] newoccupantArray = new  Object[newrow][newcol];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                newoccupantArray[i][j] = occupantArray[i][j];
            }
        }
        rowNum = newrow;
        colNum = newcol;
        occupantArray = newoccupantArray;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= rowNum || loc.getCol() >= colNum) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");

        if (loc.getRow() >= rowNum || loc.getCol() >= colNum) {

            int newRowSize = rowNum;
            int newColSize = colNum;
            while (loc.getRow() >= newRowSize || loc.getCol() >= newColSize) {
                    newRowSize *= 2;
                    newColSize *= 2;
            }

            enlargeGrid(newRowSize, newColSize);
            System.out.println(colNum);
        }
        
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= rowNum || loc.getCol() >= colNum) {
            return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
