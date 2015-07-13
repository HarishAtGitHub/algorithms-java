package com.project.algorithm.unionfind;

/**
 * Uses QuickUnion weighted by height Algorithm to implement UnionFind.
 * This is an improvement over QuickUnion by trying to reduce the
 * number of nodes that needs to be parsed when doing find operation.
 * If the trees are tall this will result in larger parsing time.
 * (Harish: Doubt? If the idea is to reduce height, what is the
 * idea behind having WeightedQuickUnionBySize ? see unionTest5() in 
 * WeightedQuickUnionBySizeTest which speaks about this case and compare
 * it with unionTest5() in WeightedQuickUnionByHeightTest)
 * <br>
 * <br>
 * Goal 1: This is an extension of QuickUnion with just the union operation
 * done keeping in mind that the shorter tree will be pointed to the taller tree.
 * <br>
 * <u> TODO: Worst case running time </u>
 * <ul>
 *   <li> Union : ??? </li>
 *   <li> Find : ??? </li>
 * </ul>
 * @author Harish Kayarohanam
 */
public class WeightedQuickUnionByHeight extends QuickUnion {
  private int[] height;
  
  /**
   * @param totalNumberOfElementsInWqubhDatastructure Total number of
   *        elements in the set.Wqubh is Weighted Quick Union By Height.
   */
  public WeightedQuickUnionByHeight(int totalNumberOfElementsInWqubhDatastructure) {
    count = totalNumberOfElementsInWqubhDatastructure;
    elements = new int[totalNumberOfElementsInWqubhDatastructure];
    height = new int[totalNumberOfElementsInWqubhDatastructure];
    for (int i = 0; i < totalNumberOfElementsInWqubhDatastructure; i++) {
      elements[i] = i;
      height[i] = 1;// initialize height as one as the are no children yet
    }
  }
  
  /**
   * Finds the union of the subsets containing the elements&#46;
   * But with the condition that the smaller of the 
   * two trees will be attached to the large tree.
   * what do you mean by large here ?
   * here it means number of nodes in the tree, in other words the
   * height of the tree.
   * there is another structure where we do this based on size in
   * WeightedQuickUnionBySize
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
    // based on the height of the tree.
    // we cannot find answer to the question 'which tree is tall'
    // during when the find is initiated because we don't know
    // if that node is the last leaf of the tree(as parse starts from that 
    // given node in the upward direction towards the root). If that is the 
    // last leaf then this would have worked , but in most of the cases
    // this may not be the last leaf. 
    // so this demands the need for maintaining a array to hold the height of
    // the tree in which that node resides in.
    // whenever union operation happens the height of the tree which
    // is the greatest is set as the value of the element in the height array.
    // if union of two elements element1 and element2 with tree heights 2 and 3
    // respectively is done then
    // height of the the representative of the tree in which the element 2
    // resides is changed to 3.
    // note: we are not concerned about changing the children of the representative
    // as height of the root node will tell the truth.
    // (after few iterations the height of the elements other than the
    // root node will give you the wrong value, so only trust height[representative]
    // this avoids unnecessary height updates of children.
    // when equal length trees are unioned the height increases by 1
    // moreover parsing entire tree and updating the heights is a painful
    // and time consuming task.
    if (height[element1Representative] == height[element2Representative]) {
      
      elements[element1Representative] = element2Representative;
      height[element2Representative]++;
    // when unequal length trees are unioned the height is the height
    // of the taller tree. as only root node matters and as root node 
    // is still the root node of the taller tree , we need not update 
    // the height of any node.
    } else if (height[element1Representative] < height[element2Representative]) {
      elements[element1Representative] = element2Representative;
    } else {
      elements[element2Representative] = element1Representative;
    }
    count--;
  }
  
}
