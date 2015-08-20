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

public class DancingBug extends Bug
{
    private int turnIndex;
    private int[] turnOrder;
    /**
     * @param length the side length
     */
    public DancingBug(int[] turnList)
    {
        turnIndex = 0;
        if(turnList == null) { 
            this.turnOrder = new int[0]; 
        } else {
            this.turnOrder = Arrays.copyOf(turnList, turnList.length); 
        } 
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        for (int i = 0; i < turnOrder[turnIndex]; i++) {
            turn();
        }
        turnIndex++;
        if (turnIndex >= turnOrder.length) {
            turnIndex = 0;
        }
        if (canMove())
        {
            move();
        }
    }
}
