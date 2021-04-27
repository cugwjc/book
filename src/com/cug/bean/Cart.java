package com.cug.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author warogychenger
 */
public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id, Integer count){
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        if (items != null){
            Collection<CartItem> values = items.values();
            for (CartItem cartItem : values) {
                totalCount += cartItem.getCount();
            }
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        if (items != null){
            Collection<CartItem> values = items.values();
            for (CartItem cartItem : values) {
                totalPrice = totalPrice.add(cartItem.getTotalPrice());
            }
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
