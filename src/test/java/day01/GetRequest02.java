package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest02 {


    @Test
    public void test01() {

        String url="https://reqres.in/api/users";

        Response response=given().when().get(url);
       // response.prettyPrint();

        // headers tests
        response.then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON)
                .header("Via","1.1 vegur");


        /*
         "id": 1,
        "email": "george.bluth@reqres.in",
        "first_name": "George",
        "last_name": "Bluth",
         */

        // id si 1 olanın datalarinin yukaridaki gibi oldugunu test edin

        // body tests
        // matcher class ile

        response.then().body("data[0].email",equalTo("george.bluth@reqres.in")
                            ,"data[0].first_name",equalTo("George")
                            ,"data[0].last_name",equalTo("Bluth"));







    }
}
