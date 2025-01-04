package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
    }

    public void display(Image image) {
        display.display(image);
    }

    public void displayNext() {
        display.displayNext();
    }

    public void displayPrevious() {
        display.displayPrevious();
    }
}
