package list.repository;

import list.domain.ListEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface ListEntityRepository extends CrudRepository<ListEntity, Long> {

    //List findByTasksParentId(Long id);
   // List findByid(Long id);
}
