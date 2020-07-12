package list;

import list.domain.ListEntity;
import list.domain.Task;
import list.repository.ListEntityRepository;
import list.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args){

        SpringApplication.run(Application.class, args);

        /*ConfigurableApplicationContext context = SpringApplication.run(Application.class);

        ListRepository listRepository = context.getBean(ListRepository.class);
        TaskRepository repository = context.getBean(TaskRepository.class);
        //repository.save(new List( 1L,"list1"));
        //repository.save(new List( "list2"));
        List newList = listRepository.save(new List("list5"));
        Task task1 = new Task();
        task1.setTitle("task1");
        task1.setList(newList);

        repository.save(task1);
        //repository.save(new Task( 1L, "task2"));
        //repository.save(new Task( 2L, "task3"));

        //Iterable<Task> tasks = repository.findAll();

        //for (Task entity: tasks){
        //    System.out.println(entity.getTitle());
        //}

        //context.close();*/
    }
}
