import java.util.HashMap;

public class ExtensionMapperFactory {

    private static HashMap<String,Folders>  map=new HashMap<>();
    public static HashMap<String,Folders> createExtensionMap(){
        addApps();
        addArchives();
        addBooks();
        addDocuments();
        addMovies();
        addImages();
        addAudios();


        return map;
    }
    private static void addDocuments(){
        map.put("pdf",Folders.DOCUMENTS);
        map.put("docx",Folders.DOCUMENTS);
        map.put("xlsx",Folders.DOCUMENTS);
        map.put("accdb",Folders.DOCUMENTS);
        map.put("pptx",Folders.DOCUMENTS);
        map.put("ppt",Folders.DOCUMENTS);
        map.put("pps",Folders.DOCUMENTS);
        map.put("doc",Folders.DOCUMENTS);
        map.put("txt",Folders.DOCUMENTS);
    }
    private static void addApps(){
        map.put("exe",Folders.APPS);
    }
    private static void addArchives(){
        map.put("zip",Folders.ARCHIVES);
        map.put("rar",Folders.ARCHIVES);
        map.put("gz",Folders.ARCHIVES);
        map.put("7zip",Folders.ARCHIVES);
    }
    private static void addBooks(){
        map.put("epub",Folders.BOOKS);
        map.put("mobi",Folders.BOOKS);
    }
    private static void addMovies(){
        map.put("mp4",Folders.MOVIES);
        map.put("avi",Folders.MOVIES);
        map.put("divx",Folders.MOVIES);
        map.put("mkv",Folders.MOVIES);
    }
    private static void addImages(){
        map.put("bmp",Folders.IMAGES);
        map.put("jpg",Folders.IMAGES);
        map.put("png",Folders.IMAGES);
        map.put("jpeg",Folders.IMAGES);
    }
    private static void addAudios(){
        map.put("mp3",Folders.AUDIO);
        map.put("flac",Folders.AUDIO);
        map.put("wav", Folders.AUDIO);
        map.put("ogg", Folders.AUDIO);
        map.put("wma", Folders.AUDIO);

    }
}
