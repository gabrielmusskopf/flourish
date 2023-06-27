package br.com.gabrielmusskopf.flourish.view;

import java.util.List;

import br.com.gabrielmusskopf.flourish.command.Command;

public class SimpleView implements View {

	private final String title;
	private final List<Command> commands;

	public SimpleView(String title, List<Command> commands) {
		this.title = title;
		this.commands = commands;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public List<Command> commands() {
		return commands;
	}

}
