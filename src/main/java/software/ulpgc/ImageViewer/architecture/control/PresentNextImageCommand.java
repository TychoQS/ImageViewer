package software.ulpgc.ImageViewer.architecture.control;

public class PresentNextImageCommand implements Command {

    private final ImagePresenter presenter;

    public PresentNextImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.presentNext();
    }
}
