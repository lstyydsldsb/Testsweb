package ldu.ldu.entity;

public class Branch {
    String branch_name;
    String branch_city;
    int assets;

    public Branch() {
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branch_name='" + branch_name + '\'' +
                ", branch_city='" + branch_city + '\'' +
                ", assets=" + assets +
                '}';
    }

    public Branch(String branch_name, String branch_city, int assets) {
        this.branch_name = branch_name;
        this.branch_city = branch_city;
        this.assets = assets;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_city() {
        return branch_city;
    }

    public void setBranch_city(String branch_city) {
        this.branch_city = branch_city;
    }

    public int getAssets() {
        return assets;
    }

    public void setAssets(int assets) {
        this.assets = assets;
    }
}
