package com.jzsoft.fm.model;

public class Job {
	private String id;
	private String workTicketNumber;
	private String jobName;
	private long startTime;
	private long endTime;
	private int endMark;
	private int syncMark;
	private String photo;
	private String video;
	private String jobRecord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkTicketNumber() {
		return workTicketNumber;
	}

	public void setWorkTicketNumber(String workTicketNumber) {
		this.workTicketNumber = workTicketNumber;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getEndMark() {
		return endMark;
	}

	public void setEndMark(int endMark) {
		this.endMark = endMark;
	}

	public int getSyncMark() {
		return syncMark;
	}

	public void setSyncMark(int syncMark) {
		this.syncMark = syncMark;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getJobRecord() {
		return jobRecord;
	}

	public void setJobRecord(String jobRecord) {
		this.jobRecord = jobRecord;
	}

}