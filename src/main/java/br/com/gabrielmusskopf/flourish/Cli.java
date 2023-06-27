package br.com.gabrielmusskopf.flourish;

import java.util.Scanner;

public class Cli implements Console {

	private static Scanner SCANNER = new Scanner(System.in).useDelimiter("/n");

	public void out(String s) {
		System.out.println(s);
	}

	public void out(String s, Object... params) {
		System.out.printf(s, params);
	}

	public Integer getInt() {
		try {
			var n = SCANNER.nextLine();
			return Integer.parseInt(n);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int forceInt() {
		Integer x = getInt();
		while (x == null) x = getInt();
		return x;
	}

	public String input() {
		return SCANNER.nextLine();
	}
}
