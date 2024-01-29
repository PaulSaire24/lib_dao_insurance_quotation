package com.bbva.pisd.lib.r601.interfaces;

import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;

public interface QuotationDAO {

    QuotationJoinCustomerInformationDTO findQuotationByPolicyQuotaInternalId(String policyQuotaInternalId) ;

}
