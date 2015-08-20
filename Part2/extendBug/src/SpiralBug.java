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

// SpiralBug Class
// extend from Bug
public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * @param length the side length
     */
    public SpiralBug(int length)
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
            // adjust the side length when the bug turns
            sideLength++;
            turn();
            turn();
            steps = 0;
        }
    }
}
