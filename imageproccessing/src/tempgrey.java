import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class tempgrey {
    public static void main(String[] args) {
        BufferedImage org = getImage("test.jpg");//valid input image
        BufferedImage greyImage = getGrayScaleAvg(org);
        new ImageFrame(org, "Original");
        new ImageFrame(greyImage, "After GrayScale");
    }

    public static BufferedImage getImage(String imageName) {
        try {
            File input = new File(imageName);
            BufferedImage image = ImageIO.read(input);
            return image;
        } catch (IOException ie) {
            System.out.println("Error:" + ie.getMessage());
        }
        return null;
    }

    public static BufferedImage getGrayScaleAvg(BufferedImage img) {
        int w1 = img.getWidth();
        int h1 = img.getHeight();
        // int value[][] = new int[w1][h1];
        BufferedImage gray = new BufferedImage(w1, h1, 1);// new image
        int value, alpha, r, g, b;
        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                value = img.getRGB(i, j); // read and store pixel value
                alpha = get_alpha(value);
                r = get_red(value);
                g = get_green(value);
                b = get_blue(value);

                value = (r + g + b) / 3; // grey by averaging the pixels
                r = g = b = value;
                value = create_rgb(alpha, r, g, b);
                gray.setRGB(i, j, value);
            }
        }
        return gray;
    }

    public static int create_rgb(int alpha, int r, int g, int b) {
        int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
        return rgb;
    }

    public static int get_alpha(int rgb) {
        return (rgb >> 24) & 0xFF;
        // return rgb & 0xFF000000;
    }

    public static int get_red(int rgb) {
        return (rgb >> 16) & 0xFF;
        // return rgb & 0x00FF0000;
    }

    public static int get_green(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int get_blue(int rgb) {
        return rgb & 0xFF;
    }
}