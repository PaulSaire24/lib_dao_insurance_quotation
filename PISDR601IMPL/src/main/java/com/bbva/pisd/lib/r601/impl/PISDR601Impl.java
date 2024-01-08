package com.bbva.pisd.lib.r601.impl;

import com.bbva.elara.domain.transaction.RequestHeaderParamsName;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.lib.r601.interfaces.QuotationDAO;
import com.bbva.pisd.lib.r601.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public QuotationEntity executeFindQuotationByPolicyQuotaInternalId(String policyQuotaInternalId) {
		LOGGER.info(" :: executeFindQuotationByPolicyQuotaInternalId [ policyQuotaInternalId :: {} ]", policyQuotaInternalId);
		QuotationEntity result = this.quotationDAO.findQuotationByPolicyQuotaInternalId(policyQuotaInternalId);
		LOGGER.info(" :: executeFindQuotationByPolicyQuotaInternalId [ QuotationEntity :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	public void setQuotationDAO(QuotationDAO quotationDAO) {
		this.quotationDAO = quotationDAO;
	}
}
