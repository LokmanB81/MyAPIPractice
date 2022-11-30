package day02;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.*;

public class GetRequest05_Token {
    @Test
    public void test05(){

        String url = "https://www.gmibank.com/api/tp-customers";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDgxIiwiYXV0aCI6IlJPTEVfQURNSU4iLCJleHAiOjE2NzAwMDQyNzd9.ZGNWLPbWqtl2htNVkOWTgF_KHJOXFJX8Z0sUFSK66KGccFrMD9TR15xJWZE3FdsVZISELbrYhg3j4nfO0YsSWQ";

       // Response response = given().headers("Authorization", "Bearer " + token).when().get(url);// Bearer yazdıktan sonra bir bosluk bırakıyoruz
        Response response = given().headers("Authorization", "Bearer " + Authentication.generateToken()).when().get(url);

        response.prettyPrint();

    }
}
