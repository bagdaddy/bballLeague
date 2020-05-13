package vu.tp.interceptors;

import vu.tp.utils.Tracker;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@CustomInterceptor
public class SpecialInterceptor implements Serializable  {
    @Inject
    Tracker tracker;

    @AroundInvoke
    public Object validateInput(InvocationContext context) throws Exception{
        System.out.println("Special interceptor");
        System.out.println(tracker.track() + " number of logged method calls");
        return context.proceed();
    }
}
