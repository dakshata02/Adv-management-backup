package com.capgemini.advertisement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.exception.AdvertisementException;
import com.capgemini.advertisement.service.AdvertisementService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/advertisement")
@Slf4j
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;
	

	//get advertisement by Id
	//http://localhost:8081/api/advertisement/1
	@ApiOperation(value = "Get Advertisement by Id",response = AdvertisementDetails.class,tags="get-advertisement-by-id",consumes="advId",httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<AdvertisementDetails> getAdvertisementById(@PathVariable Integer id){
		try {
			AdvertisementDetails advertisement= advertisementService.getAdvertisementById(id);
			log.info("Advertisement added"+ advertisement);
			return new ResponseEntity<>(advertisement,HttpStatus.OK);
		}catch(AdvertisementException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//get all advertisement
	//http://localhost:8081/api/advertisement/
	@ApiOperation(value = "Get all Advertisement",response = AdvertisementDetails.class,tags="get-all-advertisement",httpMethod = "GET")
	@GetMapping("/")
	public ResponseEntity<List<AdvertisementDetails>> getAllAdvertisement(){
		try {
			List<AdvertisementDetails> advertisementList = advertisementService.getAllAdvertisement();
			log.info("Returning all Advertisement details");
			return new ResponseEntity<>(advertisementList,HttpStatus.OK);
		}catch(AdvertisementException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/advertisement/
	//add advertisement    
	@ApiOperation(value = "Add Advertisement",
			consumes = "receives Advertisement object as request body",
			response =String.class)
	@PostMapping("/{custId}/{staffId}")
	public String addAdvertisement(@PathVariable Integer custId,@PathVariable Integer staffId,@RequestBody AdvertisementDetails advertisement) {
		try {
			
			Integer status= advertisementService.addAdvertisement(custId,staffId,advertisement);
			if(status ==1) {
				log.info("Advertisement:"+advertisement.getId()+" added to database");
				return "Advertisement:"+advertisement.getId()+" added to database";
			}else {
				log.debug("Unable to add Advertisement");
				return "Unable to add Advertisement to database";
			}

		}catch(AdvertisementException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/advertisement/1
	//delete advertisement
	@ApiOperation(value = "Delete Advertisement",
			consumes = "advertisementId",
			response =String.class)
	@DeleteMapping("/{id}")
	public String deleteAdvertisement(@PathVariable Integer id) {
		try {
			Integer status= advertisementService.deleteAdvertisement(id);
			if(status ==1) {
				log.info("Advertisement: "+id+" deleted from database");
				return "Advertisement: "+id+" deleted from database";
			}else {
				log.debug("Unable to delete Advertisement from database");
				return "Unable to delete Advertisement from database";
			}

		}catch(AdvertisementException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//http://localhost:8081/api/advertisement/
	//update advertisement
	@ApiOperation(value = "Update Advertisement",
			consumes = "receives Advertisement object as request body",
			response =AdvertisementDetails.class)
	@PutMapping("/")
	public ResponseEntity<AdvertisementDetails> updateAdvertisement(@RequestBody AdvertisementDetails advertisement) {
		try {
			AdvertisementDetails updatedAdvertisement= advertisementService.updateAdvertisement(advertisement);
			log.info("Advertisement: "+ advertisement.getId()+ " updated");
			return new ResponseEntity<>(updatedAdvertisement,HttpStatus.OK);

		}catch(AdvertisementException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}



}