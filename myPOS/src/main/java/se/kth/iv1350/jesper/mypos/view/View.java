package se.kth.iv1350.jesper.mypos.view;

import se.kth.iv1350.jesper.mypos.controller.Controller;
import se.kth.iv1350.jesper.mypos.controller.ScanItemFailedException;
import se.kth.iv1350.jesper.mypos.model.dto.ItemDTO;

/**
 * This is a placeholder for the real view. Currently contains system calls to
 * test the flow of operations.
 */
public class View {
    private Controller contr;

    /**
     * Constructor for the view
     * 
     * @param contr Reference to the controller.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileView());
    }

    /**
     * Hard coded code to test system calls from the view.
     * 
     * @param contr Reference to the controller.
     */
    public void testExecution(Controller contr) {
        // initiate sale
        System.out.println("\nStart Sale...");
        contr.startSale();
        // scan items, stroller, energydrink, item not found, bed, no connection energydrink,
        int[] scanItemList = { 12345, 11111, 123456789, 54321, 99999, 11111 }; 

        for (int i : scanItemList) {
            try {
                System.out.println("\nScaning item...");
                printItemDTO(contr.scanItem(i, 1));
                System.out.println("Running total (inc VAT): " + contr.getRunningTotalIncludingVAT());
            } catch (ScanItemFailedException scanItemFailedException) {
                System.out.println(scanItemFailedException.getMessage());
            }
        }

        // end sale
        contr.endsale();
        System.out.println("\nTotal price (inc VAT): " + contr.getRunningTotalIncludingVAT());

        // register payment
        contr.pay(10000);

        // finalize sale
        contr.finalizeSale();

        // present change to give to customer
        System.out.println("\nChange: " + contr.getChange());
    }

    private void printItemDTO(ItemDTO item) {
        System.out.println("Description: " + item.getDescription());
        System.out.println("Price: " + item.getPrice());
        System.out.println("VAT Rate: " + item.getVatRate() + "%");
    }
}