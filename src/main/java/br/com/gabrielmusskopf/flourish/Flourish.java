package br.com.gabrielmusskopf.flourish;

import java.util.concurrent.atomic.AtomicBoolean;

import br.com.gabrielmusskopf.flourish.exception.InstanceNotInitializedException;
import br.com.gabrielmusskopf.flourish.view.ViewManager;

public class Flourish {

	private static Console console;
	private static ViewManager viewManager;
	private static final AtomicBoolean running = new AtomicBoolean(true);

	private Flourish(){}

	public static void boot() {
		console = new Cli();
	}

	public static boolean running() {
		return running.get();
	}

	public static void finish() {
		running.set(false);
	}

	public static void setViewManager(ViewManager viewManager) {
		Flourish.viewManager = viewManager;
	}

	public static Console console() {
		return get(console);
	}

	public static ViewManager viewManager() {
		return get(viewManager);
	}

	private static <T> T get(T t) {
		if (t == null) {
			throw new InstanceNotInitializedException();
		}
		return t;
	}

}
