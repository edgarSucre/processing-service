package spinplug.mldn.procesingService.parse;

import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.EntityCollectionResponse;

import java.util.List;
import java.util.Map;

public interface ParserInterface {
    <T> EntityCollectionResponse<T> parseEntityCollectionFromFile(Class<T> type, MultipartFile file);
    List<Map<String,String>> parseGenericCollectionFromFile(MultipartFile file);
}
