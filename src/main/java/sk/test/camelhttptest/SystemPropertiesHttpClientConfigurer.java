package sk.test.camelhttptest;

import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultRoutePlanner;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;

@jakarta.enterprise.context.ApplicationScoped
public class SystemPropertiesHttpClientConfigurer implements HttpClientConfigurer {

    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "http.proxyHost")
    String property_proxyHost;

    @ConfigProperty(name = "http.proxyPort")
    Integer property_proxyPort;

    @ConfigProperty(name = "http.proxyEnabled")
    Boolean enableProxy;

    @Override
    public void configureHttpClient(HttpClientBuilder clientBuilder) {
        String proxyHost = property_proxyHost; // alternative approach: System.getProperty("http.proxyHost")
        String proxyPort = String.valueOf(property_proxyPort);  // alternative approach: System.getProperty("http.proxyPort")

        Log.info("Setting proxy host and port on http-proxy component: " + proxyHost + ":" + proxyPort);

        if (proxyHost != null && proxyPort != null) {
            HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
            
            DefaultRoutePlanner routePlanner = new DefaultRoutePlanner(null) {
                @Override
                protected HttpHost determineProxy(HttpHost target, HttpContext context) throws HttpException {
                    return proxy;
                }
            };

            clientBuilder.setRoutePlanner(routePlanner);
        }
    }
}
