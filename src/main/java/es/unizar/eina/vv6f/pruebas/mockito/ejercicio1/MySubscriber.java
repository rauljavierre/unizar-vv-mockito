package es.unizar.eina.vv6f.pruebas.mockito.ejercicio1;

public class MySubscriber implements Subscriber {

    private int id;

    @Override
    public void receive(String message){ }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

}
