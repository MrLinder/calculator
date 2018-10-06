package LindXdeep.calculator;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Panel extends JPanel {
	
	int frameWidth, frameHight, ScreenHight, indentHight, indentWidth, ButtonSize;
		
	Font fontScreen = new Font("Arial Narrow", Font.BOLD, 40);
	Font fontResult = new Font("Arial Narrow", Font.BOLD, 50);
	Font fontNums = new Font("Arial", Font.BOLD, 25);
	
	Desing color = Desing.inut();
			
	JTextArea calcScreen =  new JTextArea(0, 20);
	JTextField resultScreen =  new JTextField();
	
	JMenuBar menuBar =  new JMenuBar();
	JMenu fileMenu = new JMenu("about");
	
	JButton[] numbers =  new JButton[20];
	JButton[] calcOper = new JButton[10];
	String[] numsSymbols = {"<", ">", "<<|",
							"7", "8", "9",
							"4", "5", "6",
							"1", "2", "3",
							"()","0", "."};
	String[] calcSymbols = {"C", "/", "x", "-", "+", "^", "%", "sin", "cos", "tan"};
	
	int caretPos;
	
	public Panel(JFrame frame) {
		this.frameWidth = frame.getWidth();
		this.frameHight = frame.getHeight();
		
		ScreenHight = frameHight/4; 					//высота экрана вывода
		ButtonSize = (frameWidth-(5+1))/5;				//размер кнопок
				
		this.setName("Calcalator");
		this.setLayout(null);
		this.createGUI();
		this.setBackground(color.getColorBack());
	}

	private void createGUI() {
			
		JMenuItem itemabout = new JMenuItem("<Html><font size = +1>linder.igor@gmail.com");
					itemabout.setBackground(Color.BLACK);
					itemabout.setForeground(Color.WHITE);
					itemabout.setBorder(null);
					itemabout.setFocusPainted(false);
					itemabout.setBorderPainted(false);
		JMenu fileMenu = new JMenu("<Html><font size = +1>about");
			    fileMenu.add(itemabout);
				fileMenu.setBackground(Color.BLACK);
			    fileMenu.setForeground(Color.WHITE);
			    fileMenu.setFocusPainted(false);
			    fileMenu.setBorderPainted(false);
		  
		menuBar.add(fileMenu);
		menuBar.setBounds(0, 0, frameWidth, ScreenHight/7);
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		menuBar.setBorder(null);
		
		add(menuBar);
		
		calcScreen.setBounds(0, 20, frameWidth-5, ScreenHight);
		calcScreen.setFont(fontScreen);
		calcScreen.setBackground(Color.BLACK);
		calcScreen.setForeground(Color.WHITE);
		calcScreen.setLineWrap(true);
		calcScreen.setWrapStyleWord(true);
		calcScreen.setCaretColor(Color.WHITE);
		calcScreen.addCaretListener(new ResultsCalculation());
		calcScreen.setEditable(false);
				
		JScrollPane calcSreeenScroll = new JScrollPane(calcScreen);
					calcSreeenScroll.setBounds(0, 20, frameWidth-5, ScreenHight);
					calcSreeenScroll.getVerticalScrollBar().setUI(new ChangeScrollBarColor());
					calcSreeenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					calcSreeenScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					calcSreeenScroll.setBorder(null);
		add(calcSreeenScroll);
	
		
		this.indentHight = calcScreen.getHeight()+20;		//оступ для numPad'a
			
		resultScreen.setFont(fontResult);
		resultScreen.setBackground(Color.BLACK);
		resultScreen.setForeground(Color.WHITE);
		resultScreen.setBorder(new CompoundBorder(new EmptyBorder(1, 2, 2, 1), new LineBorder(color.getColorBack(), 1)));
		resultScreen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultScreen.setText("0");
		resultScreen.setEditable(false);
		
		JScrollPane resultSreeenScroll = new JScrollPane(resultScreen);
					resultSreeenScroll.setBounds(0, indentHight, frameWidth-10, ButtonSize);
					resultSreeenScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					resultSreeenScroll.setBorder(null);
		add(resultSreeenScroll);
			
		
		for(int y = 0; y<5; y++) {
			for(int x = 0; x < 3; x++) {
				int num = y*3+x;
				numbers[num] = new JButton(numsSymbols[num]);
				numbers[num].setBounds(x*ButtonSize, (indentHight+ButtonSize)+y*ButtonSize, ButtonSize, ButtonSize);
				numbers[num].setFont(fontNums);
				numbers[num].setBackground(color.getColorBack());
				numbers[num].setForeground(Color.WHITE);
				numbers[num].setFocusPainted(false);
				numbers[num].setBorderPainted(false);
				numbers[num].addMouseListener(new ActionMouse(numbers[num]));
				add(numbers[num]);
			}
		}
		
		this.indentWidth = ButtonSize*3;
		
		for(int x = 0; x<2; x++) {
			for(int y = 0; y<5; y++) {
				int n = x*5+y;
				calcOper[n] = new JButton(calcSymbols[n]);
				calcOper[n].setName("calcOper");
				calcOper[n].setPreferredSize(numbers[1].getPreferredSize());
				calcOper[n].setSize(numbers[1].getSize());
				calcOper[n].setLocation(indentWidth+x*ButtonSize, (indentHight+ButtonSize)+y*(ButtonSize));
				calcOper[n].setFont(fontNums);
				calcOper[n].setBackground(color.getColorBack());
				calcOper[n].setForeground(Color.WHITE);
				calcOper[n].setFocusPainted(false);
				calcOper[n].setBorder(new LineBorder(Color.GREEN));
				calcOper[n].setBorderPainted(false);
				if(n<5) 
					calcOper[n].setBorderPainted(true);
				if(calcSymbols[n] == " " )
					calcOper[n].setVisible(false);
				calcOper[n].addMouseListener(new ActionMouse(calcOper[n]));				
				add(calcOper[n]);
			}
		}
				
	}

	private class ActionMouse implements MouseListener{
		
		JButton b = new JButton();
				
		public ActionMouse(JButton b) {
				this.b = b;	
				calcScreen.getCaret().setVisible(true);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(b.getName() == "calcOper")
				resultScreen.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 1), new LineBorder(color.getColorBack(), 1)));
			else
				resultScreen.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 1), new LineBorder(color.getColorBack(), 1)));
			
			JButton B = (JButton) e.getSource();
			
			String s = B.getText();
						
			calcScreen.getCaret().setVisible(true);
							
			switch (s){
			case "C":
					calcScreen.setText("");
					caretPos=0;
					calcScreen.setCaretPosition(caretPos);
				break;
			
			case "<<|":
				if(caretPos != 0) {
					String strSrc = new String(calcScreen.getText()); 
					String strSub_1 = strSrc.substring(0, caretPos);
					String strSub_2 = strSrc.substring(caretPos, strSrc.length());

					strSub_1 = strSub_1.substring(0, --caretPos);
				
					calcScreen.setText(new StringBuffer().append(strSub_1).append(strSub_2).toString());
					calcScreen.setCaretPosition(caretPos);	
				}
				break;
			case "<":
				if(caretPos != 0)
					calcScreen.setCaretPosition(--caretPos);
				break;
				
			case ">":
				if(caretPos != calcScreen.getDocument().getLength())
					calcScreen.setCaretPosition(++caretPos);
				break;
				
			case "()":
				calcScreen.insert("()", caretPos);
				calcScreen.setCaretPosition(++caretPos);
				break;
			case "cos":
				calcScreen.insert("cos()", caretPos);
				calcScreen.setCaretPosition(caretPos=caretPos+4);
				break;	
			case "sin":
				calcScreen.insert("sin()", caretPos);
				calcScreen.setCaretPosition(caretPos=caretPos+4);
				break;	
			case "tan":
				calcScreen.insert("sin()", caretPos);
				calcScreen.setCaretPosition(caretPos=caretPos+4);
				break;
			case ".":
				if(caretPos !=0 && Character.isDigit(calcScreen.getText().charAt(caretPos-1))) {
					calcScreen.insert(s, caretPos++);
				}
			case "-":
				if(caretPos == 0)
					calcScreen.insert(s, caretPos++);
				if(caretPos > 0 && Character.isDigit(calcScreen.getText().charAt(caretPos-1)))
					calcScreen.insert(s, caretPos++);
				break;
			default:
				for(String i : calcSymbols) {
					if( !s.equals("C") && s.equals(i)) {
						if(caretPos != 0 && (Character.isDigit(calcScreen.getText().charAt(caretPos-1)) || calcScreen.getText().charAt(caretPos-1) ==')') ) 
						{
							String last_opr = calcScreen.getText().substring(caretPos);
								
							if(!last_opr.equals(s)) {
								calcScreen.insert(s, caretPos );
								caretPos =+ caretPos+s.length();
							}
						}
					}
				}
				break;
			}
						
			//Исключение добавления символов не имеющих отношение к математическому выроажению и тех что обрабатываются выше
			boolean cont_oper = false;
			for(String i : calcSymbols ) {
				if(s.equals(i) || s.equals("<") || s.equals(">") || s.equals("<<|") || s.equals("()") || s.equals(".")){
					cont_oper = true;
					break;
				}
			}
			if(!cont_oper)
				calcScreen.insert(s, caretPos++);
		}	
					
		@Override
		public void mousePressed(MouseEvent e) {
								
			if(b.getName() == "calcOper")
				resultScreen.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 1), new LineBorder(color.getColorClick(), 1)));
			else
				resultScreen.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 1), new LineBorder(color.getColorBack(), 1)));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			this.b.setBackground(color.getColorBack());
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			this.b.setBackground(color.getColorFocus());
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			this.b.setBackground(color.getColorFocus());
		}
	}
	
	public class ResultsCalculation implements CaretListener{

		MathExpressionAnalyser expression = new MathExpressionAnalyser();
								
		@Override
		public void caretUpdate(CaretEvent e) {

			expression.toAnalyze(calcScreen.getText());
			
			String result = new String(expression.getResultAnalize());	
				
				if(result.startsWith("-")) 
					result = result.substring(1).concat("-"); 				//так как строка выводится наоборот уберем минус//и поставим его с другой стороны
				
				resultScreen.setText(result);	
		}
	}
}
