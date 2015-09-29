package uk.org.enk;

// the standard RNG is not suitable for simulations :-(
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Collections;


public class Trial {
    static RandomGenerator rng = new MersenneTwister();
    private ArrayList<Integer> dice(int n, int sides) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (n-- > 0) {
            ret.add(rng.nextInt(sides) + 1);
        }
        return ret;
    }
    public int run() {
        int tot = 0;
        ArrayList<Integer> vals = dice(5, 6);
        Collections.sort(vals);
        vals.remove(0);
        vals.remove(0);
        for (int n : vals) {
            tot += n;
        }
        return tot;
    }
}
