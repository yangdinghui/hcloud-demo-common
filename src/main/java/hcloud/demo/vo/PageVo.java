package hcloud.demo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("当前页码")
    private Integer curPage;
    @ApiModelProperty("每页条目")
    private Integer pageSize;

    public PageVo() {
    }

    public Integer getCurPage() {
        return this.curPage == null ? 1 : this.curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return this.pageSize == null ? 10 : this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
