package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMainFrame extends JFrame {
    public static final String TITLE = "ImageViewer";
    private final FileImageLoader loader;
    private SwingImageDisplay imageDisplay;

    public SwingMainFrame(FileImageLoader loader) {
        this.loader = loader;
        initFrame();
    }

    private void initFrame() {
        this.setTitle(TITLE);
        this.setSize(getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        addPanels();
        displayFirstImage();
    }

    private void displayFirstImage() {
        this.imageDisplay.display(loader.load());
    }

    private void addPanels() {
        this.add(imageDisplay = createImageDisplay());
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
