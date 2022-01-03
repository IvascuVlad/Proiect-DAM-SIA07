package DAM.repositories;

import DAM.models.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
