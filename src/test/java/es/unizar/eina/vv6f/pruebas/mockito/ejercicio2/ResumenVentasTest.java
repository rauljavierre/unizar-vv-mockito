package es.unizar.eina.vv6f.pruebas.mockito.ejercicio2;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.*;
import static org.mockito.Mockito.when;

public class ResumenVentasTest {

    final String expectedMessage0 = "\nResumen de ventas por cliente almacenados en el fichero anterior\n\n" +
                                    "Cliente   Importe" +
                                    "\n-------   -------\n";

    final String expectedMessage1 = "\nResumen de ventas por cliente almacenados en el fichero anterior\n\n" +
                                    "Cliente   Importe" +
                                    "\n-------   -------\n" +
                                    "      1     47,70\n";

    final String expectedMessage2 = "\nResumen de ventas por cliente almacenados en el fichero anterior\n\n" +
                                    "Cliente   Importe" +
                                    "\n-------   -------\n" +
                                    "      1     47,70\n" +
                                    "      2     48,00\n";

    final String expectedMessage3 = "\nResumen de ventas por cliente almacenados en el fichero anterior\n\n" +
                                    "Cliente   Importe" +
                                    "\n-------   -------\n" +
                                    "      1     30,00\n" +
                                    "      2     70,00\n";

    @Test
    public void testNingunaVenta() {
        DataInput stub = Mockito.mock(DataInput.class);
        ByteArrayOutputStream spy = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(spy));

            when(stub.readInt())
                                .thenThrow(new EOFException());

            ResumenVentas.escribirResumen(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedMessage0, spy.toString());
    }

    @Test
    public void testUnaVenta() {
        DataInput stub = Mockito.mock(DataInput.class);
        ByteArrayOutputStream spy = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(spy));

            when(stub.readInt())
                                .thenReturn(1).thenReturn(1).thenReturn(6)
                                .thenThrow(new EOFException());

            when(stub.readDouble())
                                .thenReturn(7.95);

            ResumenVentas.escribirResumen(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedMessage1, spy.toString());
    }

    @Test
    public void testDosVentasDosClientesDistintos() {
        DataInput stub = Mockito.mock(DataInput.class);
        ByteArrayOutputStream spy = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(spy));

            when(stub.readInt())
                    .thenReturn(1).thenReturn(1).thenReturn(6)
                    .thenReturn(1).thenReturn(2).thenReturn(6)
                    .thenThrow(new EOFException());

            when(stub.readDouble())
                    .thenReturn(7.95)
                    .thenReturn(8.00);

            ResumenVentas.escribirResumen(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedMessage2, spy.toString());
    }

    @Test
    public void testCuatroVentasDosClientesDistintos() {
        DataInput stub = Mockito.mock(DataInput.class);
        ByteArrayOutputStream spy = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(spy));

            when(stub.readInt())
                    .thenReturn(1).thenReturn(1).thenReturn(1)
                    .thenReturn(1).thenReturn(1).thenReturn(2)
                    .thenReturn(1).thenReturn(2).thenReturn(3)
                    .thenReturn(1).thenReturn(2).thenReturn(4)
                    .thenThrow(new EOFException());

            when(stub.readDouble())
                    .thenReturn(10.00)
                    .thenReturn(10.00)
                    .thenReturn(10.00)
                    .thenReturn(10.00);

            ResumenVentas.escribirResumen(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedMessage3, spy.toString());
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