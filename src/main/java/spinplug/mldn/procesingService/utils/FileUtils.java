package spinplug.mldn.procesingService.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public class FileUtils {
    public static long getFileSizeinBytes(MultipartFile file) {
        return file.getSize();
    }

    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

}
