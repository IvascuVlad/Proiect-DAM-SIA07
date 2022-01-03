package DAM.repositories;

import DAM.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IContractEntityRepository extends JpaRepository<Contract, Integer> {
}
