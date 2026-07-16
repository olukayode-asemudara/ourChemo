package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.DispensedDrug;
import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository {

    private final List<DispensedDrug> dispensedDrugs = new ArrayList<>();

    @Override
    public void save(DispensedDrug dispensedDrug) {
        dispensedDrugs.add(dispensedDrug);
    }

    @Override
    public DispensedDrug findById(int id) {
        for (DispensedDrug dispensedDrug : dispensedDrugs) {
            if (dispensedDrug.getId() == id) {
                return dispensedDrug;
            }
        }
        return null;
    }

    @Override
    public List<DispensedDrug> findAll() {
        return new ArrayList<>(dispensedDrugs);
    }

    @Override
    public void delete(int id) {
        DispensedDrug dispensedDrug = findById(id);
        if (dispensedDrug != null) {
            dispensedDrugs.remove(dispensedDrug);
        }
    }

    @Override
    public void update(DispensedDrug updatedDispensedDrug) {
        for (int index = 0; index < dispensedDrugs.size(); index++) {
            if (dispensedDrugs.get(index).getId() == updatedDispensedDrug.getId()) {
                dispensedDrugs.set(index, updatedDispensedDrug);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        dispensedDrugs.clear();
    }
}