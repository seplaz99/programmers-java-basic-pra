package org.example.springtheory.ch01.ex1_5;

import org.example.springtheory.ch01.ex1_5.dao.DaoFactory;
import org.example.springtheory.ch01.ex1_5.dao.UserDAO;

// 스프링의 제어의 역전(IoC, Inversion of Control)


public class Start {

    public static void main(String[] args) {
        UserDAO dao = new DaoFactory().userDAO();

    }
}
