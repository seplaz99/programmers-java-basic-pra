// I/O Stream (입출력 스트림)
// 자바에서 데이터를 입력받거나 출력할 때 사용하는, 데이터가 흐르는 통로(Stream)이다.
// 스트림은 단방향이라서 입력용(InputStream)과 출력용(OutputStream)이 따로 존재한다.

// 바이트 스트림 vs 문자 스트림
// 바이트 스트림 : 1바이트(byte) 단위로 데이터를 처리한다. -> 이미지, 동영상, 오디오 등과 같은 모든 이진 데이터 처리에 적합하다.
// InputStream, OutputStream
// 문자 스트림 : 2바이트(char) 단위로 데이터를 처리한다. -> 텍스트 데이터 처리에 적합하다.
// Writer, Reader
// -> 스트림 사용 후 반드시 close()로 자원을 해제해야한다. -> try-with-resources 구문을 사용하면 자동으로 자원을 해제할 수 있다.

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class E_input_output_stream {

    private String desktopPath;
    private String folderPath;
    // java.nio.file 패키지 (NIO)
    // Path: 파일이나 폴더의 경로를 객체로 표현한다.
    // Files: 파일/폴더의 생성, 존재 여부 확인, 삭제 등 유틸리티 메서드를 제공한다.
    private Path myFolder;
    private String today;
    private Path todayFile;

    public E_input_output_stream() {
        // System.getProperty("user.home")은 현재 사용자의 홈 디렉터리 경로를 문자열로 반환한다
        // JVM이 실행 환경에서 읽어온 "시스템 프로퍼티" 중 하나다
        // Windows면 C:\Users\사용자이름, macOS면 /Users/사용자이름, Linux면 /home/사용자이름 같은 값이다
        // 즉 OS나 계정이 달라도 각자의 홈 경로를 알아서 가져온다
        // 여기에 File.separator(OS별 경로 구분자 / 또는 \)와 "Desktop"을 붙여 바탕화면 경로를 만든다
        this.desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        System.out.println("Desktop Path: " + desktopPath);
        this.folderPath = this.desktopPath + File.separator + "IO Stream sample";

        // Paths.get(...)은 문자열로 된 경로를 Path 객체로 변환한다
        //   - String은 단순한 글자 덩어리라서 파일/폴더 작업에 바로 쓸 수 없다
        //   - Path 객체로 바꾸면 Files 클래스의 메서드(생성, 존재 확인, 삭제 등)에 넘길 수 있다
        //   - 또 getFileName(), getParent() 같은 경로 전용 기능도 쓸 수 있다
        // 참고: 자바 11 이상에서는 Path.of(folderPath)로 써도 동일하다
        this.myFolder = Path.of(this.folderPath);
    }

    // 1. 폴더 생성
    //  •  Files.notExists(Path) : 해당 경로가 존재하지 않으면 true를 반환
    //  •  Files.createDirectory(Path) : 경로에 해당하는 폴더(디렉터리)를 생성
    public void exam1() {

        /*if(Files.notExists(myFolder)) {
            try {
                Files.createDirectory(myFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("폴더가 이미 존재합니다.");
        }*/

        try {
            if (Files.notExists(myFolder)) {
                Files.createDirectories(myFolder);
            } else {
                System.out.println("폴더가 이미 존재합니다.");
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // 2. 파일에 내용 쓰기
    // FileOutputStream으로 파일에 바이트 데이터를 기록한다.
    // 바이트 스트림은 1바이트 단위로 처리하므로, 문자열은 getBytes()로 byte 배열로 변환한 뒤 write()로 쓴다.
    public void exam2() {
        // LocalDate.now() : 오늘 날짜를 가져오고, 지정한 패턴("yyyy-MM-dd")의 문자열로 포맷팅
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // Path.resolve(String) : 현재 Path 객체에 문자열로 된 하위 경로를 추가하여 새로운 Path 객체를 만든다
        todayFile = myFolder.resolve(today + ".txt");

        if (Files.notExists(todayFile)) {
            // 방법.1 : 전통적인 방식 (직접 close)
            // finally 블록에서 스트림을 직접 닫아주어야 하므로 코드가 길고 복잡하다.
            /*
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(todayFile.toFile());
                String content = "Hello World!";
                fos.write(content.getBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            */

            // 방법.2 -> try-with-resources
            // try(...) 괄호 안에서 선언한 자원은 try 블록이 끝나면 자동으로 close()가 호출된다.
            // 따라서 finally로 직접 닫을 필요가 없어 코드가 간결하고 안전하다.
            try (FileOutputStream fos = new FileOutputStream(todayFile.toFile())){
                String content = "Hello World";
                fos.write(content.getBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("오늘 날짜의 파일이 이미 존재합니다.");
        }
    }

    // 2-1. QR 코드 이미지 만들기 (바이트 스트림 활용)
    // QR코드는 이미지(PNG)이므로 문자가 아닌 "바이너리 데이터"다. → 바이트 스트림이 적합하다.
    // exam2()처럼 텍스트가 아니라 이미지라서, 문자 스트림으로는 다룰 수 없는 대표 예시다.
    // ZXing 라이브러리로 문자열을 QR코드 행렬(BitMatrix)로 만든 뒤 PNG 파일로 저장한다.
    public void exam2_1() {
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        todayFile = myFolder.resolve(today + ".png");

        // QR 코드에 담을 내용 (URL, 텍스트 등 무엇이든 가능)
        String content = "https://programmers.co.kr";

        if (Files.notExists(todayFile)) {
            try {
                // 1) QRCodeWriter.encode(...) : 문자열을 QR코드 행렬(BitMatrix)로 변환
                // 인자: 내용, 포맷(QR_CODE), 가로 크기, 세로 크기(픽셀)
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300);
                // 2) MatrixToImageWriter.writeToPath(...) : 행렬을 실제 PNG 이미지 파일로 저장
                // 내부적으로 ImageIO(바이트 스트림)를 이용해 바이너리로 기록한다
                MatrixToImageWriter.writeToPath(matrix, "PNG", todayFile);

                System.out.println(today + "QR코드 이미지를 생성했습니다.");
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            System.out.println(today + ".png 파일이 이미 존재합니다.");
        }
    }

    public static void main(String[] args) {
        E_input_output_stream e = new E_input_output_stream();
        e.exam2_1();
    }
}
