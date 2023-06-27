package br.com.gabrielmusskopf.flourish;

public interface Console {

	void out(String s);
	void out(String s, Object... params);
	Integer getInt();
	int forceInt();
	String input();

}
