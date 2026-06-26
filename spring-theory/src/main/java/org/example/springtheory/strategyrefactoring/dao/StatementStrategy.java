package org.example.springtheory.strategyrefactoring.dao;

@FunctionalInterface
interface StatementStrategy {
    void run(Database db);
}
