package com.example.chinago;

public class Hotel {
    private int id;
    private String name;
    private String imageURl;
    private String description;
    private int room;
    private String roomPrice;

    public Hotel(int id, String name, String description, int room, String roomPrice, String imageURl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.room = room;
        this.imageURl = imageURl;
        this.roomPrice = roomPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURl() {
        return imageURl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setRentedRoom() {
        this.room -= 1;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURl='" + imageURl + '\'' +
                ", description='" + description + '\'' +
                ", room=" + room +
                ", roomPrice='" + roomPrice + '\'' +
                '}';
    }
}
