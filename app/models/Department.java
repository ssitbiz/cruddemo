package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Personal on 5/27/2015.
 */
@Entity
public class Department extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="device_gen")
    public Long id;
    public int deptno;
    public String dname;
    public String loc;
    @OneToMany(fetch = FetchType.LAZY)@JsonBackReference
    public List<Employee> employees;

    public Department(Long id, int deptno, String dname, String loc) {
        this.id = id;
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Department() {
    }
    private static Model.Finder<Long,Department>find=new Model.Finder<Long,Department>(Long.class,Department.class);
    public static List<Department> findAll(){
        return find.fetch("employees").orderBy("id").findList();
    }
    public static Department findByDeptno(int deptno){
        return find.where().eq("deptno",deptno).findUnique();
    }
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Department d: Department.find.orderBy("id").findList()) {
            options.put(d.id.toString(), d.dname);
        }
        return options;
    }
    public static Department findById(Long id){
        return find.byId(id);
    }
}
