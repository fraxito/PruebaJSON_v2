import org.json.JSONArray;
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

            System.out.println("La carrera es: " + objetoJSON.getJSONObject("educación").getString("carrera"));

            //monto un bucle para mostrar las habilidades
            JSONArray habilidades = objetoJSON.getJSONArray("habilidades");

            System.out.println("Habilidades de " + objetoJSON.getString("nombre") + ":");
            for (int i=0; i < habilidades.length(); i++){
                System.out.println("---" + habilidades.getString(i));
            }

            //modifica un dato
            objetoJSON.put("edad", 30);
            //y lo escribe en el fichero JSON. El 4 en el toString es para que el fichero quede indentado
            Files.write(Paths.get(rutaAlFichero), objetoJSON.toString(4).getBytes());
        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO!");
        }
    }
}
