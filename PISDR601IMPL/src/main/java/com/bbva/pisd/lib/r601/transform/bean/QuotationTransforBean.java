package com.bbva.pisd.lib.r601.transform.bean;

import com.bbva.pisd.dto.insurancedao.entities.QuotationModEntity;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.dto.insurancedao.entities.InsuranceProductEntity;
import com.bbva.pisd.dto.insurancedao.entities.InsuranceBusinessEntity;
import com.bbva.pisd.dto.insurancedao.entities.ModalityEntity;
import com.bbva.pisd.dto.insurancedao.join.QuotationCustomerDTO;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinQuotationModDTO;

import java.math.BigDecimal;
import java.util.Map;

public class QuotationTransforBean {

    private QuotationTransforBean(){}

    public static QuotationCustomerDTO mapTransforSimulationEntity(Map<String,Object> mapQuotation){
        QuotationCustomerDTO quotationJoinInformation = new QuotationCustomerDTO();

        QuotationEntity quotationEntity = new QuotationEntity();
        QuotationModEntity quotationModEntity = new QuotationModEntity();
        InsuranceProductEntity insuranceProductEntity = new InsuranceProductEntity();
        InsuranceBusinessEntity insuranceBusinessEntity = new InsuranceBusinessEntity();

        quotationEntity.setInsuredCustomerName((String) mapQuotation.get("INSURED_CUSTOMER_NAME"));
        quotationEntity.setClientLasName((String) mapQuotation.get("CLIENT_LAST_NAME"));
        quotationEntity.setInsuranceCompanyQuotaId((String) mapQuotation.get("INSURANCE_COMPANY_QUOTA_ID"));
        quotationModEntity.setContactEmailDesc((String) mapQuotation.get("CONTACT_EMAIL_DESC"));
        quotationModEntity.setCustomerPhoneDesc((String) mapQuotation.get("CUSTOMER_PHONE_DESC"));
        quotationModEntity.setInsuranceProductId((BigDecimal) mapQuotation.get("INSURANCE_PRODUCT_ID"));
        quotationModEntity.setInsuranceModalityType((String) mapQuotation.get("INSURANCE_MODALITY_TYPE"));

        insuranceProductEntity.setInsuranceProductType((String) mapQuotation.get("INSURANCE_PRODUCT_TYPE"));

        insuranceBusinessEntity.setInsuranceBusinessName((String) mapQuotation.get("INSURANCE_BUSINESS_NAME"));

        quotationJoinInformation.setQuotation(quotationEntity);
        quotationJoinInformation.setQuotationMod(quotationModEntity);
        quotationJoinInformation.setInsuranceProduct(insuranceProductEntity);
        quotationJoinInformation.setInsuranceBusiness(insuranceBusinessEntity);
        return quotationJoinInformation;
    }

    public static QuotationJoinQuotationModDTO mapTransformQuotationModEntity(Map<String,Object> mapQuotation){
        QuotationJoinQuotationModDTO quotationInfo = new QuotationJoinQuotationModDTO();

        QuotationModEntity quotationModEntity = new QuotationModEntity();
        quotationModEntity.setInsuranceModalityType((String) mapQuotation.get("INSURANCE_MODALITY_TYPE"));
        quotationModEntity.setContactEmailDesc((String) mapQuotation.get("CONTACT_EMAIL_DESC"));
        quotationModEntity.setCustomerPhoneDesc((String) mapQuotation.get("CUSTOMER_PHONE_DESC"));

        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setQuoteDate((String) mapQuotation.get("QUOTE_DATE"));
        quotationEntity.setUserAuditId((String) mapQuotation.get("USER_AUDIT_ID"));
        quotationEntity.setCustomerId((String) mapQuotation.get("CUSTOMER_ID"));
        quotationEntity.setPolicyQuotaStatusType((String) mapQuotation.get("POLICY_QUOTA_STATUS_TYPE"));
        quotationEntity.setPersonalDocType((String) mapQuotation.get("PERSONAL_DOC_TYPE"));
        quotationEntity.setParticipantPersonalId((String) mapQuotation.get("PARTICIPANT_PERSONAL_ID"));
        quotationEntity.setRfqInternalId((String) mapQuotation.get("RFQ_INTERNAL_ID"));

        ModalityEntity modalityEntity = new ModalityEntity();
        modalityEntity.setInsuranceCompanyModalityId((String) mapQuotation.get("INSURANCE_COMPANY_MODALITY_ID"));
        modalityEntity.setInsurModalityDesc((String) mapQuotation.get("INSUR_MODALITY_DESC"));
        modalityEntity.setInsuranceModalityName((String) mapQuotation.get("INSURANCE_MODALITY_NAME"));

        quotationInfo.setQuotationMod(quotationModEntity);
        quotationInfo.setInsuranceProductType((String) mapQuotation.get("INSURANCE_PRODUCT_TYPE"));
        quotationInfo.setQuotation(quotationEntity);
        quotationInfo.setModality(modalityEntity);

        return quotationInfo;
    }

}
