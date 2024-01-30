package com.archPortfolio.archPortfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archPortfolio.archPortfolio.dao.CategoriesRepository;
import com.archPortfolio.archPortfolio.entity.ArchCategories;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private CategoriesRepository categoriesRepository;

	@Autowired
	public CategoriesServiceImpl(CategoriesRepository theCategoriesRepository) {
		categoriesRepository = theCategoriesRepository;
	}

	@Override
	public List<ArchCategories> findAll() {
		return categoriesRepository.findAllByOrderByCategoryNameAsc();
	}

	@Override
	public ArchCategories findById(int theId) {
		Optional<ArchCategories> result = categoriesRepository.findById(theId);
		
		ArchCategories theArchCategories=null;
		
		if(result.isPresent()) {
			theArchCategories=result.get();
		}else {
			throw new RuntimeException("Did not find category id - " + theId);
		}
		
		return theArchCategories;
	}

	@Override
	public ArchCategories save(ArchCategories theArchCategories) {
		return categoriesRepository.save(theArchCategories);
	}

	@Override
	public void deleteById(int theId) {
		categoriesRepository.deleteById(theId);
	}

}
