package cardplay;

class Deck {
    final int CARD_NUM = 52;
    Card2[] cards = new Card2[CARD_NUM];

    public Deck() {
        int i = 0;

        for ( int k = Card2.KIND_MAX; k > 0; k-- ) {
            for ( int n = 0; n < Card2.NUM_MAX; n++ ) {
                cards[i++] = new Card2(k, n + 1);
            }
        }
    }

    public Card2 pick(int index) {
        return cards[index];
    }

    public Card2 pick() {
        int index = (int)(Math.random() * CARD_NUM); // 덱에서 카드 하나를 선택한다.
        return pick(index);
    }

    public void shuffle() {
        for ( int i = 0; i < cards.length; i++ ) {
            int index = (int)(Math.random() * CARD_NUM);

            Card2 temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
    }
}
