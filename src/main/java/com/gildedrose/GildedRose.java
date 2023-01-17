package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void handleItems() {
        for (Item item: items) {
            updateQuality(item);
        }
    }

    /**
     * this method update the Quality of an item
     * @param item  is an Item
     */
    private void updateQuality(Item item) {
        if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)) {
            handleUsualQuality(item);
        } else {
            handleHighQualityItem(item);
        }
        updateSellIn(item);
    }

    /**
     * this method update the SellIn, and update the quality for the UnSaleable items
     * @param item  is an Item
     */
    private void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn --;
        }
        handleUnSaleableItems(item);
   }

    /**
     * this method handle all the unsaleable items, and update the quality
     * @param item  is an Item
     */
    private void handleUsualQuality(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals(SULFURAS)) {
                item.quality --;
            }
        }
    }
    /**
     * this method handle all the items which the quality is above 50, and update the quality
     * @param item   an Item
     */
    private void handleHighQualityItem(Item item) {
       if (item.quality < 50) {
                item.quality ++;

            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    incrementQualitySmallerThanFifty(item);
                }

                if (item.sellIn < 6) {
                    incrementQualitySmallerThanFifty(item);
                }
            }
        }
    }

    /**
     * this method increment the quality when it's below 50.
     * @param item   an Item
     */
    private void incrementQualitySmallerThanFifty(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    /**
     * this method handle all the unsaleable items, and update the quality
     * @param item   an Item
     */
    private void handleUnSaleableItems(Item item) {
        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS)) {
                            item.quality --;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                incrementQualitySmallerThanFifty(item);
            }
        }
    }

    public Item[] getItems() {
        return items;
    }
}
