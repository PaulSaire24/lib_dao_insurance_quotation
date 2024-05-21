package com.bbva.pisd.lib.r601;


import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;

import java.util.Map;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	QuotationEntity executeFindQuotationByReferenceAndPayrollId(String policyQuotaInternalId);
	Map<String,Object> executeFindQuotationDetailForPreEmision(String quotationId);
}
