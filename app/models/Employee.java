package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Personal on 5/27/2015.
 */
@Entity
public class Employee extends Model {
    @Id
    public Long id;
    public int empno;
    public String name;
    @ManyToOne
    
    public Department department;
    private static Model.Finder<Long,Employee>find=new Model.Finder<Long,Employee>(Long.class,Employee.class);
    public static List<Employee> findAll(){
        return find.orderBy("id").findList();
    }
    public static Employee findByDeptno(int empno){
        return find.where().eq("empno",empno).findUnique();
    }
}
