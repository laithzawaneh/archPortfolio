package com.archPortfolio.archPortfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archPortfolio.archPortfolio.dao.ImagesRepository;
import com.archPortfolio.archPortfolio.entity.ArchImages;

@Service
public class ImagesServicesImpl implements ImagesServices {

	private ImagesRepository imagesRepository;

	@Autowired
	public ImagesServicesImpl(ImagesRepository theImagesRepository) {
		imagesRepository = theImagesRepository;
	}

	@Override
	public List<ArchImages> findAll() {
		return imagesRepository.findAll();
	}

	@Override
	public ArchImages findById(int theId) {

		Optional<ArchImages> result = imagesRepository.findById(theId);

		ArchImages theArchImages = null;

		if (result.isPresent()) {
			theArchImages = result.get();
		} else {
			throw new RuntimeException("Did not find image id - " + theId);
		}

		return theArchImages;
	}

	@Override
	public ArchImages save(ArchImages theArchImages) {
		return imagesRepository.save(theArchImages);
	}

	@Override
	public void deleteById(int theId) {
		imagesRepository.deleteById(theId);
	}

	@Override
	public List<ArchImages> findAllByProjects_projectId(int projectId) {
		return imagesRepository.findAllByProjects_projectId(projectId);
	}

//	@Override
//	public List<ArchImages> findDistinctByProjectId() {
//		return imagesRepository.findDistinctByProjectId();
//	}

//	@Override
//	public List<Object[]> getProjectAggregatedImageData() {
//		return imagesRepository.getProjectAggregatedImageData();
//	}

}
