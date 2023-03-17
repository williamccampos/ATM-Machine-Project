package com.br.atm;

public interface Iterator {
	
	abstract boolean hasNext(int position);
	abstract Object next(int position);
	abstract boolean hasPrev(int position);

}
