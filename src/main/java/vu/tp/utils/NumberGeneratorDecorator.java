package vu.tp.utils;

import javax.annotation.PostConstruct;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Decorator
public class NumberGeneratorDecorator implements NumberGenerator {

    @Delegate
    @Inject
    NumberGenerator numberGenerator;

    @PostConstruct
    public void init(){
        System.out.println("Generator decorator created");
    }

    @Override
    public Integer generateJerseyNumber() {
        System.out.println("In decorator class");
        Integer num = numberGenerator.generateJerseyNumber();
        return Integer.max(0, num - 10);
    }
}
