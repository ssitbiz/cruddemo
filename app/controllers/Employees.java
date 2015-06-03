package controllers;

import models.Department;
import models.Employee;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Personal on 5/31/2015.
 */
public class Employees extends Controller {
    private static final Form<Employee> employeeForm= Form.form(Employee.class);
    public static Result list(){
        List<Employee> employees= Employee.findAll();
        return ok(Json.toJson(employees));
        //return ok(views.html.employees.list.render(employees));
    }
    public static Result newEmployee(){
        return ok(views.html.employees.details.render(employeeForm));
    }
    public static Result details(int empno){
        return TODO;
    }
    public static Result save(){
        Form<Employee>boundForm=employeeForm.bindFromRequest();
        Department dept=Department.findById(Long.parseLong(boundForm.data().get("department.id")));
        Employee employee=boundForm.get();
        employee.department=dept;
        employee.save();
        return redirect(routes.Employees.list());
    }
}
