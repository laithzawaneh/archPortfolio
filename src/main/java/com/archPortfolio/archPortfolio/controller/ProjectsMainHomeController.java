package com.archPortfolio.archPortfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.archPortfolio.archPortfolio.entity.ArchImages;
import com.archPortfolio.archPortfolio.entity.ProjectImageView;
import com.archPortfolio.archPortfolio.service.ImagesServices;
import com.archPortfolio.archPortfolio.service.ProjectImageViewService;
import com.archPortfolio.archPortfolio.util.ImageUtil;

@Controller
public class ProjectsMainHomeController {

	private ImagesServices imagesServices;
	private ProjectImageViewService projectImageViewService;

	@Autowired
	public ProjectsMainHomeController(ImagesServices imagesServices, ProjectImageViewService projectImageViewService) {
		this.imagesServices = imagesServices;
		this.projectImageViewService = projectImageViewService;
	}

	@GetMapping("/testimages")
	public String showImages(@RequestParam(name = "projectId", required = false) Integer projectId, Model theModel) {

		List<ArchImages> projectImages;

		List<ProjectImageView> theImageViews = projectImageViewService.findAll();
		for (ProjectImageView image : theImageViews) {
			String encodedImageData = ImageUtil.encodeImage(image.getAggregated_image_data());
			image.setEncodedImageData(encodedImageData);
			System.out.println("encoded Image Data : " + encodedImageData);
		}

		if (projectId != null) {
			projectImages = imagesServices.findAllByProjects_projectId(projectId);
			for (ArchImages image : projectImages) {
				String encodedImageData = ImageUtil.encodeImage(image.getImageData());
				image.setEncodedImageData(encodedImageData);
				System.out.println("encoded Image Data : " + encodedImageData);
			}
		} else {
			projectImages = imagesServices.findAll();
			for (ArchImages image : projectImages) {
				String encodedImageData = ImageUtil.encodeImage(image.getImageData());
				image.setEncodedImageData(encodedImageData);
				System.out.println("encoded Image Data : " + encodedImageData);
			}
		}

		System.out.println(projectImages);

		theModel.addAttribute("images", theImageViews);
		theModel.addAttribute("projectImages", projectImages); // Pass the project-specific images to the template
	    theModel.addAttribute("projectId", projectId); // Add this line


		return "fragments/projects_cards";
	}

}
