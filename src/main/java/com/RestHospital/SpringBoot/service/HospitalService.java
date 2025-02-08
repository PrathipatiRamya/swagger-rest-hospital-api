package com.RestHospital.SpringBoot.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RestHospital.SpringBoot.model.Hospital;
import com.RestHospital.SpringBoot.repository.HospitalRepository;

@Service
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;

	public Hospital saveHospital(Hospital hospital) {
	return	hospitalRepository.save(hospital);
		
	}

	public List<Hospital> saveAllHospitals(List<Hospital> hospitals) {
	return	hospitalRepository.saveAll(hospitals);
		
	}

	public List<Hospital> getAllHospitals() {
		return hospitalRepository.findAll();
		
	}

	public Optional<Hospital> getHospitalById(long id) {
   Optional<Hospital>	optionaldata=hospitalRepository.findById(id);
   return optionaldata;
		
   
	}

	public Optional<List<Hospital>> searchByLocation(String location) {
	 return	hospitalRepository.findByLocation(location);
		
	}

	public Optional<List<Hospital>> searchByRating(double minRating, double maxRating) {
		return hospitalRepository.findByRatingBetween(minRating,maxRating);
		
	}

	public boolean deleteDataById(long id) {

  if(hospitalRepository.existsById(id))
  {
	  hospitalRepository.deleteById(id);
	  return true;
  }
  return false;
	  
		
	}

	public Optional<List<Hospital>> searchRatingGreaterThan(double rating) {
	return	hospitalRepository.findByRatingGreaterThan(rating);
		
	}

	public Optional<List<Hospital>> searchRatingLessThan(double rating) {
		
		return hospitalRepository.findByRatingLessThan(rating);
	}



	public Optional<List<Hospital>> searchByLocationAndName(String location, String name) {
		return hospitalRepository.findByLocationAndName(location,name);
		
	}

	public Optional<List<Hospital>> searchByLocationOrName(String location, String name) {
		
		return hospitalRepository.findByLocationOrName(location,name);
	}

	public Optional<List<Hospital>> searchByLocMinMaxRating(String location, double minRating, double maxRating) {
		return hospitalRepository.findByLocAndMinMaxRating(location,minRating,maxRating);
		
	}

	public List<Hospital> orderByRating() {
		return hospitalRepository.orderByRatingAsc();
		
	}

	public void deleteDataByEmail(String email) {
		hospitalRepository.deleteByEmail(email);
		
		
	}

	public void deleteDataByMinMaxRating(double minRating, double maxRating) {
//		if(hospitalRepository.existsByRatingBetween(minRating,maxRating))
//		{
			
		hospitalRepository.deleteByRatingBetween(minRating,maxRating);
//		return true;
//		}
//		return false;
		
	}

	public void deleteDataByGreaterRating(double rating) {
		hospitalRepository.deleteByRatingGreaterThan(rating);
		
	}

	public void deleteDataByRatingAndLocation(double rating, String location) {
		hospitalRepository.deleteByGreaterRatingAndLocation(rating,location);
		
	}

	public Hospital updateDataById(Hospital newhospital, long id) {
	Optional<Hospital> optionadata	=hospitalRepository.findById(id);
	if(optionadata.isPresent())
	{
		Hospital existingData=optionadata.get();
		existingData.setName(newhospital.getName());
		existingData.setLocation(newhospital.getLocation());
		existingData.setEmail(newhospital.getEmail());
		existingData.setRating(newhospital.getRating());
	return	hospitalRepository.save(existingData);
	}
	return null;
	}

	public Hospital updateOnlyName(long id, Map<String, Object> newdata) {
		Optional<Hospital> optionaldata=hospitalRepository.findById(id);
		if(optionaldata.isPresent())
		{
			Hospital existData=optionaldata.get();
			if(newdata.containsKey("name"))
			{
				existData.setName((String)newdata.get("name"));
			}
			if(newdata.containsKey("location"))
			{
				existData.setName((String)newdata.get("location"));
			}
			if(newdata.containsKey("email"))
			{
				existData.setName((String)newdata.get("email"));
			}
			if(newdata.containsKey("rating"))
			{
				existData.setName((String)newdata.get("rating"));
			}
			return hospitalRepository.save(existData);
		}
		return null;
		
	}

	public void updateOnlyEmail(long id, String email) {
		Optional<Hospital> optionaldata=hospitalRepository.findById(id);
		if(optionaldata.isPresent())
		{
//			Hospital existData=optionaldata.get();
//			existData.setEmail(email);
//			return hospitalRepository.save(existData);
			
			hospitalRepository.updateEmailById(id, email);
			
		}
		
		
	}
	
	

}
