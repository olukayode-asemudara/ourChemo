package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;

import java.util.List;

public interface ChemistServices {
    void addDrug(Drug drug);
    Drug getDrugById(int id);
    List<Drug> getAllDrugs();
    void updateDrug(Drug drug);
    void deleteDrug(int id);
    void deleteAllDrugs();
    void setAuthServices(AuthServices authServices);
}