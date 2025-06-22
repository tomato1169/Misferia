package azerot.azerot.DB;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class json {
    private File file;
    private JSONObject json;
    private JSONParser parser = new JSONParser();

    public json(File file) {
        this.file = file;
        reload();
        try {
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        pw.print("{");
        pw.print("}");
        pw.flush();
        pw.close();
    }
}