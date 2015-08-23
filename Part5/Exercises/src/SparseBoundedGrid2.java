
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.HashMap;
import java.util.*;

/**
 * SparseBoundedGrid2, HashMap version
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private int rowNum;
    private int colNum;
    private Map<Location, E> occupantMap;

    /**
     * Constructs an empty SparseBounded Grid.
     */
    public SparseBoundedGrid2(int row, int col)
    {
        occupantMap = new HashMap<Location, E>();
        rowNum = row;
        colNum = col;
    }

    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        return colNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
}
