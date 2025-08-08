package sk.aspecta.camelhttptest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ProxyConfiguration extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "http.proxyHost")
    String proxyHost;

    @ConfigProperty(name = "http.proxyPort")
    Integer proxyPort;

    @ConfigProperty(name = "http.proxyEnabled")
    Boolean enableProxy;

    @Override
    public void configure() throws Exception {
        // Configure proxy using Camel context global options

        if (enableProxy) {
            System.out.println("Proxy is enabled");

        System.out.println("Proxy host: " + proxyHost);
        System.out.println("Proxy port: " + proxyPort);

        camelContext.getGlobalOptions().put("http.proxyHost", proxyHost);
        camelContext.getGlobalOptions().put("http.proxyPort", String.valueOf(proxyPort));
        
        // Log the configuration for debugging
        System.out.println("=== Proxy Configuration via Global Options ===");
        System.out.println("http.proxyHost: " + camelContext.getGlobalOptions().get("http.proxyHost"));
        System.out.println("http.proxyPort: " + camelContext.getGlobalOptions().get("http.proxyPort"));
        System.out.println("=============================================");
    } else {
        System.out.println("Proxy is disabled");
    }
} 
}