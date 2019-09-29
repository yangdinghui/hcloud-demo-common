package hcloud.demo.view;

import java.util.List;

public class ListModel<T> {
    private List<T> data;

    public ListModel() {
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
