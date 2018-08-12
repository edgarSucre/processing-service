package spinplug.mldn.procesingService.controlers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.Student;
import spinplug.mldn.procesingService.services.EntityCollectionService;
import spinplug.mldn.procesingService.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/processing")
public class EntityCollectionController {

    EntityCollectionService service;

    public EntityCollectionController(EntityCollectionService service) {
        this.service = service;
    }

    @PostMapping("/students")
    public List<Map<String,String>> processStudentFile(@RequestParam("studentsFile") MultipartFile uploaded) {
        return service.getStudentsFromFile(uploaded);
    }
}
