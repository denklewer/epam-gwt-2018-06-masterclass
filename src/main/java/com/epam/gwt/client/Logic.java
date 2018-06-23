package com.epam.gwt.client;


public class Logic {

	private State _state;

	public Logic(final State state) {
		_state = state;
	}

	public boolean moveLeft() {
		_state.col--;
		if (!isFigureFitsField()) {
			_state.col++;
			return false;
		}
		return true;
	}

	public boolean moveRight() {
		_state.col++;
		if (!isFigureFitsField()) {
			_state.col--;
			return false;
		}
		return true;
	}

	public boolean moveDown() {
		_state.row++;
		if (!isFigureFitsField()) {
			_state.row--;
			pasteFigure();
			_state.setFigure(Figure.randomFigure());
			return true;
		}
		return true;
	}

	private void pasteFigure() {
		int[][] fieldData = _state._field.getData();
		int[][] figureData = _state._figure.getData();
		for (int r = 0; r < figureData.length; r++) {
			for (int c = 0; c < figureData[r].length; c++) {
				if (figureData[r][c] == 0) {
					continue;
				}
				fieldData[_state.row + r][_state.col + c] = figureData[r][c];
			}
		}
	}

	public void rotate() {
		// todo
	}

	public void dropDown() {
		// todo
	}

	public boolean isFigureFitsField() {
		int[][] data = _state._figure._data;

		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				if (data[r][c] == 0) {
					continue;
				}
				int row = _state.row + r;
				int col = _state.col + c;

				if (row < 0 || col < 0 || row >= _state._field.getHeight()
						|| col >= _state._field.getWidth()) {
					return false;
				}

				if (_state._field._data[row][col] > 0) {
					return false;
				}
			}
		}

		return true;
	}

	public State getState() {
		return new State(_state);
	}
}
