package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryImplTest {

    private DrugRepositoryImpl repository;
    private Drug myDrug;

    @BeforeEach
    public void setUp() {
        repository = new DrugRepositoryImpl();
        myDrug = new Drug();
    }

    @Test
    public void testEmptyRepository() {
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testSaveDrug() {
        myDrug.setId(1);
        repository.save(myDrug);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindDrugByID() {
        myDrug.setId(1);
        repository.save(myDrug);
        Drug foundDrug = repository.findById(1);
        assertNotNull(foundDrug);
        assertEquals(1, foundDrug.getId());
    }

    @Test
    public void testFindDrugByInvalidID() {
        Drug foundDrug = repository.findById(100);
        assertNull(foundDrug);
    }

    @Test
    public void testDeleteDrug() {
        myDrug.setId(1);
        repository.save(myDrug);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
        assertNull(repository.findById(1));
    }

    @Test
    public void testDeleteNonExistingDrug() {
        myDrug.setId(1);
        repository.save(myDrug);
        repository.delete(10);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testUpdateDrug() {
        myDrug.setId(1);
        repository.save(myDrug);

        Drug updatedDrug = new Drug();
        updatedDrug.setId(1);

        repository.update(updatedDrug);

        assertEquals(1, repository.findById(1).getId());
    }

    @Test
    public void testDeleteAllDrugs() {
        myDrug.setId(1);

        Drug myDrug2 = new Drug();
        myDrug2.setId(2);

        repository.save(myDrug);
        repository.save(myDrug2);
        assertEquals(2, repository.findAll().size());

        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testFindAllReturnsCopy() {
        myDrug.setId(1);
        repository.save(myDrug);
        repository.findAll().clear();
        assertEquals(1, repository.findAll().size());
    }
}