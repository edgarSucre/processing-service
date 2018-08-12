package spinplug.mldn.procesingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spinplug.mldn.procesingService.parse.CSVParser;
import spinplug.mldn.procesingService.parse.ParserInterface;

@Configuration
public class ParserConfig {

    @Bean("csv")
    public ParserInterface getCSVParser() {
        return new CSVParser();
    }
}
