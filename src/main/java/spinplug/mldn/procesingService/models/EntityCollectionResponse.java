package spinplug.mldn.procesingService.models;

import java.util.List;
import java.util.Map;

public class EntityCollectionResponse<T> {
    private List<T> data;
    private List<Map<String, String>> failures;

    public EntityCollectionResponse() {
    }

    public EntityCollectionResponse(List<T> data, List<Map<String, String>> failures) {
        this.data = data;
        this.failures = failures;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Map<String, String>> getFailures() {
        return failures;
    }

    public void setFailures(List<Map<String, String>> failures) {
        this.failures = failures;
    }
}
