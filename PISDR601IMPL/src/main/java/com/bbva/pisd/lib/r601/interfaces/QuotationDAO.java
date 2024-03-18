package com.bbva.pisd.lib.r601.interfaces;

import com.bbva.pisd.dto.insurancedao.join.QuotationCustomerDTO;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinQuotationModDTO;

public interface QuotationDAO {

    QuotationCustomerDTO findQuotationByPolicyQuotaInternalId(String policyQuotaInternalId) ;
    QuotationJoinQuotationModDTO findQuotationJoinQuotationMod(String quotationInternalId);

}
