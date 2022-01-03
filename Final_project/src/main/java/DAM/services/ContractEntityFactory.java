package DAM.services;

import DAM.models.Contract;
import DAM.models.Position;
import DAM.models.User;
import javafx.geometry.Pos;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Component("ContarctEntityFactoryBase")
@Scope("singleton")
public class ContractEntityFactory implements IContractEntityFactory {
    private static Logger logger = Logger.getLogger(ContractEntityFactory.class.getName());
    public ContractEntityFactory() {
        logger.info(">>> BEAN: ContractEntityFactory instantiated!");
    }

    @Override
    public Contract buildSimpleContract(User user, Position position1) {
        List<Position> positionList = new ArrayList<>();
        positionList.add(position1);
        return new Contract(user, positionList);
    }

    @Override
    public Contract buildContractWith2P(User user, Position position1, Position position2) {
        List<Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);
        return new Contract(user, positionList);
    }

    @Override
    public Contract buildPositionWithNP(User user, List<Position> positions) {
        return new Contract(user, positions);
    }

    @Override
    public Contract toEntity(Contract contarctDTO) {
        return null;
    }

    @Override
    public void initDomainServiceEntities() {

    }
}
