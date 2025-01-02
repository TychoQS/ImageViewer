package software.ulpgc.ImageViewer.architecture.model;

public interface Image {
    Image next();
    Image previous();
    byte[] content();

    enum Format {
        jpeg, jpg, png, bmp
    }
}
