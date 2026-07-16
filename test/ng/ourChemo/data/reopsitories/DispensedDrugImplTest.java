package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.DispensedDrug;
import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DispensedDrugImplTest {

    private DispensedDrugsRepository repository;
    private DispensedDrug dispensedDrug;
    private Drug drug;

    @BeforeEach
    public void setUp(){
        repository = new DispensedDrugsRepositoryImpl();
        dispensedDrug = new DispensedDrug();
        drug = new Drug();
    }

    @Test
    public void testEmptyRepository(){;
        assertEquals(0,repository.findAll().size());
    }

    @Test
    public void testSaveDrug() {
        dispensedDrug.setDrug(drug);
        repository.save(dispensedDrug);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testDeleteDrug() {
        dispensedDrug.setDrug(drug);
        repository.save(dispensedDrug);
        repository.delete(0);
        assertEquals(0, repository.findAll().size());
    }
}
