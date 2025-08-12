package sk.test.camelhttptest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;

import org.apache.camel.component.http.HttpComponent;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultRoutePlanner;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.HttpContext;

@ApplicationScoped
public class ComponentConfiguration {

    @ConfigProperty(name = "http.proxyHost")
    String proxyHost;

    @ConfigProperty(name = "http.proxyPort")
    Integer proxyPort;

    @ConfigProperty(name = "http.proxyEnabled")
    Boolean enableProxy;

    @Inject
    CamelContext camelContext;

    @Named("http-proxy")
    HttpComponent http() {
        HttpComponent component = new HttpComponent();
        component.setLogHttpActivity(true);
        
        if (enableProxy) {
            Log.info("Setting proxy host and port on http-proxy component: " + proxyHost + ":" + proxyPort);
            
            // Create a custom HttpClientConfigurer to set up proxy
            HttpClientConfigurer proxyConfigurer = new HttpClientConfigurer() {
                @Override
                public void configureHttpClient(HttpClientBuilder clientBuilder) {
                    HttpHost proxy = new HttpHost(proxyHost, proxyPort);
                    
                    DefaultRoutePlanner routePlanner = new DefaultRoutePlanner(null) {
                        @Override
                        protected HttpHost determineProxy(HttpHost target, HttpContext context) throws HttpException {
                            return proxy;
                        }
                    };
                    
                    clientBuilder.setRoutePlanner(routePlanner);
                    Log.info("Proxy route planner configured for: " + proxy);
                }
            };
            
            component.setHttpClientConfigurer(proxyConfigurer);
        }

        return component;
    }
}