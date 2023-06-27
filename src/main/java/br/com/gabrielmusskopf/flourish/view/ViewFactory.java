package br.com.gabrielmusskopf.flourish.view;

import java.util.ArrayList;
import java.util.List;

import br.com.gabrielmusskopf.flourish.command.ChangeViewCommand;
import br.com.gabrielmusskopf.flourish.command.Command;
import br.com.gabrielmusskopf.flourish.command.FinishCommand;
import br.com.gabrielmusskopf.flourish.command.GoBackCommand;
import br.com.gabrielmusskopf.flourish.exception.CommandNotFoundException;

public class ViewFactory {

	public static ViewBuilder create(String name){
		return new ViewBuilder(name);
	}

	public static class ViewBuilder {

		private final String name;
		private final List<Command> commands;
		private FixedCommand withFinish;
		private FixedCommand withGoBack;

		public ViewBuilder(String name) {
			this.commands = new ArrayList<>();
			this.name = name;
			this.withGoBack = new FixedCommand("Back");
			this.withFinish = new FixedCommand("Finish");
		}

		public ViewBuilder command(Command command) {
			command.setPriority(commands.size() + 1);
			commands.add(command);
			return this;
		}

		public ViewBuilder commandChangeView(String name, String viewName) {
			var command = new ChangeViewCommand(name, viewName, commands.size() + 1);
			commands.add(command);
			return this;
		}

		public ViewBuilder withFinish() {
			withFinish.add();
			return this;
		}

		public ViewBuilder withFinish(String name) {
			withFinish.setName(name);
			withFinish.add();
			return this;
		}

		public ViewBuilder withGoBack(String name) {
			withGoBack.setName(name);
			withGoBack.add();
			return this;
		}

		public ViewBuilder withGoBack() {
			withGoBack.add();
			return this;
		}

		public View build() {
			if (commands.isEmpty()) {
				throw new CommandNotFoundException();
			}

			if (withFinish.mustAdd()) {
				commands.add(new FinishCommand(withFinish.getName(), commands.size() + 1));
			}

			if (withGoBack.mustAdd()) {
				commands.add(new GoBackCommand(withGoBack.getName(), commands.size() + 1));
			}

			return new SimpleView(name, commands);
		}


		private static class FixedCommand {

			private boolean mustAdd;
			private String name;

			public FixedCommand(String name) {
				this.name = name;
				this.mustAdd = false;
			}

			public void add() {
				mustAdd = true;
			}

			public void setName(String name) {
				this.name = name;
			}

			public boolean mustAdd() {
				return mustAdd;
			}

			public String getName() {
				return name;
			}
		}

	}
}
