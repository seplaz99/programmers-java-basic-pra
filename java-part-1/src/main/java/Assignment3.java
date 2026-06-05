package main.java;

import java.util.Scanner;

public class Assignment3 {
    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;

    public static void printMenu(int totalMoney) {
        System.out.println("================================= 자판기 ================================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("==========================================================================");
    }

    public static int getChoice() {
        System.out.println("메뉴를 선택하세요.");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        return choice;
    }

    public static int getMoney() {
        System.out.println("돈을 넣어주세요.");

        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();

        return money;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        boolean isRunning = true;
        int totalMoney = getMoney();

        printMenu(totalMoney);

        while (isRunning) {
            int choice = getChoice();

            switch(choice) {
                case 1:
                    if (totalMoney >= COKE) {
                        System.out.println("콜라가 나왔습니다.");
                        totalMoney -= COKE;
                        System.out.println("현재 금액 : " + totalMoney + "원");
                    } else  {
                        System.out.println("금액이 부족합니다.");
                    }
                    break;
                case 2:
                    if (totalMoney >= CIDER) {
                        System.out.println("사이다가 나왔습니다.");
                        totalMoney -= CIDER;
                        System.out.println("현재 금액 : " + totalMoney + "원");
                    } else  {
                        System.out.println("금액이 부족합니다.");
                    }
                    break;
                case 3:
                    if (totalMoney >= FANTA) {
                        System.out.println("환타가 나왔습니다.");
                        totalMoney -= FANTA;
                        System.out.println("현재 금액 : " + totalMoney + "원");
                    } else  {
                        System.out.println("금액이 부족합니다.");
                    }
                    break;
                case 4:
                    if (totalMoney >= WATER) {
                        System.out.println("물이 나왔습니다.");
                        totalMoney -= WATER;
                        System.out.println("현재 금액 : " + totalMoney + "원");
                    } else  {
                        System.out.println("금액이 부족합니다.");
                    }
                    break;
                case 5:
                    System.out.println("얼마를 넣으시겠습니까?");
                    int addMoney = sc.nextInt();
                    totalMoney += addMoney;
                    System.out.println("현재 금액 : " + totalMoney + "원");
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 번호 입니다.");
                    break;
            }

            System.out.println();
        }
    }
}
