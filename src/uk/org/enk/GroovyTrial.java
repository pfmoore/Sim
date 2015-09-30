package uk.org.enk;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

// Note that on a simple 3d6 example, this is approximately half the speed of Trial.
// The difference may be less significant (or more!) on more complex expressions.
public class GroovyTrial implements Expression {
    static RandomGenerator rng = new MersenneTwister();
    Script script;

    public GroovyTrial(String expr) {
        GroovyShell shell = new GroovyShell();
        this.script = shell.parse(expr);
        Binding b = new Binding();
        b.setVariable("rng", rng);
        b.setVariable("trial", this);
        this.script.setBinding(b);
    }

    public int run() {
        return (int)this.script.run();
    }
    public int[] dice(int n, int m) {
        int[] ret = new int[n];
        for (int i = 0; i < n; ++i) {
            ret[i] = rng.nextInt(m) + 1;
        }
        return ret;
    }
}
