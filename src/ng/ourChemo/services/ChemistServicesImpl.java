package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;

import java.util.List;

public class ChemistServicesImpl implements ChemistServices {

    private DrugRepository drugRepository;

    private AuthServices authServices;

    public ChemistServicesImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public void setAuthServices(AuthServices authServices) {
        this.authServices = authServices;
    }

    private void validateLogin() {
        if (authServices == null || !authServices.isLoggedIn()) {
            throw new IllegalStateException("Please login first.");
        }
    }

    @Override
    public void addDrug(Drug drug) {
        validateLogin();
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
        validateLogin();
        return drugRepository.findById(id);
    }

    @Override
    public List<Drug> getAllDrugs() {
        validateLogin();
        return drugRepository.findAll();
    }

    @Override
    public void updateDrug(Drug drug) {
        validateLogin();
        drugRepository.update(drug);
    }

    @Override
    public void deleteDrug(int id) {
        validateLogin();
        drugRepository.delete(id);
    }

    @Override
    public void deleteAllDrugs() {
        validateLogin();
        drugRepository.deleteAll();
    }
}