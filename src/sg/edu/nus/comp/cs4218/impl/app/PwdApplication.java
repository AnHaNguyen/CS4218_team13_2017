package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

public class PwdApplication implements Application{

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		// TODO Auto-generated method stub
		if (stdout == null) {
			throw new EchoException("OutputStream not provided");
		}
		try {
			stdout.write(Environment.currentDirectory.getBytes());
			stdout.write("\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}