package vu.tp.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@CustomInterceptor
public class BasicInterceptor implements Serializable {
    @AroundInvoke
    public Object validateInput(InvocationContext context) throws Exception{
        System.out.println("Basic interceptor");
        System.out.println(context.toString());
        return context.proceed();
    }
}
