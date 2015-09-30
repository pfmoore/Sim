package uk.org.enk;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sim {

    public static void main(String[] args) {
        ResultSet results = new ResultSet();
        // Trial sim = new Trial();
        String script = "";
        Expression sim;
        int i;
        int tot = 0;
        int N;
        long start;
        long end;
        Outputter o;

        try {
            script = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            N = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.format("Invalid number %s - using 1000000 instead%n", args[1]);
            N = 1000000;
        }

        sim = new GroovyTrial(script);

        start = System.nanoTime();;
        for (i = 0; i < N; ++i)
            results.add(sim.run());
        end = System.nanoTime();

        o = new Outputter(results);
        o.display();

        System.out.format("Program execution took %.2f seconds%n", (end-start)/1000000.0/1000.0);
    }
}
