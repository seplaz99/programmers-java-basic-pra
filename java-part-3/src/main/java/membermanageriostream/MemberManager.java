package membermanageriostream;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MemberManager {
    private final List<Member> members = new ArrayList<>();
    private final int capacity;
    private static final String FILE = "members.txt";

    public MemberManager(int capacity) {
        this.capacity = capacity;
        load();
    }

    public void save() {
        try (FileWriter fw = new FileWriter(FILE)) {
            for (Member m : members) {
                fw.write(m.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("저장 오류 : " + e.getMessage());
        }
    }

    public void load() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] p = line.split(",");
                String grade = p[0];
                String name = p[1];
                String email = p[2];
                String phone = p[3];

                Member m = grade.equals("VIP") ? new VipMember(name, email, phone) : new NormalMember(name, email, phone);
                members.add(m);
            }
        } catch (IOException e) {
            System.out.println("로드 오류 : " + e.getMessage());
        }
    }

    public boolean isFull() { return members.size() >= capacity; }

    public boolean existsEmail(String email) {
        for (Member m : members) if (m.getEmail().equals(email)) return true;
        return false;
    }
    public void add(Member m) {
        members.add(m);
        save();
    }

    public int size()     { return members.size(); }
    public int capacity() { return capacity; }

    public Member findByEmail(String email) {
        for (Member m : members) if (m.getEmail().equals(email)) return m;
        return null;
    }
    public Member findByName(String name) {
        for (Member m : members) if (m.getName().equals(name)) return m;
        return null;
    }
    public void printAll() {
        if (members.isEmpty()) { System.out.println("등록된 회원이 없습니다."); return; }
        for (Member m : members) m.printInfo();
    }
    public boolean update(String email, String name, String newEmail, String phone) {
        Member m = findByEmail(email);
        if (m == null) return false;
        m.update(name, newEmail, phone);
        save();
        return true;
    }
    public boolean delete(String email) {
        Member m = findByEmail(email);
        if (m == null) return false;
        members.remove(m);
        save();
        return true;
    }
}
