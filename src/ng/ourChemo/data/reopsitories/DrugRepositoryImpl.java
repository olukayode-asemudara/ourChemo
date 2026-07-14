package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {

    private final List<Drug> drugs = new ArrayList<>();

    @Override
    public void save(Drug drug) {
        if (drug == null) {
            throw new IllegalArgumentException("Drug cannot be null.");
        }
        for (Drug existingDrug : drugs) {
            if (existingDrug.getId() == drug.getId()) {
                throw new IllegalStateException(
                        "Drug with id " + drug.getId() + " already exists."
                );
            }
        }
        drugs.add(drug);
    }

    @Override
    public Drug findById(int id) {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        throw new IllegalArgumentException(
                "Drug with id " + id + " does not exist.");
    }

    @Override
    public List<Drug> findAll() {
        return new ArrayList<>(drugs);
    }

    @Override
    public void delete(int id) {
        Drug drug = findById(id);
        drugs.remove(drug);
    }

    @Override
    public void update(Drug updatedDrug) {
        if (updatedDrug == null) {
            throw new IllegalArgumentException("Updated drug cannot be null.");
        }
        Drug existingDrug = findById(updatedDrug.getId());
        existingDrug.setName(updatedDrug.getName());
        existingDrug.setPrice(updatedDrug.getPrice());
        existingDrug.setBrand(updatedDrug.getBrand());
        existingDrug.setExpiryDate(updatedDrug.getExpiryDate());
    }

    @Override
    public void deleteAll() {
        if (drugs.isEmpty()) {
            throw new IllegalStateException("Repository is already empty.");
        }
        drugs.clear();
    }
}