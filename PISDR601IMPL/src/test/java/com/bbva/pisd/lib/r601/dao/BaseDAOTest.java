package com.bbva.pisd.lib.r601.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.IncorrectResultSizeException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurancedao.constants.PISDInsuranceErrors;
import com.bbva.pisd.dto.insurancedao.operation.Operation;
import com.bbva.pisd.dto.insurancedao.operation.OperationConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/PISDR601-app.xml",
        "classpath:/META-INF/spring/PISDR601-app-test.xml",
        "classpath:/META-INF/spring/PISDR601-arc.xml",
        "classpath:/META-INF/spring/PISDR601-arc-test.xml" })
public class BaseDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAOTest.class);

    @Spy
    private Context context;

    @InjectMocks
    private BaseDAO baseDAO;

    @Mock
    private JdbcUtils jdbcUtils;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = new Context();
        ThreadContext.set(context);
        getObjectIntrospection();
    }

    private Object getObjectIntrospection() throws Exception{
        Object result = this.baseDAO;
        if(this.baseDAO instanceof Advised){
            Advised advised = (Advised) this.baseDAO;
            result = advised.getTargetSource().getTarget();
        }
        return result;
    }

    private Map<String, Object> PrepareParamsToSearchInsuranceContract(){
        Map<String, Object> params = new HashMap<>();
        params.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        params.put("INSURANCE_CONTRACT_BRANCH_ID","0142");
        params.put("INSRC_CONTRACT_INT_ACCOUNT_ID","4000317312");
        return params;
    }
    private Map<String, Object> PrepareParamsSelectConditions() {
        Map<String, Object> params = new HashMap<>();
        params.put("PERIOD_NEXT_PAYMENT_DATE","ICTD");

        return params;
    }

    @Test
    public void executeQuerySelectTest(){
        LOGGER.info("Executing the executeQuerySelectTest...");
        Operation opSelect = new Operation();
        opSelect.setNameProp("PISD.SELECT_FIND_QUOTATION_BY_POLICY_QUOTA_INTERNAL_ID");
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsToSearchInsuranceContract());
        opSelect.setForListQuery(false);
        opSelect.setContainsParameters(true);

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("CUSTOMER_ID","0011");
        resultExpected.put("POLICY_ID","0486");
        resultExpected.put("INSRNC_COMPANY_RENWL_PRPSL_ID","1222");
        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenReturn(resultExpected);

        Object resultObject = baseDAO.executeQuery(opSelect);
        Map<String, Object> result = (Map<String, Object>) resultObject;
        Assert.assertNotNull(result);
        Assert.assertEquals(resultExpected, resultObject);
        Mockito.verify(jdbcUtils, Mockito.atLeastOnce()).queryForMap(Mockito.anyString(), Mockito.anyMap());
    }



    @Test
    public void executeQueryForListOKTest(){
        LOGGER.info("Executing the executeQueryForListOKTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(true);

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        Map<String, Object> resultExpected01 = new HashMap<>();
        resultExpected01.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected01.put("INSURANCE_CONTRACT_BRANCH_ID","0242");
        resultExpected01.put("INSRC_CONTRACT_INT_ACCOUNT_ID","111");
        resultExpected01.put("SETTLE_PENDING_VAR_PREM_AMOUNT","4588");

        List<Map<String,Object>> responseExpected = new ArrayList<>();
        responseExpected.add(resultExpected);
        responseExpected.add(resultExpected01);
        Mockito.when(jdbcUtils.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(responseExpected);

        Object resultObject= baseDAO.executeQuery(opSelect);
        List<Map<String,Object>> result = (List<Map<String,Object>>) resultObject;

        Assert.assertNotNull( resultObject);

        Assert.assertEquals(2,result.size() );
        Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                .queryForList(Mockito.anyString(),
                        Mockito.anyMap());
    }

    @Test
    public void executeQueryForListWithNoParams(){
        LOGGER.info("Executing the executeQueryForListWithNoParams...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setForListQuery(false);
        opSelect.setParams(null);
        opSelect.setContainsParameters(false);

        Map<String, Object> resultExpected = new HashMap<>();
        resultExpected.put("INSURANCE_CONTRACT_ENTITY_ID","0011");
        resultExpected.put("INSURANCE_CONTRACT_BRANCH_ID","0486");
        resultExpected.put("INSRC_CONTRACT_INT_ACCOUNT_ID","1222");
        resultExpected.put("SETTLE_PENDING_VAR_PREM_AMOUNT","2500");

        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString())).thenReturn(resultExpected);

        Object resultObject= baseDAO.executeQuery(opSelect);
        Map<String,Object> result = (Map<String,Object>) resultObject;

        Assert.assertNotNull(resultObject);

        Assert.assertEquals(4,result.size());
        Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                .queryForMap(Mockito.anyString());
    }


    @Test
    public void executeQueryNoResultExceptionTest(){
        LOGGER.info("Executing the executeQueryUpdateTest...");
        Operation opUpdated = new Operation();
        opUpdated.setTypeOperation(OperationConstants.Operation.SELECT);
        opUpdated.setParams(PrepareParamsToSearchInsuranceContract());
        opUpdated.setForListQuery(false);
        opUpdated.setContainsParameters(true);

        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(NoResultException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opUpdated);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertNull(resultObject);
            Assert.assertEquals(PISDInsuranceErrors.QUERY_EMPTY_RESULT.getAdviceCode(),e.getAdviceCode());
            Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }

    }

    @Test
    public void executeQuerySelectTimeoutExceptionTest(){
        LOGGER.info("Executing the executeQuerySelectAPXExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setContainsParameters(true);

        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(TimeoutException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertNull(resultObject);
            Assert.assertEquals(PISDInsuranceErrors.ERROR_TIME_OUT.getAdviceCode(), e.getAdviceCode());
            Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    @Test
    public void executeQueryDuplicateKeyExceptionTest(){
        LOGGER.info("Executing the executeQueryDuplicateKeyExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(false);
        opSelect.setContainsParameters(true);

        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(DuplicateKeyException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDInsuranceErrors.ERROR_DUPLICATE_KEY.getAdviceCode(), e.getAdviceCode());
            Assert.assertEquals(PISDInsuranceErrors.ERROR_DUPLICATE_KEY.isRollback(), PISDInsuranceErrors.ERROR_DUPLICATE_KEY.isRollback());
            Assert.assertEquals(PISDInsuranceErrors.ERROR_DUPLICATE_KEY.getMessage(), PISDInsuranceErrors.ERROR_DUPLICATE_KEY.getMessage());
            Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }


    @Test
    public void executeQueryIncorrectResultSizeExceptionTest(){
        LOGGER.info("Executing the executeQueryIncorrectResultSizeExceptionTest...");
        Operation opSelect = new Operation();
        opSelect.setTypeOperation(OperationConstants.Operation.SELECT);
        opSelect.setParams(PrepareParamsSelectConditions());
        opSelect.setForListQuery(false);
        opSelect.setContainsParameters(true);
        Mockito.when(jdbcUtils.queryForMap(Mockito.anyString(), Mockito.anyMap())).thenThrow(IncorrectResultSizeException.class);

        Object resultObject = null;

        try {
            resultObject= baseDAO.executeQuery(opSelect);
            Assert.fail();
        }catch (BusinessException e){
            Assert.assertEquals(null, resultObject);
            Assert.assertEquals(PISDInsuranceErrors.ERROR_INCORRECT_RESULT.getAdviceCode(), e.getAdviceCode());
            Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                    .queryForMap(Mockito.anyString(),
                            Mockito.anyMap());
        }
    }

    @Test
    public void executeBatchQueryTest(){
        LOGGER.info("Executing the executeBatchQueryTest...");
        Operation opSelect = new Operation();
        opSelect.setNameProp("UPDATED_CONTRACT");
        opSelect.setTypeOperation(OperationConstants.Operation.BATCH);
        Map<String, Object> [] params = new Map[]{ PrepareParamsSelectConditions(), PrepareParamsSelectConditions()};
        opSelect.setBatchValues(params);

        int [] resultExpected = new int[2];
        Mockito.when(jdbcUtils.batchUpdate(Mockito.anyString(), Mockito.any(params.getClass()))).thenReturn(resultExpected);

        Object resultObject = baseDAO.executeQuery(opSelect);

        int [] result = (int []) resultObject;
        Assert.assertEquals(result.length, resultExpected.length);
        Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                .batchUpdate(Mockito.anyString(),
                        Mockito.any(params.getClass()));
    }

    @Test
    public void executeQueryUpdateTest(){
        LOGGER.info("Executing the executeQueryUpdateTest...");
        Operation opUpdated = new Operation();
        opUpdated.setTypeOperation(OperationConstants.Operation.UPDATE);
        opUpdated.setParams(PrepareParamsSelectConditions());

        when(jdbcUtils.update(Mockito.anyString(), Mockito.anyMap())).thenReturn(1);

        Object resultObject = baseDAO.executeQuery(opUpdated);

        int result = (int) resultObject;

        Assert.assertEquals(1, result);
        Mockito.verify(jdbcUtils, Mockito.atLeastOnce())
                .update(Mockito.anyString(),
                        Mockito.anyMap());
    }
}