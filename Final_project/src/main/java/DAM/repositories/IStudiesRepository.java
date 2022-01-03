package DAM.repositories;

import DAM.models.EmployeeStudies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudiesRepository extends JpaRepository<EmployeeStudies, Integer> {
}
