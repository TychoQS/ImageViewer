package software.ulpgc.ImageViewer.model;

public interface Image {
    Image next();
    Image previous();
    byte[] content();
}
