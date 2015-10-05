package uk.org.enk;

import org.apache.commons.cli.*;

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
        CommandLine cmd = null;

        Options options = new Options();
        options.addOption("n", "samples", true, "Number of samples");
        options.addOption("f", "file", true, "The expression script file");
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Encountered exception while parsing command line:\n" + e.getMessage());
        }

        try {
            script = new String(Files.readAllBytes(Paths.get(cmd.getOptionValue("f"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            N = Integer.parseInt(cmd.getOptionValue("n", "10000"));
        } catch (NumberFormatException e) {
            System.out.format("Invalid number %s - using 1000000 instead%n", args[1]);
            N = 1000000;
        }

        sim = new GroovyTrial(script);

        start = System.nanoTime();
        for (i = 0; i < N; ++i)
            results.add(sim.run());
        end = System.nanoTime();

        o = new Outputter(results);
        o.display();

        System.out.format("Program execution took %.2f seconds%n", (end-start)/1000000.0/1000.0);
    }
}
