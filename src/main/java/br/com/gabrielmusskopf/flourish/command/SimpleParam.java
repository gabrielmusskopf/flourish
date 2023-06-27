package br.com.gabrielmusskopf.flourish.command;

public class SimpleParam implements Param {

	private final String name;
	private Object value;

	public SimpleParam(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Object value() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

}
