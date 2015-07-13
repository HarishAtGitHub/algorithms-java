package com.project.algorithm.unionfind;

/**
 * Uses QuickUnion Algorithm to implement UnionFind.
 * <br>
 * <br>
 * Goal 1:<br>Find a data structure equivalent to mathematical set
 *         and the elements that each of the set holds&#46;<br>
 * The algorithm uses array as the datastructure to hold the
 * elements and the owning subset's identifier&#46;<br>
 * 1&#46; The index of the array are the elements themselves&#46;<br>
 * 2&#46; If you go by the chain of elements that each element points
 *        to and if at a point you find that the element index is equal to
 *        the element itself then that is the representative of that subset.
 *        for eg. in<br> 
 *                  <br>
 *             _____________________
 * index       | 0 | 1 | 2 | 3 | 4 |
 *             ---------------------
 * value       | 0 | 2 | 3 | 3 | 4 |
 *             ---------------------
 *        <br>
 *        In the above array the subset idenfitier of 1 can be found out as
 *        1 points to 2, 2 points 3, 3 points to 3 itself so 3 is the representative
 *        of the subset containing 1,2,3.
 *        
 * <br>
 * <br>
 * Goal 2:<br>How are we going to use the above data structure
 *         to do union, find operations, and checking if 2 elements
           are connected ?<br>
 * Find: Get the value at an index in an array&#46; Then follow the chain by 
 * values in each index and end when the index is equal to value. the final
 * value is the subset identifier of that subset to which the element
 * belongs to&#46;i<br>
 * Union: <br>Given two elements, just make one element point to the 
 * other&#46;<br>
 * Are they connected ?: <br>Follow the chain by the pointers and find the subset identifier&#46;
 *        Do the above for both elements and if both have the same subset identifier
 *        then it means they are connected . If not it means they are not connected.
 *        
 * <br>
 * <u> Worst case running time </u>
 * <ul>
 *   <li> Union : O(1)(But union also involves find so it is 
 *        effectively O(N) or O(tree height)) </li>
 *   <li> Find : O(N) </li>
 * </ul>
 * @author Harish Kayarohanam
 */
public class QuickUnion implements UnionFind {
  protected int[] elements;
  protected int count;
  
  /*
   * Default Constructor to be used by the subclasses when
   * the parameterized QuickUnion Constructor cannot be used.
   * It is protected as I don't want classes other than subclasses to
   * use it as it simply doesn't make any sense to have a QuickUnion
   * class without the elements array initialized.
   */
  protected QuickUnion() {
    
  }
  
  /**
   * @param totalNumberOfElementsInQuickUnionDatastructure Total number of
   *        elements in the set.
   */
  public QuickUnion(int totalNumberOfElementsInQuickUnionDatastructure) {
    count = totalNumberOfElementsInQuickUnionDatastructure;
    elements = new int[totalNumberOfElementsInQuickUnionDatastructure];
    for (int i = 0; i < totalNumberOfElementsInQuickUnionDatastructure; i++) {
      elements[i] = i;
    }
  }
  
  /**
   * Finds the union of the subsets containing the elements&#46;
   * If it is already in the same subset , it does nothing.
   *
   * @param element1 integer representing the element.
   * @param element2 integer representing the element.
   */
  public void union(final int element1, final int element2) {
    int element1Representative = find(element1);
    int element2Representative = find(element2);
    if (element1Representative == element2Representative) {
      return;
    }
    elements[element1Representative] = element2Representative;
    count--;
  }

  /**
   * Finds the subset containing the given element.
   *
   * @param element integer.
   * @return identifier or representative of the
   *         subset containing the given element.
   */
  public int find(final int element) {
    int shiftingElement = element; 
    while (shiftingElement != elements[shiftingElement]) {
      shiftingElement = elements[shiftingElement];
    }
    return shiftingElement;
  }

  /**
   * Finds whether 2 elements are in the same subset.
   *
   * @param element1 integer(element) whose location needs to be resolved.
   * @param element2 integer(element) whose location needs to be resolved.
   * @return true if the 2 elements are in the same subset, otherwise false.
   */
  public boolean connected(final int element1, final int element2) { 
    return find(element1) == find(element2);
  }

  /**
   * Counts the number of subsets.
   *
   * @return the number of subsets.
   */
  public int count() {
    return count;
  }
}
