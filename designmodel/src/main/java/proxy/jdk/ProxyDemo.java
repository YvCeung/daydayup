package proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author zy
 * @Date 2025/1/23 21:52
 **/
public class ProxyDemo {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxyInstance =(UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new LoggingInvocationHandler(userService));
        proxyInstance.addUser();
    }
}
