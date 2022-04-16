package com.lawencon.community.dto.thread;

import java.util.List;

public class GetAllThreadDtoDataRes {
	private String id;
	private String threadCode;
	private String threadTitle;
	private String threadContent;
	private String typeCode;
	private List<String> categoryId;
	private List<String> categoryName;
	private List<String> attachmentId;
	private List<String> attachemntExtension;
	private String pollingName;
	private List<GetThreadPollingChoiceDtoRes> choices;
	private List<Integer> countVote;
	private Integer totalVote;
	private Integer totalLike;
	private Integer totalComment;
	private Boolean isPremium;
	private Boolean isLiked = false;
	private Boolean isBookmarked = false;
	private Boolean isVoted = false;
	private String profileName;
	private String profileImage;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}

	public String getThreadTitle() {
		return threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public List<String> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}

	public List<String> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(List<String> categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(List<String> attachmentId) {
		this.attachmentId = attachmentId;
	}

	public List<String> getAttachemntExtension() {
		return attachemntExtension;
	}

	public void setAttachemntExtension(List<String> attachemntExtension) {
		this.attachemntExtension = attachemntExtension;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public List<GetThreadPollingChoiceDtoRes> getChoices() {
		return choices;
	}

	public void setChoices(List<GetThreadPollingChoiceDtoRes> choice) {
		this.choices = choice;
	}

	public List<Integer> getCountVote() {
		return countVote;
	}

	public void setCountVote(List<Integer> countVote) {
		this.countVote = countVote;
	}

	public Integer getTotalVote() {
		return totalVote;
	}

	public void setTotalVote(Integer totalVote) {
		this.totalVote = totalVote;
	}

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	public Integer getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Boolean getIsBookmarked() {
		return isBookmarked;
	}

	public void setIsBookmarked(Boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}

	public Boolean getIsVoted() {
		return isVoted;
	}

	public void setIsVoted(Boolean isVoted) {
		this.isVoted = isVoted;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
