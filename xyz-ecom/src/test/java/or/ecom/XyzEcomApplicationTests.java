package or.ecom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.ecom.XyzEcomApplication;
import org.ecom.dao.ProductDao;
import org.ecom.entity.Product;
import org.ecom.service.EcomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=XyzEcomApplication.class)
class XyzEcomApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	EcomService service;
	
	@Mock
	ProductDao productDao;
	
	@Test
	public void searchProductTest() {
		List<Product> list = new ArrayList<>();
		list.add(new Product("SH123","Green","M","Shirt", "Black Shirt with two pockets",25,350,Calendar.getInstance().getTime(),1,"LEVIS"));
		list.add(new Product("TR345","Green","S","Shirt", "Trouser with two pockets",20,2250,Calendar.getInstance().getTime(),2,"PEOPLE"));
		list.add(new Product("SH124","Green","L","Shirt", "Red Shirt with short sleeves",10,2100,Calendar.getInstance().getTime(),1,"PEOPLE"));
		
		Mockito.when(service.serachProduct("Shirt", "Green")).thenReturn(list);
		
		List<Product> listP = service.serachProduct("Shirt", "Green");
		assertEquals("SH123", listP.get(0).getSKU());
	}
	
	@Test
	public void findByBrandTest() {
		List<Product> list = new ArrayList<>();
		list.add(new Product("SH124","Red","L","Shirt", "Red Shirt with short sleeves",10,2100,Calendar.getInstance().getTime(),1,"PEOPLE"));
		Mockito.when(productDao.findByBrand("PEOPLE")).thenReturn(list);
		
		List<Product> listP = productDao.findByBrand("PEOPLE");
		
		 assertEquals("PEOPLE", listP.get(0).getBrandName());
	}
}
