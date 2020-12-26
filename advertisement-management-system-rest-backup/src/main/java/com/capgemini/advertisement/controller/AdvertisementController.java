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

/**
 * 
 * @author Sapna and Dakshata
 *
 */
@RestController
@RequestMapping("/api/advertisement")
@Slf4j
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;

	@ApiOperation(value = "Get Advertisement by Id",
			response = AdvertisementDetails.class,tags="get-advertisement-by-id",
			consumes="id",httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<AdvertisementDetails> getAdvertisementById(@PathVariable Integer id){
		try {
			AdvertisementDetails advertisements= advertisementService.getAdvertisementById(id);
			log.info("Advertisement added"+ advertisements);
			return new ResponseEntity<>(advertisements,HttpStatus.OK);
		}catch(AdvertisementException advertisementException) {
			log.error(advertisementException.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
		}
	}
	@ApiOperation(value = "Get All Advertisement",
			response = AdvertisementDetails.class,tags="get-all-advertisement",
			httpMethod = "GET")
	@GetMapping("/")
	public ResponseEntity<List<AdvertisementDetails>> getAllAdvertisements(){
		try {
			List<AdvertisementDetails> advertisementList = advertisementService.getAllAdvertisement();
			log.info("Returning all customer details");
			return new ResponseEntity<>(advertisementList,HttpStatus.OK);
		}catch(AdvertisementException advertisementException) {
			log.error(advertisementException.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
		}
	}

	@ApiOperation(value = "Add Advertisement",
			consumes = "receives Advertisement object as request body",
			response =String.class)
	@PostMapping("/{cid}/{sid}")
	public String addAdvertisements(@PathVariable Integer cid,@PathVariable Integer sid, @RequestBody AdvertisementDetails advertisements) {
		try {
			Integer status= advertisementService.addAdvertisement(cid,sid,advertisements);

			if(status ==1) {
				log.info("advertisement:"+advertisements.getAdvType()+" added to database");
				return "advertisement:"+advertisements.getAdvType()+" added to database";
			}else {
				log.debug("Unable to add advertisement");
				return "Unable to add advertisement to database";
			}

		}catch(AdvertisementException advertisementException) {
			log.error(advertisementException.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
		}
	}

	@ApiOperation(value = "Delete Advertisement",
			consumes = "id",
			response =String.class)
	@DeleteMapping("/{id}")
	public String deleteAdvertisements(@PathVariable Integer id) {
		try {
			Integer status= advertisementService.deleteAdvertisement(id);
			if(status ==1) {
				log.info("advertisement: "+id+" deleted from database");
				return "advertisement: "+id+" deleted from database";
			}else {
				log.debug("Unable to delete advertisement from database");
				return "Unable to delete advertisement from database";
			}

		}catch(AdvertisementException advertisementException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
		}
	}
	
	@ApiOperation(value = "Update Advertisement",
			consumes = "receives Advertisement object as request body",
			response =AdvertisementDetails.class)
	@PutMapping("/")
	public ResponseEntity<AdvertisementDetails> updateAdvertisements(@RequestBody AdvertisementDetails advertisements) {
		try {
			AdvertisementDetails updatedAdvertisement= advertisementService.updateAdvertisement(advertisements);
			log.info("Advertisement: "+ advertisements.getId()+ " updated");
			return new ResponseEntity<>(updatedAdvertisement,HttpStatus.OK);

		}catch(AdvertisementException advertisementException) {
			log.error(advertisementException.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,advertisementException.getMessage());
		}
	}
}


