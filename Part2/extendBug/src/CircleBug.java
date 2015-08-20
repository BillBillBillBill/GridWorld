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

// CircleBug Class
// extend from Bug
public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * @param length the side length
     */
    public CircleBug(int length)
    {
        // initialize the steps and sideLength
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        // try to move to next location
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            // turn only once, 45 degrees
            turn();
            steps = 0;
        }
    }

}
