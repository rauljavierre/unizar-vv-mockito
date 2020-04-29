package es.unizar.eina.vv6f.pruebas.mockito.ejercicio2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.*;
import static org.mockito.Mockito.when;

public class ResumenVentasTest {

    final String expectedMessage = "\nResumen de ventas por cliente almacenados en el fichero anterior\n\n" +
                                    "Cliente   Importe" +
                                    "\n-------   -------\n" +
                                    "      7     46,45\n" +
                                    "      9     46,75\n";

    @Test
    public void escribirResumenTest() {
        DataInput stub = Mockito.mock(DataInput.class);
        ByteArrayOutputStream spy = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(spy));
            when(stub.readInt()).thenReturn(1).thenReturn(7).thenReturn(1)
                                .thenReturn(7).thenReturn(7).thenReturn(2)
                                .thenReturn(3).thenReturn(9).thenReturn(5)
                                .thenThrow(new EOFException());

            when(stub.readDouble()) .thenReturn(7.95)
                                    .thenReturn(19.25)
                                    .thenReturn(9.35);
            ResumenVentas.escribirResumen(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedMessage, spy.toString());
    }

    @Test
    public void inyeccionNecesaria100Cobertura() {
        DataInput mock = Mockito.mock(DataInput.class);
        try {
            when(mock.readInt()).thenThrow(new IOException());
            ResumenVentas.escribirResumen(mock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}