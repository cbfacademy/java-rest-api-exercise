package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/ious")
public class IOUController{

    
    private IOUService iouService;

     public IOUController(IOUService iouService) {
    this.iouService = iouService;
  }



@GetMapping(produces = "application/json")
public List<IOU> getAllIOUs(String borrower) {
    return iouService.getAllIOUs();
    }

@GetMapping("/{id}")
public ResponseEntity<IOU> getIOUById(@PathVariable Long id){
    Optional<IOU> iou = iouService.getIOU(UUID id);
    return iou.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

@PostMapping (produces = "application/json")
public IOU createIOU (@RequestBody IOU iou) {
    return iouService.createIOU(iou);
}

@PutMapping("/{id}")
public ResponseEntity<IOU> updateIOU(@PathVariable Long id, @RequestBody IOU iou) {
    Optional<IOU> updatedIOU = iouService.updateIOU(id, iou);
    return updatedIOU.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
}


@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteIOU(@PathVariable Long id) {
    iouService.deleteIOU(id);
    return ResponseEntity.noContent().build();
}


}