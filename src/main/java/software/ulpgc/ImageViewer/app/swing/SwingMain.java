package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.control.Command;
import software.ulpgc.ImageViewer.architecture.control.DisplayNextImageCommand;
import software.ulpgc.ImageViewer.architecture.control.DisplayPreviousImageCommand;
import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

import java.io.File;

public class SwingMain {

    public static final String RESOURCES_RELATIVE_PATH = "src/main/resources";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";

    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame(new FileImageLoader(getResourcesAsFile()));
        mainFrame.put(NEXT_COMMAND, createDisplayNextImageCommand(mainFrame.getImageDisplay()))
                 .put(PREVIOUS_COMMAND, createDisplayPreviousImageCommand(mainFrame.getImageDisplay()));
        mainFrame.setVisible(true);
    }

    private static Command createDisplayPreviousImageCommand(ImageDisplay imageDisplay) {
        return new DisplayPreviousImageCommand(imageDisplay);
    }

    private static Command createDisplayNextImageCommand(ImageDisplay imageDisplay) {
        return new DisplayNextImageCommand(imageDisplay);
    }

    private static File getResourcesAsFile() {
        return new File(RESOURCES_RELATIVE_PATH);
    }
}
