package observer;

/**
 * A class can implement the Observer interface when it wants to be informed of changes in observable objects.
 *
 *
 *
 *@author     Nikita Korotov
 *@version    1.0 27.11.2022
 */

public interface Observer {

    /**
     *  This method is called whenever the observed object is changed.
     *  An application calls an Observable object's notifyObservers method
     *  to have all the object's observers notified of the change.
     *
     *@param observable - the observable object.
     *@param arg - an argument passed to the notifyObservers method.
     *@author     Nikita Korotov
     */
    public void update(Observable observable, Object arg);

    /**
     *  This method does something idk.
     *  pls help.
     *
     *@param sub - the observable object.
     *@author     Nikita Korotov
     */
    public void setSubject(Observable sub);

}