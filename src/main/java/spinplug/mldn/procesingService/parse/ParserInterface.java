package spinplug.mldn.procesingService.parse;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ParserInterface {
    <T> List<T> parseCollection(Class<T> type, MultipartFile file);
    List<Map<String,String>> parseCollectionFromFile(MultipartFile file);
}
