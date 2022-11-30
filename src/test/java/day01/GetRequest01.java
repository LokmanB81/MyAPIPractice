package day01;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest01 {

    @Test
    public void test01() {

        // set url
        String url="https://restful-booker.herokuapp.com/booking";

        // send request n get response
        Response response=given().when().get(url);
       // Response response=given().auth().basic("user","password").when().get(url);
       // basic auth ile request gondermek icin

       // response.prettyPrint();// response body i yazar
        // response.prettyPeek(); // response daki herseyi header ve body yazar

      //  response.print();// string olarak yazar

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        // Junit assertions
        Assert.assertEquals("status kod hatali",200,response.statusCode());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());

        // assertthat assertions
        response.then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json; charset=utf-8");



    }
}
