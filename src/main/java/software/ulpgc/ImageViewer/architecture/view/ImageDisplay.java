package software.ulpgc.ImageViewer.architecture.view;

import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.model.Image;

public interface ImageDisplay {
    void display(Image image);
    void displayNext();
    void displayPrevious();
}
