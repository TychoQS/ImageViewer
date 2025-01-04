package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.control.Command;
import software.ulpgc.ImageViewer.architecture.control.DisplayNextImageCommand;
import software.ulpgc.ImageViewer.architecture.control.DisplayPreviousImageCommand;
import software.ulpgc.ImageViewer.architecture.control.ImagePresenter;
import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;

import java.io.File;

public class SwingMain {

    public static final String RESOURCES_RELATIVE_PATH = "src/main/resources";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";

    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame(new FileImageLoader(getResourcesAsFile()));
        ImagePresenter presenter = new ImagePresenter(mainFrame.getImageDisplay());
        mainFrame.put(NEXT_COMMAND, createDisplayNextImageCommand(presenter))
                 .put(PREVIOUS_COMMAND, createDisplayPreviousImageCommand(presenter));
        mainFrame.setVisible(true);
    }

    private static Command createDisplayPreviousImageCommand(ImagePresenter presenter) {
        return new DisplayPreviousImageCommand(presenter);
    }

    private static Command createDisplayNextImageCommand(ImagePresenter presenter) {
        return new DisplayNextImageCommand(presenter);
    }

    private static File getResourcesAsFile() {
        return new File(RESOURCES_RELATIVE_PATH);
    }
}
