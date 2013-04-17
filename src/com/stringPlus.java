package com;

public class stringPlus {
	public String stringAppend(String input){
		String result = input.replaceAll("\\s", "+");
		return result;
	}
}
