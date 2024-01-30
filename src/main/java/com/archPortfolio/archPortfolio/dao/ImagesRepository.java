package com.archPortfolio.archPortfolio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archPortfolio.archPortfolio.entity.ArchImages;

public interface ImagesRepository extends JpaRepository<ArchImages, Integer> {

	List<ArchImages> findAllByProjects_projectId(int projectId);

//	@Query("SELECT DISTINCT project_id FROM arch_images image_id")
//	List<ArchImages> findDistinctByProjectId();

//	@Query("SELECT i.project_id, p.project_name, MIN(i.image_data) AS aggregated_image_data, p.project_description "
//			+ "FROM arch_images i, arch_projects p " + "WHERE i.project_id = p.project_id "
//			+ "GROUP BY p.project_id, p.project_name, p.project_description")
//	@Query("SELECT i.project_id, p.project_name, MIN(i.image_data) AS aggregated_image_data, p.project_description "
//			+ "FROM arch_images i JOIN arch_projects p ON i.project_id = p.project_id "
//			+ "GROUP BY p.project_id, p.project_name, p.project_description")
//	List<Object[]> getProjectAggregatedImageData();
}
