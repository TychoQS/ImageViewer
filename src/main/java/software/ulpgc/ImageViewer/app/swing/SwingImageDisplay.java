package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.ImageDeserializer;
import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;
import software.ulpgc.ImageViewer.architecture.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private final List<PaintOrder> paintOrders;
    private Released released;
    private Dragged dragged;
    private int initialDraggingPosition;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
        this.paintOrders = new ArrayList<>();
        dragged = Dragged.Null;
        released = Released.Null;
        this.addMouseListener(createMouseListener());
        this.addMouseMotionListener(createMouseMotionListener());
    }

    private MouseMotionListener createMouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragged.position(e.getX() - initialDraggingPosition);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    private MouseListener createMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initialDraggingPosition = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.position(e.getX() - initialDraggingPosition);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
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
            g.drawImage(image, viewPort.x() + paintOrder.offset(), viewPort.y(), viewPort.width(), viewPort.height(), null);
        }
    }

    private ViewPort createViewPort(java.awt.Image image) {
        return ViewPort.ofSize(getWidth(), getHeight())
                       .fit(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void display(Image image, int offset) {
        paintOrders.add(getPaintOrderFrom(image, offset));
        repaint();
    }

    public void clear() {
        paintOrders.clear();
    }

    @Override
    public void onReleasing(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    @Override
    public void onDragging(Dragged dragged) {
        this.dragged = dragged != null ? dragged : Dragged.Null;
    }

    private java.awt.Image deserialize(byte[] content) {
        return (java.awt.Image) deserializer.deserialize(content);
    }

    private static PaintOrder getPaintOrderFrom(Image image, int offset) {
        return new PaintOrder(image.content(), offset);
    }

    private record PaintOrder(byte[] content, int offset) {}

}
