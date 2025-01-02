package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.ImageDeserializer;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SwingImageDeserializer implements ImageDeserializer {
    @Override
    public Object deserialize(byte[] content) {
        try {
            return ImageIO.read(asInputStream(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream asInputStream(byte[] content) {
        return new ByteArrayInputStream(content);
    }
}
