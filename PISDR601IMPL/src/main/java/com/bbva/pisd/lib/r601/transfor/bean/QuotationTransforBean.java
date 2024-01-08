package com.bbva.pisd.lib.r601.transfor.bean;

import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.lib.r601.util.FunctionUtils;

import java.util.Date;
import java.util.Map;

public class QuotationTransforBean {

    public static QuotationEntity mapTransforSimulationEntity(Map<String,Object> mapQuotation){
        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setPolicyQuotaInternalId((String) mapQuotation.get("POLICY_QUOTA_INTERNAL_ID"));
        quotationEntity.setInsuranceSimulationId((String) mapQuotation.get("INSURANCE_SIMULATION_ID"));
        quotationEntity.setInsuranceCompanyQuotaId((String) mapQuotation.get("INSURANCE_COMPANY_QUOTA_ID"));
        quotationEntity.setQuoteDate(FunctionUtils.getDateTimeToString(mapQuotation,"QUOTE_DATE"));
        quotationEntity.setQuotaHmsDate((String) mapQuotation.get("QUOTA_HMS_DATE"));
        quotationEntity.setPolicyQuotaEndValidityDate( (String) mapQuotation.get("POLICY_QUOTA_END_VALIDITY_DATE"));
        quotationEntity.setCustomerId((String) mapQuotation.get("CUSTOMER_ID"));
        quotationEntity.setPolicyQuotaStatusType((String) mapQuotation.get("POLICY_QUOTA_STATUS_TYPE"));
        quotationEntity.setLastChangeBranchId((String) mapQuotation.get("LAST_CHANGE_BRANCH_ID"));
        quotationEntity.setSourceBranchId((String) mapQuotation.get("SOURCE_BRANCH_ID"));
        quotationEntity.setCreationUserId((String) mapQuotation.get("CREATION_USER_ID"));
        quotationEntity.setCreationDate((Date) mapQuotation.get("CREATION_DATE"));
        quotationEntity.setUserAuditId((String) mapQuotation.get("USER_AUDIT_ID"));
        quotationEntity.setAuditDate((Date) mapQuotation.get("AUDIT_DATE"));
        quotationEntity.setPersonalDocType((String) mapQuotation.get("PERSONAL_DOC_TYPE"));
        quotationEntity.setParticipantPersonalId((String) mapQuotation.get("PARTICIPANT_PERSONAL_ID"));
        quotationEntity.setPolicyQuotaCancellationDate((Date) mapQuotation.get("POLICY_QUOTA_CANCELLATION_DATE"));
        quotationEntity.setInsuredCustomerName((String) mapQuotation.get("INSURED_CUSTOMER_NAME"));
        quotationEntity.setClientLasName((String) mapQuotation.get("CLIENT_LAST_NAME"));
        quotationEntity.setIssuedReceiptNumber((String) mapQuotation.get("ISSUED_RECEIPT_NUMBER"));
        quotationEntity.setLastFourPanId((String) mapQuotation.get("LAST_FOUR_PAN_ID"));
        return quotationEntity;
    }

}
