/**
 * Created by A261711 on 2017-07-14.
 */
public class Start {
    public static void main(String[] args) {
        //String line = "sin (2^1) * ( - 5 +1,5* (4^(2*(sin(30)^1)^1)^1 ) +  28)"; //0.5
        //String line = "0.03*(-5+1.5*(4^(2*0.5^1)^1)+28)";
        //String line = "0.03*(-5+1.5*(5*4^1.0^1)+28)";
        String line = "sin(2*(-5+1.5*4)+28) "; //з задачі 0.5
        //String line = "0.03*(-5+1.5*(4^(2*0.5^1)^1)+28)+sin(2*(-5+1.5*4+sin(26+4^1)*0^1^2)+28) - 1";
        //String line = "2+2";
        Process process = new Process();
        CountCalc calc = new CountCalc();
        System.out.println(process.getResult(line) +" " + calc.getResult(line));
    }
}
