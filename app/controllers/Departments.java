package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Department;
import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.RequestBody;
import java.util.List;

/**
 * Created by Personal on 5/27/2015.
 */
public class Departments extends Controller {
    private static final Form<Department> departmentForm=Form.form(Department.class);
    public static Result list(){
        List<Department>departments= Department.findAll();
        return ok(Json.toJson(departments));
        //return ok(views.html.departments.list.render(departments));
    }
    public static Result newDepartment(){
        return ok(views.html.departments.details.render(departmentForm));
    }
    public static Result details(int deptno){
        final Department department=Department.findByDeptno(deptno);
        if(department==null){
            return notFound(String.format("Department,%d does not exists",deptno));
        }
        Form<Department>filledForm=departmentForm.fill(department);
        return ok(views.html.departments.details.render(filledForm));
    }
    public static Result save(){
        Form<Department>boundForm=departmentForm.bindFromRequest();
        Department department=boundForm.get();
        department.save();
        return redirect(routes.Departments.list());
    }
    @BodyParser.Of(BodyParser.Json.class)
    public static Result add(){
        JsonNode json = request().body().asJson();
        Department department=Json.fromJson(json,Department.class);
        department.save();
        return redirect(routes.Departments.list());
    }
}
