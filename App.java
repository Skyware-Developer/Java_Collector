package app;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        // URL Where we get the data
        String route = "https://siata.gov.co/DatosRedAire/txtRedAire/Datos_Red_de_Calidad_del_Aire_81_PM2.5.txt";
        // This is the number of the station from which we get the data
        String orden[] = new String[] { "81", "3", "82", "87", "86", "85", "25", "80", "94", "83", "79", "84", "44",
                "28", "88", "38", "78", "90", "79" };


        Collector collector = new Collector();

        collector.promedio(route,orden);
    }
}
