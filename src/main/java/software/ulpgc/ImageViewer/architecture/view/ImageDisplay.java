package software.ulpgc.ImageViewer.architecture.view;

import software.ulpgc.ImageViewer.architecture.model.Image;

public interface ImageDisplay {
    void display(Image image, int offset, double zoomFactor);
    void onReleasing(Released released);
    void onDragging(Dragged dragged);
    void onZooming(Zoomed zoomed);
    boolean isBeingDragged();
    int getWidth();
    void clear();
    interface Dragged {
        Dragged Null = position -> {};
        void position (int position);
    }
    interface Released {
        Released Null = position -> {};
        void position (int position);
    }
    interface Zoomed {
        Zoomed Null = position -> {};
        void increase(int increment);
    }
}
