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

    private final HashMap<String, Room> rooms = new HashMap<>(); // links String to room

    private final Player player; // player that is going to be editing and moving in the world

    private static World instance; // a static instance of the Wold

    private final ArrayList<Entity> entities; // all the entities/creatures in the world

    private final Wumpus wumpus; // wumpus is the entity to create
    private final Popstar popstar; // popstar is the annoying person that doesn't do anything
    private final Chicken chicken; // chicken are just random brained things

    // thread that moves all the entities
    private final Thread movingEntitiesThread = new Thread() {
        @Override
        public void run() {
            for (Entity entity : entities) {
                entity.move();
            }

            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * a private constructor which makes sure that there is only one instance
     */
    private World() {

        initRooms();
        entities = new ArrayList<>();

        this.player = new Player(rooms.get("hall"));
        this.wumpus = new Wumpus(getRoom("closet"));
        this.popstar = new Popstar(getRoom("dungeon"));
        this.chicken = new Chicken(getRoom("bathroom"));
        addEntitiesToArrayList();
    }

    /**
     * initializing the creatures into the master entities list
     */
    private void addEntitiesToArrayList() {
        entities.add(wumpus);
        entities.add(popstar);
        entities.add(chicken);
    }

    /**
     * initializes room, and add some edges(completely arbitrary)
     */
    public void initRooms() {
        addRoom("hall");
        addRoom("closet");
        addRoom("dungeon");
        addRoom("bathroom");
        addRoom("bedroom");
        addRoom("Theater");
        addRoom("playroom");
        addRoom("basketballCourt");

        addDirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");
        addUndirectedEdge("dungeon", "bathroom");
        addUndirectedEdge("theater", "playroom");
        addUndirectedEdge("basketballCourt", "closet");
    }

    /**
     * starts moving the entities
     */
    public void moveEntities() {
        if (movingEntitiesThread.isAlive()) return;
        movingEntitiesThread.start();
    }

    /**
     * returns the same instance of a class
     * @return a static World instance
     */
    public static World getInstance() {
        if (instance == null) instance = new World();
        return instance;
    }

    /**
     * add room to master list of rooms
     * @param name
     */
    public void addRoom(String name) {
        rooms.put(name, new Room(name));
    }


    /**
     * makes a traversable path between the two rooms(one direction)
     * @param name1 name of first room
     * @param name2 name of two room
     */
    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
    }

    /**
     * makes a traversable path between the two rooms(both directions)
     * @param name1 name of first room
     * @param name2 name of two room
     */
    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1.trim());
        Room n2 = getRoom(name2.trim());
        if (n1 == null || n2 == null) return;

        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }


    /**
     * gets a room with the string name
     * @param name the name of the room
     * @return Room object with the the string name
     */
    public Room getRoom(String name) {
        return rooms.get(name);
    }

    /**
     * @return player in the world
     */
    public Player getPlayer() {
        return player;
    }

    public boolean killWumpus() {
        if (getPlayer().isInRoom(wumpus.getRoom())){
            entities.remove(wumpus);
            return true;
        }

        return false;
    }

    /**
     * Room fo the world
     */
    public class Room implements ItemContainer {
        private final String name; // name of the room

        private final ArrayList<Room> neighbors; // rooms that a player can move to from this room
        private final ArrayList<Item> items; // the items in this room a player can pickup

        /**
         * Constructs a room
         * @param name of the room
         */
        private Room(String name) {
            this.name = name;
            this.neighbors = new ArrayList<>();
            items = new ArrayList<>();
        }

        /**
         * adds a neighbor to this room
         * @param n is just a room
         */
        private void addNeighbor(Room n) {
            neighbors.add(n);
        }

        /**
         * @return the name of the room
         */
        public String getName() {
            return name;
        }

        /**
         * @return a string of all the names of the neighbors
         */
        public String getNeighborNames() {
            StringBuilder string = new StringBuilder();

            for (Room room : neighbors) {
                string.append(" ").append(room.getName());
            }

            return string.toString();
        }

        /**
         * @return a string of all the items in the room
         */
        public String getItemsName() {
            StringBuilder string = new StringBuilder();

            for (Item item : items) {
                string.append(" ").append(item.getName());
            }

            return string.toString();
        }

        /**
         * @param name of the room you want to get
         * @return a room with that name, if it doesn't exist it return null
         */
        public Room getNeighbor(String name) {
            for (Room room : neighbors) {
                if (room.getName().equals(name))
                    return room;
            }

            return null;
        }


        /**
         * @return a unmodifiable list of neighbors
         */
        public List<Room> getNeighbors() {
            return Collections.unmodifiableList(neighbors);
        }

        /**
         * checks if room has a neighbor
         * @param room the room that you want to check
         * @return a boolean if the room exist
         */
        public boolean hasNeighbor(Room room) {
            return neighbors.contains(room);
        }

        /**
         * checks if room has a neighbor
         * @param name the name of the room you want to check
         * @return  a boolean if the room exist
         */
        public boolean hasNeighbor(String name){
            for (Room room : neighbors){
                if (room.getName().equalsIgnoreCase(name)) return true;
            }

            return false;
        }

        /**
         * return an unmodifiable list to any object other than Command, if command its a modifiable list
         * @return a list of rooms(unmodifiable, modifiable)
         */
        @Override
        public List<Item> getListOfItems() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[2];

            if (element.getClassName().equals("ItemContainer"))
                return items;

            return Collections.unmodifiableList(items);
        }

        /**
         * checks if the room contains player
         * @return
         */
        public boolean containsPlayer() {
            return player.isInRoom(this);
        }

        /**
         * gets a random neighbor from Room
         * @return a random room(neighbor)
         */
        public Room getRandomNeighbor() {
            if (neighbors.size() == 0) return this;

            int rand = (int) (Math.random() * neighbors.size());
            return neighbors.get(rand);
        }

        /**
         * @return a string of all entities in room
         */
        public String getStringOfEntities() {
            StringBuilder string = new StringBuilder();
            for (Entity entity : entities) {
                if (entity.getRoom() == this) string.append(" ").append(entity.getName());
            }

            return string.toString();
        }

        /**
         * @return null if there isn't a wumpus, if theres a wumpus it return the wumpus
         */
        public Wumpus getWumpus(){
            if (wumpus.getRoom() == this) return wumpus;
            else return null;
        }
    }
}