package com.archPortfolio.archPortfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archPortfolio.archPortfolio.dao.ProjectsRepository;
import com.archPortfolio.archPortfolio.entity.ArchProjects;

@Service
public class ProjectsServicesImpl implements ProjectsServices {

	private ProjectsRepository projectsRepository;

	@Autowired
	public ProjectsServicesImpl(ProjectsRepository theCategoriesRepository) {
		projectsRepository = theCategoriesRepository;
	}

	@Override
	public List<ArchProjects> findAll() {
		return projectsRepository.findAll();
	}

	@Override
	public ArchProjects findById(int theId) {
		Optional<ArchProjects> result = projectsRepository.findById(theId);

		ArchProjects theArchProjects = null;

		if (result.isPresent()) {
			theArchProjects = result.get();
		} else {
			throw new RuntimeException("Did not find project id - " + theId);
		}

		return theArchProjects;
	}

	@Override
	public void deleteById(int theId) {
		projectsRepository.deleteById(theId);
	}

	@Override
	public ArchProjects save(ArchProjects theArchProjects) {
		return projectsRepository.save(theArchProjects);
	}

	@Override
	public List<ArchProjects> findAllByCategories_categoryId(Long categoryId) {
		return projectsRepository.findAllByCategories_categoryId(categoryId);
	}






}
