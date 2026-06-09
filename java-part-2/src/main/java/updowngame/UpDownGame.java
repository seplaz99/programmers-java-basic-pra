package updowngame;

import java.util.*;

public class UpDownGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int answer = rand.nextInt(100) + 1;
        int count = 0;

        System.out.println("숫자를 맞혀보세요! (1 ~ 100)");
        while (true) {
            System.out.print("입력 : ");
            int guess = sc.nextInt();
            if (guess > 100) {
                System.out.println("범위를 벗어났어요.");
                continue;
            }

            count++;

            if (guess == answer) {
                System.out.printf("정답입니다! %d번 만에 맞혔어요.", count);
                break;
            } else if (guess > answer) {
                System.out.println("DOWN! 더 작은 수 입니다. ");
            } else {
                System.out.println("UP! 더 큰 수 입니다. ");
            }
        }
    }
}
