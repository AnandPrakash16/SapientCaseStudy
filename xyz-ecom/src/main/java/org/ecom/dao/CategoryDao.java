package org.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.ecom.entity.Category;
import org.ecom.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Integer>{

	@Transactional
	@Query(value = "select * from PRODUCT where CAT_ID = ?1", nativeQuery = true)
	List<Category> findByCatId(int CAT_ID);
}
