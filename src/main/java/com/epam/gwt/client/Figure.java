package com.epam.gwt.client;

import java.util.Random;

public class Figure {
	
	public static final Figure FOUR_HORIZONTAL; 
	
	private static int[][] T = {
		{0,2,2,2},	
		{0,0,2,0},	
		{0,0,0,0},	
		{0,0,0,0},	
	};
	
	private static int[][] L = {
		{0,3,0,0},	
		{0,3,0,0},	
		{0,3,3,0},	
		{0,0,0,0},	
	};
	
	private static int[][] I = {
		{0,1,0,0},	
		{0,1,0,0},	
		{0,1,0,0},	
		{0,1,0,0},	
	};
	
	private static int[][] S = {
		{0,4,4,0},	
		{4,4,0,0},	
		{0,0,0,0},	
		{0,0,0,0},	
	};
	
	private static int[][] Z = {
		{4,4,0,0},	
		{0,4,4,0},	
		{0,0,0,0},	
		{0,0,0,0},	
	};
	
	private static int[][] Q = {
		{0,5,5,0},	
		{0,5,5,0},	
		{0,0,0,0},	
		{0,0,0,0},	
	};
	
	private static int[][] J = {
		{0,0,3,0},	
		{0,0,3,0},	
		{0,3,3,0},	
		{0,0,0,0},	
	};
	
	public static int[][][] FIGURES = {
		T, L, I, S, Z, Q, J
	};

	private static Random random = new Random();
	
	
	static {
		FOUR_HORIZONTAL = new Figure();
		FOUR_HORIZONTAL._data = new int[][] {
				{0,0,0,0},	
				{0,0,0,0},	
				{1,1,1,1},	
				{0,0,0,0},	
		};
	}
	
	int[][] _data;

	public Figure() {
		_data = new int[4][4];
	}

	public int[][] getData() {
		return _data;
	}

	public static Figure randomFigure() {
		Figure figure = new Figure();
		figure._data = FIGURES[random.nextInt(FIGURES.length)];
		return figure;
	}

}
