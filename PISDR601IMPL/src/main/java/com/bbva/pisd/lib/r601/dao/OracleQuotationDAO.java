package com.bbva.pisd.lib.r601.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.pisd.dto.insurancedao.constants.PISDInsuranceErrors;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r601.interfaces.QuotationDAO;
import com.bbva.pisd.lib.r601.transform.bean.QuotationTransforBean;
import com.bbva.pisd.lib.r601.transform.map.QuotationTransforMap;
import com.bbva.pisd.lib.r601.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OracleQuotationDAO implements QuotationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQuotationDAO.class);
    private BaseDAO baseDAO;

    @Override
    public QuotationEntity findInternalIdAdnPayrollIdJoinByPolicyQuotaInternalId(String policyQuotaInternalId) {
        Map<String,Object> mapQuerySearchByPolicyQuotaInternalId = QuotationTransforMap.policyQuotaInternalIdTransforMap(policyQuotaInternalId);
        if (FunctionUtils.parametersIsValid(mapQuerySearchByPolicyQuotaInternalId,"POLICY_QUOTA_INTERNAL_ID")) {
            Operation operationDTO = Operation.Builder.an().withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp("PISD.SELECT_FIND_RFQ_INTERNAL_ID_AND_PAYROLL_ID_BY_POLICY_QUOTA_INTERNAL_ID")
                    .withParams(mapQuerySearchByPolicyQuotaInternalId)
                    .withIsContainsParameters(true)
                    .withIsForListQuery(false).build();
            Map<String,Object> mapQuotationEntity = (Map<String,Object>) this.baseDAO.executeQuery(operationDTO);
            mapQuotationEntity.forEach((key, value) -> LOGGER.info(" :: PISD.SELECT_FIND_RFQ_INTERNAL_ID_AND_PAYROLL_ID_BY_POLICY_QUOTA_INTERNAL_ID :: [ Key {} with value: {} ]", key, value));
            LOGGER.info(" :: mapQuotationEntity : {}",mapQuotationEntity);
            return QuotationTransforBean.mapTransforQuotationEntity(mapQuotationEntity);
        }
        LOGGER.info(" :: Parameters invalidate [ policyQuotaInternalId : {} ]",policyQuotaInternalId);
        throw new BusinessException(PISDInsuranceErrors.PARAMETERS_INVALIDATE.getAdviceCode(), false, PISDInsuranceErrors.QUERY_EMPTY_RESULT.getMessage());
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
}
