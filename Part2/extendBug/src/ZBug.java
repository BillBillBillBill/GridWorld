/* 
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @13331093
 */
import info.gridworld.actor.Bug;

// ZBug Class
// extend from Bug
public  class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int moveTime;

    // define the constant
    private static final int EAST = 90;
    private static final int SOUTHWEST = 225;
    private static final int ZPATTERNLENGTH = 4;
    private static final int MAXMOVETIME = 3;
    private static final int FIRSTTURN = 1;
    private static final int SECONDTURN = 2;

    /**
     * @param length the side length
     */
    public ZBug()
    {
        steps = 0;
        sideLength = ZPATTERNLENGTH;
        moveTime = 0;
        // at first it should face east
        setDirection(EAST);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        // if complete the Z pattern or ZBug can't move it stop
        if (moveTime >= MAXMOVETIME || (!canMove() && steps != ZPATTERNLENGTH))
        {
            return;
        }
        // move to next location
        if (steps < sideLength)
        {
            move();
            steps++;
            if (steps == ZPATTERNLENGTH && moveTime != MAXMOVETIME)
            {
                moveTime++;
            }
        }
        else
        {
            // complete the first '-'
            if (moveTime == FIRSTTURN)
            {
                setDirection(SOUTHWEST);
            }
            // complete the '/'
            if (moveTime == SECONDTURN)
            {
                setDirection(EAST);
            }
            // start a new way
            steps = 0;
        }
    }
}
