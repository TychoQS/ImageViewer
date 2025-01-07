package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.model.Image;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    public static final int DEFAULT_OFFSET = 0;
    private static final double MINIMUM_ZOOM_FACTOR = 1.0;
    private static final double MAXIMUM_ZOOM_FACTOR = 2.0;
    private final ImageDisplay display;
    private Image image;
    private double zoomFactor = MINIMUM_ZOOM_FACTOR;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.onDragging(this::doDrag);
        this.display.onReleasing(this::doRelease);
        this.display.onZooming(this::doZoom);
    }

    private void doDrag(int offset) {
        this.zoomFactor = MINIMUM_ZOOM_FACTOR;
        display.clear();
        display.display(image, offset, zoomFactor);
        if (offset > 0) {
            display.display(image.previous(), offset - display.getWidth(), zoomFactor);
        } else {
            display.display(image.next(), offset + display.getWidth(), zoomFactor);
        }
    }

    private void doZoom(int increment) {
        if (display.isBeingDragged()) return;
        display.clear();
        setZoomFactor(increment);
        display.display(image, DEFAULT_OFFSET, zoomFactor);
    }

    private void setZoomFactor(int increment) {
        this.zoomFactor = Math.max(computeZoomFactorWith(increment), MINIMUM_ZOOM_FACTOR);
        this.zoomFactor = Math.min(zoomFactor, MAXIMUM_ZOOM_FACTOR);
    }

    private double computeZoomFactorWith(int increment) {
        return this.zoomFactor - ((double) increment / 10);
    }

    private void doRelease(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2) {
          image = offset > 0 ? image.previous() : image.next();
        }
        present();
    }

    private void present() {
        display.clear();
        zoomFactor = MINIMUM_ZOOM_FACTOR;
        display.display(image, DEFAULT_OFFSET, zoomFactor);
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
