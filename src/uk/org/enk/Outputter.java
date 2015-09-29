package uk.org.enk;

import java.io.PrintStream;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Wraps a result set for output
 */
public class Outputter {
    public Outputter(ResultSet rs) {
        this.rs = rs;
    }

    public void display() {
        this.display(System.out);
    }

    public void display(PrintStream out) {
        Map<Integer, Integer> histo = new TreeMap<Integer, Integer>(this.rs.histogram);

        for (Map.Entry<Integer, Integer> e: histo.entrySet()) {
            Integer key = e.getKey();
            Integer val = e.getValue();
            double range[] = this.rs.calculateHistogramConfidenceInterval(key);

            out.format("%2d - %7d ~ %f [%f - %f]%n", key, val, range[1], range[0], range[2]);
        }
        out.format("Number of samples  = %d%n", this.rs.getCount());
        out.format("Mean               = %f%n", this.rs.getMean());
        out.format("Standard deviation = %f%n", Math.sqrt(this.rs.getVariance()));
    }

    private ResultSet rs;
}
