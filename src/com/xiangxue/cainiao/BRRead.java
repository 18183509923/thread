import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BRRead {
    public static void main(String[] args) throws Exception{
        char c;
        //使用Systeam.in 创建BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符，按下Q建结束");
        //读取字符
        do{
            c = (char) br.read();
            System.out.println(c);
        }while (c != 'q');
    }
}
