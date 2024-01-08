package com.bbva.pisd.lib.r601;

import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	QuotationEntity executeFindQuotationByPolicyQuotaInternalId(String policyQuotaInternalId);

}
