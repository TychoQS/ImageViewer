package software.ulpgc.ImageViewer.io;

import software.ulpgc.ImageViewer.model.Image;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileImageLoader implements ImageLoader {

    private final List<File> images;

    public FileImageLoader(File directory) {
        this.images = List.of(Objects.requireNonNull(directory.listFiles(areImages())));
    }

    public Iterator<File> iterator() {
        return images.iterator();
    }

    private FileFilter areImages() {
        return f -> getImageExtensions().anyMatch(e -> f.getName().toLowerCase().endsWith(e));
    }

    private Stream<String> getImageExtensions() {
        return Arrays.stream(Image.Format.values()).map(Enum::name);
    }

    @Override
    public Image load() {
        return null;
    }
}
