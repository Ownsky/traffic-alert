package pers.ownsky.trafficalert.dataaccess.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Autowired
    private DBProperties dbProperties;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("main", dbProperties.getMain());
        targetDataSources.put("read1", dbProperties.getRead1());
        targetDataSources.put("read2", dbProperties.getRead2());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dbProperties.getMain());
        return dynamicDataSource;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
////        return new DataSourceTransactionManager(dataSource());
//        return null;
//    }
}
