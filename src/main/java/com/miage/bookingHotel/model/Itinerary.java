package com.miage.bookingHotel.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="Itinerary")
public class Itinerary {


    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="reference_num")
    private String referenceNumber;
    @Column(name="hotel_name")
    private String hotelName;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="checkin_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date check_in_date;
    @Column(name="checkout_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date check_out_date;
    @Column(name="price")
    private double price;
    @Column(name="room_type")
    private String roomType;
    @Column(name="customer_id")
    private String customer_id;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Itinerary() {
    }

//    public Itinerary(String referenceNumber, String hotelName, String address, String phone, String check_in_date,
//                     String check_out_date, double price, String roomType, String customer_id) {
//        this.referenceNumber = referenceNumber;
//        this.hotelName = hotelName;
//        this.address = address;
//        this.phone = phone;
//        this.check_in_date = check_in_date;
//        this.check_out_date = check_out_date;
//        this.price = price;
//        this.roomType = roomType;
//        this.customer_id = customer_id;
//    }
}
