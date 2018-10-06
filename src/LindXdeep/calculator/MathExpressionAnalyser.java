package LindXdeep.calculator;

import java.util.HashMap;

//����� ������������� ����������� ������� �.� ����� ������� ��������� � �������� ������� (������ ���� �����)
public class MathExpressionAnalyser {
	
	private HashMap<String, Double> variables;

    public MathExpressionAnalyser() {
        variables = new HashMap<String, Double>();
    }

    public void setVariable(String variableName, Double variableValue){
        variables.put(variableName, variableValue);
    }

    public Double getVariable(String variableName){
        if (!variables.containsKey(variableName)) {
            System.err.println( "Error: Try get unexists variable '"+variableName+"'" );
            return 0.0;
        }
        return variables.get(variableName);
    }
	
	Result result;		
	
	public void toAnalyze(String str) {
		result = plusMinus(str);
	}
	
	public String getResultAnalize() {
		
		int i = 0;
		
		if(!result.rest.isEmpty()) {
			while(result.rest.charAt(i++) != ':' && i < result.rest.length() ) {
				if(result.rest.charAt(i) == ':') {
					return result.rest.substring(i+1);
				}
			}
		}
			if(result.acc == 0.0 )
				return String.valueOf(0);
			else
				return String.valueOf(result.acc);
	}
	
	//1. ������� �������������� �������� � ���������
	public Result plusMinus(String str) {
		
		//�� ������� �������� ���� �� �������� ��������� � ������� 
		Result result = multDiv(str);
		double acc = result.acc;		// ��������� ������� ����������
		
		while(result.rest.length()> 0) {
			if(! (result.rest.charAt(0) == '+' || result.rest.charAt(0) == '-') ) 
				break;
			
			char sign = result.rest.charAt(0);
			String next_str = result.rest.substring(1);
	
			result = multDiv(next_str);		//�������� �� ������ ��������
			
			if(sign == '+')
				acc += result.acc;
			else
				acc -= result.acc;
		}
		
		return new Result(acc, result.rest);
	}
	
	//2. ������� �������������� ��������� � �������
	public Result multDiv(String str) {
		
		//�� ����� �������� � ������� ��������,  ����� �� ��������� � ���������. 	
		Result result = brackets(str);
		
		double acc = result.acc;
		
		while(true) {
			if (result.rest.length() == 0)
				return result;
	      
			char sign = result.rest.charAt(0);
			if(	!(sign == 'x' || sign == '/' || sign == '^'|| sign == '%'))
				break;
						
			String next_str = result.rest.substring(1);
					
			Result right = brackets(next_str);
		
			if(sign == 'x')
				acc *= right.acc;
			if(sign == '/')
				acc /= right.acc;
			if(sign == '^')
				acc = Math.pow(acc, right.acc);
			if(sign == '%' && (acc != 0 && right.acc != 0)) {
				
				acc = Math.floorDiv((int)acc, (int)right.acc);
			}

			result = new Result(acc, right.rest);	
		}
		return result;	
	}
	
	//��� ����� ��������� ����� ��������
	private Result brackets(String str) {
				
		char zero_braket = ' ';
		if(!str.isEmpty())
			zero_braket = str.charAt(0);
			
		if(zero_braket == '(') {
			Result result = plusMinus(str.substring(1));
			if(!result.rest.isEmpty() && result.rest.charAt(0) ==')')
				result.rest = result.rest.substring(1);
			else
				result = Result(0, "Error:not close bracket");
			return result;
		}

		//3.���� ���������� ����� ������ ������� 
		return functionVariable(str);
	}
	
	private Result Result(double d, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private Result functionVariable(String str) {
		String f = "";
        int i = 0;
        // ���� �������� ������� ��� ���������� ��� ����������� ������ ���������� � �����
        while (i < str.length() && (Character.isLetter(str.charAt(i)) || ( Character.isDigit(str.charAt(i)) && i > 0 ) )) {
            f += str.charAt(i);
            i++;
        }
        if (!f.isEmpty()) { // ���� ���-������ �����
            if ( str.length() > i && str.charAt( i ) == '(') { 		// � ��������� ������ ������ ������ - ��� �������
                Result r = brackets(str.substring(f.length()));
                return processFunction(f, r);
            } else { // ����� - ��� ����������
                return new Result(getVariable(f), str.substring(f.length()));
            }
        }
		
		//4. � ��������� ����� ������������� ���������� ����� �� ������������
		return num(str);
	}
	
	//5.����� ����� ��������� ����������� ������ �����
	private Result num(String str) {
		
		if(str.isEmpty()) {
			return new Result(0, "Caution: 0");
		}
		
		boolean negative = false;
		if(str.charAt(0) == '-') {
			negative = true;
			str = str.substring(1);
		}
		
		int idx = 0;
		int dot_counter = 0;
		//���������: ������ ����� � ����� 
		while(idx < str.length() && (Character.isDigit(str.charAt(idx)) || str.charAt(idx) =='.' )) {
			if(str.charAt(idx) == '.' && ++dot_counter > 1) {
				return new Result(0, "Error:Can't be double dot");
			}
			idx++;
		}
		
		double acc = 0;
		if(!str.isEmpty() && Character.isDigit(str.charAt(0)))
			acc = Double.parseDouble(str.substring(0, idx));
				
		if(negative)
			acc = -acc;
		String rest = str.substring(idx);
						
		return new Result(acc, rest);
	}
	
	private Result processFunction(String func, Result r)
    {
        if (func.equals("sin")) {
            return new Result(Math.sin(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("cos")) {
            return new Result(Math.cos(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("tan")) {
            return new Result(Math.tan(Math.toRadians(r.acc)), r.rest);
        } else {
            System.err.println("function '" + func + "' is not defined");
        }
        return r;
    }
}