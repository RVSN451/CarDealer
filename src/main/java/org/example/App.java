package org.example;

public class App {

    public final static long timeAcceptanceToStock = 300;
    public final static long timeTransferToBuyer = 200;
    public final static long timeCarFactory = 800;

    public static void dealerVolvo(){
        final Dealer volvo = Dealer.get("Volvo", 2);

        new Thread(volvo::transferToBuyer, "Buyer-1").start();
        new Thread(volvo::transferToBuyer, "Buyer-2").start();
        new Thread(volvo::transferToBuyer, "Buyer-3").start();
        new Thread(volvo::transferToBuyer, "Buyer-4").start();
        new Thread(volvo::transferToBuyer, "Buyer-5").start();
        new Thread(volvo::transferToBuyer, "Buyer-6").start();
        new Thread(volvo::transferToBuyer, "Buyer-7").start();
        new Thread(volvo::transferToBuyer, "Buyer-8").start();
        new Thread(volvo::transferToBuyer, "Buyer-9").start();
        new Thread(volvo::transferToBuyer, "Buyer-10").start();

        for (int i = 0; i < 8; i++){
            new Thread(null, volvo::acceptToStock, "Производитель").start();
            try {
                Thread.sleep(timeCarFactory);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dealerToyota(){
        final TrueDealer toyota = TrueDealer.get("TOYOTA", 3);

        new Thread(toyota::transferToBuyer, "ToyotaBuyer-1").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-2").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-3").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-4").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-5").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-6").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-7").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-8").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-9").start();
        new Thread(toyota::transferToBuyer, "ToyotaBuyer-10").start();

        for (int i = 0; i < 7; i++){
            new Thread(null, toyota::acceptToStock, "Производитель").start();
            try {
                Thread.sleep(timeCarFactory);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Автосалон VOLVO:");
        dealerVolvo();

        System.out.println("\n\nПродвинутый (честный) автосалон TOYOTA");
        dealerToyota();
    }
}
