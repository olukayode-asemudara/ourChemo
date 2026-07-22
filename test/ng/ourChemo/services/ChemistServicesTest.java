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
    public void testAddDrugGeneratesIDAutomatically(){
        chemist.addDrug(drug);
        System.out.println(drug.getId());
        assertEquals(1, drug.getId());
    }

    @Test
    public void testGetDrugById(){
        chemist.addDrug(drug);
        Drug foundDrug = chemist.getDrugById(1);
        assertEquals("Paracetamol", foundDrug.getName());
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
    public void testDeleteAllDrugs(){
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Ibuprofen");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        chemist.deleteAllDrugs();
        assertEquals(0, chemist.getAllDrugs().size());
    }

    @Test
    public void testAddDifferentDrugs(){
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Ibuprofen");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
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

    @Test
    public void testAddSameDrugDifferentBrand(){
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Paracetamol");
        secondDrug.setBrand("GSK");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
    }

    @Test
    public void testAddDifferentDrugSameBrand(){
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Vitamin C");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
    }

    @Test
    public void testUpdateDrug(){
        chemist.addDrug(drug);
        drug.setBrand("GSK");
        chemist.updateDrug(drug);
        assertEquals("GSK", chemist.getDrugById(1).getBrand());
    }
}
