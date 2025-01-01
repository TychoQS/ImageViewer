package software.ulpgc.ImageViewer.model;

import java.util.Arrays;

public interface Image {
    Image next();
    Image previous();
    byte[] content();

    enum Format {
        jpeg, jpg, png, bmp
    }
}
