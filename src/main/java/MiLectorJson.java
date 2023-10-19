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

            //añade un trabajo nuevo

            // Crear un nuevo trabajo
            JSONObject nuevoTrabajo = new JSONObject();
            nuevoTrabajo.put("puesto", "Líder de Proyecto");
            nuevoTrabajo.put("añoInicio", 2023);
            nuevoTrabajo.put("añoFin", "Actualidad");
            nuevoTrabajo.put("empresa", "TopTech");

            // Añadir el nuevo trabajo al arreglo de trabajos
            JSONArray trabajos = objetoJSON.getJSONArray("trabajos");
            trabajos.put(nuevoTrabajo);
            //y lo escribe en el fichero JSON. El 4 en el toString es para que el fichero quede indentado
            Files.write(Paths.get(rutaAlFichero), objetoJSON.toString(4).getBytes());

            String habilidadBuscada = "java"; // Esto puedes cambiarlo por un scan que le pida el dato al usuario
            if (tieneHabilidad(objetoJSON, habilidadBuscada)) {
                System.out.println("Yennefer tiene la habilidad: " + habilidadBuscada);
            } else {
                System.out.println("Yennefer no tiene la habilidad: " + habilidadBuscada);
            }


        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO!");
        }
    }

    /**
     * Verifica si el JSONObject proporcionado contiene la habilidad dada.
     * @param jsonObject el objeto JSON a examinar.
     * @param habilidad la habilidad a buscar.
     * @return true si el JSON contiene la habilidad, false en caso contrario.
     */
    public static boolean tieneHabilidad(JSONObject jsonObject, String habilidad) { //static?
        JSONArray habilidades = jsonObject.getJSONArray("habilidades");

        for (int i = 0; i < habilidades.length(); i++) {
            if (habilidades.getString(i).equalsIgnoreCase(habilidad)) {
                return true;
            }
        }

        return false;
    }

}
