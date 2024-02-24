package com.crm.portal.Register;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DatasheetService {

	
	@Autowired
	private Datasheetrepo repo;
	
	public List<DataSheet> listAll() {
		return repo.findAll();
	}
	public DataSheet get(Long id) {
		return repo.findById(id).get();
	}
	public void save(DataSheet datasheet) {
		repo.save(datasheet);	
	}
	public void saveAll(List<DataSheet> datasheet) {
		repo.saveAll(datasheet);	
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
