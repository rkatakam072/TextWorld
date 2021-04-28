package Main;

import Entities.Chicken;
import Entities.Entity;
import Entities.Popstar;
import Entities.Wumpus;
import Items.Item;
import Items.ItemContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class World {

    private final HashMap<String, Room> rooms = new HashMap<>();

    private final Player player;

    private static World instance;

    private final ArrayList<Entity> entities;

    private final Wumpus wumpus;
    private final Popstar popstar;
    private final Chicken chicken;

    Thread movingEntitiesThread = new Thread() {
        @Override
        public void run() {
            for (Entity entity : entities) {
                entity.move();
            }

            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    private World() {

        initRooms();
        entities = new ArrayList<>();

        this.player = new Player(rooms.get("hall"));
        this.wumpus = new Wumpus(getRoom("closet"));
        this.popstar = new Popstar(getRoom("dungeon"));
        this.chicken = new Chicken(getRoom("bathroom"));

        addEntitiesToArrayList();
    }

    private void addEntitiesToArrayList() {
        entities.add(wumpus);
        entities.add(popstar);
        entities.add(chicken);
    }

    public void initRooms() {
        addRoom("hall");
        addRoom("closet");
        addRoom("dungeon");
        addRoom("bathroom");

        addDirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");
    }

    public void moveEntities() {
        if (movingEntitiesThread.isAlive()) return;
        movingEntitiesThread.start();
    }

    public static World getInstance() {
        if (instance == null) instance = new World();
        return instance;
    }

    public void addRoom(String name) {
        rooms.put(name, new Room(name));
    }


    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }


    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public Player getPlayer() {
        return player;
    }

    public class Room implements ItemContainer {
        private final String name;

        private final ArrayList<Room> neighbors;
        private final ArrayList<Item> items;

        public Room(String name) {
            this.name = name;
            this.neighbors = new ArrayList<>();
            items = new ArrayList<>();
        }

        private void addNeighbor(Room n) {
            neighbors.add(n);
        }

        public String getName() {
            return name;
        }

        public String getNeighborNames() {
            StringBuilder string = new StringBuilder();

            for (Room room : neighbors) {
                string.append(" ").append(room.getName());
            }

            return string.toString();
        }

        public String getItemsName() {
            StringBuilder string = new StringBuilder();

            for (Item item : items) {
                string.append(" ").append(item.getName());
            }

            return string.toString();
        }

        public Room getNeighbor(String name) {
            for (Room room : neighbors) {
                if (room.getName().equals(name))
                    return room;
            }

            return null;
        }


        public List<Room> getNeighbors() {
            return Collections.unmodifiableList(neighbors);
        }

        public boolean hasNeighbor(Room room) {
            return neighbors.contains(room);
        }

        @Override
        public List<Item> getListOfItems() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[2];

            if (element.getClassName().equals("ItemContainer"))
                return items;

            return Collections.unmodifiableList(items);
        }

        public boolean containsPlayer() {
            return player.isInRoom(this);
        }

        public Room getRandomRoom() {
            int rand = (int) (Math.random() * neighbors.size());
            return neighbors.get(rand);
        }

        public String getStringOfEntities() {
            StringBuilder string = new StringBuilder();
            for (Entity entity : entities){
                if (entity.getRoom() == this)string.append(" ").append(entity.getName());
            }

            return string.toString();
        }
    }
}