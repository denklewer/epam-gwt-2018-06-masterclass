package com.epam.gwt.client;

public class Controller implements ModelListener {

	private View _view;
	private Model _model;

	@Override
	public void onChange(final State state) {
		_view.draw(state);
	}

	public void setView(final View view) {
		_view = view;
	}

	public void moveLeft() {
		_model.moveLeft();
	}

	public void moveRight() {
		_model.moveRight();
	}

	public void setModel(final Model model) {
		_model = model;
	}

	public void moveDown() {
		_model.moveDown();
	}

}
