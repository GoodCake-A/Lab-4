package bsuir.vmsis.lab4.solver;

import java.util.concurrent.CountDownLatch;
import javax.script.ScriptException;

import bsuir.vmsis.lab4.expressions.Equation;

class SolvingThread extends Thread
{
	private Equation equation;
	private int beginning;
	private int end;
	private Integer solution;
	private CountDownLatch counter;
	private volatile boolean endFlag; 

	@Override
	public void run()
	{
		solution=null;
		setEndFlag(false);
		for(int i=beginning;i<=end&&endFlag!=true;i++) {
			try {
				if(equation.replaceX(i)==0){
					solution=i;
					while(counter.getCount()>0)
						counter.countDown();
					return;
				}
			} catch(ScriptException ex) {
				 System.out.println(ex.getMessage());
			}
		}
		counter.countDown();
	}
	public Equation getEquation() {
		return equation;
	}
	public void setEquation(Equation equation) {
		this.equation = new Equation(equation.getStringForm());
	}
	public int getBeginning() {
		return beginning;
	}
	public void setBeginning(int beginning) {
		this.beginning = beginning;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public CountDownLatch getCounter() {
		return counter;
	}
	public void setCounter(CountDownLatch counter) {
		this.counter = counter;
	}
	public boolean isEndFlag() {
		return endFlag;
	}
	public void setEndFlag(boolean endFlag) {
		this.endFlag = endFlag;
	}
	public Integer getSolution() {
		return solution;
	}
}

