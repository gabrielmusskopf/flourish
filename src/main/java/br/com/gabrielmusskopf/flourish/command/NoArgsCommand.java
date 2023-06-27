package br.com.gabrielmusskopf.flourish.command;

public abstract class NoArgsCommand extends AbstractCommand {

	public NoArgsCommand(String name) {
		super(name);
	}

	public NoArgsCommand(String name, int priority) {
		super(name, priority);
	}

}
