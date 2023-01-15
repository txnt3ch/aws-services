package txn.tech.awsservices.config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "database")
@Component
@Data
public class DatabaseConfig {



    private String url;
    private String username;
    private String password;

}
