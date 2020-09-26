public enum MyEnum  {
    ALL(1),LLK(2),
    UU(8),LL(5);
    private int code;
    private String description;
    MyEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
