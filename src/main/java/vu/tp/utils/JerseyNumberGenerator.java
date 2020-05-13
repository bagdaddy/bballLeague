package vu.tp.utils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class JerseyNumberGenerator implements Serializable, NumberGenerator {

    @PostConstruct
    public void init(){
        System.out.println("Simple generator created");
    }

    public Integer generateJerseyNumber(){
        System.out.println("Simple generator");
        return new Random().nextInt(100);
    }
}
