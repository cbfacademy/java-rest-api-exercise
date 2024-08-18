package com.cbfacademy.restapiexercise.ious;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {
    //List<IOU> findByBorrower (String borrower);

  //  @Query(value = "SELECT * FROM IOU i WHERE i.amount > (SELECT AVG(amount) FROM IOU)",
     //nativeQuery = true)
    //List<IOU> findHighValueIOUs();
        
    

}
