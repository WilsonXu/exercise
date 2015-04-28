package org.weber.siia.booking.domain.hotel;

/**
 * Created by wxu on 4/28/2015.
 */
public class HotelCriteria {
    private RoomType roomType = RoomType.Twin;
    private boolean smokingRoom = false;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isSmokingRoom() {
        return smokingRoom;
    }

    public void setSmokingRoom(boolean smokingRoom) {
        this.smokingRoom = smokingRoom;
    }
}
