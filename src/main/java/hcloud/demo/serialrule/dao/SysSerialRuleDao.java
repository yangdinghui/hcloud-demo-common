package hcloud.demo.serialrule.dao;

import hcloud.demo.serialrule.model.SysSerialRule;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface SysSerialRuleDao extends Mapper<SysSerialRule>, MySqlMapper<SysSerialRule> {
}
