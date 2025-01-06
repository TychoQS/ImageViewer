package software.ulpgc.ImageViewer.architecture.control;

public class PresentPreviousImageCommand implements Command {
    private final ImagePresenter presenter;

    public PresentPreviousImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.presentPrevious();
    }
}
