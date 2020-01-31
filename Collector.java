package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Collector {

    // URL Where we get the data
    // String route =
    // "https://siata.gov.co/DatosRedAire/txtRedAire/Datos_Red_de_Calidad_del_Aire_81_PM2.5.txt";
    // This is the number of the station from which we get the data
    // String orden[] = new String[] { "81", "3", "82", "87", "86", "85", "25",
    // "80", "94", "83", "79", "84", "44",
    // "28", "88", "38", "78", "90", "79" };

    public double promedio(String route, String orden[]) throws IOException {
        double promedio = 0;

        for (int k = 0; k <= orden.length; k++) {
            URL url = new URL(route);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            ArrayList<Float> arrayList = new ArrayList<Float>();
            while ((inputLine = in.readLine()) != null) {
                if (!inputLine.contains("valor")) {
                    content.append(inputLine + " ");
                }
            }
            in.close();
            con.disconnect();
            String[] output = content.toString().split(" ");
            float acum = 0;
            for (int i = 1; i < output.length; i = i + 2) {
                Float number = Float.parseFloat(output[i].split("	")[1]);
                arrayList.add(number);
                acum += number;
            }
            System.out.println(acum / arrayList.size());
            promedio = acum / arrayList.size();

            if (k != orden.length - 1) {
                route = route.replace(orden[k], orden[k + 1]);
            }

        }

        return promedio;
    }

}
