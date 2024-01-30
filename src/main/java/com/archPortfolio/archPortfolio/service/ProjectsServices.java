package com.archPortfolio.archPortfolio.service;

import java.util.List;

import com.archPortfolio.archPortfolio.entity.ArchProjects;

public interface ProjectsServices {
	List<ArchProjects> findAll();

	ArchProjects findById(int theId);

	ArchProjects save(ArchProjects theArchProjects);

	void deleteById(int theId);

	List<ArchProjects> findAllByCategories_categoryId(Long categoryId);

}
