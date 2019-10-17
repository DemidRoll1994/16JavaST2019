package by.samtsov.task02multithreadmatrix.service.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidatorTest {

    @DataProvider(name = "validInputData")
    public Object[][] createData() {
        return new Object[][]{new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}
                , {0, 1, 0, 3}, {0, 1, 2, 0}},
                new int[][]{{0, 1, 2, 3, 0}, {0, 0, 2, 3, 0}
                        , {0, 1, 0, 3, 0}, {0, 1, 2, 0, 0}, {0, 1, 2, 0, 0}},
                new int[][]{{0, 1, 2}, {0, 0, 2}, {0, 1, 0}},
                new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}, {0, 1, 0, 3}
                        , {0, 1, 2, 0}},
                new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}, {0, 1, 0, 3}
                        , {0, 1, 2, 0}},
                new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 0, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 0, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 0, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 0, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 0, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 0, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 0, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 7, 0}}
        };

    }

    @DataProvider(name = "nonSquareMatrix")
    public Object[][] createData1() {
        return new Object[][]{new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}
                , {0, 1, 0, 3}},
                new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3, 0}
                        , {0, 1, 0, 3, 0}, {0, 1, 2, 0, 0}, {0, 1, 2, 0, 0}},
                new int[][]{{0, 1, 2}, {0, 0, 2}, {}},
                new int[][]{{0, 1, 2, 3}},
                new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}, {0, 1, 0, 3}
                        , {0, 1, 2, 0}, {0, 1, 2, 0}},
                new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 0, 2, 3, 4, 5, 6, 7}
                        , {0, 1, 0, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 0, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 0, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 0, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 0, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 0, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 7, 0}}
        };
    }

    @DataProvider(name = "diagonalNotEmpty")
    public Object[][] createData2() {
        return new Object[][]{new int[][]{{1, 1, 2, 3}, {0, 0, 2, 3}
                , {0, 1, 0, 3}, {0, 1, 2, 0}},
                new int[][]{{1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}
                        , {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
                new int[][]{{1, 1, 2}, {1, 0, 2}, {1, 0, 3}},
                new int[][]{{7, 7, 8, 8}, {8, 8, 7, 7}, {0, 1, 0, 3}
                        , {0, 1, 2, 0}},
                new int[][]{{0, 1, 2, 3}, {0, 0, 2, 3}, {0, 1, 0, 3}
                        , {0, 1, 2, 7}},
                new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 0, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 7, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 0, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 7, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 0, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 7, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 7, 8}
                        , {0, 1, 2, 3, 4, 5, 6, 7, 7}}
        };
    }

    @DataProvider(name = "notInitializedMatrix")
    public Object[][] createData3() {
        return new Object[][]{new int[][]{{}}};
    }

    @Test(dataProvider = "validInputData")
    public void isValidTest(int[][] matrix) {
        Assert.assertTrue(new Validator().isValid(matrix));
    }

    @Test(dataProvider = "nonSquareMatrix")
    public void isValidTest1(int[][] matrix) {
        Assert.assertFalse(new Validator().isValid(matrix));
    }

    @Test(dataProvider = "diagonalNotEmpty")
    public void isValidTest2(int[][] matrix) {
        Assert.assertFalse(new Validator().isValid(matrix));
    }

    @Test(dataProvider = "notInitializedMatrix")
    public void isValidTest3(int[][] matrix) {
        Assert.assertFalse(new Validator().isValid(matrix));
    }


}