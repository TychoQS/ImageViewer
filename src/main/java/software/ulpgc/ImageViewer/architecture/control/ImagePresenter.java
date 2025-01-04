package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.onDragging(this::drag);
        this.display.onReleasing(this::release);
    }

    private void drag(int offset) {
        display.clear();
        display.display(image, offset);
        if (offset > 0) {
            display.display(image.previous(), offset - display.getWidth());
        } else {
            display.display(image.next(), offset + display.getWidth());
        }
    }

    private void release(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2) {
          image = offset > 0 ? image.previous() : image.next();
        }
        display(image);
    }

    public void display(Image image) {
        this.image = image;
        display.clear();
        display.display(image, 0);
    }

    public void displayNext() {
        display(image.next());
    }

    public void displayPrevious() {
        display(image.previous());
    }

}
