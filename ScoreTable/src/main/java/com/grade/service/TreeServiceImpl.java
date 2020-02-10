package com.grade.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {

	@Override
	public <T> List<Node<String>> printTree() {

		List<Node<String>> nodeList = new ArrayList<Node<String>>();
		try {
			String path = "D:\\Dev\\JAVA\\classdata\\menu.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
			String line = "";
			Node<T> root = new Node<T>();
			root.setData("¸Þ´º");
			root.setChildNo(0);
			root.setParentNo(0);
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
		
		return nodeList;
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