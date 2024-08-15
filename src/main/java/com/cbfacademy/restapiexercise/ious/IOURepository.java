package com.cbfacademy.restapiexercise.ious;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {

 List<IOU> findByBorrower(String borrower);

    

}