package com.bbva.pisd.lib.r601.transform.map;

import java.util.HashMap;
import java.util.Map;

public class QuotationTransforMap {

    public static Map<String,Object> policyQuotaInternalIdTransforMap(String policyQuotaInternalId){
        Map<String,Object> mapQuerySearchByPolicyQuotaInternalId = new HashMap<>();
        mapQuerySearchByPolicyQuotaInternalId.put("POLICY_QUOTA_INTERNAL_ID",policyQuotaInternalId);
        return mapQuerySearchByPolicyQuotaInternalId;
    }

}
