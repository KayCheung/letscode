    package com.studytrails.json.jackson;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
 
import org.apache.commons.io.IOUtils;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class TreeModelParser2 {
    public static void main(String[] args) throws MalformedURLException, IOException {
        // Get a list of albums from free music archive. limit the results to 5
        String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=10";
        // Get the contents of json as a string using commons IO IOUTils class.
        String genreJson = IOUtils.toString(new URL(url));
 
        // create an ObjectMapper instance.
        ObjectMapper mapper = new ObjectMapper();
        // use the ObjectMapper to read the json string and create a tree
        JsonNode node = mapper.readTree(genreJson);
 
        // not the use of path. this does not cause the code to break if the
        // code does not exist
        Iterator<JsonNode> albums = node.path("dataset").iterator();
        while (albums.hasNext()) {
            System.out.println(albums.next().path("album_title"));
        }
 
    }
}