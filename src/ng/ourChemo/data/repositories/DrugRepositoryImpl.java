package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {

    private static long count;

    private static List<Drug> drugs = new ArrayList<>();

    @Override
    public void save(Drug drug) {
        drug.setId(++count);
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
        for (int index = 0; index < drugs.size(); index++) {
            if (drugs.get(index).getId() == updatedDrug.getId()) {
                drugs.set(index, updatedDrug);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        drugs.clear();
        count = 0;
    }
}