package list.controllers;

//import antlr.StringUtils;

import list.domain.ListEntity;
import list.domain.Task;
import list.repository.ListEntityRepository;
import list.repository.TaskRepository;
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
public class ListController {
    @Autowired
    private ListEntityRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value ={"/", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model){
        Map<Long, ListEntity> lists = getLists();
        //Iterable<List> lists = listRepository.findAll();
        model.addAttribute("lists", lists.values());
        model.addAttribute("currentList", lists.get(0L));
        return "index";
    }

    @RequestMapping(value = {"/index/{uid}"}, method = RequestMethod.GET)
    public String getIndex(Model model, @PathVariable Long uid){
        Map<Long, ListEntity> lists = getLists();
        Map<Long, Task> tasks = getTasks(uid);
        //Optional<List> list = listRepository.findById(id);
        //Iterable<Task> tasks = list.getTask();

        model.addAttribute("lists", lists.values());
        model.addAttribute("currentList", lists.get(uid));
        model.addAttribute("tasks", tasks.values());
        return "index";
    }

    private  Map<Long, ListEntity> getLists(){
        Map<Long, ListEntity> result = new HashMap<>();
        result.put(0L, new ListEntity(("")));
        Iterable<ListEntity> lists = listRepository.findAll();
        for(ListEntity list: lists){
            result.put(list.getUid(), list);
        }
        return result;
    }
    private  Map<Long, Task> getTasks(Long parentUid){
        Map<Long, Task> result = new HashMap<>();
        ListEntity list = listRepository.findById(parentUid).get();
        List<Task> tasks = taskRepository.findByListEntity(list);
        for(Task task: tasks){
            result.put(task.getUid(), task);
        }
        return result;
    }

    /*@RequestMapping(value={"/addList"}, method=RequestMethod.POST)
    public String listSubmit(@ModelAttribute ListEntity addList, Model model) {
        if(StringUtils.hasText(addList.getName())){
            ListEntity result = listRepository.save(new ListEntity(addList.getName()));
            Long uid = result.getUid();
            return "redirect:/index/" + uid;
        }
        return "redirect:/";
    }*/

    /*@RequestMapping(value={"/addList"}, method=RequestMethod.GET)
    public String listForm(Model model) {
        model.addAttribute("addList", new List());
        return "addList";
    }

    @RequestMapping(value={"/addList"}, method=RequestMethod.POST)
    public String listSubmit(@ModelAttribute List addList) {
        if(StringUtils.hasText(addList.getName())){
            listRepository.save(new List(addList.getName()));
            //Long id = result.getId();

            //return "redirect:/index/" + id;
        }


        return "redirect:/index";
    }*/
}
