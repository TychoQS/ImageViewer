package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class DisplayNextImageCommand implements Command {

    private final ImageDisplay display;

    public DisplayNextImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.displayNext();
    }
}
