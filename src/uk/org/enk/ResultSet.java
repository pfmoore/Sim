package uk.org.enk;

// import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.Map;
import java.util.HashMap;

/**
 * A set of simulation results.
 */
public class ResultSet {
    public ResultSet() {
        // Using SummaryStatistics appears substantially slower...
        // Need to profile this!
        // stats = new SummaryStatistics();
        this.count = 0;
        this.mean = 0;
        this.squaredDeviation = 0;
        this.histogram = new HashMap<Integer, Integer>();
    }

    public long getCount() {
        // return this.stats.getN();
        return this.count;
    }

    public void add(int result) {
        // this.stats.addValue(result);

        // Welford's recurrence relation for variance
        // From http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
        double oldM = this.mean;
        this.count = this.count + 1;
        this.mean = this.mean + (result - this.mean)/this.count;
        this.squaredDeviation = this.squaredDeviation + (result - this.mean) * (result - oldM);

        this.histogram.put(result, this.histogram.getOrDefault(result, 0) + 1);
    }

    // Calculate the Agresti-Coull confidence interval for a value
    // See https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Agresti-Coull_Interval
    public double[] calculateHistogramConfidenceInterval(Integer value) {
        double z = 1.96; // 95% CI, hard code for now
        double n = this.count + z * z;
        // Possible exception here if value is missing...
        double p = (this.histogram.get(value) + (z * z) / 2) / n;
        double error = z * Math.sqrt(p * (1 - p) / n);
        // Range is p +/- error
        double ret[] = new double[3];
        ret[0] = p - error;
        ret[1] = p;
        ret[2] = p + error;
        return ret;
    }
    public double getMean() {
        //System.out.format("Sum = %d%nCount = %d%n", this.sum, this.count);
        return this.mean;
        // return this.stats.getMean();
    }

    public double getVariance() {
        // Note this is the sample variance, so we divide by N-1
        // The formula breaks down for 1 sample, so special case it
        return (this.count < 2 ? 0 : this.squaredDeviation / (this.count - 1));
        // return this.stats.getVariance();
    }

    // Sample count
    private long count;
    // Mean
    private double mean;
    // Sum of squared deviation from the mean
    private double squaredDeviation;

    public Map<Integer, Integer> histogram;
    // Commons math summary statistics
    // SummaryStatistics stats;
}