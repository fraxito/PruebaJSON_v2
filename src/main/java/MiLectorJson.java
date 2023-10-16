import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MiLectorJson {
    public static void main(String[] args) {
        String rutaAlFichero = "src/main/java/datosUsuario.json";
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaAlFichero)));
            JSONObject objetoJSON = new JSONObject(contenido);

            //Acceder al elemento "universidad" y mostrarlo

            JSONObject educacion = objetoJSON.getJSONObject("educación");
            String universidad = educacion.getString("universidad");
            System.out.println("La universidad es: " + universidad);
        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO!");
        }
    }
}
