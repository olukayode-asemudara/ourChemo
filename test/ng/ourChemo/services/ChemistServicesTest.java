package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChemistServicesTest {

    private DrugRepository repository;
    private Drug drug;
    private ChemistServices chemist;

    @BeforeEach
    public void setUp(){
        repository = new DrugRepositoryImpl();
        repository.deleteAll();
        drug = new Drug();
        drug.setName("Paracetamol");
        drug.setBrand("Emzor");
        chemist = new ChemistServicesImpl(repository);
    }

    @Test
    public void testEmptyDrugRepository(){
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void testAddDrug() {
        chemist.addDrug(drug);
        assertEquals(1, chemist.getAllDrugs().size());
    }

    @Test
    void testDeleteDrug() {
        chemist.addDrug(drug);
        chemist.deleteDrug(1);
        assertEquals(0, chemist.getAllDrugs().size());
    }

    @Test
    void testAddDrugWithSameName(){
        chemist.addDrug(drug);
        Drug mydrug = new Drug();
        mydrug.setName("Paracetamol");
        mydrug.setBrand("Emzor");
        assertThrows(
                IllegalArgumentException.class,
                () -> chemist.addDrug(mydrug)
        );
    }
}
