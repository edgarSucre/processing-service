package spinplug.mldn.procesingService.parse;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import spinplug.mldn.procesingService.models.EntityCollectionResponse;

import java.util.*;

@Component
public class CSVParser implements ParserInterface{

    public <T> EntityCollectionResponse<T> parseEntityCollectionFromFile(Class<T> type, MultipartFile file) {
        List<T> collection = new ArrayList<>();
        List<Map<String,String>> failures = new ArrayList<>();
        try {
            //TODO: tried to inject csvMapper as a configured bean, but the response fails on controller
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema headersSchema = csvMapper.schemaFor(type).withHeader();
            CsvSchema emptySchema = CsvSchema.emptySchema().withHeader();

            MappingIterator<T> entityValues = csvMapper
                    .readerFor(type)
                    .with(headersSchema)
                    .readValues(file.getInputStream());

            MappingIterator<Map<String,String>> dynamicValues = csvMapper
                    .readerFor(Map.class)
                    .with(emptySchema)
                    .readValues(file.getInputStream());

            while (entityValues.hasNext()) {

                if (tryToExecute(entityValues, collection)) {
                    tryToMoveNext(dynamicValues);
                } else {
                    tryToExecute(dynamicValues, failures);
                }
            }

            EntityCollectionResponse<T> response = new EntityCollectionResponse<>(collection, failures);

            return response;
        } catch (Exception e) {
            //TODO: log error on parsed type collection
            return new EntityCollectionResponse<>();
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
                tryToExecute(readValues, collection);
            }
            return collection;
        }
        catch (Exception error) {
            //TODO: log error on generic parse
            return collection;
        }
    }

    private boolean tryToExecute(Iterator looper, List collection) {
       try {
           collection.add(looper.next());
       }
       catch (Exception e) {
           return false;
       }
       return true;
    }

    private void tryToMoveNext(Iterator looper) {
        try {
            looper.next();
        }
        catch (Exception e) {

        }
    }
}
