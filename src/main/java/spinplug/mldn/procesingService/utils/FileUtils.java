package spinplug.mldn.procesingService.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static long getFileSizeinBytes(MultipartFile file) {
        return file.getSize();
    }
}
