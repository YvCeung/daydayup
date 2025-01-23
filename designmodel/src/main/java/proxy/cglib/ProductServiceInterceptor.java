package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author zy
 * @Date 2025/1/23 22:23
 **/
public class ProductServiceInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB-BEGIN");
        Object res = methodProxy.invokeSuper(object, args);
        System.out.println("CGLIB-AFTER");
        return res;
    }
}
