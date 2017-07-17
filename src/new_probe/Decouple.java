package new_probe;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by A261711 on 2017-07-14.
 */
class Decouple {

    private String line;
    private String result;

    Decouple(String line) {
        this.line = line;
        setResult();
    }

    private String getLine() {
        return line;
    }

    String getResult() {
        return result;
    }

    private void setResult() {
        this.result = String.valueOf(decouple(getLine()));
    }

    private String decouple(String line) {
        double result = 0;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object engineResult = engine.eval(line);
            result = (double) engineResult;
        } catch (Exception ignored) {
        }
        return String.valueOf(result);
    }

}
