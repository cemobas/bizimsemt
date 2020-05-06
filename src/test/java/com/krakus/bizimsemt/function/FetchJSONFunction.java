package com.krakus.bizimsemt.function;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

@Component
public class FetchJSONFunction implements Function<String, JSONArray> {

    @Override
    public JSONArray apply(String file) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        String path = getClass().getResource("db/" + file + ".json").getPath();
        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
