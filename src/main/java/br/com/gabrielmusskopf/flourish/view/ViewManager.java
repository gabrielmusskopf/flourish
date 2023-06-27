package br.com.gabrielmusskopf.flourish.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.com.gabrielmusskopf.flourish.Console;
import br.com.gabrielmusskopf.flourish.Flourish;
import br.com.gabrielmusskopf.flourish.command.Command;
import br.com.gabrielmusskopf.flourish.command.Parameterizable;
import br.com.gabrielmusskopf.flourish.exception.CommandNotFoundException;
import br.com.gabrielmusskopf.flourish.exception.EmptyViewHistoryException;
import br.com.gabrielmusskopf.flourish.exception.NoCurrentViewException;

public final class ViewManager {

	private final Stack<View> viewHistory = new Stack<>();
	private final List<View> views = new ArrayList<>();
	private final Console console = Flourish.console();

	public void start() {
		while (Flourish.running()) {
			showCurrent();
			var command = chose();
			execute(command);
		}
	}

	public ViewManager add(View view) {
		views.add(view);

		if (viewHistory.isEmpty()) {
			viewHistory.add(view);
		}

		return this;
	}

	public void goTo(String viewName) {
		var view = views.stream()
				.filter(v -> v.title().equals(viewName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("View does not exist!")); //mudar

		viewHistory.add(view);
	}

	public void goTo(View view) {
		boolean notRegistered = views.stream().noneMatch(v -> v.title().equals(view.title()));
		
		if (notRegistered) {
			throw new IllegalArgumentException("View does not exist!."); //mudar
		}
		
		viewHistory.add(view);
	}

	private void showCurrent() {
		var v = current();
		console.out("\n" + v.title() + "\n");
		v.commands().forEach(c -> console.out("%d - %s".formatted(c.getPriority(), c.getName())));
	}

	private Command chose() {
		var chosen = console.forceInt();

		return current()
				.commands()
				.stream()
				.filter(c -> c.getPriority() == chosen)
				.findFirst()
				.orElseThrow(CommandNotFoundException::new);
	}

	private void execute(Command command) {
		if (command instanceof Parameterizable com) {
			com.params().forEach(p -> {
				console.out("%s: ", p.name());
				p.setValue(console.input());
			});
		}

		command.execute();
	}

	private View current() {
		if (viewHistory.isEmpty()) {
			throw new NoCurrentViewException();
		}
		var v = viewHistory.peek();
		if (v == null) {
			throw new NoCurrentViewException();
		}
		return v;
	}

	public void goBack() {
		var view = viewHistory.pop();
		if (view == null ) {
			throw new EmptyViewHistoryException();
		}
	}

}
