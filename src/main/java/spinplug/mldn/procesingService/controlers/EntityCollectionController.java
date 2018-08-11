package spinplug.mldn.procesingService.controlers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.utils.FileUtils;

@RestController
@RequestMapping("/processing")
public class EntityCollectionController {
    @PostMapping("/students")
    public long processStudentFile(@RequestParam("studentsFile") MultipartFile file) {
        return FileUtils.getFileSizeinBytes(file);
    }
}
