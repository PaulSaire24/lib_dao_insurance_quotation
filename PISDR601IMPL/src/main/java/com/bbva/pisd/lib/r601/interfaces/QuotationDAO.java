package com.bbva.pisd.lib.r601.interfaces;

import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinQuotationModDTO;

public interface QuotationDAO {

    QuotationJoinCustomerInformationDTO findQuotationByPolicyQuotaInternalId(String policyQuotaInternalId) ;
    QuotationJoinQuotationModDTO findQuotationJoinQuotationMod(String quotationInternalId);

}
