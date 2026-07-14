package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryImplTest {

    private DrugRepository repository;
    private Drug drug;

    @BeforeEach
    public void setUp() {
        repository = new DrugRepositoryImpl();
        drug = new Drug(1, "Paracetamol", "Emzor");
        drug.setExpiryDate(LocalDate.of(2028, 12, 31));
    }

    @Test
    public void testThatICanSaveADrug() {
        repository.save(drug);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testThatICanFindDrugById() {
        repository.save(drug);
        Drug foundDrug = repository.findById(1);
        assertNotNull(foundDrug);
        assertEquals(1, foundDrug.getId());
        assertEquals("Paracetamol", foundDrug.getName());
        assertEquals("Emzor", foundDrug.getBrand());
    }

    @Test
    public void testThatICanDeleteDrugById() {
        repository.save(drug);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testThatICanUpdateDrug() {
        repository.save(drug);
        Drug updatedDrug = new Drug(1, "Ibuprofen", "M&B");
        updatedDrug.setExpiryDate(LocalDate.of(2030, 1, 1));
        repository.update(updatedDrug);
        Drug foundDrug = repository.findById(1);
        assertNotNull(foundDrug);
        assertEquals(1, foundDrug.getId());
        assertEquals("Ibuprofen", foundDrug.getName());
        assertEquals("M&B", foundDrug.getBrand());
        assertEquals(LocalDate.of(2030, 1, 1), foundDrug.getExpiryDate());
    }

    @Test
    public void testFindAllReturnsAllDrugs() {
        repository.save(drug);
        Drug drug2 = new Drug(2, "Ibucap", "M&B");
        repository.save(drug2);
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void testDeleteAllDrugs() {
        repository.save(drug);
        Drug drug2 = new Drug(2, "Ibucap", "M&B");
        repository.save(drug2);
        assertEquals(2, repository.findAll().size());
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testDeleteNonExistingDrugThrowsException() {
        repository.save(drug);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.delete(2)
        );
        assertEquals("Drug with id 2 does not exist.", exception.getMessage());
    }

    @Test
    public void testFindNonExistingDrugThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.findById(10)
        );
        assertEquals("Drug with id 10 does not exist.", exception.getMessage());
    }

    @Test
    public void testUpdateNonExistingDrugThrowsException() {
        Drug updatedDrug = new Drug(1, "Ibuprofen", "M&B");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(updatedDrug)
        );
        assertEquals("Drug with id 1 does not exist.", exception.getMessage());
    }

    @Test
    public void testSaveNullDrugThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.save(null)
        );
        assertEquals("Drug cannot be null.", exception.getMessage());
    }

    @Test
    public void testAddDrugWithExistingIdThrowsException() {
        repository.save(drug);
        Drug duplicateDrug = new Drug(1, "PCM", "Emzor");
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> repository.save(duplicateDrug)
        );
        assertEquals(
                "Drug with id 1 already exists.",
                exception.getMessage()
        );
    }

    @Test
    public void testDeleteAllFromEmptyRepositoryThrowsException() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> repository.deleteAll()
        );
        assertEquals(
                "Repository is already empty.",
                exception.getMessage()
        );
    }
}