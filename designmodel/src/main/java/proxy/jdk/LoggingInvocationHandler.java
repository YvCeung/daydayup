package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author zy
 * @Date 2025/1/23 20:55
 **/
public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;
    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method call: " + method.getName());
        Object res = method.invoke(target, args);
        System.out.println("After method call: " + method.getName());
        return res;
    }
}
