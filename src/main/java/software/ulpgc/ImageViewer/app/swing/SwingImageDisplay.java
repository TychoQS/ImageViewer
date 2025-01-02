package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.ImageDeserializer;
import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;
import software.ulpgc.ImageViewer.architecture.view.ViewPort;

import javax.swing.*;
import java.awt.*;


public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private Image currentImage;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCurrentImage(g);
    }

    private void drawCurrentImage(Graphics g) {
        java.awt.Image image = deserialize();
        ViewPort viewPort = createViewPort(image);
        g.drawImage(image, viewPort.x(), viewPort.y(), viewPort.width(), viewPort.height(), null);
    }

    private ViewPort createViewPort(java.awt.Image image) {
        return ViewPort.ofSize(getWidth(), getHeight())
                       .fit(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void display(Image image) {
        currentImage = image;
    }

    private java.awt.Image deserialize() {
        return (java.awt.Image) deserializer.deserialize(currentImage.content());
    }

    @Override
    public void displayNext() {
        display(currentImage.next());
    }

    @Override
    public void displayPrevious() {
        display(currentImage.previous());
    }


}
