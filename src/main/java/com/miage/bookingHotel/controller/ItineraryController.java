package com.miage.bookingHotel.controller;

import com.miage.bookingHotel.service.itineraryService;
import com.miage.bookingHotel.model.Itinerary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@Controller
public class ItineraryController {

    public static final Logger logger = LoggerFactory.getLogger(ItineraryController.class);
    @Autowired
    itineraryService itineraryService;


    // get all itineraries
    @RequestMapping(value="/itineraries", method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getAllItineraries(){
        List<Itinerary> itineraries=itineraryService.getAllItineraries();
        if(itineraries.isEmpty()){
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("No content found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Itinerary>>(itineraries, HttpStatus.OK);
    }

    //get itinerary by reference number
    @RequestMapping(value="/itineraries/ref/{referenceNumber}", method=RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getItineraryByReferenceNumber(@PathVariable("referenceNumber") String referenceNumber){
        logger.info("Fetching itinerary with reference number {}", referenceNumber);
        Optional<Itinerary> itinerary=itineraryService.findByReferenceNumber(referenceNumber);
        if(!itinerary.isPresent()){
            logger.error("Itinerary with reference number {} not found", referenceNumber);
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Itinerary with reference number " + referenceNumber
                    + " not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Itinerary>>(itinerary, HttpStatus.OK);
    }

    //get itinerary by customer id
    @RequestMapping(value="/itineraries/id/{customer_id}", method=RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getItineraryByCustomerId(@PathVariable("customer_id") String customer_id){
        logger.info("Fetching itinerary with customer id {}", customer_id);
        List<Itinerary> itineraries = itineraryService.findByCustomer_id(customer_id);
        if(itineraries.isEmpty()){
            logger.error("Itinerary with customer id {} not found", customer_id);
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Itinerary with customer id " + customer_id
                    + " not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Itinerary>> (itineraries, HttpStatus.OK);
    }

    //add new itinerary
    @RequestMapping(value = "/itineraries", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> createItinerary(@RequestBody Itinerary itinerary, UriComponentsBuilder ucBuilder){
        logger.info("Creating Itinerary : {}", itinerary);
        itinerary.setReferenceNumber(itineraryService.generateReference_num(itinerary.getHotelName(), itinerary.getCustomer_id()));
        if(itineraryService.isItineraryExist(itinerary)){
            logger.error("Unable to create this itinerary with reference number {} ,already exist", itinerary.getReferenceNumber());
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Unable to create a itinerary with reference number " +
                    itinerary.getReferenceNumber() + " ,already exist."),HttpStatus.CONFLICT);
        }
        itineraryService.saveItinerary(itinerary);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/itineraries/{}").buildAndExpand(itinerary.getReferenceNumber()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //update itinerary
    @RequestMapping(value = "/itineraries/ref/{referenceNumber}", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> updateItinerary(@PathVariable("referenceNumber") String referenceNumber, @RequestBody Itinerary itinerary){
        logger.info("Updating Itinerary with reference number {}", referenceNumber);
        Optional<Itinerary> currentItinerary = itineraryService.findByReferenceNumber(referenceNumber);
        if(!currentItinerary.isPresent()){
            logger.error("Unable to update itinerary with reference number {} ,not found.", referenceNumber);
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Unable to update itinerary with reference number "
                    + referenceNumber + " ,not found."), HttpStatus.NOT_FOUND);
        }
        itineraryService.updateItinerary(referenceNumber, itinerary);
        return new ResponseEntity<Itinerary>(itinerary, HttpStatus.OK);
    }

    // delete itinerary by reference number
    @RequestMapping(value = "/itineraries/ref/{referenceNumber}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> deleteItineraryByReferenceNumber(@PathVariable("referenceNumber") String referenceNumber){
        logger.info("Deleting itinerary with reference number{}", referenceNumber);
        Optional<Itinerary> itinerary = itineraryService.findByReferenceNumber(referenceNumber);
        if(!itinerary.isPresent()){
            logger.error("Unable to delete itinerary with reference number {} ,not found.", referenceNumber);
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Unable to delete itinerary with reference number "
                    + referenceNumber + ",not found."), HttpStatus.NOT_FOUND);
        }
        itineraryService.deleteItineraryByReferenceNumber(referenceNumber);
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Successfully deleted itinerary with reference number "
                + referenceNumber), HttpStatus.OK);
    }

    // response status
    public class ProjectStatus {
        private String status;

        public ProjectStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
