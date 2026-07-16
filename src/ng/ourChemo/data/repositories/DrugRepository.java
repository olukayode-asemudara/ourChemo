package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

import java.util.List;

public interface DrugRepository {

    void save(Drug drug);
    Drug findById(int id);
    List<Drug> findAll();
    void delete(int id);
    void update(Drug drug);

    void deleteAll();
}
