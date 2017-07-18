import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by A261711 on 2017-07-14.
 */

public class Process implements Results{


    private String refact(String line) {
        String refactoredLine = line.replaceAll(",", ".").replaceAll(" ", "").toUpperCase();
        //System.out.println("Refactored line: " + refactoredLine);
        return process(refactoredLine);
    }

    private String process(String string) {

        String originalLine = string;
        while (originalLine.contains("(")) { //шукаємо дужки
            String line = originalLine.substring(originalLine.lastIndexOf("(") + 1);
            String opened = line.substring(0, line.indexOf(")"));
            originalLine = algorithm(new String[]{opened, originalLine});
        }

        while (!isDouble(originalLine)) {
            String opened = originalLine;
            originalLine = algorithm(new String[]{opened, originalLine});
        }
        return replacing(originalLine);
    }

    private String algorithm(String[] array) {
        String originalLine = array[1];
        String opened = array[0];
        //System.out.println("Opened = " + opened);
        if (opened.contains("^")) { //шукаємо степені. навіть якщо їх там кілька - ми шукаємо в методі їх по одному
            String[] voidResult = findPows(opened);
            originalLine = originalLine.replace(voidResult[0], voidResult[1]);
            //System.out.println("original after pow = " + originalLine);
        } else if (opened.contains("SIN") || opened.contains("COS") || opened.contains("TG") || opened.contains("CTG")) {
            //System.out.println("CONTAINS");
            String[] replaceFromTo = findTrigonometricFunction(opened); //0 - from, 1 - to
            //System.out.println("Function result: " + replaceFromTo[1]);
            originalLine = originalLine.replace(replaceFromTo[0], replaceFromTo[1]);
            //System.out.println("Original after function: " + originalLine);
        } else if (opened.contains("*") || opened.contains("-") || opened.contains("+") || opened.contains("/")) {
            Decouple decouple = new Decouple();
            originalLine = originalLine.replace(opened, decouple.getResult(opened));
            //System.out.println("original after decouple = " + originalLine);
        } else if (isDouble(opened)) {
            originalLine = originalLine.replace("(" + opened + ")", opened);
            //System.out.println("original = " + originalLine);
        }
        return originalLine;
    }

    private String replacing(String line) {
        return String.valueOf(new DecimalFormat("#.##").format(Double.parseDouble(line))).replace(",", ".");
    }

    private String[] findTrigonometricFunction(String originalLine) {
        String value = null;
        String[] valueArray = originalLine.split("[A-Z]"); //залишає все крім букв
        List<String> valueList = new LinkedList<>(Arrays.asList(valueArray));
        Collections.reverse(valueList);
        for (String aValueList : valueList) {
            if (!aValueList.equals("")) value = aValueList;
            break;
        }
        String[] temporaryValue = value.split("[*/+-]");
        value = temporaryValue[0];
        //System.out.println("Value: " + value);

        String math = null;
        String[] mathArray = originalLine.split("[\\W\\d]"); //залишає тільки букви
        List<String> mathList = new LinkedList<>(Arrays.asList(mathArray));
        Collections.reverse(mathList);
        for (String aMathList : mathList) {
            if (!aMathList.equals("")) math = aMathList;
            break;
        }
        //System.out.println("Math: " + math);
        return new String[]{math + value, resultTrigonometricFunction(math, value)};
    }

    private String resultTrigonometricFunction(String math, String value) {
        //System.out.println("Trigonometric Method");
        double result = 0;
        //System.out.println(value);
        double integer = Math.toRadians(Double.parseDouble(value));
        //System.out.println(integer);
        if (math.equals("SIN")) result = Math.sin(integer);
        if (math.equals("COS")) result = Math.cos(integer);
        if (math.equals("TG")) result = Math.tan(integer);
        if (math.equals("CTG")) result = Math.cos(integer) / Math.sin(integer);
        return replacing(String.valueOf(result));
    }

    private String[] findPows(String string) {
        String from = string;
        String to;
        String[] openedArray = from.split("[*/+-]");
        for (String anOpenedArray : openedArray) if (anOpenedArray.contains("^")) from = anOpenedArray;
        //System.out.println("opened in pow " + from);
        int count = countOfPows(from);
        if (count == 1) {
            Pow pow = new Pow();
            to = pow.getResult(from);
        } else {
            String[] array = from.split("[\\^]");
            from = array[array.length - 2] + "^" + array[array.length - 1];
            Pow pow = new Pow();
            to = pow.getResult(from);
        }
        return new String[]{from, to};
    }

    private int countOfPows(String line) {
        int count = 0;
        char[] array = line.toCharArray();
        for (char anArray : array)
            if (anArray == '^') count++;
        return count;
    }

    public boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getResult(String line) {
        return refact(line);
    }
}
