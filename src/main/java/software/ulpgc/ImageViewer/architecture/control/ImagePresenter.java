package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    public static final int DEFAULT_OFFSET = 0;
    private static final double DEFAULT_SCALE_FACTOR = 1.0;
    private static final double MAXIMUM_SCALE_FACTOR = 2.0;
    private final ImageDisplay display;
    private Image image;
    private double scaleFactor;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.onDragging(this::doDrag);
        this.display.onReleasing(this::doRelease);
        this.display.onScrolling(this::doScale);
        setScaleFactorToDefault();
    }

    private void doDrag(int offset) {
        setScaleFactorToDefault();
        display.clear();
        display.display(image, offset, scaleFactor);
        displayAdjacentImageWith(offset);
    }

    private void displayAdjacentImageWith(int offset) {
        if (offset > 0) {
            display.display(image.previous(), offset - display.getWidth(), scaleFactor);
        } else {
            display.display(image.next(), offset + display.getWidth(), scaleFactor);
        }
    }

    private void doScale(int increment) {
        if (display.isBeingDragged()) return;
        display.clear();
        setScaleFactor(increment);
        display.display(image, DEFAULT_OFFSET, scaleFactor);
    }

    private void setScaleFactor(int increment) {
        this.scaleFactor = Math.max(computeZoomFactorWith(increment), DEFAULT_SCALE_FACTOR);
        this.scaleFactor = Math.min(scaleFactor, MAXIMUM_SCALE_FACTOR);
    }

    private double computeZoomFactorWith(int increment) {
        return this.scaleFactor - ((double) increment / 10);
    }

    private void doRelease(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2) {
          image = offset > 0 ? image.previous() : image.next();
        }
        present();
    }

    private void present() {
        display.clear();
        setScaleFactorToDefault();
        display.display(image, DEFAULT_OFFSET, scaleFactor);
    }

    private void setScaleFactorToDefault() {
        scaleFactor = DEFAULT_SCALE_FACTOR;
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
