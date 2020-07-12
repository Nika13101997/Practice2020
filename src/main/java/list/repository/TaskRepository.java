package list.repository;

import list.domain.ListEntity;
import list.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    //Iterable<Task> findByParentId(Long parentId);
    List<Task> findByListEntity (ListEntity list);
}