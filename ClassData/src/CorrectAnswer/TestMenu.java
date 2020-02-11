package CorrectAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TestMenu {
	
	public static void main(String[] args) {
		ArrayList<MenuItem> menuList = initMenu();
		toTree(menuList);
	}

	private static ArrayList<MenuItem> initMenu() {
		ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
		MenuItem menuItem; 
		
		menuItem = new MenuItem(1,0,"ī��");menuList.add(menuItem);
		menuItem = new MenuItem(2,0,"����");menuList.add(menuItem);
		menuItem = new MenuItem(3,0,"��α�");menuList.add(menuItem);
		menuItem = new MenuItem(4,0,"����");menuList.add(menuItem);
		menuItem = new MenuItem(8,4,"��ǰ");menuList.add(menuItem);
		menuItem = new MenuItem(9,2,"���� �б�");menuList.add(menuItem);
		menuItem = new MenuItem(10,3,"��α� ��");menuList.add(menuItem);
		menuItem = new MenuItem(11,1,"�� ī��");menuList.add(menuItem);
		menuItem = new MenuItem(12,2,"���� ����");menuList.add(menuItem);
		menuItem = new MenuItem(15,3,"��� ��α�");menuList.add(menuItem);
		menuItem = new MenuItem(17,3,"��α� ��Ų");menuList.add(menuItem);
		menuItem = new MenuItem(22,2,"�ּҷ�");menuList.add(menuItem);
		menuItem = new MenuItem(24,1,"ī�� ���丮");menuList.add(menuItem);
		menuItem = new MenuItem(26,4,"�м�");menuList.add(menuItem);
		menuItem = new MenuItem(27,4,"����");menuList.add(menuItem);
		menuItem = new MenuItem(31,4,"����");menuList.add(menuItem);
		menuItem = new MenuItem(32,2,"���Ը���");menuList.add(menuItem);
		menuItem = new MenuItem(34,1,"ī�װ�");menuList.add(menuItem);
		menuItem = new MenuItem(44,1,"��� ī��");menuList.add(menuItem);
		menuItem = new MenuItem(101,44,"�׽�Ʈ");menuList.add(menuItem);
		menuItem = new MenuItem(102,32,"��������");menuList.add(menuItem);
		
		return menuList;
	}

	private static void toTree(ArrayList<MenuItem> menuList) {
		// TODO Auto-generated method stub
		System.out.println(menuList);
		Node<MenuItem> root =new Node<MenuItem>(new MenuItem(0,0,"root"));
		
		HashMap<Integer, Node<MenuItem>> hMenu = new HashMap<Integer, Node<MenuItem>>();
		
		hMenu.put(new Integer(0), root );
		
		Iterator<MenuItem> it = menuList.iterator();
		Node<MenuItem> curPNode;
		Node<MenuItem> curNode;
		while(it.hasNext()) {
			MenuItem mi = it.next();
			int pCode = mi.getPcode();
			int code = mi.getCode();
			
			curNode =new Node<MenuItem>(mi);
			System.out.println(code +" "+pCode);
			
			hMenu.put(code, curNode);

			
			if (hMenu.containsKey(pCode)) {
				curPNode = hMenu.get(pCode);
				curPNode.addChild(curNode);
				
			} else {
				curPNode = new Node<MenuItem>(mi);
				
			}
			hMenu.put(pCode, curPNode);
			
		}
		
		printTree(root, "  ");
	}
	
	private static <T> void printTree(Node<T> node, String appender) {
		   System.out.println(appender + node.getData());
		   node.getChildren().forEach(each ->  printTree(each, appender + appender));
		 }

}