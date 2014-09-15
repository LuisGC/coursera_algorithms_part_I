import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author luisgc
 */
public class PercolationStatsTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testOne() {

        PercolationStats perStats = new PercolationStats(200, 100);

        logStats(perStats, 200, 100);
        Assert.assertTrue("mean is incorrect", perStats.mean() > 0.5
                && perStats.mean() < 0.6);
        Assert.assertTrue("stddev is incorrect", perStats.stddev() > 0.008
                && perStats.stddev() < 0.011);
        Assert.assertTrue("confidenceLo is incorrect",
                perStats.confidenceLo() > 0.5 && perStats.confidenceLo() < 0.6);
        Assert.assertTrue("confidenceHi is incorrect",
                perStats.confidenceHi() > 0.5 && perStats.confidenceHi() < 0.6);
    }

    @Test
    public void testTwo() {

        PercolationStats perStats = new PercolationStats(200, 100);

        logStats(perStats, 200, 100);
        Assert.assertTrue("mean is incorrect", perStats.mean() > 0.5
                && perStats.mean() < 0.6);
        Assert.assertTrue("stddev is incorrect", perStats.stddev() > 0.008
                && perStats.stddev() < 0.011);
        Assert.assertTrue("confidenceLo is incorrect",
                perStats.confidenceLo() > 0.5 && perStats.confidenceLo() < 0.6);
        Assert.assertTrue("confidenceHi is incorrect",
                perStats.confidenceHi() > 0.5 && perStats.confidenceHi() < 0.6);
    }

    @Test
    public void testThree() {

        PercolationStats perStats = new PercolationStats(2, 10000);

        logStats(perStats, 2, 10000);
        Assert.assertTrue("mean is incorrect", perStats.mean() > 0.66
                && perStats.mean() < 0.67);
        Assert.assertTrue("stddev is incorrect", perStats.stddev() > 0.11
                && perStats.stddev() < 0.12);
        Assert.assertTrue("confidenceLo is incorrect",
                perStats.confidenceLo() > 0.6 && perStats.confidenceLo() < 0.7);
        Assert.assertTrue("confidenceHi is incorrect",
                perStats.confidenceHi() > 0.6 && perStats.confidenceHi() < 0.7);
    }

    @Test
    public void testFour() {

        PercolationStats perStats = new PercolationStats(2, 100000);

        logStats(perStats, 2, 100000);
        Assert.assertTrue("mean is incorrect", perStats.mean() > 0.66
                && perStats.mean() < 0.67);
        Assert.assertTrue("stddev is incorrect", perStats.stddev() > 0.117
                && perStats.stddev() < 0.118);
        Assert.assertTrue("confidenceLo is incorrect",
                perStats.confidenceLo() > 0.66
                        && perStats.confidenceLo() < 0.67);
        Assert.assertTrue("confidenceHi is incorrect",
                perStats.confidenceHi() > 0.66
                        && perStats.confidenceHi() < 0.67);
    }

    private void logStats(PercolationStats perStats, final int defaultSize,
            final int numberOfExperiments) {
        System.out.println("\n" + defaultSize + " " + numberOfExperiments);
        System.out.println("mean                    = " + perStats.mean());
        System.out.println("stddev                  = " + perStats.stddev());
        System.out.println("95% confidence interval = "
                + perStats.confidenceLo() + ", " + perStats.confidenceHi());
    }
}
