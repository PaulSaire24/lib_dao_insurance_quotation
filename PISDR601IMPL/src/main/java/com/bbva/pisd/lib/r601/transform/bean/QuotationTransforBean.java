package com.bbva.pisd.lib.r601.transform.bean;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import java.util.Map;

public class QuotationTransforBean {

    private QuotationTransforBean(){}

    public static QuotationEntity mapTransforQuotationEntity(Map<String,Object> mapQuotation){

        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setRfqInternalId((String) mapQuotation.get("RFQ_INTERNAL_ID"));
        quotationEntity.setPayrollId((String) mapQuotation.get("PAYROLL_ID"));

        return quotationEntity;
    }

}
