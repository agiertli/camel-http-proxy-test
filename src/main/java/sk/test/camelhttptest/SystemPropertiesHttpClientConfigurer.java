package sk.test.camelhttptest;

import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultRoutePlanner;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.HttpContext;

@jakarta.enterprise.context.ApplicationScoped
public class SystemPropertiesHttpClientConfigurer implements HttpClientConfigurer {

    @Override
    public void configureHttpClient(HttpClientBuilder clientBuilder) {
        String proxyHost = System.getProperty("http.proxyHost");
        String proxyPort = System.getProperty("http.proxyPort");

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
