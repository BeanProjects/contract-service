package io.beandev.contracts;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.AuthorizationValue;

import java.util.Arrays;

import static java.lang.System.out;

public class OpenAPIParserExample {
    public static void main(String[] args) {
        String openApiSpecPath = "path/to/your/openapi-spec.yaml";

        // build a authorization value
        AuthorizationValue mySpecialHeader = new AuthorizationValue()
                .keyName("x-special-access")  //  the name of the authorization to pass
                .value("i-am-special")        //  the value of the authorization
                .type("header");              //  the location, as either `header` or `query`

        // or in a single constructor
        AuthorizationValue apiKey = new AuthorizationValue("api_key", "special-key", "header");


        // Parse the OpenAPI specification
//        OpenAPI openAPI = new OpenAPIV3Parser().read(openApiSpecPath);
        OpenAPI openAPI = new OpenAPIV3Parser().read(
                "https://petstore3.swagger.io/api/v3/openapi.json",
                Arrays.asList(mySpecialHeader, apiKey),
                null
        );

        // Now you can work with the parsed OpenAPI object
        // For example, you can access information about paths, components, etc.
        out.println("OpenAPI Version: " + openAPI.getOpenapi());
        openAPI.getComponents().getSchemas().forEach((k, v) -> out.println("Schema: " + k + " -> " + v));
    }
}
