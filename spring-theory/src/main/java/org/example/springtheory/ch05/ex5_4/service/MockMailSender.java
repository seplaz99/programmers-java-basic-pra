package org.example.springtheory.ch05.ex5_4.service;

import java.util.ArrayList;
import java.util.List;

// 실제로는 테스트쪽에 만들어야 함
// * MockMailSender - 보낸 메일을 '기록'하는 테스트 대역(목 오브젝트)
// DummyMailSender가 '그냥 무시'한다면, 이 목 오브젝트는 한 걸음 더 나아가
// '무엇을 보냈는지'를 안에 저장해 둔다.
//  -> 테스트에서 "업그레이드된 사용자에게 메일이 정확히 갔는가?"를 검증할 수 있게 해준다.
//     (예: upgradeLevels() 후 getSentTo()에 기대한 수신자들이 들어 있는지 확인)

// 보통 이런 목 클래스는 src/test 에 두지만, 추상화의 효과를 한곳에서 보여주려고 여기 함께 둔다.
// 핵심: 실제 메일 서버 없이도, 추상화(MailSender) 덕분에 발송 동작을 검증할 수 있다는 점이다.
public class MockMailSender implements MailSender {
    private List<String> sendTo = new ArrayList<>();

    @Override
    public void send(Mail mail) {
        sentTo.add(mail.getTo());
    }

    public List<String> getSendTo() {
        return sendTo;
    }
}
