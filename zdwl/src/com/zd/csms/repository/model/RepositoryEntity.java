package com.zd.csms.repository.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryEntity {
	
	private RepositoryVO repository=new RepositoryVO();
	private RepositoryTrainVO repositoryTrain=new RepositoryTrainVO();
	private List<RepositoryDispatchCityVO> repositoryDispatchCity=new ArrayList<RepositoryDispatchCityVO>();
	public RepositoryVO getRepository() {
		return repository;
	}
	public void setRepository(RepositoryVO repository) {
		this.repository = repository;
	}
	public RepositoryTrainVO getRepositoryTrain() {
		return repositoryTrain;
	}
	public void setRepositoryTrain(RepositoryTrainVO repositoryTrain) {
		this.repositoryTrain = repositoryTrain;
	}
	public List<RepositoryDispatchCityVO> getRepositoryDispatchCity() {
		return repositoryDispatchCity;
	}
	public void setRepositoryDispatchCity(
			List<RepositoryDispatchCityVO> repositoryDispatchCity) {
		this.repositoryDispatchCity = repositoryDispatchCity;
	}

}
