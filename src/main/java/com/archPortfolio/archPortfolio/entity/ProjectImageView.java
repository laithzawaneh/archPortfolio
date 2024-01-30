package com.archPortfolio.archPortfolio.entity;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class ProjectImageView {

	@Id
	private Long project_id;
	private String project_name;
	private byte[] aggregated_image_data;
	private String project_description;
	@Transient
	private String encodedImageData;

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public byte[] getAggregated_image_data() {
		return aggregated_image_data;
	}

	public void setAggregated_image_data(byte[] aggregated_image_data) {
		this.aggregated_image_data = aggregated_image_data;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public String getEncodedImageData() {
		return encodedImageData;
	}

	public void setEncodedImageData(String encodedImageData) {
		this.encodedImageData = encodedImageData;
	}

	@Override
	public String toString() {
		return "ProjectImageView [project_id=" + project_id + ", project_name=" + project_name
				+ ", aggregated_image_data=" + Arrays.toString(aggregated_image_data) + ", project_description="
				+ project_description + "]";
	}

}
