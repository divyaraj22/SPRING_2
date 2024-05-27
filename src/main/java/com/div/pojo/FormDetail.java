package com.div.pojo;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "form_details")
public class FormDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "speakers")
	private String speakers;

	@Column(name = "publicURL")
	private String publicURL;

	@Column(name = "video_date")
	private Date videoDate;

	@Column(name = "published_date")
	private Date publishedDate;

	@Column(name = "description")
	private String description;

	@Column(name = "synopsis")
	private String synopsis;

	@Lob
	@Column(name = "banner", columnDefinition = "LONGBLOB")
	private byte[] banner;

	@Column(name = "video_url")
	private String videoUrl;

	@Column(name = "preview_video_url")
	private String previewVideoUrl;

	@NotNull(message = "Access category date is required")
	@Column(name = "access_category")
	private String accessCategory;

	@Column(name = "free_view_expiry")
	private Date freeViewExpiry;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String contentType;

	@Column(name = "is_premium")
	private boolean isPremium;

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

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

	public String getPreviewVideoUrl() {
		return previewVideoUrl;
	}

	public void setPreviewVideoUrl(String previewVideoUrl) {
		this.previewVideoUrl = previewVideoUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FormDetail(int id, String title, String speakers, String publicURL, Date videoDate, Date publishedDate,
			String description, String synopsis, byte[] banner, String videoUrl, String previewVideoUrl,
			@NotNull(message = "Access category date is required") String accessCategory, Date freeViewExpiry,
			User user, String contentType, boolean isPremium) {
		super();
		this.id = id;
		this.title = title;
		this.speakers = speakers;
		this.publicURL = publicURL;
		this.videoDate = videoDate;
		this.publishedDate = publishedDate;
		this.description = description;
		this.synopsis = synopsis;
		this.banner = banner;
		this.videoUrl = videoUrl;
		this.previewVideoUrl = previewVideoUrl;
		this.accessCategory = accessCategory;
		this.freeViewExpiry = freeViewExpiry;
		this.user = user;
		this.contentType = contentType;
		this.isPremium = isPremium;
	}

	public FormDetail() {

	}

}