package org.library.manager.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@PropertySource("classpath:liquibase.properties")
@RequiredArgsConstructor
public class LiquibaseConfig {
    private final DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLogParameters(Collections.singletonMap("mysqlEnableDbclPrimaryKey", "true"));
        return liquibase;
    }
}
