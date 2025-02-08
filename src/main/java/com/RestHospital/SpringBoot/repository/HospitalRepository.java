package com.RestHospital.SpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestHospital.SpringBoot.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	public Optional<List<Hospital>> findByLocation(String location);

	public Optional<List<Hospital>> findByRatingBetween(double minRating, double maxRating);

	public Optional<List<Hospital>> findByRatingGreaterThan(double rating);

	public Optional<List<Hospital>> findByRatingLessThan(double rating);

	public Optional<List<Hospital>> findByLocationAndName(String location, String name);

	public Optional<List<Hospital>> findByLocationOrName(String location, String name);
    
	//JPQl query
	@Query("select h from Hospital h where h.rating between :minRating and :maxRating and h.location=:location")
	 Optional<List<Hospital>> findByLocAndMinMaxRating(@Param("location") String location,
															@Param("minRating")  double minRating,
															@Param("maxRating")  double maxRating);
	
	//Native query
    @Query(value = "select * from Hospital order by rating ASC",nativeQuery = true)
	List<Hospital> orderByRatingAsc();
    
    @Transactional
    @Modifying
	public void deleteByEmail(String email);
   
    @Transactional
    @Modifying
	public void deleteByRatingBetween(double minRating, double maxRating);
     
    @Transactional
    @Modifying
	public void deleteByRatingGreaterThan(double rating);
     
    @Transactional
    @Modifying
    @Query("delete from Hospital h where h.rating > :rating and h.location = :location")
	 void deleteByGreaterRatingAndLocation(@Param("rating") double rating,@Param("location") String location);
    
//    public boolean existsByRatingBetween(double minRating, double maxRating);
    
    
    @Transactional
   @Modifying
    @Query("update Hospital h SET h.email= :email where h.id= :id")
    void updateEmailById(@Param("id") long id,@Param("email") String email);
    
}
