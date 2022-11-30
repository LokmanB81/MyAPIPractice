package day04;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.CustomerPojo;
import pojos.UserPojo;
import utilities.GMIBankBaseUrl;
import utilities.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GteRequest09 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers/110472
 */
    /*
    {
        "id": 111206,
        "login": "delilah.metz",
        "firstName": "Melva",
        "lastName": "Bernhard",
        "email": "chas.kuhlman@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
     */

    @Test
    public void get09() {
        spec01.pathParams("1","tp-customers","2","110472");

        UserPojo userPojo=new UserPojo(111206,"delilah.metz","Melva","Bernhard","chas.kuhlman@yahoo.com"
        ,true,"en",null,null);

        /*
    "country": {
        "id": 24105,
        "name": "San Jose",
        "states": null
    },
     */
        CountryPojo countryPojo=new CountryPojo(24105,"San Jose",null);
         /*
    {
    "id": 110472,
    "firstName": "Melva",
    "lastName": "Bernhard",
    "middleInitial": "A",
    "email": "chas.kuhlman@yahoo.com",
    "mobilePhoneNumber": "192-580-3408",
    "phoneNumber": "192-580-3408",
    "zipCode": "40207",
    "address": "Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872",
    "city": "New Jordanhaven",
    "ssn": "523-50-1191",
    "createDate": "2021-11-30T21:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 24105,
        "name": "San Jose",  // CountryPojo
        "states": null
    },
    "state": "",
    "user": {

          */
        CustomerPojo expectedData=new CustomerPojo(110472,"Melva","Bernhard","A","chas.kuhlman@yahoo.com","192-580-3408",
                "192-580-3408","40207","Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872","New Jordanhaven","523-50-1191",
                "2021-11-30T21:00:00Z",false,countryPojo,"",userPojo);

        Response response=given().spec(spec01).headers("Authorization", "Bearer " + generateToken()).when().get("/{1}/{2}");
        response.prettyPrint();

        CustomerPojo actualData=response.as(CustomerPojo.class);
       // System.out.println("actualData = " + actualData);
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getUser().getId(),actualData.getUser().getId());


        // ObjectMapper
        CustomerPojo actualData2= JsonUtil.convertJsonToJava(response.asString(),CustomerPojo.class);
       // System.out.println("actualData2 = " + actualData2);

        assertEquals(expectedData.getId(),actualData2.getId());
        assertEquals(expectedData.getFirstName(),actualData2.getFirstName());
        assertEquals(expectedData.getUser().getId(),actualData2.getUser().getId());
    }
}
