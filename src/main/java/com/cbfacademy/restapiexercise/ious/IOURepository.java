import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface IOURepository extends ListCrudRepository<IOU, UUID>{

}
