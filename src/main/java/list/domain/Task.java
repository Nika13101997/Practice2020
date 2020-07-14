package list.domain;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import list.domain.ListEntity;

@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @Column(name = "UID", nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    //private Long parentId;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
    @Column(name = "DONE")
    private Boolean done;
    @Column(name = "DATE")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)//, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "listEntityUid", nullable = false)
    private ListEntity listEntity;

    public Task(){
    }

    /*public Task(Long parentId, String title){
        this(null, parentId, title, null, false, null);
    }
    public Task(Long id, Long parentId, String title, String description, Boolean done, Date date) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.description = description;
        this.done = done;
        this.date = date;
    }*/

    public void setUid(Long uid){
        this.uid = uid;
    }

    public Long getUid(){
        return uid;
    }

    //public void setParentId(Long parentId){
    //    this.parentId = parentId;
    //}

    //public Long getParentId(){
    //    return parentId;
    //}

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setDone(Boolean done){
        this.done = done;
    }

    public Boolean getDone(){
        return done;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }


    //public List getList() {
    //    return list;
    //}

    //public void setList(List list) {
    //    this.list = list;
    //}

    @JsonIgnore
    public ListEntity getListEntity(){
        return listEntity;
    }
    @JsonIgnore
    public void setListEntity(ListEntity listEntity){
        this.listEntity = listEntity;
    }

    public Long getListEntityId(){
        return listEntity.getUid();
    }
    public String getListEntityName(){
        return listEntity.getName();
    }
}
