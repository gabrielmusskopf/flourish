package br.com.gabrielmusskopf.flourish.command;

import br.com.gabrielmusskopf.flourish.Flourish;

public class GoBackCommand extends AbstractCommand {

	public GoBackCommand(int priority) {
		this("Back", priority);
	}

	public GoBackCommand(String name, int priority) {
		super(name, priority);
	}

	@Override
	public void execute() {
		Flourish.viewManager().goBack();
	}

}
