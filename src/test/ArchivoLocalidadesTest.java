package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import modelo.Localidad;
import modelo.ArchivoLocalidades;

public class ArchivoLocalidadesTest {

    @Test
    public void guardarYLuegoCargarDeberiaMantenerLosDatos() throws IOException {
        
        ArchivoLocalidades archivo = new ArchivoLocalidades("test_localidades.json");
        List<Localidad> listaOriginal = new ArrayList<>();
        listaOriginal.add(new Localidad("Buenos Aires", "Buenos Aires", -34.6, -58.3, 0));
        listaOriginal.add(new Localidad("Rosario", "Santa Fe", -32.9, -60.6, 1));

        
        archivo.guardar(listaOriginal);
        List<Localidad> listaCargada = archivo.cargar();

        
        assertEquals(listaOriginal.size(), listaCargada.size());
        assertEquals(listaOriginal.get(0).getNombre(), listaCargada.get(0).getNombre());
    }
}