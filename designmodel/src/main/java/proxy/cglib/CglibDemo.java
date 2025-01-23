package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author zy
 * @Date 2025/1/23 22:32
 **/
public class CglibDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProductService.class);
        enhancer.setCallback(new ProductServiceInterceptor());
        ProductService productService =(ProductService)enhancer.create();
        productService.addProduct();
    }
}
