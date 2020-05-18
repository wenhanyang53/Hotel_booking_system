package com.miage.bookingHotel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
class BookingHotelApplicationTests {

	public static final String REST_SERVICE_URI = "http://localhost:8080";

	private static void listAllitineraries(){
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/itineraries/", List.class);

		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
				System.out.println("Itineraries : referenceNumber="+map.get("referenceNumber")+
						", hotelName="+map.get("hotelName")+", address="+map.get("address")+
						", phone="+map.get("phone")+", check_in_date="+map.get("check_in_date")+
						", check_out_date="+map.get("check_out_date")+ ", price="+map.get("price")+
						", roomType="+map.get("roomType")+", customer_id="+map.get("customer_id"));;
			}
		}else{
			System.out.println("No user exist----------");
		}
	}

	public static void main(String args[]){
		listAllitineraries();
//		getUser();
//		createUser();
//		listAllUsers();
//		updateUser();
//		listAllUsers();
//		deleteUser();
//		listAllUsers();
//		deleteAllUsers();
//		listAllUsers();
	}

}
