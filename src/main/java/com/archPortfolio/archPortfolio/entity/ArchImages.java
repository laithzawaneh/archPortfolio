package com.archPortfolio.archPortfolio.entity;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "arch_images")
public class ArchImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int imageId;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private ArchProjects projects;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ArchCategories categories;

	@Lob
	@Column(name = "image_data", columnDefinition = "LONGBLOB")
	private byte[] imageData;

	@Transient
	@NotNull(message = "Please select an image file")
	@Size(max = 5 * 1024 * 1024, message = "Image file size must be less than 5MB")
	private MultipartFile imageFile;

    @Transient
	private String encodedImageData;

//	@Column(name = "image_url")
//	private String imageURL;

	@Column(name = "note")
	private String note;

	public ArchImages() {
	}

	public ArchImages(int imageId, ArchProjects projects, ArchCategories categories, byte[] imageData, String note) {
		super();
		this.imageId = imageId;
		this.projects = projects;
		this.categories = categories;
		this.imageData = imageData;
		this.note = note;
	}

	public String getEncodedImageData() {
		return encodedImageData;
	}

	public void setEncodedImageData(String encodedImageData) {
		this.encodedImageData = encodedImageData;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public ArchProjects getProjects() {
		return projects;
	}

	public void setProjects(ArchProjects projects) {
		this.projects = projects;
	}

	public ArchCategories getCategories() {
		return categories;
	}

	public void setCategories(ArchCategories categories) {
		this.categories = categories;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "ArchImages [imageId=" + imageId + ", projects=" + projects + ", categories=" + categories
				+ ", imageData=" + Arrays.toString(imageData) + ", note=" + note + "]";
	}

}
