package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.CountDownLatchFillerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchFiller extends MatrixDiagonalFiller {

    private static final Logger logger = LogManager.getLogger();

    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    private CountDownLatch countDown;
    private int numberOfElementToStop;

    public CountDownLatchFiller(int fillerNumber
            , CountDownLatchFillerService countDownLatchFillerService
            , int number_of_element_to_stop) {
        threadNumber = fillerNumber;
        diagonalFillerService = countDownLatchFillerService;
        numberOfElementToStop = number_of_element_to_stop;
        countDown = countDownLatchFillerService.getCountDownLatch();
    }

    @Override
    public void run() {

        int i = 0;
        // to avoid a deadlock we don't place a barrier for last non full
        // pack of elements. Last full-pack element has next index:
        int lastModifiedWithBarrierElement
                = diagonalFillerService.getMatrixDimension()
                - diagonalFillerService.getMatrixDimension()
                % (numberOfElementToStop * (int) countDown.getCount()) + 1;
        while (diagonalFillerService.getModifiedElements()
                < diagonalFillerService.getMatrixDimension()) {


            diagonalFillerService.modifyNextElement(threadNumber);

            if (++i % numberOfElementToStop == 0
                    && diagonalFillerService.getModifiedElements()
                    < lastModifiedWithBarrierElement) {
                try {
                    countDown.await(1000, TimeUnit.MILLISECONDS);
                    countDown.countDown();
                } catch (InterruptedException e) {
                    logger.error(String.format(STANDARD_ERROR_FORMAT
                            , Thread.currentThread().getName()
                            , e.getMessage()));
                }
            }
        }
    }
}
