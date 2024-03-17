package notifications;

import input.ActionInput;
import pootv.User;

import java.util.ArrayList;
import java.util.HashMap;

public final class Observable {
    private static final Observable OBSERVABLE = new Observable();
    private Observable() { }

    public static Observable getObservable() {
        return OBSERVABLE;
    }

    private final HashMap<String, ArrayList<Observer>> observers = new HashMap<>();

    /** Method that adds a new user to the hashtable */
    public void attach(final Observer observer, final String genre) {
        int ok = 0;
        if (!observers.isEmpty()) {
            for (String key : observers.keySet()) {
                if (key.equals(genre)) {
                    ArrayList<Observer> list = observers.get(key);
                    list.add(observer);
                    observers.replace(key, list);
                    ok = 1;
                    break;
                }
            }
        }
        if (observers.isEmpty() || ok == 0) {
            ArrayList<Observer> list = new ArrayList<>();
            list.add(observer);
            observers.put(genre, list);
        }
    }

    /** Method that finds all the users subscribed to at least one of the recently
     * added movie's genres and sends them a notification */
    public void notifyAllObservers(final ActionInput action) {
        int ok = 0;
        if (!observers.isEmpty()) {
            for (int i = 0; i < action.getAddedMovie().getGenres().size(); i++) {
                if (observers.containsKey(action.getAddedMovie().getGenres().get(i))) {
                    ArrayList<Observer> list = observers.get(action.getAddedMovie().
                            getGenres().get(i));
                    for (Observer observer : list) {
                        for (int j = 0; j < ((User) observer).getNotifications().size(); j++) {
                            if (((User) observer).getNotifications().get(j).getMovieName().
                                    equals(action.getAddedMovie().getName())) {
                                ok = 1;
                                break;
                            }
                        }
                        if (ok == 0) {
                            observer.update(observer, action.getAddedMovie().getName());
                        }
                    }
                }
            }
        }
    }
}
