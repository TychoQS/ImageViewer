package software.ulpgc.ImageViewer.architecture.io;

import software.ulpgc.ImageViewer.architecture.model.Image;

public interface ImageDeserializer {
    Object deserialize(byte[] content);
}
