package br.com.gabrielmusskopf.flourish.command;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import br.com.gabrielmusskopf.flourish.exception.ParamNotPresentException;

public abstract class ArgsCommand extends AbstractCommand implements Parameterizable {

	private final List<Param> params;

	protected ArgsCommand(String name, String... nome) {
		super(name);

		if (nome.length == 0) {
			throw new ParamNotPresentException();
		}

		this.params = Stream.of(nome)
				.map(n -> (Param) new SimpleParam(n))
				.toList();
	}

	@Override
	public final void execute() {
		var nullVal = params.stream()
				.map(Param::value)
				.filter(Objects::isNull)
				.findFirst();

		if (nullVal.isPresent()) {
			throw new ParamNotPresentException();
		}

		run();
	}

	protected abstract void run();

	@Override
	public List<Param> params() {
		return params;
	}

	public Object getParam(String name) {
		return params.stream().filter(p -> p.name().equals(name))
				.findFirst()
				.map(Param::value)
				.orElse(null);
	}
}
