package sk.test.camelhttptest;

import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.CamelContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.annotation.PostConstruct;
import io.quarkus.runtime.Startup;

@ApplicationScoped
@Startup
public class ComponentConfiguration {

    @Inject
    CamelContext camelContext;

    @PostConstruct
    public void init() {
        System.out.println("<<<<<< ComponentConfiguration @PostConstruct called >>>>>>");
        
        if (camelContext == null) {
            System.err.println("<<<<<< ERROR: CamelContext is null >>>>>>");
            return;
        }
        
        try {
            System.out.println("<<<<<< Creating http-proxy component >>>>>>");
            HttpComponent httpComponent = new HttpComponent();
            httpComponent.setUseSystemProperties(true);
            httpComponent.setLogHttpActivity(true);
            
            // Register the component with the Camel context
            camelContext.addComponent("http-proxy", httpComponent);
            
            System.out.println("<<<<<< http-proxy component registered successfully >>>>>>");
            System.out.println("<<<<<< Available components: " + camelContext.getComponentNames() + " >>>>>>");
            
            // Test if the component is accessible
            try {
                var endpoint = camelContext.getEndpoint("http-proxy://test.com");
                System.out.println("<<<<<< Component test successful: " + endpoint + " >>>>>>");
            } catch (Exception e) {
                System.err.println("<<<<<< Component test failed: " + e.getMessage() + " >>>>>>");
            }
        } catch (Exception e) {
            System.err.println("<<<<<< Error registering http-proxy component: " + e.getMessage() + " >>>>>>");
            e.printStackTrace();
        }
    }
}