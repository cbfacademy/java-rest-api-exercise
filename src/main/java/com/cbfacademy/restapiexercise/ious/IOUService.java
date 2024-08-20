package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class IOUService {
  
  private final  IOURepository iouRepository;
    

  public IOUService(IOURepository iouRepository) {
    this.iouRepository = iouRepository;
  }

  public List<IOU> getAllIOUs() {
    return this.iouRepository.findAll();
  }

  public IOU getIOU(UUID id) throws NoSuchElementException {
    return this.iouRepository.findById(id).orElseThrow();
  }

  public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
    return this.iouRepository.save(iou);
  }

  public IOU updateIOU(UUID id, IOU updatedIOU) {
    IOU existingIOU = getIOU(id); {
        // Update the existing IOU with values from the updatedIOU
        existingIOU.setAmount(updatedIOU.getAmount());
        existingIOU.setBorrower(updatedIOU.getBorrower());
        existingIOU.setLender(updatedIOU.getLender());
        existingIOU.setDateTime(updatedIOU.getDateTime());
        // Save the updated IOU back to the database
        return iouRepository.save(existingIOU);
    }};



  public void deleteIOU(UUID id) {
    this.iouRepository.delete(getIOU(id));
  }
public List <IOU> getIOUsByBorrower(String borrower){
  return this.iouRepository.findByBorrower(borrower);
  
}


}