package software.ulpgc.ImageViewer.architecture.control;

public class DisplayPreviousImageCommand implements Command {
    private final ImagePresenter presenter;

    public DisplayPreviousImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.displayPrevious();
    }
}
