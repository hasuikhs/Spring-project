package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//https://stackoverflow.com/questions/18056878/dynamically-building-a-menu
public class Tree {
	public static <T> void main(String[] args) {

		List<HashMap<String, String>> mapList = readData();

		createTree(mapList);
	}

	private static <T> void createTree(List<HashMap<String, String>> mapList) {
		Node<T> root = new Node<T>();
		root.setChildNo(0);
		root.setParentNo(0);
		root.setData("¸Þ´º");
		
		HashMap<Integer, Node<Map<String, String>>> hMenu = new HashMap<Integer, Node<Map<String,String>>>();
		
		hMenu.put(new Integer(0), (Node<Map<String, String>>) root);
		
		Iterator<HashMap<String, String>> it = mapList.iterator();
		Node<Map<String, String>> curPNode;
		Node<Map<String, String>> curNode;
		
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			int pCode = Integer.parseInt(map.get("pcode"));
			int code = Integer.parseInt(map.get("code"));
			
			curNode = new Node<Map<String, String>>(map.get("data"));
			
			hMenu.put(code, curNode);
			
			if (hMenu.containsKey(pCode)) {
				curPNode = hMenu.get(pCode);
				curPNode.addChild(curNode);
			} else {
				curPNode = new Node<Map<String, String>>(map.get("data"));
			}
			hMenu.put(pCode, curPNode);
		}
		printTree(root,"\t");
	}

	private static List<HashMap<String, String>> readData() {
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();

		String path = "D:\\Dev\\JAVA\\classdata\\menu.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
			String line = "";

			while ((line = br.readLine()) != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("code", line.split("\t")[0]);
				map.put("pcode", line.split("\t")[1]);
				map.put("data", line.split("\t")[2]);
				mapList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapList;
	}
	
	private static <T> void printTree(Node<T> node, String appender) {
		System.out.println(appender + node.getData());
		node.getChildren().forEach(each -> printTree(each, appender + appender));
	}
	
}
