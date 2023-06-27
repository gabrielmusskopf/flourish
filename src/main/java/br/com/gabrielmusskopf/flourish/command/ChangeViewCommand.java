package br.com.gabrielmusskopf.flourish.command;

import br.com.gabrielmusskopf.flourish.Flourish;

public class ChangeViewCommand extends AbstractCommand {

	private final String nextViewName;

	public ChangeViewCommand(String name, String nextViewName) {
		super(name);
		this.nextViewName = nextViewName;
	}

	public ChangeViewCommand(String name, String nextViewName, int priority) {
		super(name, priority);
		this.nextViewName = nextViewName;
	}

	@Override
	public final void execute() {
		beforeChange();
		Flourish.viewManager().goTo(nextViewName);
		afterChange();
	}

	protected void beforeChange() {
		//Override if you want to do someting
	}

	protected void afterChange() {
		//Override if you want to do someting
	}

}
