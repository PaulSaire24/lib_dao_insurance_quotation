package com.bbva.pisd.lib.r601.impl;

import com.bbva.pisd.lib.r601.interfaces.QuotationDAO;
import com.bbva.pisd.lib.r601.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * The PISDR601Impl class...
 */
public class PISDR601Impl extends PISDR601Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR601Impl.class);

	private QuotationDAO quotationDAO;

	/**
	 * The execute method
	 * @executeFindQuotationByPolicyQuotaInternalId(policyQuotaInternalId)
	 */
	@Override
	public Map<String,Object> executeFindQuotationByReferenceAndPayrollId(String policyQuotaInternalId) {
		LOGGER.info(" :: executeFindQuotationByReferenceAndPayrollId [ policyQuotaInternalId :: {} ]", policyQuotaInternalId);
		Map<String,Object> result = this.quotationDAO.findQuotationByReferenceAndPayrollId(policyQuotaInternalId);
		LOGGER.info(" :: executeFindQuotationByReferenceAndPayrollId [ QuotationEntity :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	@Override
	public Map<String, Object> executeFindQuotationDetailForPreEmision(String quotationId) {
		LOGGER.info("PISDR601Impl :: executeFindQuotationDetailForPreEmision [ quotationId :: {} ]", quotationId);
		Map<String,Object> result = this.quotationDAO.findQuotationDetail(quotationId);
		LOGGER.info("PISDR601Impl :: executeFindQuotationDetailForPreEmision [ result :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	public void setQuotationDAO(QuotationDAO quotationDAO) {
		this.quotationDAO = quotationDAO;
	}
}
