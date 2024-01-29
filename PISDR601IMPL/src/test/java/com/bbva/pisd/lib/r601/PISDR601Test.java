package com.bbva.pisd.lib.r601;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurancedao.entities.QuotationEntity;
import com.bbva.pisd.dto.insurancedao.join.QuotationJoinCustomerInformationDTO;
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
		QuotationJoinCustomerInformationDTO result = this.pisdR601.executeFindQuotationJoinByPolicyQuotaInternalId("0814000000691");
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getQuotation());
		Assert.assertEquals("customer name",result.getQuotation().getInsuredCustomerName());
		Assert.assertEquals("client last name",result.getQuotation().getClientLasName());
		Assert.assertNotNull(result.getQuotationMod());
		Assert.assertEquals("email@bbva.com",result.getQuotationMod().getContactEmailDesc());
		Assert.assertEquals("923453849",result.getQuotationMod().getCustomerPhoneDesc());
		Assert.assertNotNull(result.getInsuranceProduct());
		Assert.assertEquals("834",result.getInsuranceProduct().getInsuranceProductType());
		Assert.assertNotNull(result.getInsuranceBusiness());
		Assert.assertEquals("PROTECCION_TARJETAS",result.getInsuranceBusiness().getInsuranceBusinessName());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeFindByInsrncCompanySimulationIdNullTest(){
		/**
		 * Execution
		 * */
		QuotationJoinCustomerInformationDTO result = this.pisdR601.executeFindQuotationJoinByPolicyQuotaInternalId(null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	private Map<String, Object> getMockMapQuotationEntity() throws ParseException {

		Map<String,Object> mapMockQuotationEntity = new HashMap<>();
		mapMockQuotationEntity.put("INSURED_CUSTOMER_NAME","customer name");
		mapMockQuotationEntity.put("CLIENT_LAST_NAME","client last name");
		mapMockQuotationEntity.put("CONTACT_EMAIL_DESC","email@bbva.com");
		mapMockQuotationEntity.put("CUSTOMER_PHONE_DESC","923453849");
		mapMockQuotationEntity.put("INSURANCE_PRODUCT_TYPE","834");
		mapMockQuotationEntity.put("INSURANCE_BUSINESS_NAME","PROTECCION_TARJETAS");

		return mapMockQuotationEntity;
	}
	
}
