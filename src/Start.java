/**
 * Created by A261711 on 2017-07-14.
 */
public class Start {
    public static void main(String[] args) {
        String line = "sin(2*(-5+1.5*4)+28) "; //з задачі 0.5
        Process process = new Process();
        System.out.println(process.getResult(line));
    }
}
