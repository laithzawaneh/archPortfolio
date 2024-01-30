package com.archPortfolio.archPortfolio.service;

import java.util.List;

import com.archPortfolio.archPortfolio.entity.ArchImages;

public interface ImagesServices {

	List<ArchImages> findAll();

	ArchImages findById(int theId);

	ArchImages save(ArchImages theArchImages);

	void deleteById(int theId);

	List<ArchImages> findAllByProjects_projectId(int projectId);

//    List<ArchImages> findDistinctByProjectId();

//    List<Object[]> getProjectAggregatedImageData();

}
