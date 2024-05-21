package com.bbva.pisd.lib.r601.interfaces;

import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;

import java.util.Map;

public interface QuotationDAO {

    QuotationEntity findQuotationByReferenceAndPayrollId(String policyQuotaInternalId);
    Map<String,Object> findQuotationDetail(String quotationId);

}
