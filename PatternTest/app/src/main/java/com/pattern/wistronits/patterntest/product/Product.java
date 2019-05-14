package com.pattern.wistronits.patterntest.product;

public class Product implements Runnable {

    private ProductList mProductList;

    public Product(ProductList mProductList) {
        this.mProductList = mProductList;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mProductList.increase();

        }

    }
}
