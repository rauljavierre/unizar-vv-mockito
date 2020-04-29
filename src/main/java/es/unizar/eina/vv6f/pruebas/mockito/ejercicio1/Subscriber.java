package es.unizar.eina.vv6f.pruebas.mockito.ejercicio1;

public interface Subscriber {

    void receive(String message);

    void setId(int id);

    int getId();

}
