package list.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LIST")
public class ListEntity {
    @Id
    @Column(name = "UID", nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listEntity", cascade = CascadeType.ALL) //EAGER
    private Set<Task> tasks = new HashSet<>();

    public ListEntity(){
    }

    public ListEntity(String name){
        this(null, name);
    }
    public ListEntity(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public void setUid(Long uid){
        this.uid = uid;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getUid(){
        return uid;
    }

    public String getName(){
        return name;
    }



    public Set<Task> getTask() {
        return tasks;
    }

    public void setTask(Set<Task> tasks) {
        this.tasks = tasks;
    }

}