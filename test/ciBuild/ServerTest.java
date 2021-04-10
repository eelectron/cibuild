package ciBuild;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ServerTest {
    @Test
    public void testCreateBuild() {
        Server server = new Server();
        Config config = new Config(1, "a", 1);
        server.createBuild(config);
        assertEquals("expected 1 client in list", 1, server.getSize());
    }
    
    @Test
    public void testFirstComeServe() {
        Server server = new Server();
        Config config1 = new Config(1, "a", 1);
        Config config2 = new Config(2, "b", 2);
        
        server.createBuild(config1);
        server.createBuild(config2);
        
        // get the first request
        Config c1 = server.getBuild();
        assertEquals("expected first client", 1, c1.id);
        
        // get the next request
        Config c2 = server.getBuild();
        //System.out.println(2 == c2.id);
        assertEquals("expected second client", 2, c2.id);
        
        // testing null request
        Config c3 = server.getBuild();
        //System.out.println(c3 == null);
        assertEquals("expected null", null, c3);
    }
    
    @Test
    public void testFirstComeServeWithPriority() {
        // add three request with 1 2 3
        Server server = new Server();
        Config config1 = new Config(1, "a", 1);
        Config config2 = new Config(2, "a", 1);
        Config config3 = new Config(3, "a", 3);
        
        
        server.createBuild(config2);
        server.createBuild(config3);
        server.createBuild(config1);
        
        Config r1 = server.getBuild();
        System.out.println(r1.priority == 1 && r1.id == 2);
        assertEquals("expected priority 2", 1, r1.priority);
        assertEquals("expected id 2", 2, r1.id);
        
    }
}