package com.bbva.pisd.lib.r601;

import java.util.Map;

public interface PISDR601 {

	Map<String,Object> executeFindQuotationByReferenceAndPayrollId(String policyQuotaInternalId);
	Map<String,Object> executeFindQuotationDetailForPreEmision(String quotationId);
}
