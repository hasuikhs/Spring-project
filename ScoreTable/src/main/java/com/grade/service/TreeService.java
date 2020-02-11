package com.grade.service;

import java.util.List;

public interface TreeService {
	public <T> List<Node<String>> printTree();
	
	public <T> Node<T> printMenu();
}
