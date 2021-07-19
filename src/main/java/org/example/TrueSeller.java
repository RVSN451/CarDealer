package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TrueSeller {
    private TrueDealer trueDealer;
    ReentrantLock locker;
    Condition condition;

    public TrueSeller(TrueDealer trueDealer, ReentrantLock locker){
        this.trueDealer = trueDealer;
        this.locker = locker;
        this.condition = locker.newCondition();
    }

    public void receiveCar()  {
        try {
            locker.lock();
            System.out.println("Производитель выпустил новую машину.");
            System.out.println("Продавец принимает " + trueDealer.carModel + " на склад.");
            Thread.sleep(App.timeAcceptanceToStock);
            trueDealer.cars.add(new Car());
            System.out.println("Приёмка окончена. Вналичии на складе - "
                    + trueDealer.cars.size()   + " " + trueDealer.carModel + ".");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public synchronized Car sellCar() {
        try {
            locker.lock();
            System.out.println("Продаю " + trueDealer.carModel + " покупателю: " + Thread.currentThread().getName() + ".");
            while (trueDealer.cars.size() == 0) {
                System.out.println("На данный момент " + trueDealer.carModel + " отсутствуют на складе. Ожидайте.");
                condition.await();
            }
            Thread.sleep(App.timeTransferToBuyer);
            System.out.println(trueDealer.carModel + " продана покупателю: - " + Thread.currentThread().getName() + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }

        return trueDealer.cars.remove(0);
    }
}
