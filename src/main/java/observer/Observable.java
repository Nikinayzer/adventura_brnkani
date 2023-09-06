package observer;
/**
 *  This cinterface represents an observable object.
 * An observable object can have one or more observers. An observer may be any object that implements interface Observer. After an observable instance changes, an application calling the Observable's notifyObservers method causes all of its observers to be notified of the change by a call to their update method.
 * The order in which notifications will be delivered is unspecified. The default implementation provided in the Observable class will notify Observers in the order in which they registered interest, but subclasses may change this order, use no guaranteed order, deliver notifications on separate threads, or may guarantee that their subclass follows this order, as they choose.
 * Note that this notification mechanism is has nothing to do with threads and is completely separate from the wait and notify mechanism of class Object.
 * When an observable object is newly created, its set of observers is empty. Two observers are considered the same if and only if the equals method returns true for them.
 *
 *
 *
 *@author     Nikita Korotov
 *@version    1.0 27.11.2022
 */

public interface Observable {

    /**
     *  Adds an observer to the set of observers for this object,
     *  provided that it is not the same as some observer already in the set.
     *  The order in which notifications will be delivered to multiple observers is not specified.
     *
     *@param obj - an observer to be added.
     *@author     Nikita Korotov
     */
    public void register(Observer obj);
    /**
     *  Deletes an observer from the set of observers of this object. Passing null to this method will have no effect.
     *
     *@param obj - an observer to be deleted.
     *@author     Nikita Korotov
     */

    public void unregister(Observer obj);

    /**
     *  If this object has changed, notify all of its observers.
     *
     *@author     Nikita Korotov
     */
    public void notifyObservers();

    /**
     * Getting an update of object
     *
     *@param obj - object.
     *@author     Nikita Korotov
     */
    public Object getUpdate(Observer obj);


}
