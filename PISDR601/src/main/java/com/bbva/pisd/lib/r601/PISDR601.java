package com.bbva.pisd.lib.r601;


import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	QuotationJoinCustomerInformationDTO executeFindQuotationJoinByPolicyQuotaInternalId(String policyQuotaInternalId);

}
