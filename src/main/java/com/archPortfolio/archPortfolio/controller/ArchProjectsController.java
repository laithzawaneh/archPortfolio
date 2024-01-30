package com.archPortfolio.archPortfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.archPortfolio.archPortfolio.entity.ArchCategories;
import com.archPortfolio.archPortfolio.entity.ArchProjects;
import com.archPortfolio.archPortfolio.service.CategoriesService;
import com.archPortfolio.archPortfolio.service.ProjectsServices;

@Controller
@RequestMapping("/archprojects")
public class ArchProjectsController {

	private ProjectsServices projectsServices;
	private CategoriesService categoriesService;
	

	public ArchProjectsController(ProjectsServices theProjectsServices, CategoriesService theCategoriesService) {
		projectsServices = theProjectsServices;
		categoriesService = theCategoriesService;
	}

	@GetMapping("/porjectlist")
	public String listProjects(Model theModel) {
		List<ArchProjects> theArchProjects = projectsServices.findAll();
		theModel.addAttribute("projects", theArchProjects);
		return "dashboard/projects";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		List<ArchProjects> getTheArchProjects = projectsServices.findAll();
		ArchProjects theArchProjects = new ArchProjects();
		List<ArchCategories> categories = categoriesService.findAll();
		System.out.println(categories);

		theModel.addAttribute("categories", categories);
		System.out.println(getTheArchProjects);

		theModel.addAttribute("project", theArchProjects);

		return "dashboard/add_project_form";
	}

	@PostMapping("/save")
	public String saveProjects(@ModelAttribute("project") ArchProjects theArchProjects) {

		// save the employee
		projectsServices.save(theArchProjects);

		// use a redirect to prevent duplicate submissions
		return "redirect:/archprojects/porjectlist";

	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("projectId") int theId, Model theModel) {

		// get the employee form the service
		ArchProjects theArchProjects = projectsServices.findById(theId);
		List<ArchCategories> categories = categoriesService.findAll();
		System.out.println(categories);

		theModel.addAttribute("categories", categories);

		// set the employee in the model to prepopulate the form
		theModel.addAttribute("project", theArchProjects);

		// send over to our form

		return "dashboard/add_project_form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("projectId") int theId) {

		// delete the employee
		projectsServices.deleteById(theId);

		// redirect to the /employees/list
		return "redirect:/archprojects/porjectlist";
	}
}
