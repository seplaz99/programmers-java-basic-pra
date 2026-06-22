package solid;

import java.util.ArrayList;

// ❌ 나쁜 예: 바뀔 이유가 2개 (내용 규칙 변경 / 저장 방식 변경)
/*class Journal {
    private ArrayList<String> entries = new ArrayList<>();
    void add(String text) { entries.add(text); }

    void saveToFile(String filename) {   // ← 이게 두 번째 책임!
        // 파일에 저장하는 코드...
    }
}*/

class Journal {
    private ArrayList<String> entries = new ArrayList<>();

    void add(String text) {
        entries.add(text);
    }

    String getText() {
        return String.join("\n", entries);
    }
}

class JournalSaver {
    void saveToFile(Journal journal) {
        System.out.println(journal.getText());
    }
}