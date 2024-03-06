package com.bbva.pisd.lib.r601;


import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinQuotationModDTO;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	QuotationJoinCustomerInformationDTO executeFindQuotationJoinByPolicyQuotaInternalId(String policyQuotaInternalId);

	QuotationJoinQuotationModDTO executeFindQuotationInformationByInternalQuotation(String policyQuotaInternalId);

}
