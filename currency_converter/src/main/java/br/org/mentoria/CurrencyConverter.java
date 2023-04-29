package br.org.mentoria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.util.Scanner;
import java.util.Map;


import com.google.gson.Gson;

public class CurrencyConverter {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Caso desconheça os códigos consulte: 'https://pt.wikipedia.org/wiki/ISO_4217'");
        System.out.println("Código iso da moeda a converter:");
        String code = scanner.next().toUpperCase();
        System.out.println("Quanto você precisa converter dessa moeda?");
        float value = Float.parseFloat(scanner.next());
        System.out.println("Código iso da moeda convertida:");
        String codein = scanner.next().toUpperCase();
        scanner.close();

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

        Map<String, Object> json = new Gson().fromJson(jsonConversao.toString(), Map.class);
        Converter converter = new Gson().fromJson(new Gson().toJson(json.get(code + codein)), Converter.class);
        // ** Consumindo API externa

        float vlrConvertido = value * Float.parseFloat(converter.getHigh());

        FormatFloat format = new FormatFloat();

        System.out.println("Conversão " + converter.getName() + ": seus "+ format.formatarFloat(value) + converter.getCode() + " foram convertidos em " + format.formatarFloat(vlrConvertido) +converter.getCodein());
    }
}
