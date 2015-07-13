package com.project.algorithm.unionfind;

/**
 * Uses QuickUnion weighted by size Algorithm to implement UnionFind.
 * This is an improvement over QuickUnion by trying to reduce the
 * number of nodes that needs to be parsed when doing find operation.
 * If the trees are tall this will result in larger parsing time.
 * (Harish: Doubt? If the idea is to reduce height, what is the
 * idea behind having WeightedQuickUnionBySize ? What is the purpose 
 * behind trying to go by size? see unionTest5() in 
 * WeightedQuickUnionBySizeTest which speaks about this case and compare
 * it with unionTest5() in WeightedQuickUnionByHeightTest)
 * <br>
 * <br>
 * Goal 1:This is an extension of QuickUnion with just the union operation
 * done keeping in mind that the smaller size tree will be pointed to the 
 * larger size tree.
 * <br>
 * <u> Worst case running time </u>
 * <ul>TODO: Worst Case Running Time.
 *   <li> Union : ??? </li>
 *   <li> Find : ??? </li>
 * </ul>
 * @author Harish Kayarohanam
 */
public class WeightedQuickUnionBySize extends QuickUnion {
  private int[] size;
  
  /**
   * @param totalNumberOfElementsInWqubsDatastructure Total number of
   *        elements in the set. WQUBS is Weighted Quick Union By Size.
   */
  public WeightedQuickUnionBySize(int totalNumberOfElementsInWqubsDatastructure) {
    count = totalNumberOfElementsInWqubsDatastructure;
    elements = new int[totalNumberOfElementsInWqubsDatastructure];
    size = new int[totalNumberOfElementsInWqubsDatastructure];
    for (int i = 0; i < totalNumberOfElementsInWqubsDatastructure; i++) {
      elements[i] = i;
      size[i] = 1;
    }
  }
  
  /**
   * Finds the union of the subsets containing the elements&#46;
   * But with the condition that the smaller of the 
   * two trees will be attached to the large tree.
   * what do you mean by large here ?
   * here it means number of nodes in the tree, in other words the
   * size of the tree.
   * there is another structure where we do this based on height in
   * WeightedQuickUnionByHeight
   * If it is already in the same subset , it does nothing.
   *
   * @param element1 integer representing the element.
   * @param element2 integer representing the element.
   */
  @Override
  public void union(final int element1, final int element2) {
    int element1Representative = find(element1);
    int element2Representative = find(element2);
    if (element1Representative == element2Representative) {
      return;
    }
    // check which representative should point to which 
    // based on the size of the tree.
    // the lesser sized tree points to larger size tree.
    if (size[element1Representative] <= size[element2Representative]) {
      elements[element1Representative] = element2Representative;
      size[element2Representative] += size[element1Representative];
    } else {
      elements[element2Representative] = element1Representative;
      size[element1Representative] += size[element2Representative];
    }
    count--;
  }
}
