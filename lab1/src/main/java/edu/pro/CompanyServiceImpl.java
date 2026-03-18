package edu.pro;/*
  @author tanus
  @project lab1
  @class CompanyServiceImpl
  @version 1.0.0
  @since 18.03.2026 - 17.32
*/

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }

        Company current = child;
        while (current.getParent() != null) {
            current = current.getParent();
        }

        return current;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null) {
            return 0;
        }

        long total = company.getEmployeesCount();

        if (companies == null || companies.isEmpty()) {
            return total;
        }

        for (Company current : companies) {
            if (current != null && current.getParent() == company) {
                total += getEmployeeCountForCompanyAndChildren(current, companies);
            }
        }

        return total;
    }
}