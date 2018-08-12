package spinplug.mldn.procesingService.services;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.Student;
import spinplug.mldn.procesingService.parse.ParserInterface;
import spinplug.mldn.procesingService.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class EntityCollectionService {

    ApplicationContext context;

    public EntityCollectionService(ApplicationContext context) {
        this.context = context;
    }

    public List<Map<String,String>> getStudentsFromFile(MultipartFile file) {
        String fileExtension = FileUtils.getFileExtension(file);
        ParserInterface parser = (ParserInterface) context.getBean(fileExtension);
        return parser.parseCollectionFromFile(file);
    }

}
