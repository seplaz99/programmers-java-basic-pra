package stream;

// 스트림
// 컬렉션(리스트 등)의 데이터를 흐름처럼 처리하는 도구

// 스트림의 세 단계
// 1. 스트림 만들기(list.stream())
// 2. 중간 연산(filter/map/sorted… 여러 개 가능)
// 3. 종료 연산(forEach/collect/count/sum 하나)
// 종료 연산이 와야 비로소 실행

// 자주 쓰는 연산과 람다의 종류
// filter	조건에 맞는 것만 남김    조건(p -> p.getPrice() >= 1000) → true/false
// map	다른 값으로 변환(1:1)	 변환(p -> p.getName())
// flatMap	변환 결과가 리스트일 때 평탄화    스트림 반환(o -> o.getItems().stream())
// forEach	하나씩 처리(출력 등)	처리(p -> System.out.println(p))
// sorted	정렬      비교((a, b) -> a.getPrice() - b.getPrice())
// collect	리스트 등으로 모음   (보통 Collectors.toList())
// count	개수 세기    없음

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("연필", 500),
                new Product("공책", 1200),
                new Product("지우개", 300),
                new Product("필통", 3000),
                new Product("볼펜", 800)
        ));

        System.out.println("===== 1. 스트림 만들고 전체 출력 (forEach) =====");
        products.forEach(p -> System.out.println(p.getName() + "(" +  p.getPrice() + ")"));

        System.out.println("\n===== 2. filter: 1000원 이상만 =====");
        products.stream()
                .filter(p -> p.getPrice() >= 1000)
                .forEach(p -> System.out.println(p.getName() + "(" +  p.getPrice() + ")"));

        System.out.println("\n===== 3. map: 이름만 뽑기 =====");
        products.stream().map(Product::getName).forEach(System.out::println);

        System.out.println("\n===== 4. map vs flatMap (주문 속 상품 목록) =====");
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList("연필", "공책")),
                new Order(2, Arrays.asList("필통", "볼펜", "공책"))
        );

        List<List<String>> byMap = orders.stream()
                .map(o -> o.getItems())
                .collect(Collectors.toList());
        System.out.println("map : " + byMap);

        List<String> byFlatMap = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.toList());
        System.out.println("flatMap : " + byFlatMap);

        System.out.println("\n===== 5. filter + map + collect: 1000원 이상 상품 이름 리스트 =====");
        List<String> expensiveNames = products.stream()
                .filter(p -> p.getPrice() >= 1000)
                .map(p -> p.getName())
                .collect(Collectors.toList());
        System.out.println(expensiveNames);

        System.out.println("\n===== 6. 통계 =====");
        long count = products.stream()
                .filter(p -> p.getPrice() >= 1000)
                .count();

        int sum = products.stream()
                .mapToInt(p -> p.getPrice())
                .sum();

        double avg = products.stream()
                .mapToInt(p -> p.getPrice())
                .average()
                .getAsDouble();

        List<String> byPrice = products.stream()
                .sorted((a, b) -> a.getPrice() - b.getPrice())
                .map(p -> p.getName())
                .collect(Collectors.toList());

        System.out.println("1000원 이상 개수 : " + count);
        System.out.println("전체 가격 합계 : " + sum);
        System.out.println("전체 가격 평균 ; " + avg);
        System.out.println("가격 오름차순 : " + byPrice);
    }
}
