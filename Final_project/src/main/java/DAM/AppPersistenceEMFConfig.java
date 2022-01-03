package DAM;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.logging.Logger;

@Configuration
@ComponentScan("DAM")
@EnableJpaRepositories(basePackages = "DAM")
@EnableTransactionManagement
public class AppPersistenceEMFConfig {
    private static Logger logger = Logger.getLogger(AppPersistenceEMFConfig.class.getName());
}
