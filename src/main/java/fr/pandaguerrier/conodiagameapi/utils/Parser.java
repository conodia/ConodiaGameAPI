package fr.pandaguerrier.conodiagameapi.utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    public static JSONObject parse(String json) {
        JSONParser parser = new JSONParser();
        JSONObject payload;
        try {
            payload = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return payload;
    }
}
