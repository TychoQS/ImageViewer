package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    public static final int DEFAULT_OFFSET = 0;
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
        present();
    }

    private void present() {
        display.clear();
        display.display(image, DEFAULT_OFFSET);
    }

    public void present(Image image) {
        this.image = image;
        present();
    }

    public void presentNext() {
        present(image.next());
    }

    public void presentPrevious() {
        present(image.previous());
    }
}
