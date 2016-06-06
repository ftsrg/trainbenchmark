package hu.bme.mit.trainbenchmark.benchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.cli.ParseException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SerializationMain {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
	    Kryo kryo = new Kryo();
	    // ...
	    Output output = new Output(new FileOutputStream("/tmp/file.bin"));
	    BenchmarkConfig bc = new BenchmarkConfig();
	    	    
	    kryo.writeObject(output, bc);
	    output.close();
	    // ...
	    Input input = new Input(new FileInputStream("/tmp/file.bin"));
	    BenchmarkConfig someObject = kryo.readObject(input, BenchmarkConfig.class);
	    input.close();
	}
	
}
