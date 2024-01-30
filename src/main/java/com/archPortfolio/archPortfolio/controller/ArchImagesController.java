package com.archPortfolio.archPortfolio.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.archPortfolio.archPortfolio.entity.ArchCategories;
import com.archPortfolio.archPortfolio.entity.ArchImages;
import com.archPortfolio.archPortfolio.entity.ArchProjects;
import com.archPortfolio.archPortfolio.service.CategoriesService;
import com.archPortfolio.archPortfolio.service.ImagesServices;
import com.archPortfolio.archPortfolio.service.ProjectsServices;
import com.archPortfolio.archPortfolio.util.ImageUtil;

@Controller
@RequestMapping("/archimages")
public class ArchImagesController {

	private ImagesServices imagesServices;
	private ProjectsServices projectsServices;
	private CategoriesService categoriesService;

	public ArchImagesController(ImagesServices theImagesServices, ProjectsServices theProjectsServices,
			CategoriesService theCategoriesService) {
		this.imagesServices = theImagesServices;
		this.projectsServices = theProjectsServices;
		this.categoriesService = theCategoriesService;
	}

	@GetMapping("/imagelist")
	public String listImages(Model theModel) {
		List<ArchImages> theArchImages = imagesServices.findAll();
		for (ArchImages image : theArchImages) {
			String encodedImageData = ImageUtil.encodeImage(image.getImageData());
			image.setEncodedImageData(encodedImageData);
			System.out.println("encoded Image Data : " + encodedImageData);
		}
		theModel.addAttribute("images", theArchImages);
		return "dashboard/images";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam(name = "categoryId", required = false) Long categoryId, Model theModel) {
		ArchImages theArchImages = new ArchImages();

		List<ArchProjects> projects;
		List<ArchCategories> categories = categoriesService.findAll();

		if (categoryId != null) {
			projects = projectsServices.findAllByCategories_categoryId(categoryId);
		} else {
			projects = projectsServices.findAll();
		}

		theModel.addAttribute("categories", categories);
		theModel.addAttribute("projects", projects);
		theModel.addAttribute("image", theArchImages);
	    theModel.addAttribute("categoryId", categoryId); // Add this line


		return "dashboard/add_image_form";
	}

//	@GetMapping("/showFormForAdd")
//	public String showFormForAdd(Model theModel) {
//
//		// create model attribute to bind form data
//		ArchImages theArchImages = new ArchImages();
//
//		List<ArchProjects> projects = projectsServices.findAll();
//		List<ArchCategories> categories = categoriesService.findAll();
//
//		theModel.addAttribute("categories", categories);
//		theModel.addAttribute("projects", projects);
//
//		theModel.addAttribute("image", theArchImages);
//
//		return "dashboard/add_image_form";
//	}

//	@PostMapping("/save")
//	public String saveEmployee(@ModelAttribute("image") ArchImages theArchImages) {
//
//		// save the employee
//		imagesServices.save(theArchImages);
//
//		// use a redirect to prevent duplicate submissions
//		return "redirect:/archimages/imagelist";
//
//	}

	@PostMapping("/save")
	public String saveImage(@ModelAttribute("image") @Valid ArchImages theArchImages, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			// If validation errors occur, return to the form with error messages
			return "dashboard/add_image_form";
		}

		// Handle file upload
		MultipartFile imageFile = theArchImages.getImageFile();
		if (imageFile != null && !imageFile.isEmpty()) {
			try {
				byte[] imageData = imageFile.getBytes();
				theArchImages.setImageData(imageData);

				// Convert byte array to Base64
//				String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//				theArchImages.setBase64ImageData(base64ImageData);
			} catch (IOException e) {
				// Handle the exception
				e.printStackTrace();
			}
		}

		// Save the image
		imagesServices.save(theArchImages);

		// Use a redirect to prevent duplicate submissions
		return "redirect:/archimages/imagelist";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("imageId") int theId, Model theModel) {

		// get the employee form the service
		ArchImages theArchImages = imagesServices.findById(theId);

		List<ArchCategories> categories = categoriesService.findAll();
		theModel.addAttribute("categories", categories);

		List<ArchProjects> projects = projectsServices.findAll();
		theModel.addAttribute("projects", projects);

		String encodedImageData = ImageUtil.encodeImage(theArchImages.getImageData());
		theArchImages.setEncodedImageData(encodedImageData);

		// set the employee in the model to prepopulate the form
		theModel.addAttribute("image", theArchImages);

		// send over to our form

		return "dashboard/add_image_form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("imageId") int theId) {

		// delete the employee
		imagesServices.deleteById(theId);

		// redirect to the /employees/list
		return "redirect:/archimages/imagelist";
	}

	@GetMapping("/encodeImage/{imageId}")
	public ResponseEntity<byte[]> encodeImage(@PathVariable int imageId) {
		// Retrieve the ArchImages entity from your service or repository
		ArchImages archImage = imagesServices.findById(imageId);

		if (archImage != null && archImage.getImageData() != null) {
			// Set the appropriate headers for image data
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG); // Adjust based on your image type

			return new ResponseEntity<>(archImage.getImageData(), headers, HttpStatus.OK);
		} else {
			// Return a 404 Not Found response if the image or data is not available
			return ResponseEntity.notFound().build();
		}
	}

}
