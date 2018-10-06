package LindXdeep.calculator;

public class Result {
	public double acc;		//текущий (отделенный) результат (аккомулятор)
	public String rest;		//остаток строки для парсингаж
	public Result(double acc, String rest) {
		this.acc = acc;
		this.rest = rest;
	}
}
