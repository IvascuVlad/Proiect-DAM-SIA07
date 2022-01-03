package DAM.services;

import DAM.models.Contract;
import DAM.models.Position;
import DAM.models.User;

import java.util.List;

public interface IContractEntityFactory {
    // build contract with: 1 position
    public Contract buildSimpleContract(User user, Position position1);
    // build contract with: 2 position
    public Contract buildContractWith2P(User user, Position position1, Position position2);
    // build contract with: n position
    public Contract buildPositionWithNP(User user, List<Position> positions);

    // build entity from DTO
    public Contract toEntity(Contract contarctDTO);

    public void initDomainServiceEntities() ;
}
