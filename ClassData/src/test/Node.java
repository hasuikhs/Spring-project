package test;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	private int parentNo;
	private int childNo; 
	private String data;
	private Node<T> parent;
	private List<Node<T>> children = new ArrayList();

	public void addChild(Node<T> node) {
		node.setParent(this);
		getChildren().add(node);
	}

	public String getData() {
		return data;
	}

	public void setData(String string) {
		this.data = string;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getChildNo() {
		return childNo;
	}

	public void setChildNo(int childNo) {
		this.childNo = childNo;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}
}
