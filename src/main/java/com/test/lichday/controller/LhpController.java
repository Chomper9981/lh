package com.test.lichday.controller;


import com.test.lichday.model.Lhp;
import com.test.lichday.model.ResponseObject;
import com.test.lichday.repository.LhpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Lhp")
public class LhpController {

    @Autowired
    private LhpRepository repository;
    @GetMapping("")
    List<Lhp> getAllLhp(){
        //http://localhost:8080/api/v1/Lhp
        return repository.findAll();//database
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        //format: data, message, status
        Optional<Lhp> foundLhp = repository.findById(id);
        return foundLhp.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "tim thay thanh cong lhp", foundLhp)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "khong tim thay lhp "+id, "")
            );
        }


        //insert
    //postman: raw, json
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertLhp(@RequestBody Lhp newLhp){
        //check Lhp da bi chiem hay chua
        List<Lhp> foundToa = repository.findByToa(newLhp.getToa().trim());
        List<Lhp> foundPhong = repository.findByPhong(newLhp.getPhong().trim());
        List<Lhp> foundThu = repository.findByThu(newLhp.getThu());
        List<Lhp> foundKip = repository.findByKip(newLhp.getKip());
        if(foundToa.size() > 0 && foundPhong.size() > 0 && foundThu.size() > 0 && foundKip.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "da co nguoi dang ki lhp nay", "")
                        );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "insert thanh cong", repository.save(newLhp))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateLhp(@RequestBody Lhp newLhp, @PathVariable Long id){
        List<Lhp> foundToa = repository.findByToa(newLhp.getToa().trim());
        List<Lhp> foundPhong = repository.findByPhong(newLhp.getPhong().trim());
        List<Lhp> foundThu = repository.findByThu(newLhp.getThu());
        List<Lhp> foundKip = repository.findByKip(newLhp.getKip());
        if(foundToa.size() > 0 && foundPhong.size() > 0 && foundThu.size() > 0 && foundKip.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "da co nguoi dang ki lhp nay", "")
            );
        }
        Lhp updateLhp = repository.findById(id)
                .map(Lhp -> {
                    Lhp.setLhpName(newLhp.getLhpName());
                    Lhp.setToa(newLhp.getToa());
                    Lhp.setPhong(newLhp.getPhong());
                    Lhp.setThu(newLhp.getThu());
                    Lhp.setKip(newLhp.getKip());
                    return repository.save(Lhp);
                }).orElseGet(() -> {
                    newLhp.setId(id);
                    return repository.save(newLhp);
                });
        //check Lhp da bi chiem hay chua

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "sua thanh cong", updateLhp)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteLhp(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "xoa thanh cong", "")
            );

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "khong tim thay lop hoc phan", "")
        );

    }

}
