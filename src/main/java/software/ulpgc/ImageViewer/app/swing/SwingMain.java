package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.control.Command;
import software.ulpgc.ImageViewer.architecture.control.PresentNextImageCommand;
import software.ulpgc.ImageViewer.architecture.control.PresentPreviousImageCommand;
import software.ulpgc.ImageViewer.architecture.control.ImagePresenter;
import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;

import java.io.File;

public class SwingMain {

    public static final String RESOURCES_RELATIVE_PATH = "src/main/resources/trials";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";

    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame();
        ImagePresenter presenter = new ImagePresenter(mainFrame.getImageDisplay());
        initialize(presenter);
        mainFrame.put(NEXT_COMMAND, createDisplayNextImageCommand(presenter))
                 .put(PREVIOUS_COMMAND, createDisplayPreviousImageCommand(presenter));
        mainFrame.setVisible(true);
    }

    private static void initialize(ImagePresenter presenter) {
        presenter.present(new FileImageLoader(getResourcesAsFile()).load());
    }

    private static Command createDisplayPreviousImageCommand(ImagePresenter presenter) {
        return new PresentPreviousImageCommand(presenter);
    }

    private static Command createDisplayNextImageCommand(ImagePresenter presenter) {
        return new PresentNextImageCommand(presenter);
    }

    private static File getResourcesAsFile() {
        return new File(RESOURCES_RELATIVE_PATH);
    }
}
