package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChemistServicesTest {

    private DrugRepository repository;
    private Drug drug;
    private ChemistServices chemist;
    private AuthServices authService;

    @BeforeEach
    public void setUp() {
        repository = new DrugRepositoryImpl();
        repository.deleteAll();
        authService = new AuthServicesImpl();
        authService.registerUser(
                "chemist",
                "Olukayode Kay",
                "1234"
        );
        authService.login(
                "chemist",
                "1234"
        );
        chemist = new ChemistServicesImpl(repository);
        chemist.setAuthServices(authService);
        drug = new Drug();
        drug.setName("Paracetamol");
        drug.setBrand("Emzor");
    }

    @Test
    public void shouldStartWithEmptyDrugRepository() {
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToGenerateDrugIdWhenDrugIsAdded() {
        chemist.addDrug(drug);
        assertEquals(1, drug.getId());
    }

    @Test
    public void shouldAllowLoggedInChemistToFindDrugById() {
        chemist.addDrug(drug);
        Drug foundDrug = chemist.getDrugById(1);
        assertEquals("Paracetamol", foundDrug.getName());
    }

    @Test
    public void shouldAllowLoggedInChemistToAddDrug() {
        chemist.addDrug(drug);
        assertEquals(1, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToDeleteDrug() {
        chemist.addDrug(drug);
        chemist.deleteDrug(1);
        assertEquals(0, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToDeleteAllDrugs() {
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Ibuprofen");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        chemist.deleteAllDrugs();
        assertEquals(0, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToAddDifferentDrugs() {
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Ibuprofen");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldThrowExceptionWhenLoggedInChemistAddsDuplicateDrug() {
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Paracetamol");
        secondDrug.setBrand("Emzor");
        assertThrows(
                IllegalArgumentException.class,
                () -> chemist.addDrug(secondDrug)
        );
    }

    @Test
    public void shouldAllowLoggedInChemistToAddDrugWithSameNameButDifferentBrand() {
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Paracetamol");
        secondDrug.setBrand("GSK");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToAddDrugWithDifferentNameButSameBrand() {
        chemist.addDrug(drug);
        Drug secondDrug = new Drug();
        secondDrug.setName("Vitamin C");
        secondDrug.setBrand("Emzor");
        chemist.addDrug(secondDrug);
        assertEquals(2, chemist.getAllDrugs().size());
    }

    @Test
    public void shouldAllowLoggedInChemistToUpdateDrug() {
        chemist.addDrug(drug);
        drug.setBrand("GSK");
        chemist.updateDrug(drug);
        assertEquals("GSK", chemist.getDrugById(1).getBrand());
    }

    @Test
    public void shouldPreventLoggedOutChemistFromAddingDrug() {
        authService.logout("chemist");
        assertThrows(
                IllegalStateException.class,
                () -> chemist.addDrug(drug)
        );
    }
}