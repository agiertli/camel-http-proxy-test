package sk.test.camelhttptest;

import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.spi.CamelEvent;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import io.quarkus.runtime.Startup;
import org.apache.camel.quarkus.core.events.ComponentAddEvent;


@ApplicationScoped
@Startup
public class ComponentConfiguration {

    @Inject
    SystemPropertiesHttpClientConfigurer systemPropertiesHttpClientConfigurer;

    public void onComponentAdd(@Observes ComponentAddEvent event) {
        if (event.getComponent() instanceof HttpComponent) {
        System.out.println("<<<<<< Creating http-proxy component >>>>>>");
         HttpComponent httpComponent = (HttpComponent) event.getComponent();
        httpComponent.setLogHttpActivity(true);
        httpComponent.setHttpClientConfigurer(systemPropertiesHttpClientConfigurer);
        
        }
    }
}