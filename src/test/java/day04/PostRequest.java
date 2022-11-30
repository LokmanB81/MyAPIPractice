package day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.CountryPostPojo;
import utilities.GMIBankBaseUrl;
import utilities.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-countries adresine yeni bir ulke ekleyelim--post yapalim
 */
    @Test
    public void post01() {
        spec01.pathParam("1","tp-countries");

       // CountryPostPojo expectedData=new CountryPostPojo("Batch81");
      //  CountryPostPojo countryPostPojo=new CountryPostPojo("Batch81");
        CountryPojo expectedData=new CountryPojo("Batch8181");

        Response response=given().spec(spec01).contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + generateToken())
                .body(expectedData).when().post("/{1}");

        response.prettyPrint();

       // CountryPojo actualData=response.as(CountryPojo.class);
        CountryPojo actualData= JsonUtil.convertJsonToJava(response.asString(),CountryPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());

    }
}
