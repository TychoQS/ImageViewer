package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.control.Command;
import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    public static final String TITLE = "ImageViewer";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";
    private final FileImageLoader loader;
    private SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public SwingMainFrame(FileImageLoader loader) {
        this.loader = loader;
        initFrame();
        commands = new HashMap<>();
    }

    private void initFrame() {
        this.setTitle(TITLE);
        this.setSize(getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        addPanels();
        displayFirstImage();
    }

    public SwingMainFrame put(String key, Command value) {
        commands.put(key, value);
        return this;
    }

    private void displayFirstImage() {
        this.imageDisplay.display(loader.load());
    }

    private void addPanels() {
        this.add(createPreviousButtonPanel());
        this.add(imageDisplay = createImageDisplay());
        this.add(createNextButtonPanel());
    }

    private Component createNextButtonPanel() {
        JButton button = new JButton(">");
        button.addActionListener(e -> commands.get(NEXT_COMMAND).execute());
        return button;
    }

    private Component createPreviousButtonPanel() {
        JButton button = new JButton("<");
        button.addActionListener(e -> commands.get(PREVIOUS_COMMAND).execute());
        return button;
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
