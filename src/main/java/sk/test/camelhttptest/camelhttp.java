package sk.test.camelhttptest;

import org.apache.camel.builder.RouteBuilder;

public class CamelHttp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    //    from("timer:myTimer?period=5s")
    //         .to("http:6895e6b2039a1a2b2890bc40.mockapi.io/api/v1/test?logHttpActivity=true&followRedirects=true")
    //         .log("Response from web via proxy ${body}");


            from("timer:myTimer?period=5s")
            .to("http-proxy:6895e6b2039a1a2b2890bc40.mockapi.io/api/v1/test")
            .log("Response from web via proxy ${body}");
    }
}
