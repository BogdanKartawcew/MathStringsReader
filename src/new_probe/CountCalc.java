package new_probe;

import java.util.Objects;

/**
 * Created by MyWORID on 17.07.2017.
 */
public class CountCalc {
    private String line;

    public CountCalc(String line) {
        this.line = line;
    }

    public int getCount() {
        int count = 0;
        String[] valueArray = line.split("[\\W\\d]");
        for (String s : valueArray) if (!Objects.equals(s, "")) count++;

        String[] temporaryValue = line.split("");
        for (String s : temporaryValue)
            if (s.equals("-") || s.equals("*") || s.equals("/") || s.equals("+")) count++;
        return count;
    }
}
