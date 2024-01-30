package com.archPortfolio.archPortfolio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archPortfolio.archPortfolio.entity.ArchCategories;

public interface CategoriesRepository extends JpaRepository<ArchCategories, Integer> {

	public List<ArchCategories> findAllByOrderByCategoryNameAsc();
}
