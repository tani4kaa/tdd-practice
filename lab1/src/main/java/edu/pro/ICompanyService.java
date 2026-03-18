package edu.pro;/*
  @author tanus
  @project lab1
  @class ICompanyService
  @version 1.0.0
  @since 18.03.2026 - 17.29
*/

import java.util.List;

public interface ICompanyService {

    Company getTopLevelParent(Company child);

    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}