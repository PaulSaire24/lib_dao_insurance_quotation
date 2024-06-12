package com.bbva.pisd.lib.r601.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.pisd.dto.insurancedao.constants.PISDColumn;
import com.bbva.pisd.dto.insurancedao.constants.PISDInsuranceErrors;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import com.bbva.pisd.lib.r601.interfaces.QuotationDAO;
import com.bbva.pisd.lib.r601.transform.map.QuotationTransforMap;
import com.bbva.pisd.lib.r601.util.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

public class OracleQuotationDAO implements QuotationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQuotationDAO.class);
    private BaseDAO baseDAO;

    @Override
    public Map<String,Object> findQuotationByReferenceAndPayrollId(String policyQuotaInternalId) {
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
            return mapQuotationEntity;
        }
        LOGGER.info(" :: Parameters invalidate [ policyQuotaInternalId : {} ]",policyQuotaInternalId);
        throw new BusinessException(PISDInsuranceErrors.PARAMETERS_INVALIDATE.getAdviceCode(), false, PISDInsuranceErrors.QUERY_EMPTY_RESULT.getMessage());
    }

    @Override
    public Map<String, Object> findQuotationDetail(String quotationId) {
        Map<String,Object> arguments = Collections.singletonMap(PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID,quotationId);

        if(FunctionUtils.parametersIsValid(arguments,PISDColumn.Contract.FIELD_POLICY_QUOTA_INTERNAL_ID)){
            Operation operation = Operation.Builder.an()
                    .withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp("PISD.SELECT_QUOTATION_DETAIL_FOR_PRE_EMISION")
                    .withParams(arguments)
                    .withIsContainsParameters(true)
                    .withIsForListQuery(false).build();
            return (Map<String,Object>) this.baseDAO.executeQuery(operation);
        }

        return Collections.emptyMap();
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
}
