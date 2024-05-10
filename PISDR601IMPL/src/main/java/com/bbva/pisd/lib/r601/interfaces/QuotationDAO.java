package com.bbva.pisd.lib.r601.interfaces;

import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;

public interface QuotationDAO {

    QuotationEntity findQuotationByReferenceAndPayrollId(String policyQuotaInternalId) ;

}
