#include "BinaryTree.h"
using namespace std;

template<typename K, typename V>
BinaryTree<K, V>::BinaryTree() {
	tsize=0;
  makeEmpty();
}

/**
 *  Returns the number of entries stored in the dictionary.  Entries with
 *  the same key (or even the same key and value) each still count as
 *  a separate entry.
 *  @return number of entries in the dictionary.
 **/
template<typename K, typename V>
int BinaryTree<K, V>::size() {
  return tsize;
}

/**
 *  Tests if the dictionary is empty.
 *
 *  @return true if the dictionary has no entries; false otherwise.
 **/
template<typename K, typename V>
bool BinaryTree<K, V>::isEmpty() {
  return size() == 0;
}

template<typename K, typename V>
void BinaryTree<K, V>::insertHelper(Entry<K, V>* entry, const K& key, BinaryTreeNode<K, V>* node) {
  if (key.compareTo(node->entry->getkey()) <= 0) {
    if (node->leftChild == NULL) {
      node->leftChild = new BinaryTreeNode<K, V>(entry, node);
    } else {
      insertHelper(entry, key, node->leftChild);
    }
  } else {
    if (node->rightChild == NULL) {
      node->rightChild = new BinaryTreeNode<K, V>(entry, node);
    } else {
      insertHelper(entry, key, node->rightChild);
    }
  }
}

/**
 *  Create a new Entry object referencing the input key and associated value,
 *  and insert the entry into the dictionary.
 *  Multiple entries with the same key (or even the same key and
 *  value) can coexist in the dictionary.
 *
 *  @param key the key by which the entry can be retrieved.
 *  @param value an arbitrary object.
 **/
template<typename K, typename V>
void BinaryTree<K, V>::insert(const K& key, const V& value) {
  Entry<K, V>* entry = new Entry<K, V>(key, value);
  if (root == NULL) {
    root = new BinaryTreeNode<K, V>(entry);
  } else {
    insertHelper(entry, key, root);
  }
  tsize++;
}

/**
 *  Search for a node with the specified key, starting from "node".  If a
 *  matching key is found (meaning that key1.compareTo(key2) == 0), return
 *  a node containing that key.  Otherwise, return null.
 *
 *  Be sure this method returns null if node == null.
 **/
template<typename K, typename V>
BinaryTreeNode<K, V>* BinaryTree<K, V>::findHelper(const K& key, BinaryTreeNode<K, V>* node) {
  BinaryTreeNode<K,V>* find=node;
  int comp;
  
  while(1){
  	comp=find->entry->getkey().compareTo(key);
  	if(comp==0){
  		return find;
  		//cout<<"\nget in 3\n";
	  }
	else if (comp==1){
		if(find->leftChild==NULL){return NULL;}
		else{
			find=find->leftChild;
			//cout<<"\nget in 1\n";
		}
	}
	else if (comp==-1){
		if(find->rightChild==NULL){return NULL;}
		else{
			find=find->rightChild;
			//cout<<"\nget in 2\n";
		}
	}
  }
  
  // Replace the following line with your solution.
  
}

/**
 *  Search for an entry with the specified key.  If such an entry is found,
 *  return true; otherwise return false.
 *
 *  @param key the search key.
 *  @return an entry containing the key and an associated value, or null if
 *          no entry contains the specified key.
 **/
template<typename K, typename V>
Entry<K, V>* BinaryTree<K, V>::find(const K& key) {
  BinaryTreeNode<K, V>* node = findHelper(key, root);
  if (node != NULL) {
    return node->entry;
  } else {
    return NULL;
  }
}

/**
 *  Remove an entry with the specified key.  If such an entry is found,
 *  remove it from the table.
 *  If several entries have the specified key, choose one arbitrarily, then
 *  remove it.
 *
 *  @param key the search key.
 */
template<typename K, typename V>
void BinaryTree<K, V>::remove_one(BinaryTreeNode<K, V>* node){
	//cout<<"in 7\n";
	bool L=(node->leftChild==NULL);
  	bool R=(node->rightChild==NULL);
  	if(node==root){
  		if(R && !(L)){
  			root=node->leftChild;
  			node->leftChild->parent=NULL;
		  }
		else if(!(R) && L){
			root=node->rightChild;
  			node->rightChild->parent=NULL;
		}
	  }
  	else if (L && R && node->parent->leftChild==node){
  		//cout<<"in 6\n";
  		node->parent->leftChild=NULL;
	}
	else if(L && R && node->parent->rightChild==node){
		//cout<<"in 5\n";
		node->parent->rightChild=NULL;
	}
	else if(L  && !(R)){
		
		if (node->parent->leftChild==node){
		//	cout<<"in 4\n";
  			node->parent->leftChild=node->rightChild;
  			node->rightChild->parent=node->parent;
		    }
		else if(node->parent->rightChild==node){
		//	cout<<"in 3\n";
			node->parent->rightChild=node->rightChild;
  			node->rightChild->parent=node->parent;
		   }
		   else{
		cout<<"error 2";
	}
		
		
	}
	else if(R  && !(L)){
		if (node->parent->leftChild==node){
  			node->parent->leftChild=node->leftChild;
  			node->leftChild->parent=node->parent;
  			//cout<<"in 1\n";
		}
		else if(node->parent->rightChild==node){
			//cout<<"in 2\n";
			node->parent->rightChild=node->leftChild;
  			node->leftChild->parent=node->parent;
		}
		else{
		cout<<"error 1";
		}
	}

	
	
}

template<typename K, typename V>
void BinaryTree<K, V>::remove(const K& key) {
    BinaryTreeNode<K, V>* node = findHelper(key, root);
    BinaryTreeNode<K, V>* find=node;
    remove_one(node);
    bool L=(node->leftChild==NULL);
  	bool R=(node->rightChild==NULL);
  	if(!(R) && !(L)){
  		find=node->rightChild;
  		while(1){
  			if(find->leftChild!=NULL){find=find->leftChild;}
			else{break;}
		  }
		  node->entry=find->entry;
		  remove_one(find);
	  }
	tsize--;
	
  
}

/**
 *  Remove all entries from the dictionary.
 */
template<typename K, typename V>
void BinaryTree<K, V>::makeEmpty() {
	tsize=0;
	root=NULL;
}

/**
 *  Convert the tree into a string.
 **/
template<typename K, typename V>
std::string BinaryTree<K, V>::toString() {
  if (root == NULL) {
    return "";
  } else {
    return root->toString();
  }
}
