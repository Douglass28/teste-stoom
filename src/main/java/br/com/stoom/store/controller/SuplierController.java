package br.com.stoom.store.controller;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Suplier;
import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.model.request.SuplierRequest;
import br.com.stoom.store.model.request.UpdateActiveRequest;
import br.com.stoom.store.service.CategoryService;
import br.com.stoom.store.service.SuplierService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/suplier")
public class SuplierController {


    private SuplierService suplierService;

    public SuplierController(SuplierService suplierService){
        this.suplierService = suplierService;
    }

    @PostMapping
    public ResponseEntity<Suplier> insertSuplier(@RequestBody SuplierRequest suplierRequest){
         Suplier suplier =  suplierService.insert(suplierRequest);
         return ResponseEntity.ok().body(suplier);
    }

    @GetMapping
    public ResponseEntity<List<Suplier>> getAllSuplier(){
        List<Suplier> listSuplier =  this.suplierService.getAllSuplier();
        return ResponseEntity.ok().body(listSuplier);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Suplier>> getSuplierById(@PathVariable("id") String id){
        Optional<Suplier> suplier =  this.suplierService.getSuplierById(id);
        return ResponseEntity.ok().body(suplier);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suplier> updateSuplier(@PathVariable("id") String id,
                                                   @RequestBody SuplierRequest suplierRequest){
        Suplier updateSuplier = this.suplierService.updateSuplier(id, suplierRequest);
        return ResponseEntity.ok().body(updateSuplier);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Suplier> updateSuplierActive(@PathVariable("id") String id, @RequestBody UpdateActiveRequest updateActiveRequest) {
        Suplier suplier = suplierService.updateSuplierActive(id, updateActiveRequest.isActive());
        return ResponseEntity.ok().body(suplier);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSuplier(@PathVariable("id") String id){
        this.suplierService.deleteSuplier(id);
        return ResponseEntity.noContent().build();

    }


}
