import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileOperations {


    private Path sourcePath;
    private Path targetPath;
    private String folderName = "OrganisedFiles";

    private HashMap<String, Folders> extensionToNames = ExtensionMapperFactory.createExtensionMap();

    public FileOperations(Path sourceTargetPath) {
        this(sourceTargetPath, sourceTargetPath);
    }

    public FileOperations(Path sourcePath, Path targetPath) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.targetPath = Paths.get(targetPath.toString() + "\\" + folderName);
    }

    public void moveFiles() {


        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(sourcePath.toString()));
            //createFolder();
            System.out.println(targetPath);
            for (Path path : directoryStream) {

                if (!isSameAsFolderName(path)) {
                    Files.move(path, generateExtensionBasedPath(path));
                }
            }
        } catch (IOException e) {
            /*DEBBUGING
            TODO ADDAXCEPTION HANDLING
            \*/
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }

    }

    private void createFolder() {
        createFolder(sourcePath, folderName);
    }

    private void createFolder(Path path, String name) {
        new File(path + "\\" + name).mkdirs();
    }

    private boolean isSameAsFolderName(Path path) {
        return (path.getFileName().toString().equals(folderName));
    }

    private Path generateExtensionBasedPath(Path path) {
        String extension = FileNamesUtils.getExtension(path);
        if (extensionToNames.containsKey(extension)) extension = extensionToNames.get(extension).toString();
        else extension = Folders.OTHERS.toString();
        createFolder(targetPath, extension);
        return Paths.get(targetPath + "\\" + extension + "\\" + path.getFileName());
    }

    public void setSourcePath(Path path) {
        this.sourcePath=path;
    }

    public void setTargetPath(Path path) {
        this.targetPath=Paths.get(path.toString() + "\\" + folderName);

    }

}
