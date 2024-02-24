package com.crm.portal.Register;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	public List<ProductResource> listAll() {
		return repo.findAll();
	}
	public ProductResource get(Long id) {
		return repo.findById(id).get();
	}
	public void save(ProductResource courses) {
		repo.save(courses);	
	}
	public void saveAll(List<ProductResource> courses) {
		repo.saveAll(courses);	
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
//	public void insertDataIntoProduct( String readingmaterials1,
//			 String filedescription1, String type1) {
//		repo.insertDataIntoProduct(readingmaterials1, filedescription1,type1);
//	}
	
	public String getproductfile(String type1) {
	  	return repo.getproductfile(type1);
	}
}
