package spinplug.mldn.procesingService.parse;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class CSVParser implements ParserInterface{

    public <T> List<T> parseEntityCollectionFromFile(Class<T> type, MultipartFile file) {
        try {
            //TODO: tried to inject csvMapper as a configured bean, but the response fails on controller
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema bootstrapSchema = csvMapper.schemaFor(type).withHeader();

            MappingIterator<T> readValues = csvMapper.readerFor(type).with(bootstrapSchema).readValues(file.getInputStream());
            return readValues.readAll();
        } catch (Exception e) {
            //TODO: log error on parsed type collection
            return Collections.emptyList();
        }
    }

    public List<Map<String,String>> parseGenericCollectionFromFile(MultipartFile file) {
        List<Map<String,String>> collection = new ArrayList<Map<String,String>>();
        try {
            //TODO: tried to inject csvMapper as a configured bean, but the response fails on controller
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Map<String,String>> readValues = csvMapper
                    .readerFor(Map.class)
                    .with(bootstrapSchema)
                    .readValues(file.getInputStream());

            while (readValues.hasNext()) {
                collection.add(readValues.next());
            }
            return collection;
        }
        catch (Exception error) {
            //TODO: log error on generic parse
            return collection;
        }
    }
}
