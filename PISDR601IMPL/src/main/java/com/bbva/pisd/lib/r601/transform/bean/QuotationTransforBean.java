package com.bbva.pisd.lib.r601.transform.bean;

import com.bbva.pisd.dto.insurancedao.entities.InsuranceBusinessEntity;
import com.bbva.pisd.dto.insurancedao.entities.InsuranceProductEntity;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.dto.insurancedao.entities.QuotationModEntity;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;
import com.bbva.pisd.lib.r601.util.FunctionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class QuotationTransforBean {

    public static QuotationJoinCustomerInformationDTO mapTransforSimulationEntity(Map<String,Object> mapQuotation){
        QuotationJoinCustomerInformationDTO quotationJoinInformation = new QuotationJoinCustomerInformationDTO();

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

}
