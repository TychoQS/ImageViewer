package software.ulpgc.ImageViewer.io;

import software.ulpgc.ImageViewer.model.Image;

public interface ImageDeserializer {
    Image deserialize(byte[] content);
}
