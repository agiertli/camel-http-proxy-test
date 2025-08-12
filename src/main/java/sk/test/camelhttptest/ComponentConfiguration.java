package sk.test.camelhttptest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import org.apache.camel.component.http.HttpComponent;


@ApplicationScoped
public class ComponentConfiguration {

    @Named("http-proxy")
    HttpComponent http() {

        HttpComponent component = new HttpComponent();
        component.setUseSystemProperties(true);
        component.setLogHttpActivity(true);

        return component;
    }
}