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


public final class CircleBugRunner
{
    // give it a constructor
    private CircleBugRunner()
    {
    }
    public static void main(String[] args)
    {
        // set the  parameters
        final int aliceLocationX = 7;
        final int aliceLocationY = 8;
        final int bobLocationX = 5;
        final int bobLocationY = 5;
        final int aliceSideLength = 6;
        final int bobSideLength = 3;
        // construct a ActorWorld
        ActorWorld world = new ActorWorld();
        // construct two bugs
        CircleBug alice = new CircleBug(aliceSideLength);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(bobSideLength);
        // add two bugs to the ActorWorld
        world.add(new Location(aliceLocationX, aliceLocationY), alice);
        world.add(new Location(bobLocationX, bobLocationY), bob);
        world.show();
    }
}