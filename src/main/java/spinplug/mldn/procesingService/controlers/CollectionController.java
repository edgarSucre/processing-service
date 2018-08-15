package spinplug.mldn.procesingService.controlers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.EntityCollectionResponse;
import spinplug.mldn.procesingService.models.Student;
import spinplug.mldn.procesingService.services.ProcessCollectionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    ProcessCollectionService service;

    public CollectionController(ProcessCollectionService service) {
        this.service = service;
    }

    @PostMapping("/generic")
    public List<Map<String,String>> genericCollectionFromCSV(@RequestParam("studentsFile") MultipartFile uploaded) {
        List<Map<String,String>> collection = service.getCollectionFromCSV(uploaded);
        return collection;
    }

    @PostMapping("/students")
    public EntityCollectionResponse<Student> studentsCollectionFromCSV(@RequestParam("studentsFile") MultipartFile uploaded) {
        return service.getStudentsFromCSV(uploaded);
    }
}
