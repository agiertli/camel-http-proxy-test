package sk.test.camelhttptest;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelHttp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("<<<<<< CamelHttp configure() called >>>>>>");
        
        from("timer:myTimer?period=5s")
            .to("http:localhost:8080")
            .log("Response from web via proxy ${body}");
    }
}