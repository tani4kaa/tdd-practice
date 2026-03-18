package edu.pro;/*
  @author tanus
  @project lab1
  @class CompanyServiceImplTest
  @version 1.0.0
  @since 18.03.2026 - 17.29
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CompanyServiceImplTest {

    private ICompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl();
    }

    @Test
    void getTopLevelParent_shouldReturnNull_whenChildIsNull() {
        Company result = companyService.getTopLevelParent(null);
        assertNull(result);
    }

    @Test
    void getTopLevelParent_shouldReturnSameCompany_whenCompanyHasNoParent() {
        Company company = new Company(null, 10);

        Company result = companyService.getTopLevelParent(company);

        assertEquals(company, result);
    }

    @Test
    void getTopLevelParent_shouldReturnTopLevelParent_whenCompanyHasOneParent() {
        Company parent = new Company(null, 100);
        Company child = new Company(parent, 20);

        Company result = companyService.getTopLevelParent(child);

        assertEquals(parent, result);
    }

    @Test
    void getTopLevelParent_shouldReturnTopLevelParent_whenCompanyHasSeveralParents() {
        Company top = new Company(null, 100);
        Company middle = new Company(top, 50);
        Company child = new Company(middle, 20);

        Company result = companyService.getTopLevelParent(child);

        assertEquals(top, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldReturnZero_whenCompanyIsNull() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(null, List.of());
        assertEquals(0, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldReturnOwnEmployees_whenCompaniesListIsEmpty() {
        Company company = new Company(null, 10);

        long result = companyService.getEmployeeCountForCompanyAndChildren(company, List.of());

        assertEquals(10, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldReturnOwnEmployees_whenCompanyHasNoChildren() {
        Company company = new Company(null, 15);

        long result = companyService.getEmployeeCountForCompanyAndChildren(company, List.of(company));

        assertEquals(15, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldIncludeOneDirectChild() {
        Company parent = new Company(null, 10);
        Company child = new Company(parent, 5);

        List<Company> companies = List.of(parent, child);

        long result = companyService.getEmployeeCountForCompanyAndChildren(parent, companies);

        assertEquals(15, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldIncludeSeveralDirectChildren() {
        Company parent = new Company(null, 10);
        Company child1 = new Company(parent, 5);
        Company child2 = new Company(parent, 15);

        List<Company> companies = List.of(parent, child1, child2);

        long result = companyService.getEmployeeCountForCompanyAndChildren(parent, companies);

        assertEquals(30, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldIncludeNestedChildren() {
        Company parent = new Company(null, 10);
        Company child = new Company(parent, 5);
        Company grandChild = new Company(child, 3);

        List<Company> companies = List.of(parent, child, grandChild);

        long result = companyService.getEmployeeCountForCompanyAndChildren(parent, companies);

        assertEquals(18, result);
    }

    @Test
    void getEmployeeCountForCompanyAndChildren_shouldCountOnlyCurrentBranch() {
        Company top = new Company(null, 100);
        Company child = new Company(top, 20);
        Company grandChild = new Company(child, 5);
        Company anotherChild = new Company(top, 30);

        List<Company> companies = List.of(top, child, grandChild, anotherChild);

        long result = companyService.getEmployeeCountForCompanyAndChildren(child, companies);

        assertEquals(25, result);
    }
}