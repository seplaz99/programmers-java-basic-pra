package waitnotify;

public class AnswerThread extends Thread {
    private Chat chat;
    private String[] answers = {"Hello", "I'm fine, thank you.", "I'm coding."};

    public AnswerThread(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        for (String answer : answers) {
            chat.answer(answer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
