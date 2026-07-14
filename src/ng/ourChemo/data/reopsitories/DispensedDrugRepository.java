package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.DispensedDrug;

import java.util.List;

public interface DispensedDrugRepository {
    void save(DispensedDrug DispensedDrug);
    DispensedDrug findById(int id);
    List<DispensedDrug> findAll();
    void delete(int id);
    void update(DispensedDrug updatedDispensedDrug);
    void deleteAll();
}
