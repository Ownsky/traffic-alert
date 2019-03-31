package pers.ownsky.trafficalert.dataaccess.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "datasource")
@Data
public class DBProperties {
    private HikariDataSource main;
    private HikariDataSource read1;
    private HikariDataSource read2;
}
