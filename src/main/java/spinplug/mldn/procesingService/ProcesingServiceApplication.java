package spinplug.mldn.procesingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ProcesingServiceApplication {

	//TODO: when using feing, create user context interceptor for feing, to propagate the correlation id
    //TODO: add hystrix

	public static void main(String[] args) {
		SpringApplication.run(ProcesingServiceApplication.class, args);
	}
}
