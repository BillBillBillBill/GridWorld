import imagereader.IImageIO;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;  

public class ImplementImageIO implements IImageIO {
	private static final int FILEHEADER = 14;
	private static final int INFOHEADER = 40;
	private static final int Oxff = 0xff;
	private boolean isGray = false;

	public Image myRead(String filePath) {
		Image image;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			byte fileheader[] = new byte[FILEHEADER];
			byte infoheader[] = new byte[INFOHEADER];
			fis.read(fileheader, 0,FILEHEADER);
			fis.read(infoheader, 0,INFOHEADER);
			// 18-21 保存位图宽度（以像素个数表示）
			int width = (((int)infoheader[7] & Oxff) << 24) |
						(((int)infoheader[6] & Oxff) << 16) |
						(((int)infoheader[5] & Oxff) << 8)  |
						(((int)infoheader[4] & Oxff));
			// 22-25 保存位图高度（以像素个数表示）
			int height = (((int)infoheader[11] & Oxff) << 24) |
						 (((int)infoheader[10] & Oxff) << 16) |
					 	 (((int)infoheader[9] & Oxff) << 8)   |
						 (((int)infoheader[8] & Oxff));

			// 34-37    保存图像大小。这是原始（:en:raw）位图数据的大小，不要与文件大小混淆。
			int size = (((int)infoheader[23] & Oxff) << 24)  |
					   (((int)infoheader[22] & Oxff) << 16)  |
					   (((int)infoheader[21] & Oxff) << 8)   |
					   (((int)infoheader[20] & Oxff));

			int biBitCount = (int)infoheader[14]  & Oxff;
			int padding;
			// 行补位
			if (biBitCount == 8) {
				isGray = true;
				padding = size / height - width;
				fis.skip(1024);
			}
			else {
				isGray = false;
				padding = size / height - width * 3;
			}

			// System.out.println(width);
			// System.out.println(height);
			// System.out.println(size);
			// System.out.println(padding);
			// System.out.println(biBitCount);

			int data[] = new int[height * width];
			byte rgbData[] = new byte[size];
			fis.read(rgbData, 0, size);

			int index = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int realindex = (height - 1 -i) * width + j;
					
					if (biBitCount == 8) {
						data[realindex] = ((255 & Oxff) << 24) |
						(((int)rgbData[index] & Oxff) << 16) |
						(((int)rgbData[index] & Oxff) << 8)  |
						(((int)rgbData[index] & Oxff));
						index++;
					} else {
						data[realindex] = ((255 & Oxff) << 24) |
						(((int)rgbData[index+2] & Oxff) << 16) |
						(((int)rgbData[index+1] & Oxff) << 8)  |
						(((int)rgbData[index] & Oxff));
						index += 3;
					}
					
				}
				index += padding;
			}
			fis.close();
			Image i = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(  
                           width, height, data, 0, width)); 
			return i; 

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (Image)null;
	}


	public Image myWrite(Image image, String filePath) {
		try {
			int w = image.getWidth(null);
			int h = image.getHeight(null);
			BufferedImage bi;
			// System.out.println(isGray);
			if (isGray) {
				bi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			}
			else {
				bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
			}
			
			(bi.getGraphics()).drawImage(image, 0, 0, null);
			ImageIO.write(bi, "bmp", new File(filePath));
			return image;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (Image)null;
	}

}
