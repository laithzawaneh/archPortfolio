package com.archPortfolio.archPortfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archPortfolio.archPortfolio.dao.ProjectImageViewRepository;
import com.archPortfolio.archPortfolio.entity.ProjectImageView;

@Service
public class ProjectImageViewServiceImpl implements ProjectImageViewService {

	private ProjectImageViewRepository projectImageViewRepository;

	@Autowired
	public ProjectImageViewServiceImpl(ProjectImageViewRepository projectImageViewRepository) {
		super();
		this.projectImageViewRepository = projectImageViewRepository;
	}

	@Override
	public List<ProjectImageView> findAll() {
		return projectImageViewRepository.findAll();
	}
}
