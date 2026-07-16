package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.reopsitories.DrugRepository;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {

    private final List<Drug> drugs = new ArrayList<>();

    @Override
    public void save(Drug drug) {
        drugs.add(drug);
    }

    @Override
    public Drug findById(int id) {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        return new ArrayList<>(drugs);
    }

    @Override
    public void delete(int id) {
        Drug drug = findById(id);
        if (drug != null) {
            drugs.remove(drug);
        }
    }

    @Override
    public void update(Drug updatedDrug) {
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getId() == updatedDrug.getId()) {
                drugs.set(i, updatedDrug);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        drugs.clear();
    }
}