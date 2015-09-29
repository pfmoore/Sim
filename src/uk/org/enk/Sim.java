package uk.org.enk;


public class Sim {
    static String script =
            "int roll() {\n" +
            "rng.nextInt(6) + 1\n" +
            "}\n" +
            "l = [roll(), roll(), roll(), roll(), roll()]\n" +
            "l.sort()\n" +
            "l[2] + l[3] + l[4]\n";

    public static void main(String[] args) {
        ResultSet results = new ResultSet();
        Trial sim = new Trial();
        // GroovyTrial sim = new GroovyTrial(script);
        int i;
        int tot = 0;
        int N = 10000000;
        long start = System.nanoTime();
        long end;
        Outputter o;

        for (i = 0; i < N; ++i)
            results.add(sim.run());

        o = new Outputter(results);
        o.display();

        end = System.nanoTime();
        System.out.format("Program execution took %.2f seconds%n", (end-start)/1000000.0/1000.0);
    }
}
