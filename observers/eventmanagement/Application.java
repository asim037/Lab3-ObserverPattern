/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observers.eventmanagement;

/**
 *
 * @author FA20-BSE-037
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();
    public void subscribe(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }
    public void unsubscribe(String eventType, EventListener listener) {
        listeners.getOrDefault(eventType, new ArrayList<>()).remove(listener);
    }
    public void notify(String eventType, String data) {
        List<EventListener> eventListeners = listeners.getOrDefault(eventType, new ArrayList<>());
        for (EventListener listener : eventListeners) {
            listener.update(data);
        }
    }
}
class Editor {
    public EventManager events;
    private File file;
    public Editor() {
        events = new EventManager();
    }
    public void openFile(String path) {
        file = new File(path);
        events.notify("open", file.getName());
    }
    public void saveFile() {
        file.write();
        events.notify("save", file.getName());
    }
}
interface EventListener {
    void update(String filename);
}
class LoggingListener implements EventListener {
    private File log;
    private String message;
    public LoggingListener(String logFilename, String message) {
        log = new File(logFilename);
        this.message = message;
    }
    @Override
    public void update(String filename) {
        System.out.println( message.replace("%s", filename));
    }
}
class EmailAlertsListener implements EventListener {
    private String email;
    private String message;

    public EmailAlertsListener(String email, String message) {
        this.email = email;
        this.message = message;
    }
    @Override
    public void update(String filename) {
        System.out.println("Email sent to " + email + ": " + message.replace("%s", filename));
    }
}
class SMSSupportListener implements EventListener {
    private String phoneNumber;
    private int maxSMSSize;

    public SMSSupportListener(String phoneNumber, int maxSMSSize) {
        this.phoneNumber = phoneNumber;
        this.maxSMSSize = maxSMSSize;
    }

    @Override
    public void update(String filename) {
        if (maxSMSSize > 160) {
            System.out.println("Warning: Invalid default SMS length. Please set a valid SMS length.");
        } else {
            String message = String.format("File %s has been changed.", filename);
            sendSMS(phoneNumber, message);
        }
    }
    private void sendSMS(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}

class Application {
    public void config() {
        Editor editor = new Editor();

        LoggingListener logger = new LoggingListener(
            "/path/to/log.txt",
            "Someone has opened the file: %s");
        editor.events.subscribe("open", logger);

        EmailAlertsListener emailAlerts = new EmailAlertsListener(
            "asim2345@gmail.com",
            "Someone has changed the file: %s");
        editor.events.subscribe("save", emailAlerts);
        
        SMSSupportListener smsSupport = new SMSSupportListener(
            "+1234567890", 160);
        editor.events.subscribe("save", smsSupport);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.config();
        Editor editor = new Editor();
        editor.openFile("example.txt");
        editor.saveFile();
    }
}

class File {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void write() {
    }
}
