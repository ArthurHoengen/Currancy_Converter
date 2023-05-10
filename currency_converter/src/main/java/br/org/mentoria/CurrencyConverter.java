package br.org.mentoria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;

import br.org.mentoria.entitys.Converter;

public class CurrencyConverter {

    private String fromIso;
    private String toIso;
    private String value;

    public void getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Caso desconheça os códigos consulte: 'https://pt.wikipedia.org/wiki/ISO_4217'");
        System.out.println("Código iso da moeda a converter:");
        this.fromIso = scanner.next().toUpperCase();
        System.out.println("Quanto você precisa converter dessa moeda?");
        this.value = Float.parseFloat(scanner.next());
        System.out.println("Código iso da moeda convertida:");
        this.toIso = scanner.next().toUpperCase();
        scanner.close();
    }
    
    public void init(){

        

        // ** Consumindo API externa
        URL url = new URL("https://economia.awesomeapi.com.br/last/" + code + "-" + codein + "");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String conversao = "";
        StringBuilder jsonConversao = new StringBuilder();

        while ((conversao = br.readLine()) != null) {
            jsonConversao.append(conversao);
        }

        System.out.println(jsonConversao.toString());

        JSONObject json = new JSONObject(jsonConversao.toString());
        Converter converter = new Gson().fromJson(json.get(code + codein).toString(), Converter.class);

        System.out.println(json.get(code + codein));

        //Map<String, Object> json = new Gson().fromJson(jsonConversao.toString(), Map.class);
        //Converter converter = new Gson().fromJson(new Gson().toJson(json.get(code + codein)), Converter.class);
        // ** Consumindo API externa

        float vlrConvertido = value * Float.parseFloat(converter.getHigh());

        FormatFloat format = new FormatFloat();

        System.out.println("Conversão " + converter.getName() + ": seus "+ format.formatarFloat(value) + converter.getCode() + " foram convertidos em " + format.formatarFloat(vlrConvertido) +converter.getCodein());
    }
}
