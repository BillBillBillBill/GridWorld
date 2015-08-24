import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead()  throws Exception {
		FileInputStream in = new FileInputStream("../bmptest/1.bmp");
		BufferedImage goal = ImageIO.read(in);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image myimg = imageioer.myRead("../bmptest/1.bmp");
		int h = myimg.getHeight(null);
		int w = myimg.getWidth(null);
		BufferedImage mybi = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        mybi.getGraphics().drawImage(myimg, 0, 0, null);
        // 比较位图高度
        assertEquals(goal.getHeight(), mybi.getHeight());
        // 比较位图宽度
        assertEquals(goal.getWidth(), mybi.getWidth());

       int result[][] = new int[w][h];
       int minx = goal.getMinX();
       int miny = goal.getMinY();

       // 比较位图像素值
       // test all pixel rgb on image
       for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
             assertEquals(goal.getRGB(i, j) & 0xFFFFFF, mybi.getRGB(i, j) & 0xFFFFFF);
            }
       }
	}

	@Test
	public void testRead2()  throws Exception {
		FileInputStream in = new FileInputStream("../bmptest/2.bmp");
		BufferedImage goal = ImageIO.read(in);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image myimg = imageioer.myRead("../bmptest/2.bmp");
		int h = myimg.getHeight(null);
		int w = myimg.getWidth(null);
		BufferedImage mybi = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        mybi.getGraphics().drawImage(myimg, 0, 0, null);
        // 比较位图高度
        assertEquals(goal.getHeight(), mybi.getHeight());
        // 比较位图宽度
        assertEquals(goal.getWidth(), mybi.getWidth());

       int result[][] = new int[w][h];
       int minx = goal.getMinX();
       int miny = goal.getMinY();

       // 比较位图像素值
       // test all pixel rgb on image
       for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
             assertEquals(goal.getRGB(i, j) & 0xFFFFFF, mybi.getRGB(i, j) & 0xFFFFFF);
            }
       }
	}


	@Test
	public void testChanelR()  throws Exception {
		ImplementImageProcesser myprocesser = new ImplementImageProcesser();
		FileInputStream in = new FileInputStream("../bmptest/goal/1_red_goal.bmp");
		BufferedImage goal = ImageIO.read(in);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image myimg = imageioer.myRead("../bmptest/1.bmp");


		myimg = myprocesser.showChanelR(myimg);

		int h = myimg.getHeight(null);
		int w = myimg.getWidth(null);
		BufferedImage mybi = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        mybi.getGraphics().drawImage(myimg, 0, 0, null);
        // 比较位图高度
        assertEquals(goal.getHeight(), mybi.getHeight());
        // 比较位图宽度
        assertEquals(goal.getWidth(), mybi.getWidth());

       int result[][] = new int[w][h];
       int minx = goal.getMinX();
       int miny = goal.getMinY();

       // 比较位图像素值
       // test all pixel rgb on image
       for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
             assertEquals(goal.getRGB(i, j) & 0xFFFFFF, mybi.getRGB(i, j) & 0xFFFFFF);
            }
       }
	}


	@Test
	public void testChanelR2()  throws Exception {
		ImplementImageProcesser myprocesser = new ImplementImageProcesser();
		FileInputStream in = new FileInputStream("../bmptest/goal/2_red_goal.bmp");
		BufferedImage goal = ImageIO.read(in);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image myimg = imageioer.myRead("../bmptest/2.bmp");


		myimg = myprocesser.showChanelR(myimg);

		int h = myimg.getHeight(null);
		int w = myimg.getWidth(null);
		BufferedImage mybi = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        mybi.getGraphics().drawImage(myimg, 0, 0, null);
        // 比较位图高度
        assertEquals(goal.getHeight(), mybi.getHeight());
        // 比较位图宽度
        assertEquals(goal.getWidth(), mybi.getWidth());

       int result[][] = new int[w][h];
       int minx = goal.getMinX();
       int miny = goal.getMinY();

       // 比较位图像素值
       // test all pixel rgb on image
       for (int i = minx; i < w; i++) {
            for (int j = miny; j < h; j++) {
             assertEquals(goal.getRGB(i, j) & 0xFFFFFF, mybi.getRGB(i, j) & 0xFFFFFF);
            }
       }
	}



}