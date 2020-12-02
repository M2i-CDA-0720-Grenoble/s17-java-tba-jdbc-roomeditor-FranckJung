package tba.Game;

import java.util.Date;

import tba.Model.Room;

public class GameState {
    
    private String name;
    private Date createdAt;

    private int currentRoomId;

    public GameState(int startRoomId)
    {
        currentRoomId = startRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Room getCurrentRoom()
    {
        return Room.findById(currentRoomId);
    }

    public GameState setCurrentRoom(Room room)
    {
        currentRoomId = room.getId();
        return this;
    }

}
