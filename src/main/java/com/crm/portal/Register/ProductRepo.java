package com.crm.portal.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<ProductResource , Long>{
	

//	@Modifying
//	@Query(value = "insert into product_resources (readingmaterials1 , readingmaterials2,filedescription1,filedescription1, type) VALUES (:readingmaterials1, :readingmaterials2 , :readingmaterials3 ,:readingmaterials4, :readingmaterials5 , :readingmaterials6)", nativeQuery = true)
//	public void insertDataIntoProduct(@Param("readingmaterials1") String readingmaterials1,@Param("readingmaterials2") String readingmaterials2 ,@Param("readingmaterials3") String readingmaterials3 ,@Param("readingmaterials4") String readingmaterials4,@Param("readingmaterials5") String readingmaterials5 ,@Param("readingmaterials6") String readingmaterials6);
	
//	@Query(value = "insert into product_resources(readingmaterials1 , readingmaterials2,filedescription1,filedescription2, file1type,file2type) VALUES (:readingmaterials1 , :filedescription1 ,:file1type)",nativeQuery = true)
//	public void insertDataIntoProduct(@Param("readingmaterials1") String readingmaterials1 ,@Param("filedescription1") String filedescription1,@Param("file1type")String type1);
	
	
	@Query(value="select id from ProductResource where type1=?1")
	public String getproductfile(String type1);
	
	
}
