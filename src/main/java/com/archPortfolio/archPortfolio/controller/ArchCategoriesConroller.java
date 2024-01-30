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
import com.archPortfolio.archPortfolio.service.CategoriesService;

@Controller
@RequestMapping("/archcategory")
public class ArchCategoriesConroller {

	private CategoriesService categoriesService;

	public ArchCategoriesConroller(CategoriesService theCategoriesService) {
		categoriesService = theCategoriesService;
	}

	@GetMapping("/categorylist")
	public String listCategories(Model theModel) {
		List<ArchCategories> theArchCategories = categoriesService.findAll();
		theModel.addAttribute("categories", theArchCategories);
		return "dashboard/categories";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		ArchCategories theArchCategories = new ArchCategories();

		theModel.addAttribute("category", theArchCategories);

		return "dashboard/add_category_form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("categoryId") int theId, Model theModel) {

		// get the employee form the service
		ArchCategories theArchCategories = categoriesService.findById(theId);

		// set the employee in the model to prepopulate the form
		theModel.addAttribute("category", theArchCategories);

		// send over to our form

		return "dashboard/add_category_form";
	}

	@PostMapping("/save")
	public String saveCategories(@ModelAttribute("category") ArchCategories theArchCategories) {

		// save the employee
		categoriesService.save(theArchCategories);

		// use a redirect to prevent duplicate submissions
		return "redirect:/archcategory/categorylist";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("categoryId") int theId) {

		// delete the employee
		categoriesService.deleteById(theId);

		// redirect to the /employees/list
		return "redirect:/archcategory/categorylist";
	}

}
