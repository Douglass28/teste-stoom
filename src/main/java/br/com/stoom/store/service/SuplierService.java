package br.com.stoom.store.service;

import br.com.stoom.store.exception.CategoryException;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.exception.SuplierException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Suplier;
import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.model.request.SuplierRequest;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.SuplierRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SuplierService {

    private SuplierRepository suplierRepository;


    public SuplierService(SuplierRepository suplierRepository){
        this.suplierRepository = suplierRepository;
    }

    public Suplier insert(SuplierRequest request){
        Suplier suplier = new Suplier(request);
        this.suplierRepository.save(suplier);

        return suplier;
    }

    public List<Suplier> getAllSuplier(){
       return this.suplierRepository.findAll();
    }

    public Optional<Suplier> getSuplierById(String id){
       Optional<Suplier> suplier = suplierRepository.findById(Long.valueOf(id));
        return suplier;
    }

    public Suplier findBySuplier(String id){
        try {
            Suplier suplier = suplierRepository.findBySuplierId(id);
            return suplier;
        }catch (SuplierException exception){
            throw new SuplierException("Suplier not found with ID: " + id);
        }

    }
    public Suplier updateSuplier(String id, SuplierRequest request){

        Suplier updateSuplier = this.suplierRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new SuplierException("Suplier not found with id: " + id));

        if (!request.getName().isEmpty()) updateSuplier.setName(request.getName());
        if (!request.getAddress().isEmpty()) updateSuplier.setAddress(request.getAddress());
        if (!request.getPhone().isEmpty()) updateSuplier.setPhone(request.getPhone());
        if (!request.getPhone().isEmpty()) updateSuplier.setPhone(request.getPhone());

        this.suplierRepository.save(updateSuplier);

        return updateSuplier;

    }

    public Suplier updateSuplierActive(String id, boolean active) {

        Suplier suplier = suplierRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new SuplierException("Suplier not found with id: " + id));

        suplier.setActive(active);
        return suplierRepository.save(suplier);
    }

    public Void deleteSuplier(String id){
        Suplier suplier = this.suplierRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new SuplierException("Suplier not found with id: " + id));

        this.suplierRepository.delete(suplier);

        return null;
    }
}
