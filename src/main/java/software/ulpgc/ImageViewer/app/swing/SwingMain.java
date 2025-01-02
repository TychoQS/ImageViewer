package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;

import java.io.File;

public class SwingMain {

    public static final String RESOURCES_RELATIVE_PATH = "src/main/resources";

    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame(new FileImageLoader(getResourcesAsFile()));
        mainFrame.setVisible(true);
    }

    private static File getResourcesAsFile() {
        return new File(RESOURCES_RELATIVE_PATH);
    }
}
