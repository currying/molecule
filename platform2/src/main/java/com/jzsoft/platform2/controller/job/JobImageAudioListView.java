package com.jzsoft.platform2.controller.job;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.JobRepository;

@Model
@ViewScoped
public class JobImageAudioListView implements Serializable {

	private static final long serialVersionUID = 2759688047331845045L;

	@Inject
	private JobRepository jobRepository;

	private List<String> images;

	private String audio;

	private String currentId;

	@Produces
	@Named
	public List<String> getImages() {
		return images;
	}

	@Produces
	@Named
	public String getAudio() {
		return audio;
	}

	public void retrieveJobImagesById() {
		images = jobRepository.findJobImageById(currentId);
	}

	public void retrieveJobAudioById() {
		audio = jobRepository.findJobAudioById(currentId);
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

}
