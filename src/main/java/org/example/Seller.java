package org.example;

public class Seller {
    private Dealer dealer;

    public Seller (Dealer dealer){
        this.dealer = dealer;
    }

    public synchronized void receiveCar()  {

        try {
            System.out.println("Производитель выпустил новую машину.");
            System.out.println("Продавец принимает " + dealer.carModel + " на склад.");
            Thread.sleep(App.timeAcceptanceToStock);
            dealer.cars.add(new Car());
            System.out.println("Приёмка окончена. Вналичии на складе - "
                    + dealer.cars.size()   + " " + dealer.carModel + ".");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println("Продаю " + dealer.carModel + " покупателю: " + Thread.currentThread().getName() + ".");
            while (dealer.cars.size() == 0) {
                System.out.println("На данный момент " + dealer.carModel + " отсутствуют на складе. Ожидайте.");
                wait();
            }
            Thread.sleep(App.timeTransferToBuyer);
            System.out.println(dealer.carModel + " продана покупателю: - " + Thread.currentThread().getName() + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return dealer.cars.remove(0);
    }
}
