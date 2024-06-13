package com.bbva.pisd.lib.r601;



import java.util.Map;

/**
 * The  interface PISDR601 class...
 */
public interface PISDR601 {

	/**
	 * The execute method...
	 */
	Map<String,Object> executeFindQuotationByReferenceAndPayrollId(String policyQuotaInternalId);
	Map<String,Object> executeFindQuotationDetailForPreEmision(String quotationId);
}
