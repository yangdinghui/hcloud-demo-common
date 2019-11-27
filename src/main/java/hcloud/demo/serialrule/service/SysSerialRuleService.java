package hcloud.demo.serialrule.service;

import hcloud.demo.serialrule.model.SysSerialRule;

public interface SysSerialRuleService {

    String generateSerialNumber(String var1);

    SysSerialRule getSysSerialRule(String var1);

    int getCurrentNo(String var1);

    boolean updSysSerialRule(String var1, Integer var2);
}
