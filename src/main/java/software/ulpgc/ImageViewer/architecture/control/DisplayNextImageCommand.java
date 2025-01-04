package software.ulpgc.ImageViewer.architecture.control;

import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

public class DisplayNextImageCommand implements Command {

    private final ImagePresenter presenter;

    public DisplayNextImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.displayNext();
    }
}
