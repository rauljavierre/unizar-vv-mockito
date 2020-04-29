package es.unizar.eina.vv6f.pruebas.mockito.ejercicio1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyPublisherTest {

    @Test
    public void test1Mock() {
        Subscriber mock = Mockito.mock(Subscriber.class);
        MyPublisher publisher = new MyPublisher();
        publisher.add(mock);
        publisher.publish("message");

        // el método «receive» se invocó exactamente 1 vez con el mensaje «message»
        verify(mock, times(1)).receive("message");
    }

    @Test
    public void test2MocksMismoMensaje() {
        Subscriber mock1 = Mockito.mock(Subscriber.class);
        Subscriber mock2 = Mockito.mock(Subscriber.class);
        MyPublisher publisher = new MyPublisher();
        publisher.add(mock1);
        publisher.add(mock2);
        publisher.publish("message");

        // el método «receive» se invocó exactamente 1 vez con el mensaje «message» en ambos suscriptores
        verify(mock1, times(1)).receive("message");
        verify(mock2, times(1)).receive("message");
    }

    @Test
    public void test2MocksDistintoMensaje() {
        Subscriber mock1 = Mockito.mock(Subscriber.class);
        doReturn(1).when(mock1).getId();
        Subscriber mock2 = Mockito.mock(Subscriber.class);
        doReturn(2).when(mock2).getId();
        MyPublisher publisher = new MyPublisher();
        publisher.add(mock1);
        publisher.add(mock2);
        publisher.publish("Hello subscriber 1", 1);
        publisher.publish("Hello subscriber 2", 2);

        // el método «receive» se invocó exactamente 1 vez con el mensaje específico para cada suscriptor.
        verify(mock1, times(1)).receive("Hello subscriber 1");
        verify(mock2, times(1)).receive("Hello subscriber 2");
        verify(mock1, times(0)).receive("Hello subscriber 2");
        verify(mock2, times(0)).receive("Hello subscriber 1");
    }

    @Test
    public void test2MocksSegundoLlegaTarde() {
        MyPublisher publisher = new MyPublisher();
        Subscriber mock1 = Mockito.mock(Subscriber.class);
        publisher.add(mock1);
        publisher.publish("Hello everyone!");
        Subscriber mock2 = Mockito.mock(Subscriber.class);
        publisher.add(mock2);
        publisher.publish("Hello everyone!");

        // el método «receive» se invocó exactamente 2 veces para el mock1 y 1 vez para el mock2
        verify(mock1, times(2)).receive("Hello everyone!");
        verify(mock2, times(1)).receive("Hello everyone!");
    }
}