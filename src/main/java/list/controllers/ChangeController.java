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

@Controller
public class ChangeController {
    @Autowired
    private ListEntityRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

   /* @RequestMapping(value = {"/{pUid}/changeTask/{uid}"}, method = RequestMethod.GET)
    public String chahgeForm(Model model, @PathVariable Long uid, @PathVariable Long pUid){
        model.addAttribute("changeTask", new Task());
        return "/{pUid}/changeTask/{uid}";
    }

    @RequestMapping(value = {"/{pUid}/changeTask/{uid}"}, method = RequestMethod.POST)
    public String changeTask(@ModelAttribute Task chTask, @PathVariable Long uid, @PathVariable Long pUid, Model model){
        Task myTask = taskRepository.findById(uid).get();
        if(StringUtils.hasText(chTask.getTitle()))
            myTask.setTitle(chTask.getTitle());
        return "index";
    }*/

    /*@RequestMapping(value={"/addList"}, method=RequestMethod.GET)
    public String listForm(Model model) {
        model.addAttribute("addList", new ListEntity());
        return "addList";
    }

    @RequestMapping(value={"/addList"}, method=RequestMethod.POST)
    public String listSubmit(@ModelAttribute ListEntity addList, Model model) {
        if(StringUtils.hasText(addList.getName())){
            ListEntity result = listRepository.save(new ListEntity(addList.getName()));
            Long id = result.getId();
            return "redirect:/index/" + id;
        }
        return "redirect:/";
    }*/
}
