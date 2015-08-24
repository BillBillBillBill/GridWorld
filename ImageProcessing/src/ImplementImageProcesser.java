import imagereader.IImageProcessor;
import java.awt.*;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcesser implements IImageProcessor {

	private static final double RGRAY = 0.299;
	private static final double GGRAY = 0.587;
	private static final double BGRAY = 0.114;


	class RedFilter extends RGBImageFilter {

            public RedFilter() {
                // The filter's operation does not depend on the
                // pixel's location, so IndexColorModels can be
                // filtered directly.
                canFilterIndexColorModel = true;
            }

            public int filterRGB(int x, int y, int rgb) {
                return rgb & 0xffff0000;
            }
        }

    class GreenFilter extends RGBImageFilter {
		
            public GreenFilter() {
                // The filter's operation does not depend on the
                // pixel's location, so IndexColorModels can be
                // filtered directly.
                canFilterIndexColorModel = true;
            }

            public int filterRGB(int x, int y, int rgb) {
                return rgb & 0xff00ff00;
            }
        }

    class BlueFilter extends RGBImageFilter {
		
            public BlueFilter() {
                // The filter's operation does not depend on the
                // pixel's location, so IndexColorModels can be
                // filtered directly.
                canFilterIndexColorModel = true;
            }

            public int filterRGB(int x, int y, int rgb) {
                return rgb & 0xff0000ff;
            }
        }

    class GrayFilter extends RGBImageFilter {
		
            public GrayFilter() {
                // The filter's operation does not depend on the
                // pixel's location, so IndexColorModels can be
                // filtered directly.
                canFilterIndexColorModel = true;
            }

            public int filterRGB(int x, int y, int rgb) {
            	int r = (rgb & 0x00ff0000) >> 16;
            	int g = (rgb & 0x0000ff00) >> 8;
            	int b = (rgb & 0x000000ff);

            	int newrgb = (int)((double)(r * RGRAY) + (double)(g * GGRAY) + (double)(b * BGRAY));
            	int alpha = rgb & 0xff000000;
                return alpha | (newrgb << 16) | (newrgb << 8) | newrgb;
            }
        }



	public Image showChanelR(Image sourceImage) {
		ImageFilter colorfilter = new RedFilter();
		Image img = (Toolkit.getDefaultToolkit()).createImage(new FilteredImageSource(sourceImage.getSource(), colorfilter));
		return img;
	}

	public Image showChanelG(Image sourceImage) {
		ImageFilter colorfilter = new GreenFilter();
		Image img = (Toolkit.getDefaultToolkit()).createImage(new FilteredImageSource(sourceImage.getSource(), colorfilter));
		return img;
	}

	public Image showChanelB(Image sourceImage) {
		ImageFilter colorfilter = new BlueFilter();
		Image img = (Toolkit.getDefaultToolkit()).createImage(new FilteredImageSource(sourceImage.getSource(), colorfilter));
		return img;
	}

	public Image showGray(Image sourceImage) {
		ImageFilter colorfilter = new GrayFilter();
		Image img = (Toolkit.getDefaultToolkit()).createImage(new FilteredImageSource(sourceImage.getSource(), colorfilter));
		return img;
	}

}
