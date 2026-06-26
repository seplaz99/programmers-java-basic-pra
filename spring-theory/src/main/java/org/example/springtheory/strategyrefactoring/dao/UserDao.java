package org.example.springtheory.strategyrefactoring.dao;

import org.example.springtheory.strategyrefactoring.domain.User;

public class UserDao {

    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }

    public void context(StatementStrategy strategy) {
        db.open();
        strategy.run(db);
        db.close();
    }

    // 별도 클래스
    public void deleteAllWithClass() { context(new DeleteAllStrategy()); }
    public void addWithClass(User user) { context(new AddStrategy(user)); }

    // 익명 클래스
    public void deleteAllWithAnonymous() {
        context(new StatementStrategy() {
            @Override public void run(Database db) {
                db.getUsers().clear();
                System.out.println("[전략-익명] 전체 삭제");
            }
        });
    }

    public void addWithAnonymous(User user) {
        context(new StatementStrategy() {
            @Override public void run(Database db) {
                db.getUsers().add(user);
                System.out.println("[전략-익명] 추가: " + user.getName());
            }
        });
    }

    // 람다
    public void deleteAllWithLambda() {
        context(db -> {
            db.getUsers().clear();
            System.out.println("[전략-람다] 전체 삭제");
        });
    }

    public void addWithLambda(User user) {
        context(db -> {
            db.getUsers().add(user);
            System.out.println("[전략-람다] 추가: " + user.getName());
        });
    }
}
