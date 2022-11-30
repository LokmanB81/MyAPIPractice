package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Authentication {
   // public static void main(String[] args) { System.out.println(generateToken()); }

    public static String generateToken(){

         /*
    {
  "password": "Batch81+",
  "rememberMe": true,
  "username": "Batch81"
}
     */
        String username="Batch81";
        String password="Batch81+";


        Map<String,Object> postBody=new HashMap<>();
        postBody.put("username",username);
        postBody.put("password",password);
        postBody.put("rememberMe",true);
        String endPoint="https://www.gmibank.com//api/authenticate";

        Response response=given().contentType(ContentType.JSON).body(postBody).when().post(endPoint);
        //  given().body(postBody).when().post(endPoint) ile endPoint'e postBody'yi post yaptık...
        // request sonunda response olarak token aldık....

        /*
        "id_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDgxIiwiYXV0aCI6IlJPTEVfQURNSU4iLCJleHAiOjE2NzAwNTQxMjd9.
        wQpkhT1nlsLIWc450h4uPHLLvbH15GO9Pdg_g81jesFc5O9IyELdL_PTJI4t0mUGb3KzwG0WjYT8Y5xtuuQuZw"
         */
        JsonPath token=response.jsonPath();
        return token.getString("id_token"); // token verir
    }


}
