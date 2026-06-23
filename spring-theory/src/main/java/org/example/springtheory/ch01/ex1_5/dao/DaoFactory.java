package org.example.springtheory.ch01.ex1_5.dao;

public class DaoFactory {

    public UserDAO userDAO() {
        // SimpleConnectionMaker conn = new DConnectionMaker();
        // UserDAO userDAO = new UserDAO(conn);

        return new UserDAO(connectionMaker());
    }

    public AccountDAO accountDAO() {
        // SimpleConnectionMaker conn = new DConnectionMaker();
        // AccountDAO  accountDAO = new AccountDAO(conn);

        return new AccountDAO(connectionMaker());
    }

    public MessageDAO messageDAO() {
        //SimpleConnectionMaker conn = new DConnectionMaker();
        // SimpleConnectionMaker conn = new DConnectionMaker();

        return new MessageDAO(connectionMaker());
    }

    private SimpleConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
