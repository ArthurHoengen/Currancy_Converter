package main.java.br.org.mentoria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CurrancyConverter {
    public static void main(String[] args) throws Exception {
        // ** Consumindo API externa
        URL url = new URL("https://economia.awesomeapi.com.br/last/BRL-USD");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String conversao = "";
        StringBuilder jsonConversao = new StringBuilder();

        while((conversao = br.readLine()) != null){
            jsonConversao.append(conversao);
        }

        System.out.println(jsonConversao.toString());
        // ** Consumindo API externa
    }
}

