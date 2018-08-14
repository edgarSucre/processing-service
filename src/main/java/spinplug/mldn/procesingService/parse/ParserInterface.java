package spinplug.mldn.procesingService.parse;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface ParserInterface {
    <T> List<T> parseEntityCollectionFromFile(Class<T> type, MultipartFile file);
    List<Map<String,String>> parseGenericCollectionFromFile(MultipartFile file);
}
