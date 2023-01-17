package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public static final String ITEM = "item";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.handleItems();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void ITEM_sellInDateDecreases_butQualityCannotBeNegative() {
        Item[] items = new Item[] { new Item(ITEM, 0, 0) };
        GildedRose app = new GildedRose(items);

        app.handleItems();

        assertEquals(app.getItems()[0], new Item(ITEM, -1, 0));
    }

    @Test
    public void ITEM_qualityDecreases() {
        Item[] items = new Item[] { new Item(ITEM, 10, 10)};
        GildedRose app = new GildedRose(items);

        app.handleItems();

        assertEquals(app.getItems()[0], new Item(ITEM, 9, 9));
    }

    @Test
    public void ITEM_qualityDecreasesFasterAfterSellInDateExpired() {
        Item[] items = new Item[] {new Item(ITEM, 0, 10)};
        GildedRose app = new GildedRose(items);

       app.handleItems();
       assertEquals(app.getItems()[0], new Item(ITEM, -1, 8));
    }

    @Test
    public void AGEDBRIE_increasesInQuality() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 2)};

        GildedRose app = new GildedRose(items);

        app.handleItems();

        assertEquals(app.getItems()[0], new Item(AGED_BRIE, 4, 3));
    }


    @Test
    public void AGEDBRIE_notOver50() {

        Item[] items = new Item[] { new Item(AGED_BRIE, 2, 50)};
        GildedRose app = new GildedRose(items);

        app.handleItems();

        assertEquals(app.getItems()[0], new Item(AGED_BRIE, 1, 50));
    }




    @Test
    public void Sulfuras_NOCHANGES() {

        Item[] items = new Item[] { new Item(SULFURAS, 100, 100)};
        GildedRose app = new GildedRose(items);

        app.handleItems();

        assertEquals(app.getItems()[0], new Item(SULFURAS, 100, 100));
    }
}
