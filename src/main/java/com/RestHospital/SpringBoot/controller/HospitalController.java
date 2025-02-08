package com.RestHospital.SpringBoot.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestHospital.SpringBoot.error.ApiResponse;
import com.RestHospital.SpringBoot.model.Hospital;
import com.RestHospital.SpringBoot.service.HospitalService;

@RestController
@RequestMapping("/api/v1")
public class HospitalController 
{
 
	@Autowired
	HospitalService hospitalService;
	//<----------------------------------------------------POST--------------------------------------------->
	@PostMapping("/hospitals")
	public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital)
	{
	Hospital savedData=hospitalService.saveHospital(hospital);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("info", "Data save successfully")
			.body(savedData);
	}
	
	@PostMapping("/hospitals/bluk")
	public ResponseEntity<List<Hospital>> saveAllHospitals(@RequestBody List<Hospital> hospitals)
	{
	List<Hospital>	allSavedData=hospitalService.saveAllHospitals(hospitals);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("info", "data saved successfully")
			.body(allSavedData);
	}
	
	//<--------------------------------------------------GET----------------------------------------->
	
	@GetMapping("/hospitals/id/{id}")
	public ResponseEntity<?> getHospitalById(@PathVariable long id)
	{
	Optional<Hospital>	data=hospitalService.getHospitalById(id);
	if(data.isPresent())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with id: "+id );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	@GetMapping("/hospitals/location/{location}")
	public ResponseEntity<?> searchByLocation(@PathVariable String location)
	{
	Optional<List<Hospital>> data=hospitalService.searchByLocation(location);
	if(data.isPresent() & !data.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with location : "+ location);
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	@GetMapping("/hospitals")
	public ResponseEntity<List<Hospital>> getAllHospitals()
	{
	List<Hospital>	allHospitals=hospitalService.getAllHospitals();
	return ResponseEntity.status(HttpStatus.OK)
			.header("info", "Data retrieved successfully")
			.body(allHospitals);
	}
	
	
	
	@GetMapping("/hospitals/rating/greater-than/{rating}")
	public ResponseEntity<?> searchRatingGreaterThan(@PathVariable double rating)
	{
	Optional<List<Hospital>> ratingGreater	=hospitalService.searchRatingGreaterThan(rating);
	if(ratingGreater.isPresent() && !ratingGreater.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(ratingGreater.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with rating greater than : "+rating );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	@GetMapping("/hospitals/rating/less-than/{rating}")
	public ResponseEntity<?> searchRatingLessThan(@PathVariable double rating)
	{
	Optional<List<Hospital>> ratingLess	=hospitalService.searchRatingLessThan(rating);
	if(ratingLess.isPresent() && !ratingLess.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(ratingLess.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with rating greater than : "+rating );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	@GetMapping("/hospitals/rating/{minRating}/{maxRating}")
	public ResponseEntity<?> searchByRating(@PathVariable double minRating,@PathVariable double maxRating)
	{
	Optional<List<Hospital>>	data=hospitalService.searchByRating(minRating,maxRating);
	if(data.isPresent() & !data.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with ratings between : "+minRating+" , "+maxRating );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	@GetMapping("/hospitals/search/locationandname")
	public ResponseEntity<?> searchByLocationAndName(@RequestParam String location,@RequestParam String name)
	{
	Optional<List<Hospital>> data=hospitalService.searchByLocationAndName(location,name);
	if(data.isPresent() & !data.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with location : "+ location+" and "+name);
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	@GetMapping("/hospitals/search/locationorname")
	public ResponseEntity<?> searchByLocationOrName(@RequestParam String location,@RequestParam String name)
	{
	Optional<List<Hospital>> data=hospitalService.searchByLocationOrName(location,name);
	if(data.isPresent() & !data.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with location : "+ location+" or "+name);
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	
	@GetMapping("/hospitals/{location}/{minRating}/{maxRating}")
	public ResponseEntity<?> searchByLocMinMaxRating(@PathVariable String location,@PathVariable double minRating,@PathVariable double maxRating)
	{
	Optional<List<Hospital>>	data=hospitalService.searchByLocMinMaxRating(location,minRating,maxRating);
	if(data.isPresent() & !data.get().isEmpty())
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "Data prsent in Database")
				.body(data.get());
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with location : "+ location+" , and ratings between "+minRating+" , and "+maxRating);
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	
	@GetMapping("/hospitals/sort-by-rating")
	public ResponseEntity<List<Hospital>> orderByRating()
	{
	List<Hospital>	data=hospitalService.orderByRating();
	return ResponseEntity.status(HttpStatus.OK)
			.header("info", "Data retrieved successfully")
			.body(data);
	}
	
	
	
	//<-------------------------------------------------DELETE----------------------------------->
	
	@DeleteMapping("/hospitals/{id}")
	public ResponseEntity<?> deleteDataById(@PathVariable long id)
	{
	boolean	t=hospitalService.deleteDataById(id);
	if(t)
	{
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully")
				.build();
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with id "+id );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	@DeleteMapping("/hospitals/email/{email}")
	public ResponseEntity<?> deleteDataByEmail(@PathVariable String email)
	{
		hospitalService.deleteDataByEmail(email);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully")
				.build();
	}
	
	
	@DeleteMapping("/hospitals/rating/{minRating}/{maxRating}")
	public ResponseEntity<?> deleteDataByMinMaxRating(@PathVariable double minRating,@PathVariable double maxRating)
	{
		
	hospitalService.deleteDataByMinMaxRating(minRating,maxRating);
//	if(t)
//	{
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully")
				.build();
//	}
//	else
//	{
//		ApiResponse apiResponse=new ApiResponse();
//		apiResponse.setMessage("Data not present with rating between "+minRating+", and " +maxRating);
//		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//		apiResponse.setTimseStamp(LocalDateTime.now());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND)
//				.header("info", "data not present")
//				.body(apiResponse);
//	}
	}
	
	@DeleteMapping("/hospitals/rating/greater-than/{rating}")
	public ResponseEntity<?> deleteDataByGreaterRating(@PathVariable double rating)
	{
		hospitalService.deleteDataByGreaterRating(rating);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully")
				.build();
	}
	
	
	@DeleteMapping("/hospitals/delete-by-rating-and-location/{rating}/{location}")
	public ResponseEntity<?> deleteDataByRatingAndLocation(@PathVariable double rating,@PathVariable String location)
	{
		hospitalService.deleteDataByRatingAndLocation(rating,location);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully")
				.build();
	}
	
	
	
	
	//<--------------------------------------------------PUT---------------------------------------------->
	
	@PutMapping("/hospitals/{id}")
	public ResponseEntity<?> updateDataById(@RequestBody Hospital newhospital ,@PathVariable long id)
	{
	Hospital updatedData=	hospitalService.updateDataById(newhospital,id);
	if(updatedData !=null)
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "data updated successfully")
				.body(updatedData);
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with id "+id );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	
	
	
	
	//<------------------------------------------PATCH---------------------------------------------->
	
	@PatchMapping("/hospitals/{id}")
	public ResponseEntity<?> updataOnlyName(@PathVariable long id,@RequestBody Map<String, Object> newdata)
	{
	Hospital	updateNameData=hospitalService.updateOnlyName(id,newdata);
	if(updateNameData != null)
	{
		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "data updated successfully")
				.body(updateNameData);
	}
	else
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Data not present with id "+id );
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setTimseStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.header("info", "data not present")
				.body(apiResponse);
	}
	}
	
	@PatchMapping("/hospitals/update-email/{id}/{email}")
	public ResponseEntity<?> updateOnlyEmail(@PathVariable long id,@PathVariable String email)
	{
	hospitalService.updateOnlyEmail(id,email);

		return ResponseEntity.status(HttpStatus.OK)
				.header("info", "data updated successfully")
				.build();

	}
}
