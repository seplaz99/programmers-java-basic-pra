package org.example.springtheory.aop;

import org.example.springtheory.aop.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AopConfig.class);

        OrderService orderService = ctx.getBean(OrderService.class);
        MemberService memberService = ctx.getBean(MemberService.class);
        ProductService productService = ctx.getBean(ProductService.class);

        System.out.println("===== 주문 서비스 호출 =====");
        System.out.println(orderService.placeOrder("기계식 키보드"));

        System.out.println("\n===== 회원 서비스 호출 =====");
        System.out.println(memberService.register("kim"));

        // 추가
        System.out.println("\n===== 상품 서비스 호출 =====");
        System.out.println(productService.getProduct("A-100"));

        System.out.println("\n===== 진짜 프록시인지 확인 =====");
        System.out.println("orderService 의 실제 타입: " + orderService.getClass());

        ctx.close();
    }
}
