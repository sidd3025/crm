package com.crm.portal.Register;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CaseStudiesService {

	@Autowired
	private CaseStudiesrepo repo;
	
	public List<CaseStudies> listAll() {
		return repo.findAll();
	}
	public CaseStudies get(Long id) {
		return repo.findById(id).get();
	}
	public void save(CaseStudies datasheet) {
		repo.save(datasheet);	
	}
	public void saveAll(List<CaseStudies> datasheet) {
		repo.saveAll(datasheet);	
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	
	
}
