package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noise_parameters.interpolation.Interpolation;
import de.articdive.jnoise.pipeline.JNoise;

import java.util.Random;

public class Hills implements LandAltitude{

    JNoise perlin;

    public AltitudeFunction function= (x,y)-> {
        Double noise = perlin.evaluateNoise(x, y);

        return Math.max(0, Math.abs(1000*noise));
    };
    public AltitudeFunction getFunction(){

        perlin = JNoise.newBuilder().perlin(IslandCommandLineReader.randomGenerator.getSeed(), Interpolation.COSINE, FadeFunction.SMOOTHSTEP).build();
        return this.function;
    }
}
