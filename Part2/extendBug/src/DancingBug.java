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
import java.util.Arrays;

// DancingBug Class
// extend from Bug
// making different turns before each move. 
public class DancingBug extends Bug
{
    private int turnIndex;
    private int[] turnOrder;
    /**
     * @param turnList is the turn Order
     */
    public DancingBug(int[] turnList)
    {
        // initialize the turnIndex
        turnIndex = 0;
        // get turnOrder from the parameter
        if(turnList == null) { 
            this.turnOrder = new int[0]; 
        } else {
            this.turnOrder = Arrays.copyOf(turnList, turnList.length); 
        } 
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        // turn turnOrder[turnIndex] times
        for (int i = 0; i < turnOrder[turnIndex]; i++) {
            turn();
        }
        turnIndex++;
        // After carrying out the last turn in the array
        //it start again with the initial array value
        if (turnIndex >= turnOrder.length) {
            turnIndex = 0;
        }
        // move to next location
        if (canMove())
        {
            move();
        }
    }
}
