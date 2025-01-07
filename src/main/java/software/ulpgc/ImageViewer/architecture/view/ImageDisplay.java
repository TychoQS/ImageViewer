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
        Dragged Null = _ -> {};
        void position (int position);
    }
    interface Released {
        Released Null = _ -> {};
        void position (int position);
    }
    interface Zoomed {
        Zoomed Null = _ -> {};
        void increase(int increment);
    }
}
