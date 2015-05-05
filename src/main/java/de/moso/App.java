/**
 * Created by sandro on 17.04.15.
 */
package de.moso;

import de.moso.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class App {
    @Autowired
    private ComponentRepository repository;

    /*
        @Autowired
        private LocationRepository locationRepository;
    */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
