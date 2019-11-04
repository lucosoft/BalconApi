package com.balcon.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class CamelRouter extends RouteBuilder {
    @Value("${endpoint.base.uri}")
    private String baseUri;
    @Override
    public void configure() throws Exception {

        String apiDescription = null;
        try {
            InputStream fileStream = this.getClass().getResourceAsStream("/static/api-description.html");
            apiDescription = IOUtils.toString(fileStream, "UTF-8");
        } catch (IOException e) {
            apiDescription = "";
        }

        restConfiguration()
                .apiContextPath("api-doc")
                .apiProperty("api.title", "demo20171204")
                .apiProperty("api.description", apiDescription)
                .apiProperty("api.version", "0.0.1-SNAPSHOT")
                .apiProperty("cors", "true")
                .apiProperty("base.path", baseUri)
                .apiProperty("api.path", baseUri)
                .apiProperty("host", "")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .skipBindingOnErrorCode(false);

        rest().produces("text/plain")
                .get("/geocod")
                .to("direct:geocod");

        from("direct:geocod")
                .to("geocoder:address:current")
                .transform().simple("Hello from Spring Boot and Camel. We are at: ${body}");

        rest().produces("text/plain")
                .get("/echo")
                .to("direct:echo");

        from("direct:echo")
                .streamCaching()
                .to("bean:CamelService?method=echo");

        rest().description("getDataDesc")
                .get("/data")
                .to("direct:data");

        from("direct:data")
                .streamCaching()
                .to("bean:CamelService?method=getData");

        from("activemq:ExampleQueue")
                .to("log:sample");
    }
}
