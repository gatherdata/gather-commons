package org.gatherdata.commons.example.internal;

import org.gatherdata.commons.example.ExampleService;
import org.ops4j.peaberry.activation.Start;
import org.ops4j.peaberry.activation.Stop;

public class ExampleServiceImpl implements ExampleService {

    public String sayHello(String toSubject) {
        return "Hello " + toSubject + "!";
    }

    @Start
    public void start() {
        System.out.println("Starting Hello Service");
    }
    
    @Stop
    public void stop() {
        System.out.println("Stopping Hello Service");
    }
}
