import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock; 
import info.gridworld.grid.Grid; 
import java.awt.Color;

public class JumperTest {

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }

    //base init
    //test color
    @Test
    public void testInitialize() {
        Jumper jumper1 = new Jumper();
        assertEquals(jumper1.getColor(), Color.RED);
    }

    //construt with a parameter    
    @Test
    public void testInitialize2() {
        Jumper jumper1 = new Jumper(Color.BLUE);
        assertEquals(jumper1.getColor(), Color.BLUE);
    }

    //检测前方无物时，是否正常移动
    @Test
    public void testAct1() {     
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 3), jumper);
        jumper.act();
        //检测前方无物时，是否正常移动
        assertEquals(jumper.getLocation(), new Location(1,3));
    }

    //检测前方超出边界时，是否正常移动
    @Test
    public void testAct2() {     
        Jumper jumper = new Jumper();
    ActorWorld world = new ActorWorld();
        world.add(new Location(1, 8), jumper);
        jumper.act();
        assertEquals(jumper.getLocation(), new Location(1,8));
    }

    // 检测位置是否发生变化,检测角度是否变化
    @Test
    public void testAct3() {     

        Jumper jumper3 = new Jumper();
        Rock rock = new Rock();
        ActorWorld world = new ActorWorld();
        world.add(new Location(6, 6), rock);
        world.add(new Location(8, 6), jumper3);
        jumper3.act();
    // 检测位置
        assertEquals(jumper3.getLocation(), new Location(8,6));
    // 检测角度
        assertEquals(jumper3.getDirection(), 45);
    }


    @Test
    public void testTurn1() {
       Jumper jumper = new Jumper();
       ActorWorld world = new ActorWorld();
       world.add(new Location(3, 3), jumper);
       jumper.turn();
       assertEquals(jumper.getDirection(), 45);
    }

    @Test
    public void testTurn2() {
       Jumper jumper = new Jumper();
       ActorWorld world = new ActorWorld();
       world.add(new Location(1, 8), jumper);
       jumper.setDirection(180);
       jumper.turn();
       assertEquals(jumper.getDirection(), 225);
    }

    @Test
    public void testCanMove() {
       ActorWorld world = new ActorWorld();
       Jumper jumper = new Jumper();
       Rock rock = new Rock();
       Flower flower = new Flower();
       Bug bug = new Bug();
       //add a jumper at (3,3)
       world.add(new Location(3, 3), jumper);
       //检测前面没有障碍物的时候canmove是否返回true
       assertEquals(jumper.canMove(), true);
       world.add(new Location(1, 3), rock);
       //检测前面有rock的时候canmove是否返回false
       assertEquals(jumper.canMove(), false);
       rock.removeSelfFromGrid();
       world.add(new Location(1, 3), flower);
       //检测前面有rock的时候canmove是否返回false
       assertEquals(jumper.canMove(), false);
       flower.removeSelfFromGrid();
       world.add(new Location(1, 3), bug);
       //检测前面有rock的时候canmove是否返回false
       assertEquals(jumper.canMove(), false);
       bug.removeSelfFromGrid();
    }

}

