package bsuir.vmsis.lab4.solver;

import java.util.concurrent.CountDownLatch;

import bsuir.vmsis.lab4.expressions.Equation;

public class MainThread {
	
	private Equation equation;
	private int begin;
	private int end;
	private short threadsNumber;
	private CountDownLatch counter;
	
	public Integer findSolution() {
		int segmentLength =(end-begin)/threadsNumber;
		SolvingThread[] threads;
		threads = new SolvingThread[threadsNumber];
		for(int i=0;i<threadsNumber;i++) {
			threads[i]= new SolvingThread();
			threads[i].setEquation(equation);
			threads[i].setBeginning(begin+i*segmentLength);
			threads[i].setEnd(begin+(i+1)*segmentLength);
			threads[i].setCounter(counter);
		}
		threads[threadsNumber-1].setEnd(end);
		for(int i=0;i<threadsNumber;i++)
			threads[i].run();
		try {
			counter.await();
		} catch (InterruptedException e) {}
		for(int i=0;i<threadsNumber;i++)
			threads[i].setEndFlag(true);
		for(int i=0;i<threadsNumber;i++)
			if(threads[i].getSolution()!=null)
				return threads[i].getSolution();
		return null;
	}
	
	public MainThread(String equation, int begin, int end) {
		counter= new CountDownLatch(threadsNumber);
		this.begin=begin;
		this.end=end;
		this.setEquation(new Equation(equation));
	}
	
	public MainThread() {
		counter= new CountDownLatch(threadsNumber);
		this.setEquation(new Equation(""));
		threadsNumber=4;
	}

	public Equation getEquation() {
		return equation;
	}

	public void setEquation(Equation equation) {
		this.equation = equation;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public short getThreadsNumber() {
		return threadsNumber;
	}

	public void setThreadsNumber(short threadsNumber) {
		this.threadsNumber = threadsNumber;
	}
}
