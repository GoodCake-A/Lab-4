package bsuir.vmsis.lab4.gui;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import bsuir.vmsis.lab4.expressions.Equation;
import bsuir.vmsis.lab4.solver.MainThread;

import java.awt.Dimension;
import javax.script.ScriptException;


final public class GUI {	
	public static void createGUI() {
        JFrame frame = new JFrame("Подбор целого решения уравнения");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton integrateButton =new JButton("Подобрать");   
        JLabel label1=new JLabel("  Уравнение f(x)=0: ",JLabel.LEFT);
        JTextField equation=new JTextField(30);
        JLabel label1_1=new JLabel("= 0",JLabel.LEFT);
        JLabel label2=new JLabel("  Начало интервала ",JLabel.LEFT);
        JTextField begin=new JTextField(12);
        JLabel label3=new JLabel("  Конец интервала ",JLabel.LEFT);
        JTextField end=new JTextField(12);
        JLabel label4=new JLabel("Корень: ",JLabel.LEFT);
        JLabel solution=new JLabel("",JLabel.LEFT);
        JPanel mainPanel,topPanel, middlePanel, middlePanelBegin, middlePanelEnd, bottomPanel, answerPanel;
        mainPanel=new JPanel();
        mainPanel.setLayout(null);
        topPanel=new JPanel();
        topPanel.setLayout(new BorderLayout());
        middlePanel=new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanelBegin=new JPanel();
        middlePanelBegin.setLayout(new BorderLayout());
        middlePanelEnd=new JPanel();
        middlePanelEnd.setLayout(new BorderLayout());
        bottomPanel=new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        answerPanel=new JPanel();
        answerPanel.setLayout(new FlowLayout());
        
        frame.add(mainPanel);
        int lineWidth=350,lineHeight=28,leftGap=5;
        mainPanel.add(topPanel);
        topPanel.setBounds(leftGap, 0, lineWidth, lineHeight);
        middlePanel.setBounds(leftGap, 0+lineHeight, lineWidth, 2*lineHeight-10);
        bottomPanel.setBounds(leftGap, 0+3*lineHeight, lineWidth, lineHeight+10);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel);
        middlePanel.add(middlePanelBegin, BorderLayout.NORTH);
        middlePanel.add(middlePanelEnd, BorderLayout.SOUTH);
        topPanel.add(label1, BorderLayout.WEST);
        topPanel.add(equation, BorderLayout.CENTER);
        topPanel.add(label1_1, BorderLayout.EAST);
        middlePanelBegin.add(label2, BorderLayout.WEST);
        middlePanelBegin.add(begin, BorderLayout.CENTER);
        middlePanelEnd.add(label3, BorderLayout.WEST);
        middlePanelEnd.add(end, BorderLayout.CENTER);
        bottomPanel.add(answerPanel,BorderLayout.WEST);
        answerPanel.add(integrateButton,FlowLayout.LEFT);
        answerPanel.add(solution,FlowLayout.LEFT);
        answerPanel.add(label4,FlowLayout.LEFT);
        frame.setSize(new Dimension(lineWidth+40+100, lineHeight*4+50));
        frame.setResizable(false);
        frame.setVisible(true);  
        
        integrateButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
                try {
            		Equation eq=new Equation();
            		eq.setStringForm(equation.getText());
            		eq.replaceX(Integer.parseInt(begin.getText()));
            		MainThread solver= new MainThread();
            		solver.setEquation(eq);
            		if(Integer.parseInt(begin.getText())>Integer.parseInt(end.getText()))
            			throw new NumberFormatException();
            		solver.setBegin(Integer.parseInt(begin.getText()));
            		solver.setEnd(Integer.parseInt(end.getText()));
            		Integer answer=solver.findSolution();
            		if(answer==null)
            			solution.setText("Целочисленных решений нет");
            		else
            			solution.setText(Integer.toString(answer));
        		} catch(NumberFormatException exeption) {
        			solution.setText("Некорректный интервал");
        		} catch(ScriptException exeption) {
        			solution.setText("Некорректное выражение");
        		}
        	}
        });
	}
	
}

