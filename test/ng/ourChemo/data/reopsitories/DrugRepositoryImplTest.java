package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryImplTest {
    private DrugRepository repository;
    private Drug myDrug;

    @BeforeEach
    public void setUp(){
        repository = new DrugRepositoryImpl();
        myDrug = new Drug();
    }

    @Test
    public void testEmptyRepository(){
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testSaveDrug() {
        repository.save(myDrug);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testDeleteDrug() {
        myDrug.setId(1);
        repository.save(myDrug);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testClearRepository(){
        repository.save(myDrug);
        Drug myDrug2 = new Drug();
        myDrug2.setId(1);
        repository.save(myDrug2);
        assertEquals(2, repository.findAll().size());
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testTwoDrugsSameID(){
        repository.save(myDrug);
        Drug myDrug2 = new Drug();
        assertThrows(IllegalStateException.class, ()-> repository.save(myDrug2));
    }

    @Test
    public void testFindDrugByID(){
        repository.save(myDrug);
        repository.findById(0);
        assertEquals(0, myDrug.getId());
    }

    @Test
    public void testFIndDrugByIdNotExist(){
        repository.save(myDrug);
        assertThrows(IllegalArgumentException.class, ()-> repository.findById(1));
    }
}
