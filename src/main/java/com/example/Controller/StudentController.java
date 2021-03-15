package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.servlet.ModelAndView;

import com.example.Entity.StudentDAO;
import com.example.Service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@GetMapping("/")
	public String getproducts(Model model) {
		System.out.println("hello");
		List<StudentDAO> list_of_students=service.getStudents();
		model.addAttribute("list_student",list_of_students);
		return "Home";
	}
	
	@GetMapping("/new")
	public String newstudent(Model model) {
		model.addAttribute("newstudent", new StudentDAO());
		return "newstudent";
	}
	
	@PostMapping("/save")
	public String add(@ModelAttribute("newstudent") StudentDAO stu) {
		service.create(stu);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
	
	//CASE 1 : USING MODEL AND VIEW INTERFACE
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView mav=new ModelAndView("edit");	//RETURNS VIEW:EDIT , MODEL:STUDENTDATA (MATCHING ID)
		StudentDAO editabledata=service.getById(id);
		mav.addObject("editabledata", editabledata);
		return mav; //returns edit.html along with the model object (editablestudent)
	}
	
	// CASE 2: USING MODEL INTERFACE
	/* @GetMapping("/edit/{id}")
	public String edit(Model model,@PathVariable("id") Long id) {
		
	StudentDAO editablestudent=service.getById(id);
		model.addAttribute("editabledata", editablestudent);
		return "edit";
	}
	
	/*@PostMapping("/editsave")
	public String editsave(@ModelAttribute("editabledata") StudentDAO stu) {
		service.editsave(stu);
		return "redirect:/";
	}
	
	Note : /editsave is not required as edit form is directly mapped to /save (it is saved by matching with the id)
*/
}
