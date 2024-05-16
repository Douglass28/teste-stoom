package br.com.stoom.store.service;

import br.com.stoom.store.model.Suplier;
import br.com.stoom.store.model.request.SuplierRequest;
import br.com.stoom.store.repository.SuplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuplierServiceTest {

    @Mock
    private SuplierRepository suplierRepository;

    @InjectMocks
    private SuplierService suplierService;

    @Test
    public void testInsertSuplier() {

        SuplierRequest request = new SuplierRequest("Fornecedor Teste", "Rua martins goias", "12345678900", "1" );

        Suplier expectedSuplier = new Suplier(request);
        when(suplierRepository.save(Mockito.any(Suplier.class))).thenReturn(expectedSuplier);

        Suplier savedSuplier = suplierService.insert(request);

        assertThat(savedSuplier).isEqualTo(expectedSuplier);

        Mockito.verify(suplierRepository).save(expectedSuplier);
    }

    @Test
    public void testUpdateSuplier() throws Exception {

        String suplierId = "1";
        String oldName = "Old Supplier Name";
        String oldAddress = "Old Address";
        String oldPhone = "1198765432";
        boolean isActive = true;

        Suplier existingSuplier = new Suplier(oldName, oldAddress, oldPhone, suplierId,isActive);

        String newName = "Updated Supplier Name";
        String newAddress = oldAddress;
        String newPhone = "3322114455";
        String newSuplier = suplierId;
        SuplierRequest updateRequest = new SuplierRequest(newName, newAddress, newPhone, newSuplier);

        Mockito.when(suplierRepository.findById(Long.valueOf(suplierId))).thenReturn(Optional.of(existingSuplier));
        Mockito.when(suplierRepository.save(Mockito.any(Suplier.class))).thenReturn(existingSuplier);

        suplierService.updateSuplier(suplierId, updateRequest);

        Mockito.verify(suplierRepository).findById(Long.valueOf(suplierId));
        Mockito.verify(suplierRepository).save(existingSuplier);
    }
}