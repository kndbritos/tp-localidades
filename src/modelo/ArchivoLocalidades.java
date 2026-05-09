package modelo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ArchivoLocalidades {
    private String ruta;

    public ArchivoLocalidades(String ruta) {
        this.ruta = ruta;
    }

    
    public void guardar(List<Localidad> localidades) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(localidades, writer);
        }
    }

   
    public List<Localidad> cargar() throws IOException {
        Gson gson = new Gson();
        
        try (FileReader reader = new FileReader(ruta)) {
            Type tipoLista = new TypeToken<ArrayList<Localidad>>(){}.getType();
            List<Localidad> lista = gson.fromJson(reader, tipoLista);
            
            return (lista != null) ? lista : new ArrayList<>();
        }
    }
}