package sk.test.camelhttptest;

import org.apache.camel.CamelContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ProxyConfiguration {

    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "http.proxyHost", defaultValue = "localhost")
    String proxyHost;

    @ConfigProperty(name = "http.proxyPort", defaultValue = "8080")
    Integer proxyPort;

    @ConfigProperty(name = "http.proxyEnabled", defaultValue = "false")
    Boolean enableProxy;

    @PostConstruct
    public void configureProxy() {
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