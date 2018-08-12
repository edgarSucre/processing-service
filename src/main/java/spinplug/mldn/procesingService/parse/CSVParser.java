package spinplug.mldn.procesingService.parse;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CSVParser implements ParserInterface{

    public <T> List<T> parseCollection(Class<T> type, MultipartFile file) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema bootstrapSchema = mapper.schemaFor(type);

            MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file.getInputStream());
            return readValues.readAll();
        } catch (Exception e) {
            //TODO: log error
            return Collections.emptyList();
        }
    }

    public List<Map<String,String>> parseCollectionFromFile(MultipartFile file) {
        List<Map<String,String>> collection = new ArrayList<Map<String,String>>();
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Map<String,String>> readValues = mapper
                    .readerFor(Map.class)
                    .with(bootstrapSchema)
                    .readValues(file.getInputStream());

            while (readValues.hasNext()) {
                collection.add(readValues.next());
            }
            return collection;
        }
        catch (Exception error) {
            return collection;
        }
    }
}
