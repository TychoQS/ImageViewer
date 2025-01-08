package software.ulpgc.ImageViewer.architecture.view;

public record ViewPort(int x, int y, int width, int height) {

    public static final double DEFAULT_ZOOM_FACTOR = 1.0;

    public static ViewPort ofSize(int width, int height) {
        return new ViewPort(0, 0, width, height);
    }

    public ViewPort fit(int width, int height, double zoomFactor) {
        int zoomedWidth = (int) (width * zoomFactor);
        int zoomedHeight = (int) (height * zoomFactor);
        if (needToScaleImage(width, height, zoomFactor)) return centeredViewPort(zoomedWidth, zoomedHeight);

        return shouldScaleWidth(width, height) ?
                fitToWidthViewPort(width, height) :
                fitToHeightViewPort(width, height);
    }

    private boolean needToScaleImage(int width, int height, double zoomFactor) {
        return canFit(width, height) || zoomFactor != DEFAULT_ZOOM_FACTOR;
    }

    private boolean shouldScaleWidth(int width, int height) {
        return ratio(width, height) > viewPortRatio();
    }

    private double viewPortRatio() {
        return ratio(this.width, this.height);
    }

    private static double ratio(double width, double height) {
        return width / height;
    }

    private ViewPort fitToWidthViewPort(int width, int height) {
        return new ViewPort(0, yCenterFor(calculateNewHeight(width, height)), this.width, calculateNewHeight(width, height));
    }

    private ViewPort fitToHeightViewPort(int width, int height) {
        return new ViewPort(xCenterFor(calculateNewWidth(width, height)), 0, calculateNewWidth(width, height), this.height);
    }

    private int calculateNewWidth(int width, int height) {
        return this.height * width / height;
    }

    private int calculateNewHeight(int width, int height) {
        return this.width * height / width;
    }

    private boolean canFit(int width, int height) {
        return width <= this.width && height <= this.height;
    }

    private ViewPort centeredViewPort(int width, int height) {
        return new ViewPort(xCenterFor(width), yCenterFor(height), width, height);
    }

    private int yCenterFor(int height) {
        return (this.height - height) / 2;
    }

    private int xCenterFor(int width) {
        return (this.width - width) / 2;
    }
}
