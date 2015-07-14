package com.project.algorithm.unionfind;

/** Algorithm to implement union find with path compression
 *  applied to WeightedQuickUnionBySize.
 *  The idea emerges from the approach we took to do WeightedQuickUnionBySize.
 *  In WeightedQuickUnionBySize we said that we are only worried about the
 *  root node. If we are only worried about the root node, then why should
 *  we maintain the structure of the tree, why can't we flatten the tree 
 *  by making all the children nodes point to the root node ?
 *  Ya that is what this implementation is all about.
 */
public class WeightedQuickUnionBySizeWithPathCompression extends WeightedQuickUnionBySize {
  
  /** 
   * Initialize the datastructure.
   * @param totalNumberOfElementsInWqubsPcDatastructure is the number of 
   *        elements in the datastructure, where WqubhPc is
   *        WeightedQuickUnionBySizePathCompressed.
   */
  public WeightedQuickUnionBySizeWithPathCompression(
      int totalNumberOfElementsInWqubsPcDatastructure) {
    super(totalNumberOfElementsInWqubsPcDatastructure);
  }

  /**
   * Union the elements based on size and then flatten the tree.
   * @param element1 integer representing the element.
   * @param element2 integer representing the element.
   */
  @Override
  public void union(final int element1, final int element2) {
    int root = unionBasedOnSize(element1, element2);
    flattenTree(element1, root);
    flattenTree(element2, root); 
  }

  /**
   * Flattens the tree by making all the nodes starting
   * from the given leaf to directly point to the root.
   * @param leaf from which the flattening starts
   * @param root to which the the tree is flattened
   */
  private void flattenTree(final int leaf, final int root) {
    // Note: Flattening will not make an impact if the leaf itself is the
    // root or if the leaf is the immediate child of root(in a tree where some other
    // branch is really length than this one).
    // even if all cases are like the above NO FLATTENING CASE
    // the algorithm will work just like WeightedQuickUnionBySize.
    // see unionTest3() and unionTest5() in WeightedQuickUnionBySizeWithPathCompressionTest 
    // for illustration of cases where flattening will not work.
    int next = elements[leaf];
    int current = leaf;
    while (next != root) {
      elements[current] = root;
      current = next;
      next = elements[next];
    }
  }
}
