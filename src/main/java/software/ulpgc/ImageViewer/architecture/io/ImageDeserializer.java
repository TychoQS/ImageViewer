package software.ulpgc.ImageViewer.architecture.io;

public interface ImageDeserializer {
    Object deserialize(byte[] content);
}
