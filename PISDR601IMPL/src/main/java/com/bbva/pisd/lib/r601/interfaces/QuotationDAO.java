package com.bbva.pisd.lib.r601.interfaces;


import java.util.Map;

public interface QuotationDAO {

    Map<String,Object> findQuotationByReferenceAndPayrollId(String policyQuotaInternalId);
    Map<String,Object> findQuotationDetail(String quotationId);

}
