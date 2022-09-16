package se.kth.iv1350.jesper.mypos.model;

public interface SaleObserver {

    /**
     * Invoked when a sale has been completed.
     * 
     * @param receipt The receipt of the finalized sale.
     */
    void onCompleteSale(double revenue);
}
