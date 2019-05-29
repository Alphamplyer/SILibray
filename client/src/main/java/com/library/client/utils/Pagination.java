package com.library.client.utils;

import java.util.List;

public class Pagination<T> {

    private int itemByPage;
    private int itemNumber;
    private List<T> items;

    public Pagination(int itemByPage, List<T> items) {
        this.itemByPage = itemByPage;
        this.itemNumber = items.size();
        this.items = items;
    }

    public int getItemByPage() {
        return itemByPage;
    }

    public void setItemByPage(int itemByPage) {
        this.itemByPage = itemByPage;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getMaxPage() {
        return (int)Math.ceil(itemNumber / (double)itemByPage);
    }

    public List<T> getPageItem(int page) {
        if (page > getMaxPage())
            return null;

        if (hasNextPage(page))
            return items.subList((page - 1) * itemByPage, page * itemByPage);

        return items.subList((page - 1) * itemByPage, items.size());
    }

    public boolean hasPrevPage(int page) {
        return page > 1;
    }

    public boolean hasNextPage(int page) {
        return page < getMaxPage();
    }
}
