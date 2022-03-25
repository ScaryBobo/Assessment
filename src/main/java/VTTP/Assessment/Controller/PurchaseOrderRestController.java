package VTTP.Assessment.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VTTP.Assessment.Model.Quotation;
import VTTP.Assessment.Service.QuotationService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@RestController
@RequestMapping ("/api")
public class PurchaseOrderRestController {
   

    @Autowired
    private QuotationService quoSvc;



    @PostMapping(path="/po", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> placeOrder(@RequestBody String payload, HttpServletRequest request){
        //System.out.println(">>>Payload : " + payload);
      
     
        JsonObject req = null;

        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader r = Json.createReader(is);
            req = r.readObject();

            List<String> items = new ArrayList<>();
            JsonArray itemArray = req.getJsonArray("lineItems");
            //System.out.println(itemArray.toString());
            for (int i = 0 ; i < itemArray.size() ; i++){
                items.add(itemArray.getJsonObject(i).getString("item"));
            }
            //System.out.println(items);
            Quotation quoteOfItems = new Quotation();
            quoteOfItems = quoSvc.getQuotations(items).orElse(null);


            double totalCost = 0;

            for (int i = 0; i< itemArray.size(); i++){
                
                String itemName = itemArray.getJsonObject(i).getString("item");
                totalCost = (itemArray.getJsonObject(i).getInt("quantity")) * (quoteOfItems.getQuotation(itemName));
                
            }

            System.out.println(totalCost);
            
            
            } catch (IOException e){
                
            } 
            return null;
}
}
