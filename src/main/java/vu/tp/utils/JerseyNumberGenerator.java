package vu.tp.utils;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class JerseyNumberGenerator implements Serializable {
    public Integer generateJerseyNumber(){
        return new Random().nextInt(100);
    }
}
