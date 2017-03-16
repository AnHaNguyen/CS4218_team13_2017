package sg.edu.nus.comp.cs4218.impl.cmd.PipeCommandTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImplementation;

public class PipeWithCmdSubTest {

	/*@Test
	public void testSortCatHead() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"sort `cat test-data/testPipe2.txt | head -n 3`",
				outStream);
		String expected = "Beetroot Carrot apple\n";
		assertEquals(expected, outStream.toString());
	}
	
	@Test
	public void testSortCatTail() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"sort `cat test-data/testPipe2.txt | tail -n 4`",
				outStream);
		String expected = "+ 1 12 2\n";
		assertEquals(expected, outStream.toString());
	}
	
	@Test
	public void testSortCatGrep() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"sort `cat test-data/testPipe2.txt | grep '1'`",
				outStream);
		String expected = "1 12\n";
		assertEquals(expected, outStream.toString());
	}*/
	
	@Test
	public void testEchoCatGrep() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"echo `cat test-data/testPipe2.txt | grep '1'`",
				outStream);
		String expected = "12 1\n";
		assertEquals(expected, outStream.toString());
	}
	
	@Test
	public void testEchoCatHead() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"echo `cat test-data/testPipe2.txt | head -n 3`",
				outStream);
		String expected = "Carrot apple Beetroot\n";
		assertEquals(expected, outStream.toString());
	}
	
	@Test
	public void testEchoCatTail() throws AbstractApplicationException, 
	ShellException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		ShellImplementation shImpl = new ShellImplementation();
		shImpl.parseAndEvaluate(
				"echo `cat test-data/testPipe2.txt | tail -n 4`",
				outStream);
		String expected = "+ 2 12 1\n";
		assertEquals(expected, outStream.toString());
	}
}
