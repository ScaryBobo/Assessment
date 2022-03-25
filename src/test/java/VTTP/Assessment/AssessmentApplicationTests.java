package VTTP.Assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void getQuotationIsWorking () throws Exception {

	List<String> dummyItems = new ArrayList<>();
	dummyItems.add("durian");
	dummyItems.add("apple");
	dummyItems.add("pear");

	// Optional<Quotation> result = quoSvc.getQuotations(dummyItems); 
	
	//Assertions.assertTrue (result.isEmpty());
		Assertions.assertThrows(Exception.class, () -> {quoSvc.getQuotations(dummyItems);});	
	
	}

}
