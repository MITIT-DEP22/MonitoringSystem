package com.company;

public class DiskInfo  {

    private Long id;
    private String name;
    private String model;
    private float total;
    private float loaded;

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getLoaded() {
        return loaded;
    }

    public void setLoaded(float loaded) {
        this.loaded = loaded;
    }

    public DiskInfo() {

    }

    public DiskInfo(String name, float total, float loaded) {
        this.loaded = loaded;
        this.total = total;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DiskInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", total=" + total +
                ", loaded=" + loaded +
                '}';
    }
}
