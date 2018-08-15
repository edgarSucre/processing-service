package spinplug.mldn.procesingService.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.EntityCollectionResponse;
import spinplug.mldn.procesingService.models.Student;
import spinplug.mldn.procesingService.parse.CSVParser;
import java.util.List;
import java.util.Map;

@Service
public class ProcessCollectionService {

    CSVParser csvParser;

    public ProcessCollectionService(CSVParser parser) {
        this.csvParser = parser;
    }

    public List<Map<String,String>> getCollectionFromCSV(MultipartFile file) {
        return csvParser.parseGenericCollectionFromFile(file);
    }

    public EntityCollectionResponse<Student> getStudentsFromCSV(MultipartFile file) {
        return csvParser.parseEntityCollectionFromFile(Student.class, file);
    }
}
