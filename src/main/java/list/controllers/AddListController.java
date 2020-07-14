package list.controllers;

import list.domain.ListEntity;
import list.domain.Task;
import list.repository.ListEntityRepository;
import list.repository.TaskRepository;
import list.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AddListController {
    @Autowired
    private ListEntityRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;


    @RequestMapping(value={"/addList"}, method=RequestMethod.POST)
    public String listSubmit(@ModelAttribute ListEntity addList, Model model) {
        if(StringUtils.hasText(addList.getName())){
            ListEntity result = listRepository.save(new ListEntity(addList.getName()));
            Long uid = result.getUid();
            return "redirect:/index/" + uid;
        }
        return "redirect:/";
    }

    @RequestMapping(value="/addTask", method=RequestMethod.POST)
    public String taskSubmit(@ModelAttribute TaskVO addTask, Model model) {
        if(StringUtils.hasText(addTask.getTitle())){
            ListEntity list = listRepository.findById(addTask.getParentUid()).get();
            Task newTask = new Task();
            newTask.setTitle(addTask.getTitle());
            newTask.setListEntity(list);
            taskRepository.save(newTask);

            return "redirect:/index/" + list.getUid();
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/deleteList/{uid}"}, method = RequestMethod.GET)
    public String listDelete(@PathVariable Long uid){
        ListEntity list = listRepository.findById(uid).get();
        List<Task> tasks = taskRepository.findByListEntity(list);
        for(Task task: tasks){
            taskRepository.delete(task);
        }
        listRepository.deleteById(list.getUid());
        return "redirect:/";
    }

    @RequestMapping(value = {"/deleteTask/{uid}"}, method = RequestMethod.GET)
    public String taskDelete(@PathVariable Long uid){
        Task task = taskRepository.findById(uid).get();
        ListEntity list = task.getListEntity();
        taskRepository.delete(task);
        return "redirect:/index/" + list.getUid();
    }

    @RequestMapping(value = {"/deleteList/all"}, method = RequestMethod.GET)
    public String listDeleteAll(){
        Iterable<ListEntity> lists = listRepository.findAll();
        for(ListEntity list: lists){
            List<Task> tasks = taskRepository.findByListEntity(list);
            for(Task task: tasks){
                taskRepository.delete(task);
            }
            listRepository.deleteById(list.getUid());
        }
        return "redirect:/";
    }
}
