package org.example.springtheory.aop.service;

public class MemberServiceImpl implements MemberService {
    @Override
    public String register(String id) {
        sleep(50);
        return "회원가입완료 : " + id;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}
