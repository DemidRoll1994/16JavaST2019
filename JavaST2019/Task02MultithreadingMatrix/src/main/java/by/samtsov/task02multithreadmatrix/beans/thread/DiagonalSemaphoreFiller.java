package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.SemaphoreFillerService;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiagonalSemaphoreFiller extends MatrixDiagonalFiller {
    Semaphore semaphore;

    public DiagonalSemaphoreFiller(int newThreadNumber
            , SemaphoreFillerService semaphoreFillerService
            , Semaphore newSemaphore) {
        threadNumber = newThreadNumber;
        diagonalFillerService = semaphoreFillerService;
        semaphore = newSemaphore;
    }

    @Override
    public void run() {

        try {
            while (diagonalFillerService.getModifiedElements()
                    < diagonalFillerService.getMatrixDimension()) {
                semaphore.acquire();
                diagonalFillerService.modifyNextElement(threadNumber);
                semaphore.release();
                simulateWaiting(10);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * method that allows simulate time for some activity and give a time for
     * other threads to work with semaphore
     * @param milliseconds time in milliseconds to wait
     * @throws InterruptedException if has been called while thread is
     * already sleep
     */
    private void simulateWaiting(int milliseconds) throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(milliseconds);
    }
}
