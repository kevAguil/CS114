package edu.njit.cs114;
import java.util.*;
/**
 * Author: Kevin Aguilar
 * Date created: 11/6/2022
 */
public class BinarySearchTree<K extends Comparable<K>,V> {
    private BSTNode<K,V> root;
    private int size;
    
    public static class BSTNode<K extends Comparable<K>,V> implements BinTreeNode<K,V> {
        private K key;
        private V value;
        private int height;
        // number of keys (including the key inthis node) in the subtree rooted at this node
        private int size;
        private BSTNode<K, V> left, right;
        
        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            /*
             * Complete code to store height and size (for homework)
             */
            setAugmentedInfo();
        }
        public int setAugmentedInfo(){
            int leftHeight = (left == null ? 0 : left.height);
            int rightHeight = (right == null ? 0 : right.height);
            this.height = 1 + Math.max(leftHeight, rightHeight);

            int leftSize = (left == null ? 0 : left.size);
            int rightSize = (right == null ? 0 : right.size);
            this.size = 1 + rightSize + leftSize;
            
            return rightHeight - leftHeight;
        }
        public BSTNode(K key, V value) {
            this(key, value, null, null);
        }
        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public BinTreeNode<K, V> leftChild() {
            return left;
        }
        @Override
        public BinTreeNode<K, V> rightChild() {
            return right;
        }
        @Override
        public boolean isLeaf() {
            return (left == null && right == null);
        }
        private void setLeftChild(BinTreeNode<K, V> node) {
            this.left = (BSTNode<K, V>) node;
        }
        private void setRightChild(BinTreeNode<K, V> node) {
            this.right = (BSTNode<K, V>) node;
        }
        private void setValue(V value) { this.value = value; };

        /**
         * Returns height of right subtree - height of left subtree
         * @return
         */
        @Override
        public int balanceFactor() {
            /**
             * Complete code for the lab
             *
             */

            return setAugmentedInfo();
        }


        private void copy(BSTNode<K,V> node) {
            this.key = node.key;
            this.value = value;
        }
    }


    public BSTNode<K,V> getRoot() {
        return root;
    }

    public BSTNode<K,V> insertAux(BSTNode<K,V> localRoot, K key, V value) {
        if (localRoot == null) {
            return new BSTNode<K,V>(key, value);
        }
        int result = key.compareTo(localRoot.key);
        if (result < 0) {
            localRoot.setLeftChild(insertAux(localRoot.left, key, value));
        } else if (result > 0){
            localRoot.setRightChild(insertAux(localRoot.right, key, value));
        } else {
            localRoot.setValue(value);
        }
        /**
         * Complete code to set height of localRoot for the lab (also "size" for homework)
         */
        localRoot.setAugmentedInfo();
        return localRoot;
    }

    /**
     * Insert/Replace (key,value) association or mapping in the tree
     * @param key
     * @param value value to insert or replace
     */
    public void insert(K key, V value) {
        this.root = insertAux(root, key, value);
    }

    // Extra credit problem
    private BSTNode<K,V> deleteAux(BSTNode<K,V> localRoot, BSTNode<K,V> parent, K key) {
        /**
         * complete for homework extra credit
         * you need to set height and size properly in the nodes of the subtrees affected
         */
        return null;
    }

    /**
     * Delete the value associaated with the key if it exists
     * @param key
     * @return value deleted if key exists else null
     */
    public V delete(K key) {
        BSTNode<K,V> node = deleteAux(root, null, key);
        return node == null ? null : node.getValue();
    }

    public int height() {
        return (root == null ? 0 : root.height);
    }

    public int size() {
        return (root == null ? 0 : root.size);
    }

    private boolean isBalanced(BSTNode<K,V> localRoot) {
        /*
         * Complete code here for the lab
         */
        if(localRoot == null){
            return true;
        }
        if(localRoot.balanceFactor() < -1 || localRoot.balanceFactor() > 1)
        {
            return false;
        }
        if(isBalanced(localRoot.right) && isBalanced(localRoot.left)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Is the tree balanced ?
     * For every node, height of left and right subtrees differ by at most 1
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Get level ordering of nodes
     * @return a map which associates a level with list of nodes at that level
     */
    public Map<Integer, List<BSTNode<K,V>>> getNodeLevels() {
        Map<Integer, List<BSTNode<K,V>>> nodeLevels = new HashMap<>();
        if(root == null){
            return nodeLevels;
        }
        List<BSTNode<K,V>> nodeList = new ArrayList<BSTNode<K,V>>();
        nodeList.add(root);
        int level = 0;
        nodeLevels.put(level, nodeList);
        while(nodeList.size() > 0){
            List<BSTNode<K,V>> nodeList2 = new ArrayList<BSTNode<K,V>>();
            for(BSTNode<K,V> mainNode : nodeList) {
                if (mainNode.left != null) {
                    nodeList2.add(mainNode.left);
                }
                if (mainNode.right != null) {
                    nodeList2.add(mainNode.right);
                }
            }
            level += 1;
            if(nodeList2.size() > 0){
                nodeLevels.put(level, nodeList2);
            }
            nodeList = nodeList2;
        }
        return nodeLevels;
    }

    /**
     * Return list of nodes whose keys are greater than or equal to key1
     *   and smaller than or equal to key2
     * @param key1
     * @param key2
     * @return
     */
    public List<BSTNode<K,V>> getRange(K key1, K key2) {
        /**
         * Complete code for homework (define a recursive aux function to be called from here)
         */
        return getRange(key1, key2, this.root);
    }
    public List<BSTNode<K,V>> getRange(K key1, K key2 , BSTNode<K,V> localNode) {
    	List<BSTNode<K,V>> list = new ArrayList<BSTNode<K,V>>();
    	if (localNode == null) {
    		return null;
    	}
    	if (localNode.key.compareTo(key1) >= 0 && localNode.key.compareTo(key2) <= 0) {
    		list.add(localNode);
    		getRange(key1, key2, localNode.left);
    		getRange(key1, key2, localNode.right);
    		
    	}
    	else {
    		getRange(key1, key2, localNode.left);
    		getRange(key1, key2, localNode.right);
    	}

        return list;
    }

    /**
     * Find number of keys smaller than or equal to the specified key
     * @param key
     * @return
     */
    public int rank(K key) {
        /*
         * Complete code for homework (define a recursive aux function to be called from here)
         */
        return rank(key, this.root);
    }
    public int rank(K key , BSTNode<K, V> localroot ) {
    	
    	if(localroot == null) {
    		return 0;
    	}
    	else if(localroot.key.compareTo(key) == 0 || localroot.key.compareTo(key) == -1){
    		return 1 + rank(key, localroot.left) + rank(key, localroot.right);   		
    	}
    	else {
    		return rank(key, localroot.left) + rank(key, localroot.right);
    	}

    }

    public static void main(String [] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.insert(25,"a");
        bst.insert(15,"b");
        bst.insert(30,"c");
        bst.insert(5,"d");
        bst.insert(27,"e");
        bst.insert(36,"f");
        bst.insert(40,"g");
        bst.insert(10,"k");
        bst.insert(52,"l");
        System.out.println("Printing tree bst..");
        new BinTreeInOrderNavigator<Integer,String>().visit(bst.root);
        bst.insert(40,"m");
        System.out.println("Printing tree bst..");
        new BinTreeInOrderNavigator<Integer,String>().visit(bst.root);
        System.out.println("Value for deleted key 5 = " + bst.delete(5));
        System.out.println("Printing tree bst..");
        new BinTreeInOrderNavigator<Integer,String>().visit(bst.root);
        System.out.println("Value for deleted key 30 = " + bst.delete(30));
        System.out.println("Printing tree bst..");
        new BinTreeInOrderNavigator<Integer,String>().visit(bst.root);
        System.out.println("size of bst=" + bst.size());
        System.out.println("height of bst=" + bst.height());
        System.out.println("Is bst an AVL tree ? " + bst.isBalanced());
        Map<Integer, List<BSTNode<Integer,String>>> nodeLevels = bst.getNodeLevels();
        for (int level : nodeLevels.keySet()) {
            System.out.print("Keys at level " + level + " :");
            for (BSTNode<Integer,String> node : nodeLevels.get(level)) {
                System.out.print(" " + node.getKey());
            }
            System.out.println("");
        }
        BinarySearchTree<Integer, Integer> bst1 = new BinarySearchTree<>();
        bst1.insert(44,1);
        bst1.insert(17,2);
        bst1.insert(78,3);
        bst1.insert(50,4);
        bst1.insert(62,5);
        bst1.insert(88,6);
        bst1.insert(48,7);
        bst1.insert(32,8);
        System.out.println("Printing tree bst..");
        new BinTreeInOrderNavigator<Integer,Integer>().visit(bst1.root);
        System.out.println("size of bst1=" + bst1.size());
        System.out.println("height of bst1=" + bst1.height());
        System.out.println("Is bst1 an AVL tree ? " + bst1.isBalanced());
        Map<Integer, List<BSTNode<Integer,Integer>>> nodeLevels1 = bst1.getNodeLevels();
        for (int level : nodeLevels1.keySet()) {
            System.out.print("Keys at level " + level + " :");
            for (BSTNode<Integer,Integer> node : nodeLevels1.get(level)) {
                System.out.print(" " + node.getKey());
            }
            System.out.println("");
        }
        System.out.println("rank of key 10 in bst=" + bst.rank(10)); // should be 2
        System.out.println("rank of key 30 in bst=" + bst.rank(30)); // should be 6
        System.out.println("rank of key 3 in bst=" + bst.rank(3)); // should be 0
        System.out.println("rank of key 55 in bst=" + bst.rank(55)); // should be 9
        List<BSTNode<Integer,Integer>> rangeNodes = bst1.getRange(32,62);
        System.out.print("Keys in the range : [32,62] are:");
        //should get 32,44,48,50,62,
       for (BSTNode<Integer,Integer> node : rangeNodes) {
            System.out.print(node.key + ",");
        }
        System.out.println("");
        rangeNodes = bst1.getRange(10,50);
        System.out.print("Keys in the range : [10,50] are:");
        // should get 17,32,44,48,50,
        for (BSTNode<Integer,Integer> node : rangeNodes) {
            System.out.print(node.key + ",");
        }
        System.out.println("");
        rangeNodes = bst1.getRange(90,100);
        System.out.print("Keys in the range : [90,100] are:");
        // should get empty list
        for (BSTNode<Integer,Integer> node : rangeNodes) {
            System.out.print(node.key + ",");
        }
        System.out.println("");
    }

}