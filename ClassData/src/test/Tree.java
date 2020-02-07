package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tree {
	public static <T> void main(String[] args) {

		List<Node<String>> nodeList = new ArrayList<Node<String>>();
		try {
			String path = "D:\\Dev\\JAVA\\classdata\\menu.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Node<T> node = new Node<T>();
				if (line.split("\t")[1].equals("0")) {
					setNode(line, node);
					nodeList.add((Node<String>) node);
				} else {
					for (Node<String> nd : nodeList) {
						if (nd.getChildNo() == Integer.parseInt(line.split("\t")[1])) {
							setNode(line, node);
							nd.addChild((Node<String>) node);
						}
					}
				}
			}
			for (Node<String> node : nodeList) {
				printTree(node, 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static <T> void setNode(String line, Node<T> node) {
		node.setData(line.split("\t")[2]);
		node.setChildNo(Integer.parseInt(line.split("\t")[0]));
		node.setParentNo(Integer.parseInt(line.split("\t")[1]));
	}

	private static void printTree(Node<String> node, int level) {
		int indent = level++;
		String tabs = "";
		for (int i = 0; i < indent; i++) {
			tabs += "\t";
		}
		System.out.println(tabs + node.getData());
		List<Node<String>> children = node.getChildren();
		if (children.size() == 0) {
			return;
		}
		for (int i = 0; i < children.size(); i++) {
			Node<String> child = children.get(i);
			printTree(child, level);
		}
	}
}
