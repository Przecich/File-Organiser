import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileNamesUtils {
    public static String getExtension(Path path) {
        String extension;
        try {
            String fileName = path.getFileName().toString();
            extension = fileName.substring(fileName.lastIndexOf(".")+1);
        } catch (StringIndexOutOfBoundsException e) {
            extension = "dir";
        }
        return extension;
    }

    private FileTime getCreationTime(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return attr.creationTime();
    }
}
