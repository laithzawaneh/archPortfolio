package com.archPortfolio.archPortfolio.service;

import java.util.List;

import com.archPortfolio.archPortfolio.entity.ArchCategories;

public interface CategoriesService {

	List<ArchCategories> findAll();

	ArchCategories findById(int theId);

	ArchCategories save(ArchCategories theArchCategories);

	void deleteById(int theId);
}
