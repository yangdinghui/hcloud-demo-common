package hcloud.demo.serialrule.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-11-27
 */
@Table(name = "sys_serial_rule")
public class SysSerialRule implements Serializable {
    @Id
    @Column(
            name = "serialName"
    )
    private String serialname;
    @Column(
            name = "serialType"
    )
    private String serialtype;
    @Column(
            name = "specialPrefix"
    )
    private String specialprefix;
    @Column(
            name = "billPrefix"
    )
    private String billprefix;
    @Column(
            name = "dateType"
    )
    private String datetype;
    @Column(
            name = "currentNo"
    )
    private Integer currentno;
    @Column(
            name = "numberLength"
    )
    private Integer numberlength;
    @Column(
            name = "isAddType"
    )
    private String isaddtype;
    @Column(
            name = "totalLength"
    )
    private Integer totallength;
    @Column(
            name = "serialDescription"
    )
    private String serialdescription;
    @Column(
            name = "currentDate"
    )
    private Date currentdate;
    @Column(
            name = "isActive"
    )
    private String isactive;
    private static final long serialVersionUID = 1L;

    public SysSerialRule() {
    }

    public String getSerialname() {
        return this.serialname;
    }

    public SysSerialRule setSerialname(String serialname) {
        this.serialname = serialname == null ? null : serialname.trim();
        return this;
    }

    public String getSerialtype() {
        return this.serialtype;
    }

    public SysSerialRule setSerialtype(String serialtype) {
        this.serialtype = serialtype == null ? null : serialtype.trim();
        return this;
    }

    public String getSpecialprefix() {
        return this.specialprefix;
    }

    public SysSerialRule setSpecialprefix(String specialprefix) {
        this.specialprefix = specialprefix == null ? null : specialprefix.trim();
        return this;
    }

    public String getBillprefix() {
        return this.billprefix;
    }

    public SysSerialRule setBillprefix(String billprefix) {
        this.billprefix = billprefix == null ? null : billprefix.trim();
        return this;
    }

    public String getDatetype() {
        return this.datetype;
    }

    public SysSerialRule setDatetype(String datetype) {
        this.datetype = datetype == null ? null : datetype.trim();
        return this;
    }

    public Integer getCurrentno() {
        return this.currentno;
    }

    public SysSerialRule setCurrentno(Integer currentno) {
        this.currentno = currentno;
        return this;
    }

    public Integer getNumberlength() {
        return this.numberlength;
    }

    public SysSerialRule setNumberlength(Integer numberlength) {
        this.numberlength = numberlength;
        return this;
    }

    public String getIsaddtype() {
        return this.isaddtype;
    }

    public SysSerialRule setIsaddtype(String isaddtype) {
        this.isaddtype = isaddtype == null ? null : isaddtype.trim();
        return this;
    }

    public Integer getTotallength() {
        return this.totallength;
    }

    public SysSerialRule setTotallength(Integer totallength) {
        this.totallength = totallength;
        return this;
    }

    public String getSerialdescription() {
        return this.serialdescription;
    }

    public SysSerialRule setSerialdescription(String serialdescription) {
        this.serialdescription = serialdescription == null ? null : serialdescription.trim();
        return this;
    }

    public Date getCurrentdate() {
        return this.currentdate;
    }

    public SysSerialRule setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
        return this;
    }

    public String getIsactive() {
        return this.isactive;
    }

    public SysSerialRule setIsactive(String isactive) {
        this.isactive = isactive == null ? null : isactive.trim();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", serialname=").append(this.serialname);
        sb.append(", serialtype=").append(this.serialtype);
        sb.append(", specialprefix=").append(this.specialprefix);
        sb.append(", billprefix=").append(this.billprefix);
        sb.append(", datetype=").append(this.datetype);
        sb.append(", currentno=").append(this.currentno);
        sb.append(", numberlength=").append(this.numberlength);
        sb.append(", isaddtype=").append(this.isaddtype);
        sb.append(", totallength=").append(this.totallength);
        sb.append(", serialdescription=").append(this.serialdescription);
        sb.append(", currentdate=").append(this.currentdate);
        sb.append(", isactive=").append(this.isactive);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}

