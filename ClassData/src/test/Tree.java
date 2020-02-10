package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
	public static <T> void main(String[] args) {

		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();

		String path = "D:\\Dev\\JAVA\\classdata\\menu.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
			String line = "";

			while ((line = br.readLine()) != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("number", line.split("\t")[0]);
				map.put("parentNo", line.split("\t")[1]);
				map.put("data", line.split("\t")[2]);
				mapList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Node<T> root = new Node<T>();
		root.setData("메뉴");
		root.setChildNo(0);
		root.setParentNo(0);

		for (HashMap<String, String> map : mapList) {
			System.out.println(map);
//			if (Integer.parseInt(map.get("number")) > 0) {
//				Node<T> node = new Node<T>();
//				node.setData((String) map.get("data"));
//				node.setChildNo(Integer.parseInt(map.get("number")));
//				node.setParentNo(Integer.parseInt(map.get("parentNo")));
//				root.addChild(node);
//			}
		}
		
//		List<Node<String>> nodeList = new ArrayList<Node<String>>();
//		try {
//			String path = "D:\\Dev\\JAVA\\classdata\\menu.txt";
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
//			String line = "";
//			Node<T> root = new Node<T>();
//			root.setData("메뉴");
//			root.setChildNo(0);
//			root.setParentNo(0);
//			while ((line = br.readLine()) != null) {
//				Node<T> node = new Node<T>();
//				if (line.split("\t")[1].equals("0")) {
//					setNode(line, node);
//					nodeList.add((Node<String>) node);
//				} else {
//					for (Node<String> nd : nodeList) {
//						if (nd.getChildNo() == Integer.parseInt(line.split("\t")[1])) {
//							setNode(line, node);
//							nd.addChild((Node<String>) node);
//						}
//					}
//				}
//			}
//			for (Node<String> node : nodeList) {
//				printTree(node, 0);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
