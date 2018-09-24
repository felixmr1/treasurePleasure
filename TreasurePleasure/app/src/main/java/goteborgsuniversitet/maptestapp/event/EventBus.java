package goteborgsuniversitet.maptestapp.event;

class EventBus {

    private static final EventBus ourInstance = new EventBus();

    static EventBus getInstance() {
        return ourInstance;
    }

    private EventBus() {
    }






}
