package tba.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tba.Utils.DatabaseHandler;

public class RoomTransition {
    
    private int id;
    private int fromRoomId;
    private int toRoomId;
    private int directionId;

    public RoomTransition(int id, int fromRoomId, int toRoomId, int directionId) {
        this.id = id;
        this.fromRoomId = fromRoomId;
        this.toRoomId = toRoomId;
        this.directionId = directionId;
    }

    public static List<RoomTransition> findAll()
    {
        try {
            List<RoomTransition> transitions = new ArrayList<>();
            ResultSet set = DatabaseHandler.query("SELECT * FROM `room_transition`");
            while (set.next()) {
                transitions.add(
                    new RoomTransition(
                        set.getInt("id"),
                        set.getInt("from_room_id"),
                        set.getInt("to_room_id"),
                        set.getInt("direction_id")
                    )
                );
            }
            return transitions;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static List<RoomTransition> findAllFromRoom(Room room)
    {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("SELECT * FROM `room_transition` WHERE `from_room_id` = ?");
            statement.setInt(1, room.getId());
            ResultSet set = statement.executeQuery();
    
            List<RoomTransition> transitions = new ArrayList<>();
            while (set.next()) {
                transitions.add(
                    new RoomTransition(
                        set.getInt("id"),
                        set.getInt("from_room_id"),
                        set.getInt("to_room_id"),
                        set.getInt("direction_id")
                    )
                );
            }
            return transitions;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static RoomTransition findByFromRoomAndDirection(Room room, Direction direction)
    {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement("SELECT * FROM `room_transition` WHERE `from_room_id` = ? AND `direction_id` = ?");
            statement.setInt(1, room.getId());
            statement.setInt(2, direction.getId());
            ResultSet set = statement.executeQuery();
    
            if (set.first()) {
                return new RoomTransition(
                    set.getInt("id"),
                    set.getInt("from_room_id"),
                    set.getInt("to_room_id"),
                    set.getInt("direction_id")
                );
            } else {
                return null;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "[RoomTransition #" + id + "] {\n fromRoom: " + getFromRoom() + ",\n toRoom: " + getToRoom() + ",\n direction: " + getDirection() + "\n}";
    }

    public int getId() {
        return id;
    }

    public Room getFromRoom()
    {
        return Room.findById(fromRoomId);
    }

    public Room getToRoom()
    {
        return Room.findById(toRoomId);
    }

    public RoomTransition setFromRoom(Room room)
    {
        fromRoomId = room.getId();
        return this;
    }

    public RoomTransition setToRoom(Room room)
    {
        toRoomId = room.getId();
        return this;
    }

    public Direction getDirection()
    {
        return Direction.findById(directionId);
    }

    public RoomTransition setDirection(Direction direction)
    {
        directionId = direction.getId();
        return this;
    }

}
