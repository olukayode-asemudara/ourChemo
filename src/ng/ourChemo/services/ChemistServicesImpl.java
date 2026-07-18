package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import java.util.List;

public class ChemistServicesImpl implements ChemistServices {

    private DrugRepository drugRepository;

    public ChemistServicesImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public void addDrug(Drug drug) {
        List<Drug> drugs = drugRepository.findAll();
        for (Drug existing : drugs) {
            if (existing.getName().equalsIgnoreCase(drug.getName())
                    && existing.getBrand().equalsIgnoreCase(drug.getBrand())) {
                throw new IllegalArgumentException("Drug already exists.");
            }
        }
        drugRepository.save(drug);
    }

    @Override
    public Drug getDrugById(int id) {
        return drugRepository.findById(id);
    }

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public void updateDrug(Drug drug) {
        drugRepository.update(drug);
    }

    @Override
    public void deleteDrug(int id) {
        drugRepository.delete(id);
    }

    @Override
    public void deleteAllDrugs() {
        drugRepository.deleteAll();
    }
}