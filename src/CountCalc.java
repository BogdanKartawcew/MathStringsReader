import java.util.Objects;

/**
 * Created by MyWORID on 17.07.2017.
 */
public class CountCalc implements Results{


    public int getCount(String line) {
        int count = 0;
        String[] valueArray = line.split("[\\W\\d]");
        for (String s : valueArray) if (!Objects.equals(s, "")) count++;

        String[] temporaryValue = line.split("");
        for (String s : temporaryValue)
            if (s.equals("-") || s.equals("*") || s.equals("/") || s.equals("+")) count++;
        return count;
    }

    @Override
    public String getResult(String line) {
        return String.valueOf(getCount(line));
    }
}
