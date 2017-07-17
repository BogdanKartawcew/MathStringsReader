package new_probe;

/**
 * Created by A261711 on 2017-07-14.
 */
public class NewStart {
    public static void main(String[] args) {
        //String line = "sin (2^1) * ( - 5 +1,5* (4^(2*(sin(30)^1)^1)^1 ) +  28)"; //0.5
        //String line = "0.03*(-5+1.5*(4^(2*0.5^1)^1)+28)";
        //String line = "0.03*(-5+1.5*(5*4^1.0^1)+28)";
        String line = "2+2"; //з задачі 0.5
        //String line = "0.03*(-5+1.5*(4^(2*0.5^1)^1)+28)+sin(2*(-5+1.5*4+sin(26+4^1)*0^1^2)+28) - 1";
        NewProcess process = new NewProcess(line);
        CountCalc calc = new CountCalc(line);
        System.out.println(process.getMainResult()+" "+calc.getCount());
    }
}
