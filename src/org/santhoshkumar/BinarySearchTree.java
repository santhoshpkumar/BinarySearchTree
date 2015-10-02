package org.santhoshkumar;

import java.io.*;
import java.util.*;

public class BinarySearchTree {

    Node root;
    TreeMap<Integer,ArrayList> vertical;
    Queue<Node> queue;

    public static void main(String[] args) {
        System.out.println("Binary Search Tree");
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(25);
        bst.insert(75);
        bst.insert(20);
        bst.insert(30);
        bst.insert(70);
        bst.insert(80);
        bst.insert(90);
        bst.insert(95);

        bst.insert(91);
        bst.insert(21);
        bst.insert(31);

        bst.inOrder(bst.root);
        bst.verticalOrder();

        System.out.println(bst.find(100));
        System.out.println(bst.find(91));

        System.out.println(bst.getPredecessorValue(50));
        System.out.println(bst.getSuccessorValue(50));

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int input = sc.nextInt();
            if (input == -1){
                return;
            }else{
                System.out.println(bst.delete(input));
                bst.inOrder(bst.root);
                System.out.println("Count: "+bst.getSize(bst.root));
            }
        }

    }

    public int getSize(Node node){
        if (node == null){
            return 0;
        }
        return (1+getSize(node.right)+getSize(node.left));
    }

    public boolean delete(int key){
        if(root == null){
            return false;
        }

        Node parent = root;
        Node current = root;
        boolean isLeft = false;

        while(current.data != key){
            parent = current;
            if(current.data > key){
                isLeft = true;
                current = current.left;
            }else{
                isLeft = false;
                current = current.right;
            }
            if(current == null){
                return false;
            }
        }

        //Case 1: no child nodes
        if(current.left == null && current.right ==null){
            if (current == root){
                root = null;
            }else if(isLeft){
                parent.left = null;
            }else{
                parent.right = null;
            }
            return true;
        }

        //Case 2: only one child node
        if(current.left == null){
            if(current == root){
                root = current.right;
            }else if(isLeft){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
            return true;
        }
        if(current.right == null){
            if(current == root){
                root = current.left;
            }else if(isLeft){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
            return true;
        }

        //case 3: two child nodes
        if(current.left != null && current.right != null){
            Node successor = getSuccessor(current);
            successor.left = current.left;
            if(current  == root){
                root = successor;
            }else if(isLeft){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            return true;
        }

        return false;
    }

    public Node getSuccessor(Node node){
        Node current = node.right;
        Node parent = current;
        Node parentParent = current;
        while(current != null){
            parentParent = parent;
            parent = current;
            current = current.left;
        }
        if(parent != node.right){
            parentParent.left = parent.right;
            parent.right = node.right;
        }

        return parent;
    }

    public boolean find(int data){
        if(root == null){
            return false;
        }

        Node current = root;
        while(current != null){
            if(current.data == data){
                return true;
            }else if( current.data > data){
                current = current.left;
            }else{
                current = current.right;
            }
        }

        return false;
    }

    public Node getNode(int data){
        if(root == null){
            return null;
        }

        Node current = root;
        while(current != null){
            if(current.data == data){
                return current;
            }else if( current.data > data){
                current = current.left;
            }else{
                current = current.right;
            }
        }

        return null;
    }

    public void insert(int data ){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            return;
        }

        Node current = root;
        while(current!=null){
            if(current.data > data) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                }
                current = current.left;
            }
            else{
                 if (current.right == null) {
                     current.right = newNode;
                     return;
                 }
                current = current.right;
            }
        }
    }

    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.data);
        preOrder((node.left));
        preOrder(node.right);
    }

    public void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public void verticalOrder(){
        vertical = null;
        vertical = new TreeMap<Integer,ArrayList>();
        buildVerticalOrder(root, 0);

        Set<Integer> keys = vertical.keySet();
        for(int key:keys){
            System.out.println(vertical.get(key));
        }
    }

    public void buildVerticalOrder(Node node, int level){
        if (node == null){
            return;
        }
        ArrayList values = vertical.get(level);
        if (values == null){
            values= new ArrayList();
        }
        values.add(node.data);
        vertical.put(level,values);
        buildVerticalOrder(node.left,level-1);
        buildVerticalOrder(node.right,level+1);
    }

    public int getSuccessorValue(int value){

        Node node = getNode(value);
        if(node == null){
            return -1;
        }
        if(node.right == null){
            return -1;
        }
        Node parent  = null;
        Node current = node.right;
        while(current != null){
            parent = current;
            current = current.left;
        }

        return parent.data;
    }

    public int getPredecessorValue(int value){

        Node node = getNode(value);
        if(node == null){
            return -1;
        }
        if(node.left == null){
            return -1;
        }

        Node parent  = null;
        Node current = node.left;
        while(current != null){
            parent = current;
            current = current.right;
        }

        return parent.data;
    }
}

class Node {
    Node left;
    Node right;
    int data;
    Node(int data){
        this.data = data;
    }
}