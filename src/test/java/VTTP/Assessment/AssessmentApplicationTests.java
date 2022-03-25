package VTTP.Assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import VTTP.Assessment.Model.Quotation;
import VTTP.Assessment.Service.QuotationService;




@SpringBootTest
@AutoConfigureMockMvc
class AssessmentApplicationTests {

	@Autowired
	private QuotationService quoSvc;

	@Autowired
	private MockMvc mvc; 

	@Test
	void getQuotationIsWorking() {

	List<String> dummyItems = new ArrayList<>();
	dummyItems.add("durian");
	dummyItems.add("plum");
	dummyItems.add("pear");

	Assertions.assertNotNull(quoSvc.getQuotations(dummyItems));
	
	//"plum" is not inside the dropdown list, hence the test will fail. Replace "plum" with "apple", test will pass.
	}

}
