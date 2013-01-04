/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageproccessing;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

/**
 *
 * @author littlestar
 */
    public class ImageProcessor extends JFrame {
    private Picture pic = new Picture(256, 256);
    private JFileChooser chooser = new JFileChooser();

    // create the frame with an empty image
    public ImageProcessor() {
        setTitle("Image Processor 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        setResizable(false);
        setContentPane(pic.getJLabel());
        pack();
        setVisible(true);
        
    }
    
    // create the menu bar
    public JMenuBar createMenuBar() {
        JMenu menu;
        JMenuItem menuItem;

        // create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // build the File menu
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);      // only needed for Alt-f keyboard shortcut
        menuBar.add(menu);
        menuItem = new JMenuItem("Open File...");
        menuItem.addActionListener(new OpenFileListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Save As...");
        menuItem.addActionListener(new SaveAsListener());
        menu.add(menuItem);

        // build the Process menu
        menu = new JMenu("Process");
        menu.setMnemonic(KeyEvent.VK_P);      // only needed for Alt-p keyboard shortcut
        menuBar.add(menu);
        menuItem = new JMenuItem("Flip Horizontal");
        menuItem.addActionListener(new FlipHorizontalListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Brighten");
        menuItem.addActionListener(new BrigtenListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Darken");
        menuItem.addActionListener(new DarkenListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Color Separation");
        menuItem.addActionListener(new ColorSeparationListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Grayscale");
        menuItem.addActionListener(new GrayscaleListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Wave");
        menuItem.addActionListener(new WaveListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Swirl");
        menuItem.addActionListener(new SwirlListener());
        menu.add(menuItem);

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
            repaint();
        }
    }

    // brigten the image
    public class BrigtenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                    Color c = pic.get(x, y);
                    c = c.brighter();
                    pic.set(x, y, c);
                }
            }
            repaint();
        }
    }

    // darken the image
    private class DarkenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int y = 0; y < pic.height(); y++) {
                for (int x = 0; x < pic.width(); x++) {
                    Color c = pic.get(x, y);
                    c = c.darker();
                    pic.set(x, y, c);
                }
            }
            repaint();
        }
    }

    
    
    // create one frame object
    public static void main(String[] args) {
        new ImageProcessor();
    }
}
