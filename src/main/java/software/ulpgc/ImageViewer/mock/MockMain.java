package software.ulpgc.ImageViewer.mock;

import software.ulpgc.ImageViewer.view.ViewPort;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MockMain extends JFrame {

    private final BufferedImage image;

    public MockMain() throws HeadlessException, IOException {
        this.setTitle("Image");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.add(panel());
        image = ImageIO.read(Objects.requireNonNull(MockMain.class.getResourceAsStream("/image1.jpg")));
    }

    private Component panel() throws IOException {
        return new JPanel() {
            @Override
            public void paint(Graphics g) {
                clean(g);
                g.drawImage(image, calculateViewPort().x(), calculateViewPort().y(), calculateViewPort().width(), calculateViewPort().height(), this);
            }

            private ViewPort calculateViewPort() {
                return ViewPort.ofSize(this.getWidth(), this.getHeight())
                        .fit(image.getWidth(), image.getHeight());
            }

            private void clean(Graphics g) {
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    public static void main(String[] args) throws IOException {
        new MockMain().setVisible(true);
    }
}
