package hcloud.demo.view;

import java.util.List;

public class PageModel<T> {
    private Integer curPage;
    private Integer pageSize;
    private Long count;
    private Integer pageCount;
    private List<T> data;

    public PageModel(Integer curPage, Integer pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    public PageModel() {
    }

    public Integer getCurPage() {
        return this.curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
