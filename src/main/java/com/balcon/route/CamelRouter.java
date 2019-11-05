package com.balcon.route;

import com.balcon.model.ModbusDTO;
import com.balcon.service.CamelService;
import com.balcon.service.CamelServiceImpl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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

        final ModbusDTO modbusDTO = new ModbusDTO();

        final CamelServiceImpl camelServiceImpl = new CamelServiceImpl();

        String apiDescription = null;
        try {
            InputStream fileStream = this.getClass().getResourceAsStream("/static/api-description.html");
            apiDescription = IOUtils.toString(fileStream, "UTF-8");
        } catch (IOException e) {
            apiDescription = "";
        }

        restConfiguration()
                .apiContextPath("api-doc")
                .apiProperty("api.title", "balcon-api")
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

        rest().description("getModbusDataDesc")
                .get("/modbusData")
                .to("direct:modbusData");

        from("direct:modbusData")
                .streamCaching()
                .to("bean:CamelService?method=getModbusData");

        from("activemq:ExampleQueue")
//                .to("log:sample");
//                .split(xpath("/string"))
//                .split(body().tokenizePair("<string>", "</string>")).streaming()
                .to("bean:CamelService?method=procModbusData");

//        from("activemq:ExampleQueue").process(new Processor() {
//            public void process(Exchange exchange) throws Exception {
//                camelServiceImpl.procModbusData(exchange.getIn().getBody().toString());
////                modbusDTO.setDatos(exchange.getIn().getBody().toString());
//                System.out.println("procModbusData"
////                        + exchange.getIn().getBody());
////                        + modbusDTO.getDatos()
//                );
//            }
//        });

//
//        from("activemq:ExampleQueue").process(new Processor() {
//            public void process(Exchange exchange) throws Exception {
//                modbusDTO.setDatos(exchange.getIn().getBody().toString());
//                System.out.println("Modbus received: "
////                        + exchange.getIn().getBody());
//                        + modbusDTO.getDatos());
//            }
//        });
    }
}
