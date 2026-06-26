package org.example.springtheory.strategyrefactoring;

import org.example.springtheory.strategyrefactoring.dao.Database;
import org.example.springtheory.strategyrefactoring.dao.UserDao;
import org.example.springtheory.strategyrefactoring.domain.User;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        UserDao dao = new UserDao(db);

        System.out.println("== (별도 클래스) deleteAll ==");
        dao.deleteAllWithClass();

        System.out.println("\n== (익명 클래스) add(김) ==");
        dao.addWithAnonymous(new User("u1", "김"));

        System.out.println("\n== (람다) add(이) ==");
        dao.addWithLambda(new User("u2", "이"));

        // 최종 데이터 적재 결과 검증
        System.out.println("\n현재 사용자 수: " + db.getUsers().size());
        for (User u : db.getUsers()) {
            System.out.println("사용자: " + u.getName());
        }

    }
}
