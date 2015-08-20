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


public  class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int moveTime;
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
        setDirection(EAST);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {

        if (moveTime >= MAXMOVETIME || (!canMove() && steps != ZPATTERNLENGTH))
        {
            return;
        }
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
            if (moveTime == FIRSTTURN)
            {
                setDirection(SOUTHWEST);
            }
            if (moveTime == SECONDTURN)
            {
                setDirection(EAST);
            }
            steps = 0;
        }
    }
}
