package main.java.bingogame;

import java.util.*;

public class BingoGame {

    static final int SIZE = 5;        // 판 크기 5x5
    static final int MAX = 25;        // 숫자 1~25
    static final int TARGET = 3;

    boolean[] called = new boolean[MAX + 1];   // 1~25
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public void play() {
        System.out.println("===== 빙고 게임 =====");
        System.out.println("컴퓨터와 번갈아 숫자를 불러 빙고를 완성하세요!");

        int[][] playerBoard = new int[SIZE][SIZE];
        int[][] computerBoard = new int[SIZE][SIZE];

        boolean[][] playerMarked = new boolean[SIZE][SIZE];
        boolean[][] computerMarked = new boolean[SIZE][SIZE];

        makeBoard(playerBoard);
        makeBoard(computerBoard);
        System.out.println("먼저 " + TARGET + "줄을 완성하면 승리!");

        while (true) {
            System.out.println("\n===== 내 빙고판 =====");
            printBoard(playerBoard, playerMarked);

            // 내 차례
            int num = playerPick();
            callNumber(num, "내가");
            mark(playerBoard, playerMarked, num);
            mark(computerBoard, computerMarked, num);
            if (checkWin(playerMarked, "나")) break;

            // 컴퓨터 차례
            int cNum = computerPick();
            callNumber(cNum, "컴퓨터가");
            mark(playerBoard, playerMarked, cNum);
            mark(computerBoard, computerMarked, cNum);
            if (checkWin(computerMarked, "컴퓨터")) break;

            System.out.println("\n현재 빙고 줄  → 나: " + countBingo(playerMarked)
                    + "줄,  컴퓨터: " + countBingo(computerMarked) + "줄");
        }
    }

    void makeBoard(int[][] board) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= MAX; i++) {
            nums.add(i);
        }

        Collections.shuffle(nums);
        int idx = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = nums.get(idx++);
            }
        }
    }

    void printBoard(int[][] board, boolean[][] marked) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (marked[r][c]) {
                    System.out.print("[ *] ");
                } else {
                    System.out.printf("[%2d] ", board[r][c]);
                }
            }
            System.out.println();
        }
    }

    void mark(int[][] board, boolean[][] marked, int num) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == num) {
                    marked[r][c] = true;
                }
            }
        }
    }

    int countBingo(boolean[][] marked) {
        int count = 0;

        for (int r = 0; r < SIZE; r++) {
            boolean all = true;
            for (int c = 0; c < SIZE; c++) {
                if (!marked[r][c]) {
                    all = false;
                    break;
                }
            }
            if (all) {
                count++;
            }
        }

        for (int c = 0; c < SIZE; c++) {
            boolean all = true;
            for (int r = 0; r < SIZE; r++) {
                if (!marked[r][c]) {
                    all = false;
                    break;
                }
            }
            if (all) {
                count++;
            }
        }

        boolean d1 = true;
        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][i]) {
                d1 = false;
                break;
            }
        }
        if (d1) {
            count++;
        }

        boolean d2 = true;
        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][SIZE - 1 - i]) {
                d2 = false;
                break;
            }
        }
        if (d2) {
            count++;
        }

        return count;
    }

    int playerPick() {
        while (true) {
            System.out.print("부를 숫자 입력 (1~25) > ");
            int num;
            try {
                num = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요."); continue;
            }
            if (num < 1 || num > MAX)      System.out.println("1~25 사이로 입력하세요.");
            else if (called[num])          System.out.println("이미 부른 숫자입니다.");
            else {
                called[num] = true;
                return num;
            }
        }
    }

    int computerPick() {
        int num;
        do { num = rand.nextInt(MAX) + 1; } while (called[num]);

        called[num] = true;
        return num;
    }

    void callNumber(int num, String msg) {
        System.out.printf("%s 부른 숫자 : %d \n", msg, num);
    }

    boolean checkWin(boolean[][] marked, String winnerName) {
        int bingoCount = countBingo(marked);

        if (bingoCount >= TARGET) {
            System.out.println("\n========================");
            System.out.println("★ " + winnerName + "가 " + bingoCount + "줄을 완성하여 최종 승리했습니다! ★");
            System.out.println("========================");
            return true;
        }

        return false;
    }

}
