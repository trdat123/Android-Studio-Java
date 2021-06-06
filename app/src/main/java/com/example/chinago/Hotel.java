package com.example.chinago;

import java.util.Date;

public class Hotel {
    private int id;
    private String name;
    private String imageURl; // image of the hotel
    private String description;
    private int room; // available room of the hotel
    private String rentRoom; // number of room that user rent, this variable will be used when user press rent button
    private String roomPrice; // the hotel will have the same room price for each room
    private String rentDate; // the date user checkout, this variable will be used when user press rent button

    public Hotel(int id, String name, String description, int room, String roomPrice, String rentDate, String rentRoom, String imageURl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.room = room;
        this.imageURl = imageURl;
        this.roomPrice = roomPrice;
        this.rentDate = rentDate;
        this.rentRoom = rentRoom;
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

    public void deleteRentedRoom(int room) {
        this.room += room;
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getRentRoom() {
        return rentRoom;
    }

    public void setRentRoom(String rentRoom) {
        this.rentRoom = rentRoom;
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
