package com.bbva.pisd.lib.r601;


import com.bbva.pisd.dto.insurancedao.join.QuotationCustomerDTO;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinQuotationModDTO;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	QuotationCustomerDTO executeFindQuotationJoinByPolicyQuotaInternalId(String policyQuotaInternalId);

	QuotationJoinQuotationModDTO executeFindQuotationInfoByQuotationId(String policyQuotaInternalId);

}
