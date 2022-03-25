package VTTP.Assessment.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import VTTP.Assessment.Model.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {
    
    public Optional<Quotation> getQuotations (List<String> items) {

        JsonArrayBuilder arr = Json.createArrayBuilder();

        for (String a: items){
            arr.add(Json.createValue(a));
        }
        JsonArray itemJsonArray= arr.build();
        System.out.println(">> Json ItemArray : "+ itemJsonArray);

        String url = "https://quotation.chuklee.com/quotation";
        RequestEntity<String> req = RequestEntity
            .post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(itemJsonArray.toString(), String.class);
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange (req, String.class);

        System.out.println(resp.getBody());

        JsonObject data= null;

        Quotation quoteInstance = new Quotation();

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())){
            JsonReader reader = Json.createReader(is);
            data = reader.readObject();
            String quoteId = data.getString("quoteId");
            JsonArray quoteArrayJson = data.getJsonArray("quotations");

            for (int i = 0; i < quoteArrayJson.size(); i++){
                JsonObject x = quoteArrayJson.getJsonObject(i);
                quoteInstance.addQuotation(x.getString("item"), (float)x.getJsonNumber("unitPrice").doubleValue());

            }

            quoteInstance.setQuoteId(quoteId);

        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return Optional.empty();
        }


        return Optional.of(quoteInstance);
        
    }
}
