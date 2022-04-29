import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteProcess {
    public static void main(String[] args) {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("git help");
            BufferedReader result = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
