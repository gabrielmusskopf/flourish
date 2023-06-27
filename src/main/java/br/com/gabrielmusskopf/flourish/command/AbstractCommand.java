package br.com.gabrielmusskopf.flourish.command;

import br.com.gabrielmusskopf.flourish.Console;
import br.com.gabrielmusskopf.flourish.Flourish;

public abstract class AbstractCommand implements Command {

	protected final String name;
	protected int priority;
	protected final Console console;

	protected AbstractCommand(String name) {
		this.name = name;
		this.console = Flourish.console();
	}

	protected AbstractCommand(String name, int priority) {
		this.name = name;
		this.priority = priority;
		this.console = Flourish.console();
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
