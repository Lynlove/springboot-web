package com.example.springbootweb.controller;

import com.example.springbootweb.dao.DepartmentDao;
import com.example.springbootweb.dao.EmployeeDao;
import com.example.springbootweb.entities.Department;
import com.example.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class StudentController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有学生页面
    @GetMapping("/emps")
    public String lsit(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);

        //thymeleaf自动配置搜索classpath/templates/***.html路径
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAdd(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "emp/add";
    }

    //添加员工
    //springMVC自动将请求参数与入参对象的属性一一绑定；但要求请求参数的名字和JavaBean入参的对象里面的属性名一致（employee的lastname，gender，email。。。）
    @PostMapping("/emp")
    public String add(Employee employee){
        System.out.println("保存的信息："+employee);
        employeeDao.save(employee);
        //不能直接/emps，thymeleaf会在/templates/**下找，所以要用redirect（重定向）或forward（转发）
        return "redirect:/emps";
    }

    //来到修改页面
    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        //回到修改页面(修改添加二合一)
        return "emp/add";
    }

    //修改数据
    @PutMapping("/emp")
    public String update(Employee employee){
    System.out.println("修改的数据："+employee);
    employeeDao.save(employee);
    return "redirect:/emps";
    }

    //删除数据
    @PostMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
