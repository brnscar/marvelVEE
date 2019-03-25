package br.com.bruno.marvelvee.webService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebCliente {
    public String get(){
        try {
            URL url = new URL ("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=30a68a297dc1cd253b1f07fe7d7dbbe9&hash=0c7ce59e8e314b1ac5809d4ea14c638c");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
//            connection.setDoOutput(true);
//            PrintStream output = new PrintStream(connection.getOutputStream());
//            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder resposta = new StringBuilder();

            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }

            return resposta.toString();

         } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        HttpURLConnection connection;
        return null;
    }
}
