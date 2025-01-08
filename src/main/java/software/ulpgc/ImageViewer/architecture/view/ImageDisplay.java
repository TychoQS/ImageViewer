package software.ulpgc.ImageViewer.architecture.view;

import software.ulpgc.ImageViewer.architecture.model.Image;

public interface ImageDisplay {
    void display(Image image, int offset, double zoomFactor);
    void onReleasing(Released released);
    void onDragging(Dragged dragged);
    void onScrolling(Scrolled scrolled);
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
    interface Scrolled {
        Scrolled Null = _ -> {};
        void direction(int directionWay);
    }
}
