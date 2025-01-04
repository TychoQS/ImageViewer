package software.ulpgc.ImageViewer.architecture.view;

import software.ulpgc.ImageViewer.architecture.model.Image;

public interface ImageDisplay {
    void display(Image image, int offset);
    void onReleasing(Released released);
    void onDragging(Dragged dragged);
    int getWidth();
    interface Dragged {
        Dragged Null = position -> {};
        void position (int position);
    }
    interface Released {
        Released Null = position -> {};
        void position (int position);
    }
}
