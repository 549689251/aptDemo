package com.pattern.wistronits.patterntest.product;

public class Consume implements Runnable {

    private ProductList mProductList;

    public Consume(ProductList mProductList) {
        this.mProductList = mProductList;
    }

    @Override
    public void run() {


        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mProductList.decrease();

        }


    }
}
