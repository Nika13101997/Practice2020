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

import java.util.Date;
import java.util.List;

@Controller
public class ChangeController {
    @Autowired
    private ListEntityRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value={"/updateList/{uid}"}, method = RequestMethod.POST)
    public String updateList(@ModelAttribute ListEntity upList, @PathVariable Long uid){
        if(StringUtils.hasText(upList.getName())){
            ListEntity list = listRepository.findById(uid).get();
            list.setName(upList.getName());
            listRepository.save(list);
        }
        return "redirect:/index/" + uid;
    }

    @RequestMapping(value="/updateTask/{uid}", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute TaskVO addTask, @PathVariable Long uid, Model model){
        ListEntity list = listRepository.findById(addTask.getParentUid()).get();
        Task task = taskRepository.findById(uid).get();
        if(StringUtils.hasText(addTask.getTitle()))
            task.setTitle(addTask.getTitle());
        if(StringUtils.hasText(addTask.getDescription()) && (addTask.getDescription().length()<= 40)) {
            task.setDescription(addTask.getDescription());
        }
        if(addTask.getDone()){
            task.setDone(true);
        }else{
            task.setDone(false);
        }
        taskRepository.save(task);

        return "redirect:/index/" + addTask.getParentUid();
    }

    @RequestMapping(value="/doneTask/{uid}", method = RequestMethod.GET)
    public String doneTask(@PathVariable Long uid, Model model){
        Task task = taskRepository.findById(uid).get();
        ListEntity list = task.getListEntity();
        if(task.getDone()){
            task.setDone(false);
        }else{
            task.setDone(true);
        }
        taskRepository.save(task);
        return "redirect:/index/" + list.getUid();
    }



}
