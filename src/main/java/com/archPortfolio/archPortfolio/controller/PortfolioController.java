package com.archPortfolio.archPortfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archPortfolio.archPortfolio.entity.ArchImages;
import com.archPortfolio.archPortfolio.entity.ProjectImageView;
import com.archPortfolio.archPortfolio.service.ImagesServices;
import com.archPortfolio.archPortfolio.service.ProjectImageViewService;
import com.archPortfolio.archPortfolio.util.ImageUtil;

@Controller
public class PortfolioController {

	private ProjectImageViewService projectImageViewService;
	private ImagesServices imagesServices;

	@Autowired
	public PortfolioController(ProjectImageViewService projectImageViewService, ImagesServices imagesServices) {
		this.projectImageViewService = projectImageViewService;
		this.imagesServices = imagesServices;
	}

	@GetMapping("/")
	public String homePage() {
		return "home/home";
	}

	@GetMapping("/mainhome")
	public String mainHomePage(@RequestParam(name = "projectId", required = false) Integer projectId, Model theModel) {
		List<ArchImages> projectImages;

		List<ProjectImageView> theImageViews = projectImageViewService.findAll();
		for (ProjectImageView image : theImageViews) {
			String encodedImageData = ImageUtil.encodeImage(image.getAggregated_image_data());
			image.setEncodedImageData(encodedImageData);
			// System.out.println("encoded Image Data : " + encodedImageData);
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

		theModel.addAttribute("images", theImageViews);
		theModel.addAttribute("projectImages", projectImages); // Pass the project-specific images to the template
		theModel.addAttribute("projectId", projectId); // Add this line
		return "home/main_home";
	}

//    @PostMapping("/getImagesByProjectId")
//    public String getImagesByProjectId(@RequestParam int projectId, Model theModel) {
//        List<ArchImages> theArchImages = imagesServices.findAllByProjects_ProjectId(projectId);
//		for (ArchImages image : theArchImages) {
//			String encodedImageData = ImageUtil.encodeImage(image.getImageData());
//			image.setEncodedImageData(encodedImageData);
//			System.out.println("encoded Image Data : " + encodedImageData);
//		}
//        theModel.addAttribute("imagesByProjectId", theArchImages);
//        return "home/carousel_modal::carousel-content";
//    }

	@GetMapping("/getImagesByProjectId")
	public String getImagesByProjectId(@RequestParam int projectId, Model theModel) {
		List<ArchImages> theArchImages = imagesServices.findAllByProjects_projectId(projectId);
		for (ArchImages image : theArchImages) {
			String encodedImageData = ImageUtil.encodeImage(image.getImageData());
			image.setEncodedImageData(encodedImageData);
			System.out.println("Image Data for Project ID " + projectId + ":");
			System.out.println("Original Image Data: " + image.getImageData());
			System.out.println("Encoded Image Data : " + encodedImageData);
		}
		theModel.addAttribute("imagesByProjectId", theArchImages);
		return "home/carousel_modal";
	}

	@GetMapping("/getImagesByProjectIdList")
	@ResponseBody
	public List<ArchImages> getImagesByProjectIdList(
			@RequestParam(name = "projectId", required = false) Integer projectId, Model theModel) {
		List<ArchImages> theArchImages = imagesServices.findAllByProjects_projectId(projectId);
//		for (ArchImages image : theArchImages) {
//			String encodedImageData = ImageUtil.encodeImage(image.getImageData());
//			image.setEncodedImageData(encodedImageData);
//			System.out.println("Image Data for Project ID " + projectId + ":");
//			System.out.println("Original Image Data: " + image.getImageData());
//			System.out.println("Encoded Image Data : " + encodedImageData);
//		}
		if (projectId != null) {
			theArchImages = imagesServices.findAllByProjects_projectId(projectId);
			for (ArchImages image : theArchImages) {
				String encodedImageData = ImageUtil.encodeImage(image.getImageData());
				image.setEncodedImageData(encodedImageData);
				System.out.println("encoded Image Data : " + encodedImageData);
			}
		} else {
			theArchImages = imagesServices.findAll();
			for (ArchImages image : theArchImages) {
				String encodedImageData = ImageUtil.encodeImage(image.getImageData());
				image.setEncodedImageData(encodedImageData);
				System.out.println("encoded Image Data : " + encodedImageData);
			}
		}

		theModel.addAttribute("projectImages", theArchImages); // Pass the project-specific images to the template
		theModel.addAttribute("projectId", projectId); // Add this line
		return theArchImages;
	}

}
