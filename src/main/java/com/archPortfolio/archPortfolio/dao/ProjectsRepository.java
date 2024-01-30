package com.archPortfolio.archPortfolio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archPortfolio.archPortfolio.entity.ArchProjects;

public interface ProjectsRepository extends JpaRepository<ArchProjects, Integer> {

	List<ArchProjects> findAllByCategories_categoryId(Long categoryId);
}
