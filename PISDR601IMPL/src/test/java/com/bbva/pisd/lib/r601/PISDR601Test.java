package com.bbva.pisd.lib.r601;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR601-app.xml",
		"classpath:/META-INF/spring/PISDR601-app-test.xml",
		"classpath:/META-INF/spring/PISDR601-arc.xml",
		"classpath:/META-INF/spring/PISDR601-arc-test.xml" })
public class PISDR601Test {

	@Spy
	private Context context;

	@Resource(name = "pisdR601")
	private PISDR601 pisdR601;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Resource(name = "jdbcUtils")
	private JdbcUtils jdbcUtils;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",new Locale("es", "PE"));

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.pisdR601;
		if(this.pisdR601 instanceof Advised){
			Advised advised = (Advised) this.pisdR601;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}

	/**
	 *  Casos para ejecutar en la creación de un proceso biometrico .
	 *
	 *  1. Ejecución exitosa para obtener los datos de cotización
	 *  2. Parametros invalidos al obtener la cotización .
	 * */

	@Test
	public void execute_find_quotation_by_quotationid_test() throws ParseException {
		/**
		 *  Data de prueba
		 * */
		Map<String,Object> mapResponse = getMockMapQuotationEntity();
		/**
		 *  Context
		 * */
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.eq("PISD.SELECT_FIND_QUOTATION_BY_POLICY_QUOTA_INTERNAL_ID"),Mockito.anyMap())).thenReturn(mapResponse);
		/**
		 * Ejecución
		 * */
		QuotationEntity result = this.pisdR601.executeFindQuotationByPolicyQuotaInternalId("0814000000691");
		Assert.assertNotNull(result);
		Assert.assertEquals("0814000000691",result.getPolicyQuotaInternalId());
		Assert.assertEquals("691",result.getInsuranceSimulationId());
		Assert.assertEquals("1feae442-690a-4a19-88e1-e0a94b69540f",result.getInsuranceCompanyQuotaId());
		Assert.assertEquals("2021-05-21",result.getQuoteDate());
		Assert.assertEquals("2021-05-21 14:51:12.773926",result.getQuotaHmsDate());
		Assert.assertEquals("2021-06-18",result.getPolicyQuotaEndValidityDate());
		Assert.assertEquals("97171890",result.getCustomerId());
		Assert.assertEquals("COT",result.getPolicyQuotaStatusType());
		Assert.assertEquals("0814",result.getLastChangeBranchId());
		Assert.assertEquals("0814",result.getSourceBranchId());
		Assert.assertEquals("ZG13001",result.getCreationUserId());
		Assert.assertEquals(format.parse("2021-05-21 14:51:12.773926"),result.getCreationDate());
		Assert.assertEquals("ZG13001",result.getUserAuditId());
		Assert.assertEquals(format.parse("2021-05-21 14:51:12.773926"),result.getAuditDate());
		Assert.assertEquals("L",result.getPersonalDocType());
		Assert.assertEquals("74207445",result.getParticipantPersonalId());
		Assert.assertEquals(format.parse("2023-03-31 00:00:00.000000"),result.getPolicyQuotaCancellationDate());
		Assert.assertEquals("MAVILA",result.getInsuredCustomerName());
		Assert.assertEquals("ORTIZ",result.getClientLasName());
		Assert.assertNull(result.getIssuedReceiptNumber());
		Assert.assertNull(result.getLastFourPanId());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeFindByInsrncCompanySimulationIdNullTest(){
		/**
		 * Execution
		 * */
		QuotationEntity result = this.pisdR601.executeFindQuotationByPolicyQuotaInternalId(null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	private Map<String, Object> getMockMapQuotationEntity() throws ParseException {

		Map<String,Object> mapMockQuotationEntity = new HashMap<>();
		mapMockQuotationEntity.put("POLICY_QUOTA_INTERNAL_ID","0814000000691");
		mapMockQuotationEntity.put("INSURANCE_SIMULATION_ID","691");
		mapMockQuotationEntity.put("INSURANCE_COMPANY_QUOTA_ID","1feae442-690a-4a19-88e1-e0a94b69540f");
		mapMockQuotationEntity.put("QUOTE_DATE","2021-05-21");
		mapMockQuotationEntity.put("QUOTA_HMS_DATE","2021-05-21 14:51:12.773926");
		mapMockQuotationEntity.put("POLICY_QUOTA_END_VALIDITY_DATE","2021-06-18");
		mapMockQuotationEntity.put("CUSTOMER_ID","97171890");
		mapMockQuotationEntity.put("POLICY_QUOTA_STATUS_TYPE","COT");
		mapMockQuotationEntity.put("LAST_CHANGE_BRANCH_ID","0814");
		mapMockQuotationEntity.put("SOURCE_BRANCH_ID","0814");
		mapMockQuotationEntity.put("CREATION_USER_ID","ZG13001");
		mapMockQuotationEntity.put("CREATION_DATE",format.parse("2021-05-21 14:51:12.773926"));
		mapMockQuotationEntity.put("USER_AUDIT_ID","ZG13001");
		mapMockQuotationEntity.put("AUDIT_DATE",format.parse("2021-05-21 14:51:12.773926"));
		mapMockQuotationEntity.put("PERSONAL_DOC_TYPE","L");
		mapMockQuotationEntity.put("PARTICIPANT_PERSONAL_ID","74207445");
		mapMockQuotationEntity.put("POLICY_QUOTA_CANCELLATION_DATE",format.parse("2023-03-31 00:00:00.000000"));
		mapMockQuotationEntity.put("INSURED_CUSTOMER_NAME","MAVILA");
		mapMockQuotationEntity.put("CLIENT_LAST_NAME","ORTIZ");
		mapMockQuotationEntity.put("ISSUED_RECEIPT_NUMBER",null);
		mapMockQuotationEntity.put("LAST_FOUR_PAN_ID",null);
		return mapMockQuotationEntity;
	}
	
}
