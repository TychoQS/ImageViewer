package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.ImageDeserializer;
import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;
import software.ulpgc.ImageViewer.architecture.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private final List<PaintOrder> paintOrders;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
        this.paintOrders = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCurrentPaintOrders(g);
    }

    private void drawCurrentPaintOrders(Graphics g) {
        for (PaintOrder paintOrder : paintOrders) {
            java.awt.Image image = deserialize(paintOrder.content);
            ViewPort viewPort = createViewPort(image);
            g.drawImage(image, viewPort.x(), viewPort.y(), viewPort.width(), viewPort.height(), null);
        }
    }

    private ViewPort createViewPort(java.awt.Image image) {
        return ViewPort.ofSize(getWidth(), getHeight())
                       .fit(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void display(Image image) {
        paintOrders.clear();
        paintOrders.add(getPaintOrderFrom(image));
        repaint();
    }

    private java.awt.Image deserialize(byte[] content) {
        return (java.awt.Image) deserializer.deserialize(content);
    }

    private static PaintOrder getPaintOrderFrom(Image image) {
        return new PaintOrder(image.content(), 0);
    }

    private record PaintOrder(byte[] content, int offset) {}

}
