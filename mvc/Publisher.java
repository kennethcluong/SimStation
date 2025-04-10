package mvc;

import java.util.Collection;
import java.util.HashSet;

public class Publisher {
    private Collection<Subscriber> subscribers = new HashSet<Subscriber>();

    public void subscribe(Subscriber subscriber) { subscribers.add(subscriber); }

    public void unsubscribe(Subscriber subscriber) { subscribers.remove(subscriber); }

    public void notifySubscribers() {
        for(Subscriber subscriber: subscribers) {
            subscriber.update();
        }
    }
}
