package com.epam.gwt.client;

public class State {

	Figure _figure;
	Field _field;
	
	int row, col;

	public State() {
	}

	public State(State other) {
		this._figure = other._figure;
		this._field = other._field;
		this.row = other.row;
		this.col = other.col;
	}

	public Figure getFigure() {
		return _figure;
	}

	public void setFigure(final Figure figure) {
		_figure = figure;
		row = 0;
		col = _field.getWidth()/2 - 2;
	}

	public Field getField() {
		return _field;
	}

	public void setField(final Field field) {
		_field = field;
	}
}
