package software.ulpgc.ImageViewer.architecture.io;

import software.ulpgc.ImageViewer.architecture.model.Image;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileImageLoader implements ImageLoader {

    private final List<File> images;

    public FileImageLoader(File directory) {
        this.images = List.of(Objects.requireNonNull(directory.listFiles(areImages())));
    }

    private FileFilter areImages() {
        return f -> getImageExtensions().anyMatch(e -> f.getName().toLowerCase().endsWith(e));
    }

    private Stream<String> getImageExtensions() {
        return Arrays.stream(Image.Format.values()).map(Enum::name).map(String::toLowerCase);
    }

    @Override
    public Image load() {
        return loadImageAt(0);
    }

    private Image loadImageAt(int index) {
        return new Image() {
            @Override
            public Image next() {
                return loadImageAt(nextIndex());
            }

            @Override
            public Image previous() {
                return loadImageAt(previousIndex());
            }

            private int nextIndex() {
                return index == getLastIndex() ? 0 : index + 1;
            }

            private int previousIndex() {
                return index == 0 ? getLastIndex() : index - 1;
            }

            private int getLastIndex() {
                return images.size() - 1;
            }

            @Override
            public byte[] content() {
                try {
                    return Files.readAllBytes(getCurrentImage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            private Path getCurrentImage() {
                return getImagePath();
            }

            private Path getImagePath() {
                return images.get(index).toPath();
            }
        };
    }
}
