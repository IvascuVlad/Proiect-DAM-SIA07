package DAM.application.services;

import DAM.AppPersistenceEMFConfig;
import DAM.models.Contract;
import DAM.repositories.IContractEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppPersistenceEMFConfig.class)
public class TestProjectEntityRepositorySpringDataAudit {
    private static Logger logger = Logger.getLogger(TestProjectEntityRepositorySpringDataAudit.class.getName());

    @Autowired
    private IContractEntityRepository repository;

    @Test
    public void test() throws Exception{
        logger.info("Factory implementation object:: " + repository);
        logger.info("Factory implementation class:: " + repository.getClass().getName());

        Contract testContract = new Contract();
        logger.info("Saved contract: " + testContract);
        repository.save(testContract);
        logger.info("Saved contract: " + testContract);

        Long contractCount = repository.count();
        assertTrue("ContractCount not counting...", contractCount > 0);
        logger.info("contractCount = " + contractCount);
    }
}
