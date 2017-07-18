/**
 * Created by A261711 on 2017-07-14.
 */
class Pow implements Results{


    private String create(String originalLine) {
        //System.out.println("original line:" + originalLine);
        String before = originalLine.replace("(", "").replace(")", "");
        //System.out.println("before: " + before);
        String start = before.substring(0, before.indexOf("^"));
        //System.out.println(start);
        String end = before.substring(before.indexOf("^")+1);
        //System.out.println(end);
        return String.valueOf(Math.pow(Double.parseDouble(start), Double.parseDouble(end)));
    }

    @Override
    public String getResult(String line) {
        return create(line);
    }
}
