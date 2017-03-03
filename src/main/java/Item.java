public class Item {

    private Integer salePrice;

    private Integer oldSalePrice;

    Item(Integer salePrice) {
        this.salePrice = salePrice;
        this.oldSalePrice = ((Double) (salePrice / 0.75)).intValue();
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public Integer getOldSalePrice() {
        return oldSalePrice;
    }
}
