package br.com.gabrielmusskopf.flourish.command;

public interface Param {

	String name();
	Object value();
	void setValue(Object value);

}
