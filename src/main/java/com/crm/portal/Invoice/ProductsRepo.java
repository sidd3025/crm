package com.crm.portal.Invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Deals.Deal;
import com.crm.portal.Lead.Lead;

@Repository
public interface ProductsRepo extends JpaRepository <ProductMaster, Integer> {

	@Query("select p from ProductMaster p where p.item_name = ?1")
	 List<ProductMaster> GetIDById(String item_name);
	
	
}
