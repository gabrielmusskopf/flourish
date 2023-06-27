package br.com.gabrielmusskopf.flourish.command;

import br.com.gabrielmusskopf.flourish.Flourish;

public class FinishCommand extends NoArgsCommand {

	public FinishCommand(int priority) {
		super("Finish", priority);
	}

	public FinishCommand(String name, int priority) {
		super(name, priority);
	}

	@Override
	public void execute() {
		Flourish.finish();
	}

}
