package bsuir.vmsis.lab4.expressions;

import javax.script.*;

public class Equation {

	private String stringForm;
	private ScriptEngineManager mgr;
    private ScriptEngine engine;
    
	public double replaceX(int number) throws ScriptException {
		String xValue=String.valueOf(number);
		String expression=stringForm.replaceAll("x", xValue);
	    Object result = engine.eval(expression);
	    if(result instanceof Integer)
	    	return (Integer)result;
	    return (Double)result;
	}
	public String getStringForm() {
		return stringForm;
	}

	public void setStringForm(String stringForm) {
		this.stringForm = stringForm;
	}
	
	public Equation() {
		this.stringForm="";
		mgr = new ScriptEngineManager();
	    engine = mgr.getEngineByName("JavaScript");
	}
	
	public Equation(String stringForm) {
		this.stringForm=stringForm;
		mgr = new ScriptEngineManager();
	    engine = mgr.getEngineByName("JavaScript");

	}
}
