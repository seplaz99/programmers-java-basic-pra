package org.example.springtheory.ch01.ioc;

interface ClickListener {
    void onClick();
}

class Button {
    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public void press() {
        System.out.println("[시스템] 버튼이 눌렸습니다.");
        if (listener != null) {
            // 시스템(Button)이 내 코드를 호출하게 함
            listener.onClick();
        }
    }
}

class LikeAction implements ClickListener {
    @Override
    public void onClick() {
        System.out.println("내 코드 실행 : 좋아요!");
    }
}