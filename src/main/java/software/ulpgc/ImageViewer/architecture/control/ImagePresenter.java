package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
    }

    public void display(Image image) {
        this.image = image;
        display.display(image);
    }

    public void displayNext() {
        display(image.next());
    }

    public void displayPrevious() {
        display(image.previous());
    }

}
