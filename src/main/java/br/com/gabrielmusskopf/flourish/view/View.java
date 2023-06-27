package br.com.gabrielmusskopf.flourish.view;

import java.util.List;

import br.com.gabrielmusskopf.flourish.command.Command;

public interface View {

	String title();
	List<Command> commands();

}
