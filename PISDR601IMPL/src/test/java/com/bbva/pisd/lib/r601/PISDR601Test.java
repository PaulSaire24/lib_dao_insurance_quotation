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
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.eq("PISD.SELECT_FIND_RFQ_INTERNAL_ID_AND_PAYROLL_ID_BY_POLICY_QUOTA_INTERNAL_ID"),Mockito.anyMap())).thenReturn(mapResponse);
		/**
		 * Ejecución
		 * */
		QuotationEntity result = this.pisdR601.executeFindQuotationByReferenceAndPayrollId("0814000000691");
		Assert.assertNotNull(result);
		Assert.assertEquals("customer name",result.getRfqInternalId());
		Assert.assertEquals("client last name",result.getPayrollId());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeFindByInsrncCompanySimulationIdNullTest(){
		/**
		 * Execution
		 * */
		QuotationEntity result = this.pisdR601.executeFindQuotationByReferenceAndPayrollId(null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	private Map<String, Object> getMockMapQuotationEntity() throws ParseException {

		Map<String,Object> mapMockQuotationEntity = new HashMap<>();
		mapMockQuotationEntity.put("RFQ_INTERNAL_ID","customer name");
		mapMockQuotationEntity.put("PAYROLL_ID","client last name");

		return mapMockQuotationEntity;
	}

}
