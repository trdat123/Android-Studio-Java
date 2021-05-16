package com.example.chinago;

import java.util.ArrayList;

public class Utils {
    private static Utils instance;

    private static ArrayList<Hotel> allHotels;
    private static ArrayList<Hotel> outOfRoomHotels;

    private Utils() {
        if (null == allHotels) {
            allHotels = new ArrayList<>();
            initData();
        }

        if (null == outOfRoomHotels) {
            outOfRoomHotels = new ArrayList<>();
            initData();
        }
    }

    private void initData() {
        ArrayList<Hotel> Hotel = new ArrayList<>();
        allHotels.add(new Hotel(1, "The Home Hotel", "Chung cư studio 40 m² có 1 phòng tắm riêng ở Quận 1 (Apartment 5 stars + Pool infinity + View icon HCMC)", 0, "5.900.000đ","https://haianhland.com/wp-content/uploads/2019/10/hotel-l%C3%A0-g%C3%AC.jpg"));
        allHotels.add(new Hotel(2,"Anatole Hotel", "Biệt thự 300 m² 5 phòng ngủ, 5 phòng tắm riêng ở Thắng Nhất (Luck Villa 425m2 , Hồ bơi nước mặn ", 6, "1.900.000đ","https://cf.bstatic.com/images/hotel/max1024x768/264/264037166.jpg"));
        allHotels.add(new Hotel(3,"Mella Hotel", "Chung cư 97 m² 2 phòng ngủ, 2 phòng tắm riêng ở Thắng Nhất (ARIA Vung tau-Blue Saphire -Seaview- Apart-2beds)", 6, "3.900.000đ","https://cf.bstatic.com/images/hotel/max1024x768/241/241278020.jpg"));
        allHotels.add(new Hotel(4,"Hotel Vung Tau", "Biệt thự 250 m² 4 phòng ngủ, 4 phòng tắm riêng ở Phường 8 (DREAM HOUSE -POOL VILLA 21)", 6, "2.900.000đ","https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2020/10/khach-san-the-cap-vung-tau-1.jpg"));
        allHotels.add(new Hotel(5,"Hotel Quy Nhon", "Khu Nghỉ Dưỡng và Spa Léman Cap (Léman Cap Resort and Spa)", 6, "3.900.000đ","https://anyahotel.com/wp-content/uploads/2020/09/Anya-hotel.jpg"));
        allHotels.add(new Hotel(6,"Rex Hotel", "Khu nghỉ dưỡng Premier Village Đà Nẵng do Accor quản lý (Premier Village Danang Resort Managed by Accor)", 13, "3.900.000đ","https://d2ile4x3f22snf.cloudfront.net/wp-content/uploads/sites/174/2017/08/14074754/rex-hotel-vietnam-home-slideshow-image-01.jpg"));
        allHotels.add(new Hotel(7,"Phat Linh Hotel", "Chung cư 45 m² 1 phòng ngủ, 1 phòng tắm riêng ở Phước Mỹ (SEN Boutique Villa Apartment)", 7, "3.900.000đ","https://cf.bstatic.com/images/hotel/max1024x768/266/266440395.jpg"));
        allHotels.add(new Hotel(8,"Hotel Fontana", "Chung cư 40 m² 1 phòng ngủ, 1 phòng tắm riêng ở Phước Mỹ (Sea Sand Hotel & Apartment 40m2 beside the beach)", 11, "3.900.000đ","https://media-cdn.tripadvisor.com/media/photo-s/18/7b/c6/44/hotel-fontana.jpg"));
        allHotels.add(new Hotel(9,"Northern Hotel", "Chung cư 66 m² 2 phòng ngủ, 2 phòng tắm riêng ở Phước Mỹ (muong thanh beach apartments)", 6, "3.500.000đ","https://www.northernhotel.com.vn//uploads/overview-instuction.jpg"));
        allHotels.add(new Hotel(10,"Vung Tau Riva Hotel", "Căn hộ 54 m² 1 phòng ngủ, 1 phòng tắm riêng ở Phường 2 (Lovely Pinky Xuân's Homestay Vũng Tàu)", 17, "6.900.000đ","https://vungtaurivahotel.com/files/sites/site_24_banner/beach-min_1_.jpg"));
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Hotel> getAllHotels() {
        return allHotels;
    }

    public static ArrayList<Hotel> getOutOfRoomHotels() {
        return outOfRoomHotels;
    }

    public Hotel getHotelById(int id) {
        for (Hotel h: allHotels) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    public boolean addToRentHistory(Hotel hotel) {
        return outOfRoomHotels.add(hotel);
    }
}

