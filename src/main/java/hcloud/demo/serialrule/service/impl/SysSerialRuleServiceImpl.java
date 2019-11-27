package hcloud.demo.serialrule.service.impl;

import hcloud.demo.enums.ResultStatus;
import hcloud.demo.exception.BusinessException;
import hcloud.demo.serialrule.dao.SysSerialRuleDao;
import hcloud.demo.serialrule.model.SysSerialRule;
import hcloud.demo.serialrule.service.SysSerialRuleService;
import hcloud.demo.utils.ConvUtil;
import hcloud.demo.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;

/**
 * description 流水号生成类(根据数据库设置规则)
 *
 * @author 杨丁辉
 * @date 2019-11-27
 */
@Service
public class SysSerialRuleServiceImpl implements SysSerialRuleService {
    private static final Logger log = LoggerFactory.getLogger(SysSerialRuleServiceImpl.class);
    @Autowired
    private SysSerialRuleDao sysSerialRuleDao;

    public SysSerialRuleServiceImpl() {
    }

    @Override
    public String generateSerialNumber(String serialName) {
        String serialNumber = "";
        SysSerialRule sysSerialRule = this.getSysSerialRule(serialName);
        String serialType = sysSerialRule.getSerialtype();
        if (this.checkSerialRule(sysSerialRule)) {
            if ("ID".equals(serialType)) {
                serialNumber = this.idSerialType(sysSerialRule);
            } else if ("PrefixID".equals(serialType)) {
                serialNumber = this.prefixIDSerialType(sysSerialRule);
            } else if ("BILL".equals(serialType)) {
                serialNumber = this.billSerialType(sysSerialRule);
            }
        }

        return serialNumber;
    }

    @Override
    public SysSerialRule getSysSerialRule(String serialName) {
        SysSerialRule sysSerialRule = null;

        try {
            Example example = new Example(SysSerialRule.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("serialname", serialName);
            sysSerialRule = (SysSerialRule)this.sysSerialRuleDao.selectOneByExample(example);
            if (sysSerialRule == null) {
                throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "根据流水号名称获取流水号生成规则信息为空，请检查serialName是否正确");
            }
        } catch (Exception var5) {
            log.error("根据流水号名称获取流水号生成规则信息异常:", var5);
        }

        return sysSerialRule;
    }

    @Override
    public int getCurrentNo(String serialName) {
        Integer currentNo = 0;
        SysSerialRule sysSerialRule = this.getSysSerialRule(serialName);
        currentNo = sysSerialRule.getCurrentno();
        if (currentNo == null) {
            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "根据流水号名称获取当前流水号编号错误，请检查数据库中该记录信息");
        } else {
            return currentNo;
        }
    }

    @Override
    public boolean updSysSerialRule(String serialName, Integer currentNo) {
        int count = 0;

        try {
            SysSerialRule sysSerialRule = new SysSerialRule();
            sysSerialRule.setSerialname(serialName);
            sysSerialRule.setCurrentno(currentNo);
            sysSerialRule.setCurrentdate(new Date());
            count = this.sysSerialRuleDao.updateByPrimaryKeySelective(sysSerialRule);
        } catch (Exception var5) {
            log.error("更新流水号(currentNo)异常:", var5);
        }

        return count > 0;
    }

    private String idSerialType(SysSerialRule sysSerialRule) {
        String serialNumber = "";
        String serialName = sysSerialRule.getSerialname();
        int numberLength = sysSerialRule.getNumberlength();
        Integer currentNo = sysSerialRule.getCurrentno();
        currentNo = currentNo + 1;
        boolean result = this.updSysSerialRule(serialName, currentNo);
        if (!result) {
            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "更新流水号表失败");
        } else {
            String formatTemp = "%0" + String.valueOf(numberLength) + "d";
            serialNumber = String.format(formatTemp, currentNo);
            return serialNumber;
        }
    }

    private String prefixIDSerialType(SysSerialRule sysSerialRule) {
        String serialNumber = "";
        String serialName = sysSerialRule.getSerialname();
        String specialPrefix = sysSerialRule.getSpecialprefix();
        String billPrefix = sysSerialRule.getBillprefix();
        int numberLength = sysSerialRule.getNumberlength();
        Integer currentNo = sysSerialRule.getCurrentno();
        currentNo = currentNo + 1;
        boolean result = this.updSysSerialRule(serialName, currentNo);
        if (!result) {
            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "更新流水号表失败");
        } else {
            String formatTemp = "%0" + String.valueOf(numberLength) + "d";
            serialNumber = String.format(formatTemp, currentNo);
            serialNumber = specialPrefix + billPrefix + serialNumber;
            return serialNumber;
        }
    }

    private String billSerialType(SysSerialRule sysSerialRule) {
        String serialNumber = "";

        try {
            String specialPrefix = sysSerialRule.getSpecialprefix();
            String billPrefix = sysSerialRule.getBillprefix();
            String dateType = sysSerialRule.getDatetype();
            String serialName = sysSerialRule.getSerialname();
            String isAddType = sysSerialRule.getIsaddtype();
            Date currentDate = sysSerialRule.getCurrentdate();
            int numberLength = sysSerialRule.getNumberlength();
            Integer currentNo = sysSerialRule.getCurrentno();
            currentNo = currentNo + 1;
            Date sysDate = new Date();
            Date sqlDate = DateUtil.date(DateUtil.date(currentDate));
            if ("B".equals(isAddType)) {
                if (DateUtil.subtractDay(sqlDate, sysDate) > 0) {
                    currentNo = 1;
                }
            } else if ("C".equals(isAddType)) {
                if (DateUtil.subtractMonth(sqlDate, sysDate) > 0) {
                    currentNo = 1;
                }
            } else if ("D".equals(isAddType) && DateUtil.subtractYear(sqlDate, sysDate) > 0) {
                currentNo = 1;
            }

            boolean result = this.updSysSerialRule(serialName, currentNo);
            if (!result) {
                throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "更新流水号表失败");
            }

            String formatTemp = "%0" + String.valueOf(numberLength) + "d";
            serialNumber = String.format(formatTemp, currentNo);
            String middleTime = DateUtil.currDateFormat(dateType);
            serialNumber = specialPrefix + billPrefix + middleTime + serialNumber;
        } catch (Exception var16) {
            log.error("处理bill类型流水号异常", var16);
        }

        return serialNumber;
    }

    private boolean checkSerialRule(SysSerialRule sysSerialRule) {
        Integer currentNo = sysSerialRule.getCurrentno();
        Integer numberLength = sysSerialRule.getNumberlength();
        Integer totalLength = sysSerialRule.getTotallength();
        String currentNoStr = ConvUtil.toString(currentNo);
        String serialType = sysSerialRule.getSerialtype();
        Date currentDate = sysSerialRule.getCurrentdate();
        String specialPrefix = sysSerialRule.getSpecialprefix();
        String billPrefix = sysSerialRule.getBillprefix();
        String dateType = sysSerialRule.getDatetype();
        String isAddType = sysSerialRule.getIsaddtype();
        if (numberLength != null && numberLength != 0) {
            if (totalLength != null && totalLength != 0) {
                if (totalLength > totalLength) {
                    throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号数字长度定义不合法");
                } else if (currentDate == null && ("ID".equals(serialType) || "PrefixID".equals(serialType) || "BILL".equals(serialType) || "ABS".equals(serialType))) {
                    throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号生成日期历史数据没有定义");
                } else if (currentNo == null) {
                    throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "根据流水号名称获取当前流水号编号错误，请检查数据库中该记录信息");
                } else if (currentNoStr.length() > numberLength) {
                    throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号长度超过系统定义的最大数字长度");
                } else {
                    if ("PrefixID".equals(serialType)) {
                        if (specialPrefix.length() + billPrefix.length() + numberLength != totalLength) {
                            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号长度规则定义不正确");
                        }

                        if (currentNoStr.length() > totalLength - specialPrefix.length() - billPrefix.length() || currentNoStr.length() > totalLength - specialPrefix.length() || currentNoStr.length() > totalLength - billPrefix.length()) {
                            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号前缀长度不合法");
                        }
                    } else if ("BILL".equals(serialType)) {
                        if (StringUtil.isEmpty(isAddType)) {
                            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号生成规则未设定");
                        }

                        if (specialPrefix.length() + billPrefix.length() + dateType.length() + numberLength != totalLength) {
                            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号长度规则定义不正确");
                        }

                        if (currentNoStr.length() > totalLength - specialPrefix.length() - billPrefix.length() - dateType.length() || currentNoStr.length() > totalLength - specialPrefix.length() - billPrefix.length() || currentNoStr.length() > totalLength - billPrefix.length() - dateType.length() || currentNoStr.length() > totalLength - specialPrefix.length() - dateType.length() || currentNoStr.length() > totalLength - specialPrefix.length() || currentNoStr.length() > totalLength - billPrefix.length() || currentNoStr.length() > totalLength - dateType.length()) {
                            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号前缀长度不合法");
                        }
                    }

                    return true;
                }
            } else {
                throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号总长度没有定义");
            }
        } else {
            throw new BusinessException(ResultStatus.SERIALRULE_ERROR.getCode(), "流水号中的数字长度没有定义");
        }
    }
}

