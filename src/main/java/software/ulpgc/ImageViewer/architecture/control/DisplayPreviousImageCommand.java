package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class DisplayPreviousImageCommand implements Command {
    private final ImageDisplay display;

    public DisplayPreviousImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.displayPrevious();
    }
}
