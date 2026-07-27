package pet;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.print("반려동물의 이름을 지어주세요 : ");
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        Pet pet = new Pet(name);
        pet.showStatus();

        while (true) {
            System.out.println("무엇을 할까요? [1] 먹이주기 [2] 놀아주기 [3] 상태보기 [4] 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    pet.feed();
                    pet.showStatus();
                    break;
                case 2:
                    pet.play();
                    pet.showStatus();
                    break;
                case 3:
                    pet.showStatus();
                    break;
                case 4:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
            }
        }


    }

}
