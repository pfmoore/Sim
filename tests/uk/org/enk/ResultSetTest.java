package uk.org.enk;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the result set class.
 */
public class ResultSetTest {
    @Test
    public void testNewResultSetIsEmpty() throws Exception {
        ResultSet rs = new ResultSet();
        assertEquals(0, rs.getCount());
    }
    @Test
    public void testAddResultToSet() throws Exception {
        ResultSet rs;
        long start;
        int[] counts = {1, 10, 100, 1000, 10000, 100000, 1000000}; //, 1000000000};
        int[] values = {1, 10, 100, 1000, 10000, 100000, 1000000, 1000000000};

        for (int N: counts) {
            System.out.format("Testing %d values%n", N);
            for (int val: values) {
                System.out.format("    Value = %d%n", val);
                System.out.format("    Elapsed time... ");
                start = System.nanoTime();
                rs = new ResultSet();
                for (int i = 0; i < N; ++i) {
                    rs.add(val);
                }
                assertEquals(N, rs.getCount());
                // We require exact equality, so tolerance is zero
                assertEquals(val, rs.getMean(), 0);
                assertEquals(0.0, rs.getVariance(), 0);
                System.out.format(" %.4f sec%n", (System.nanoTime() - start) / 1000000.0 / 1000.0);
            }

            System.out.format("    Value = <variable>%n");
            System.out.format("    Elapsed time... ");
            start = System.nanoTime();
            rs = new ResultSet();
            for (int i = 0; i < N; ++i) {
                rs.add(i);
            }
            assertEquals(N, rs.getCount());
            // We require exact equality, so tolerance is zero
            assertEquals((N - 1) / 2.0, rs.getMean(), 0);
            System.out.format(" %.4f sec%n", (System.nanoTime() - start) / 1000000.0 / 1000.0);
        }
    }
}