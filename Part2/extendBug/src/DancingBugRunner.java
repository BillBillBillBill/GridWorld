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
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;


public final class DancingBugRunner
{
    // give it a constructor
    private DancingBugRunner()
    {
    }
    public static void main(String[] args)
    {
        // set the  parameters
        final int aliceLocationX = 7;
        final int aliceLocationY = 8;
        final int bobLocationX = 5;
        final int bobLocationY = 5;
        final int turnListSize = 10;
        // construct a ActorWorld
        ActorWorld world = new ActorWorld();
        int turnList[] = new int[turnListSize];
        for (int i = 0; i < turnListSize; i++) {
            turnList[i] = i;
        }
        // construct two bugs
        DancingBug alice = new DancingBug(turnList);
        alice.setColor(Color.ORANGE);
        DancingBug bob = new DancingBug(turnList);
        // add two bugs to the ActorWorld
        world.add(new Location(aliceLocationX, aliceLocationY), alice);
        world.add(new Location(bobLocationX, bobLocationY), bob);
        world.show();
    }
}