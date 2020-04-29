package es.unizar.eina.vv6f.pruebas.mockito.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class MyPublisher implements Publisher {
	
	private List<Subscriber> subscribers = new ArrayList<Subscriber>();

	@Override
	public void add(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void publish(String message) {
		for (Subscriber subscriber : subscribers) {
			subscriber.receive(message);
		}
	}

	@Override
	public void publish(String message, int id){
		for (Subscriber subscriber : subscribers){
			if (subscriber.getId() == id){
				subscriber.receive(message);
				break;
			}
		}
	}

}
