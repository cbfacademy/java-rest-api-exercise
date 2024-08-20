package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/ious")
public class IOUController{

    
    private final IOUService iouService;

     public IOUController(IOUService iouService) {
    this.iouService = iouService;
  }



@GetMapping(produces = "application/json")
public List<IOU> getAllIOUs(@RequestParam(required = false) String borrower) {
    System.out.println("Received borrower parameter: " + borrower);
    //if borrower is provided, and the vaule provided is meaningful
    if (borrower !=null && !borrower.isEmpty()){
    return iouService.getIOUsByBorrower(borrower);
}
    else{
        return iouService.getAllIOUs();      //return All IOUs 
    } 
    }
    

@GetMapping("/{id}")
public ResponseEntity<IOU> getIOUById(@PathVariable UUID id){
    IOU iou = iouService.getIOU(id);
    return  ResponseEntity.ok(iou);
}

@PostMapping (produces = "application/json")
public IOU createIOU (@RequestBody IOU iou) {
    return iouService.createIOU(iou);
}

@PutMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<IOU> updateIou(@PathVariable UUID id, @RequestBody IOU iou) {
        try{
        IOU updatedIou =iouService.updateIOU(id,iou);
          return ResponseEntity.ok(updatedIou); //return 200 ok with iou
        }catch (NoSuchElementException e) {
         return ResponseEntity.notFound().build();
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteIOU(@PathVariable UUID id) {
    iouService.deleteIOU(id);
    return ResponseEntity.noContent().build();
}


}