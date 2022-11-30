package day04;

import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.CustomerPojo;
import utilities.GMIBankBaseUrl;
import utilities.ReadText;
import utilities.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class GMIBank01 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers end point'ine
request gönderin
 1) Tüm Customer bilgilerini ekrana yazdırın.
 2) Tüm Customer SSN lerini ekrana yazdırın.
 3) Tüm Customer SSN lerini text dosyası olarak kaydedin
 4) Olusturduğunuz text dosyasından  SSNleri okuyarak
    "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
 */

    @Test
    public void name() throws IOException {
        CustomerPojo [] customers;

        spec01.pathParam("1","tp-customers");


        Response response=given().spec(spec01).headers("Authorization", "Bearer " + generateToken()).when().get("/{1}");
       // response.prettyPrint();

        ObjectMapper obj=new ObjectMapper();
        customers=obj.readValue(response.asString(), CustomerPojo[].class);

        for (int i = 0; i < customers.length; i++) {

            System.out.println(customers[i]);
        }

        for (int i = 0; i < customers.length; i++) {

            System.out.println("ssnler :"+customers[i].getSsn());
        }

        String fileName="src/test/java/day04/ssn_numbers.txt";
        WriteToText.saveSSNData(fileName,customers);// WriteToText classindaki, saveSSNData metoduyla
                                                    // customers icerisindeki datalari, fileName'in gosterdigi dosya yoluna
                                                    // ssn_numbers.txt ismiyle txt olarak olustur...

        List<String> expectedSSN=new ArrayList<>();
        expectedSSN.add("531-95-8437");
        expectedSSN.add("049-43-2360");
        expectedSSN.add("123-34-3434");


        List<String > actualSSN= ReadText.readCustomerSSNList(fileName);
        assertTrue("ssn hatasi",actualSSN.containsAll(expectedSSN));
    }
}
