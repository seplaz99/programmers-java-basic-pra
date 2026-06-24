package org.example.springtheory.ch02.ex2_1;

import org.example.springtheory.ch02.ex2_1.dao.DaoFactory;
import org.example.springtheory.ch02.ex2_1.dao.UserDAO;
import org.example.springtheory.ch02.ex2_1.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

// 문제점
// Start.java 테스트의 문제점
// 수동 확인 작업의 번거로움
// 콘솔에 나온 값을 보고 등록과 조회가 성공적으로 되고 있는지를 확인하는 건 사람의 책임이다.
// 실행 작업의 번거로움
// 만약 DAO가 수백 개가 되고 그에 대한 main() 메서드도 그만큼 만들어진다면,
// 전체 기능을 테스트해보기 위해 main() 메서드를 수백 번 실행하는 수고가 필요하다.

public class Start {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        User user = userDAO.get("test1");
        System.out.println(user.getName());
    }
}
