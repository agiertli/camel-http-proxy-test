package sk.test.camelhttptest;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelHttp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("<<<<<< CamelHttp configure() called >>>>>>");
        
        from("timer:myTimer?period=5s")
            .to("http:6895e6b2039a1a2b2890bc40.mockapi.io/api/v1/test?logHttpActivity=true&followRedirects=true")
            .log("Response from web via proxy ${body}");
    }
}
