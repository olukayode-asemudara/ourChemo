package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;
import ng.ourChemo.data.models.Drug;

import java.util.List;

public interface DispensedDrugsRepository {

    void save(DispensedDrug DispensedDrug);

    DispensedDrug findById(int id);
    List<DispensedDrug> findAll();
    void delete(int id);
    void update(Drug drug);

    void update(DispensedDrug updatedDispensedDrug);

    void deleteAll();
}
