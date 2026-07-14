package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.DispensedDrug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository{
    private List<DispensedDrug> DispensedDrugs = new ArrayList<>();


    @Override
    public void save(DispensedDrug DispensedDrug) {
        if (DispensedDrug == null) {
            throw new IllegalArgumentException("DispensedDrug cannot be null.");
        }
        for (DispensedDrug dispensedDrug : DispensedDrugs) {
            if (DispensedDrug.getId() == DispensedDrug.getId()) {
                throw new IllegalStateException(
                        "DispensedDrug with id " + DispensedDrug.getId() + " already exists."
                );
            }
        }
        DispensedDrugs.add(DispensedDrug);
    }

    @Override
    public DispensedDrug findById(int id) {
        for (DispensedDrug DispensedDrug : DispensedDrugs) {
            if (DispensedDrug.getId() == id) {
                return DispensedDrug;
            }
        }
        throw new IllegalArgumentException(
                "DispensedDrug with id " + id + " does not exist.");
    }

    @Override
    public List<DispensedDrug> findAll() {
        return new ArrayList<>(DispensedDrugs);
    }

    @Override
    public void delete(int id) {
        DispensedDrug DispensedDrug = findById(id);
        DispensedDrugs.remove(DispensedDrug);
    }

    @Override
    public void update(DispensedDrug updatedDispensedDrug) {
        if (updatedDispensedDrug == null) {
            throw new IllegalArgumentException("Updated DispensedDrug cannot be null.");
        }
        DispensedDrug existingDispensedDrug = findById(updatedDispensedDrug.getId());
    }

    @Override
    public void deleteAll() {
        if (DispensedDrugs.isEmpty()) {
            throw new IllegalStateException("Repository is already empty.");
        }
        DispensedDrugs.clear();
    }
}
