package br.com.gabrielmusskopf.flourish.command;

public interface Command {

	String getName();
	int getPriority();
	void setPriority(int i);
	void execute();

}
