package ciBuild;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Server {
    // queue for client
    PriorityQueue<Config> clientList = new PriorityQueue<>(new SortByPriority());
    
    public void createBuild(Config config) {
        clientList.add(config);    
    }
    
    public Config getBuild() {
        if(clientList.isEmpty()) {
            return null;
        }
        
        // get first request
        Config config = clientList.poll();
        return config;
    }
    
    public int getSize() {
        return clientList.size();
    }
}

class SortByPriority implements Comparator<Config>{
    public int compare(Config c1, Config c2) {
        return c1.priority - c2.priority;
    }
}
