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
}

class Node {
    Node left;
    Node right;
    int data;
    Node(int data){
        this.data = data;
    }
}