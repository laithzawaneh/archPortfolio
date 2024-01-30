package com.archPortfolio.archPortfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "arch_projects")
public class ArchProjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int projectId;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ArchCategories categories;

	@Column(name = "project_name", nullable = false)
	private String projectName;

	@Column(name = "project_description", columnDefinition = "TEXT")
	private String description;

	public ArchProjects() {
	}

	public ArchProjects(int projectId, ArchCategories categories, String projectName, String description) {
		this.projectId = projectId;
		this.categories = categories;
		this.projectName = projectName;
		this.description = description;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public ArchCategories getCategories() {
		return categories;
	}

	public void setCategories(ArchCategories categories) {
		this.categories = categories;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ArchProjects [projectId=" + projectId + ", categories=" + categories + ", projectName=" + projectName
				+ ", description=" + description + "]";
	}
}
