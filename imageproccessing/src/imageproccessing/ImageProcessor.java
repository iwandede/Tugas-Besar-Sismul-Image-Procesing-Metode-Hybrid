/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageproccessing;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *
 * @author Kelompok 5 if9
 */
    public class ImageProcessor extends JFrame {
    private Picture pic = new Picture(400, 400);//untuk ukuran frame
    private JFileChooser chooser = new JFileChooser();

    public ImageProcessor() {
        setTitle("Image Processing Kelompok 5 IF9"); //untuk nama title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());//untuk menampilkan menu dan submenunya
        setResizable(false);
        setContentPane(pic.getJLabel());
        pack();
        setVisible(true);
        
    }
    
    // membuat menu bar
    public JMenuBar createMenuBar() {
        JMenu menu;
        JMenuItem menuItem;
        String frame;

        // membuat menu bar
        JMenuBar menuBar = new JMenuBar();

        // pembuatan menu
        menu = new JMenu("File");//menu File
        menu.setMnemonic(KeyEvent.VK_F); 
        menuBar.add(menu);
        menuItem = new JMenuItem("Open File...");//submenunya
        menuItem.addActionListener(new OpenFileListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Save As...");//submenunya
        menuItem.addActionListener(new SaveAsListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Reset");//submenunya
        menuItem.addActionListener(new resetFileListener());
        menu.add(menuItem);

        // bagian menu process
        menu = new JMenu("Process");//menu kedua
        menu.setMnemonic(KeyEvent.VK_P); 
        menuBar.add(menu);
        menuItem = new JMenuItem("Flip Horizontal");//submenunya
        menuItem.addActionListener(new FlipHorizontalListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Brighten");
        menuItem.addActionListener(new BrigtenListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Darken");
        menuItem.addActionListener(new DarkenListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("HSV");
        menuItem.addActionListener(new ColorSeparationListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Grayscale");
        menuItem.addActionListener(new GrayscaleListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Wave");
        menuItem.addActionListener(new WaveListener());
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Contour");
        menuItem.addActionListener(new ContourListener());
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Swirl");
        menuItem.addActionListener(new SwirlListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("RGB");

       

        return menuBar;
    }

    // open a file dialog when the user selects "Open" from the menu
    private class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (chooser.showOpenDialog(ImageProcessor.this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                pic = new Picture(file);
                setContentPane(pic.getJLabel());
                pack();
            }
        }
    }
    private class resetFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                File file = chooser.getSelectedFile();
                pic = new Picture(file);
                setContentPane(pic.getJLabel());
                pack();
        }
    }
    // open a save dialog when the user selects "Save As" from the menu
    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (chooser.showSaveDialog(ImageProcessor.this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                pic.save(file);
            }
        }
    }

    // flip the image horizontally
    private class FlipHorizontalListener implements ActionListener {
        long start;
        long end;
        Component frame = null;
        public void actionPerformed(ActionEvent e) {
            int width  = pic.width();
            int height = pic.height();
            Picture R = new Picture(width, height);
            Picture G = new Picture(width, height);
            Picture B = new Picture(width, height);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width / 2; x++) {
                    Color c1 = pic.get(x, y);
                    Color c2 = pic.get(width - x - 1, y);
                    pic.set(x, y, c2);
                    pic.set(width - x - 1, y, c1);
                }
            }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            repaint();
            }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }

    // membuat efek brigten the image
    public class BrigtenListener implements ActionListener {
        long start;
        long end;
        Component frame = null;
        public void actionPerformed(ActionEvent e) {
            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                    Color c = pic.get(x, y);
                    c = c.brighter();
                    pic.set(x, y, c);
                }
            }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            repaint();
            }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }

    // membuat efek darken the image
    private class DarkenListener implements ActionListener {
        long start;
        long end;
        Component frame = null;
        public void actionPerformed(ActionEvent e) {
            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                    Color c = pic.get(x, y);
                    c = c.darker();
                    pic.set(x, y, c);
                }
            }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            repaint();
         }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }

// pemanggilan class color yang ada di dalam package image processor
    private class ColorSeparationListener implements ActionListener {
        long start;
        long end;
        Component frame = null;
        public void actionPerformed(ActionEvent e) {
            int width  = pic.width();
            int height = pic.height();
            Picture R = new Picture(width, height);
            Picture G = new Picture(width, height);
            Picture B = new Picture(width, height);
            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                    Color c = pic.get(x, y);
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    R.set(x, y, new Color(r, 0, 0));
                    G.set(x, y, new Color(0, g, 0));
                    B.set(x, y, new Color(0, 0, b));
                }
            }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            R.show();
            G.show();
            B.show();
        }catch (Exception ex){}
        end = System.currentTimeMillis();
        JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }

    // fungsi untuk membuat efek Wave
    private class WaveListener implements ActionListener{
            long start;
            long end;
            Component frame = null;
        public void actionPerformed(ActionEvent e) {
            int width  = pic.width();
            int height = pic.height();

            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                int yy = y;
                int xx = (int) (x + 20 * Math.sin(y* 2 * Math.PI / 64));
                    if (xx >= 0 && xx < width) {
                    pic.set(x, y, pic.get(xx, yy));
                    }
                }
            }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            repaint();
            }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }
    // fungsi untuk membuat efek swirl
    private class SwirlListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int width  = pic.width();
            int height = pic.height();
            long start;
            long end;
            Component frame = null;
            double x0 = 0.5 * (width- 1);
            double y0 = 0.5 * (height - 1);

            for (int sx = 0; sx < width; sx++) {
            for (int sy = 0; sy < height; sy++) {
                double dx = sx - x0;
                double dy = sy - y0;
                double r = Math.sqrt(dx*dx + dy*dy);
                double angle = Math.PI / 256 * r;
                int tx = (int) (+dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
                int ty = (int) (+dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

                // plot pixel (sx, sy) the same color as (tx, ty) if it's in bounds
                if (tx >= 0 && tx < width && ty >= 0 && ty < height)
                    pic.set(sx, sy, pic.get(tx, ty));
            }
        }
        start = System.currentTimeMillis();//menghitung waktu proses dalam detik
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        try{
            repaint();
        }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }
    private class GrayscaleListener implements ActionListener {
        long start;
        long end;
        Component frame = null;
        public void actionPerformed(ActionEvent e) {
            for (int x = 0; x < pic.width(); x++) {
                for (int y = 0; y < pic.height(); y++) {
                    Color color = pic.get(x, y);
                    Color gray = Luminance.toGray(color);
                    pic.set(x, y,gray);
                }
            }
            start = System.currentTimeMillis();//menghitung waktu proses dalam detik
            BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
            try{
            repaint();
            }catch (Exception ex) { }
                end = System.currentTimeMillis();           
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }
    
    private class ContourListener implements ActionListener{
      long start;
      long end;
      Component frame = null;
      public int truncate(int a) {
        if(a < 0) return 0;
        else if (a > 255) return 255;
        else return a;
        }
       public void actionPerformed(ActionEvent e) {
            int[][] filter1 = { {-1,0,1},
                                {-2,0,2},
                                {-1,0,1}
                              };
            int[][] filter2 = { {  1,  2,  1 },
                                {  0,  0,  0 },
                                { -1, -2, -1 }
                              };

            //Picture pic0 = new Picture(args[0]);
            int width    = pic.width();
            int height   = pic.height();
            Picture pic1 = new Picture(width, height);


            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {

                    // get 3-by-3 array of colors in neighborhood
                    int[][] gray = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            gray[i][j] = (int) Luminance.lum(pic.get(x-1+i, y-1+j));
                        }
                    }

                    // apply filter
                    int gray1 = 0, gray2 = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            gray1 += gray[i][j] * filter1[i][j];
                            gray2 += gray[i][j] * filter2[i][j];
                        }
                    }
                    //int magnitude = 255 - truncate(Math.abs(gray1) + Math.abs(gray2));
                    int magnitude = 255 - truncate((int) Math.sqrt(gray1*gray1 + gray2*gray2));
                    Color grayscale = new Color(magnitude, magnitude, magnitude);
                    pic1.set(x, y, grayscale);
                }
            }
            //pic.show();
            start = System.currentTimeMillis();//menghitung waktu proses dalam detik
            BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
            try{
            pic1.show();
            // pic1.save("baboon-edge.jpg");
            }catch (Exception ex) { }
                end = System.currentTimeMillis();
                JOptionPane.showMessageDialog(frame,"\nWaktu yang diperlukan selama proses adalah " + ((end - start) / 1000.0) + " detik");
        }
    }
      
    // create one frame object
    public static void main(String[] args) {

            new ImageProcessor();//mainya untuk ditampilkan
        
    }
}
