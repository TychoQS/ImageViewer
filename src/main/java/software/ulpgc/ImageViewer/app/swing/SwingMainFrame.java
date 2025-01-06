package software.ulpgc.ImageViewer.app.swing;

import software.ulpgc.ImageViewer.architecture.control.Command;
import software.ulpgc.ImageViewer.architecture.io.FileImageLoader;
import software.ulpgc.ImageViewer.architecture.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SwingMainFrame extends JFrame {
    public static final String TITLE = "ImageViewer";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";
    public static final String RIGHT_BUTTON_ICON = "/rightArrow.png";
    public static final String LEFT_BUTTON_ICON = "/leftArrow.png";
    public static final String ICON = "/icon.png";
    private SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public SwingMainFrame() {
        this.commands = new HashMap<>();
        initFrame();
    }

    private void initFrame() {
        this.setTitle(TITLE);
        this.setIconImage(getFrameIcon());
        this.setSize(getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        addPanels();
    }

    private static Image getFrameIcon() {
        return new ImageIcon(Objects.requireNonNull(SwingMainFrame.class.getResource(ICON))).getImage();
    }

    public SwingMainFrame put(String key, Command value) {
        commands.put(key, value);
        return this;
    }

    private void addPanels() {
        this.add(createPreviousButtonPanel());
        this.add(imageDisplay = createImageDisplay());
        this.add(createNextButtonPanel());
    }

    private Component createNextButtonPanel() {
        JButton button = new JButton();
        initButton(button);
        button.setIcon(getNextButtonIcon());
        button.addActionListener(e -> commands.get(NEXT_COMMAND).execute());
        return button;
    }

    private static ImageIcon getNextButtonIcon() {
        return new ImageIcon(Objects.requireNonNull(SwingMainFrame.class.getResource(RIGHT_BUTTON_ICON)));
    }

    private Component createPreviousButtonPanel() {
        JButton button = new JButton();
        initButton(button);
        button.setIcon(getPreviousButtonIcon());
        button.addActionListener(e -> commands.get(PREVIOUS_COMMAND).execute());
        return button;
    }

    private static ImageIcon getPreviousButtonIcon() {
        return new ImageIcon(Objects.requireNonNull(SwingMainFrame.class.getResource(LEFT_BUTTON_ICON)));
    }

    private static void initButton(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(new Color(0, 0, 0, 0));
        button.setOpaque(false);
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
