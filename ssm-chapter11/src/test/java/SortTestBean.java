public class SortTestBean {
    private  int order =0;
    public int getOrder(){
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    public  SortTestBean(int order){
        this.order = order;
    }

    @Override
    public String toString() {
        return "SortTestBean{" +
                "order=" + order +
                '}';
    }
}
