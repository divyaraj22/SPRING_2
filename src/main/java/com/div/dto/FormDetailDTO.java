package com.div.dto;

import java.sql.Date;
import com.div.pojo.FormDetail;

public class FormDetailDTO {
    private int id;
    private String title;
    private String speakers;
    private String publicURL;
    private Date videoDate;
    private Date publishedDate;
    private String description;
    private String synopsis;
    private byte[] banner;
    private String videoUrl;
    private String previewVideoUrl;
    private String accessCategory;
    private Date freeViewExpiry;
    private UserDTO user;
    private String contentType;
    private boolean isPremium;

    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpeakers() {
		return speakers;
	}

	public void setSpeakers(String speakers) {
		this.speakers = speakers;
	}

	public String getPublicURL() {
		return publicURL;
	}

	public void setPublicURL(String publicURL) {
		this.publicURL = publicURL;
	}

	public Date getVideoDate() {
		return videoDate;
	}

	public void setVideoDate(Date videoDate) {
		this.videoDate = videoDate;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public byte[] getBanner() {
		return banner;
	}

	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getPreviewVideoUrl() {
		return previewVideoUrl;
	}

	public void setPreviewVideoUrl(String previewVideoUrl) {
		this.previewVideoUrl = previewVideoUrl;
	}

	public String getAccessCategory() {
		return accessCategory;
	}

	public void setAccessCategory(String accessCategory) {
		this.accessCategory = accessCategory;
	}

	public Date getFreeViewExpiry() {
		return freeViewExpiry;
	}

	public void setFreeViewExpiry(Date freeViewExpiry) {
		this.freeViewExpiry = freeViewExpiry;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	// Convert FormDetail model to FormDetailDTO
    public static FormDetailDTO fromModel(FormDetail formDetail) {
        FormDetailDTO dto = new FormDetailDTO();
        dto.setId(formDetail.getId());
        dto.setTitle(formDetail.getTitle());
        dto.setSpeakers(formDetail.getSpeakers());
        dto.setPublicURL(formDetail.getPublicURL());
        dto.setVideoDate(formDetail.getVideoDate());
        dto.setPublishedDate(formDetail.getPublishedDate());
        dto.setDescription(formDetail.getDescription());
        dto.setSynopsis(formDetail.getSynopsis());
        dto.setBanner(formDetail.getBanner());
        dto.setVideoUrl(formDetail.getVideoUrl());
        dto.setPreviewVideoUrl(formDetail.getPreviewVideoUrl());
        dto.setAccessCategory(formDetail.getAccessCategory());
        dto.setFreeViewExpiry(formDetail.getFreeViewExpiry());
        dto.setUser(UserDTO.fromModel(formDetail.getUser()));
        dto.setContentType(formDetail.getContentType());
        dto.setPremium(formDetail.isPremium());
        return dto;
    }

    // Convert FormDetailDTO to FormDetail model
    public FormDetail toModel() {
        FormDetail formDetail = new FormDetail();
        formDetail.setId(this.getId());
        formDetail.setTitle(this.getTitle());
        formDetail.setSpeakers(this.getSpeakers());
        formDetail.setPublicURL(this.getPublicURL());
        formDetail.setVideoDate(this.getVideoDate());
        formDetail.setPublishedDate(this.getPublishedDate());
        formDetail.setDescription(this.getDescription());
        formDetail.setSynopsis(this.getSynopsis());
        formDetail.setBanner(this.getBanner());
        formDetail.setVideoUrl(this.getVideoUrl());
        formDetail.setPreviewVideoUrl(this.getPreviewVideoUrl());
        formDetail.setAccessCategory(this.getAccessCategory());
        formDetail.setFreeViewExpiry(this.getFreeViewExpiry());
        formDetail.setUser(this.getUser().toModel());
        formDetail.setContentType(this.getContentType());
        formDetail.setPremium(this.isPremium());
        return formDetail;
    }
}
