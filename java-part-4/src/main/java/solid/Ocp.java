package solid;

// ❌ 나쁜 예: 등급이 늘 때마다 이 메서드를 '수정'해야 함
/*class DiscountCalculator {
    int calc(String grade, int price) {
        if (grade.equals("GOLD"))      return price * 90 / 100;
        else if (grade.equals("VIP"))  return price * 80 / 100;
        else                           return price;
    }
}*/

interface DiscountPolicy {
    int discount(int price);
}

class BasicDiscount implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return price;
    }
}

class GoldDiscount implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return price * 90 / 100;
    }
}

class VipDiscount implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return price * 80 / 100;
    }
}