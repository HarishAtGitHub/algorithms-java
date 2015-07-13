package com.project.algorithm.unionfind;

/**
 * Uses QuickFind Algorithm to implement UnionFind.
 * <br>
 * <br>
 * Goal 1:<br>Find a data structure equivalent to mathematical set
 *         and the elements that each of the set holds&#46;<br>
 * The algorithm uses array as the datastructure to hold the
 * elements and the owning subset's identifier&#46;<br>
 * 1&#46; The index of the array are the elements themselves&#46;<br>
 * 2&#46; The values corresponding to the index(elements here)
 *         is the representative of the subset it belongs to&#46;In
 *         other words the identifier of the subset&#46;<br>
 * <br>
 * <br>
 * Goal 2:<br>How are we going to use the above data structure
 *         to do union, find operations, and checking if 2 elements
           are connected ?<br>
 * Find: Get the value at an index in an array&#46; This will get you the
 *       identifier of the subset in which the element resides&#46;i<br>
 * Union: <br>Given two elements, changing the value at the index equivalent to
 *        one element value to the value of the other will do the union&#46;<br>
 *        Note: Union also includes the reality that when the identifier of
 *              one element is changed to another, the associated element's
 *              identifier also needs to be changed&#46;<br>
 * Are they connected ?: <br>If the value at the index equivalent to the
 *        elements passed are the same then they are connected&#46;
 *        If not, then they are not connected&#46; In other words
 *        they belong to different disjoint sets&#46;
 * <br>
 * <u> Worst case running time </u>
 * <ul>
 *   <li> Union : O(N) </li>
 *   <li> Find : O(1) </li>
 * </ul>
 * @author Harish Kayarohanam
 */
public class QuickFind implements UnionFind {
  private int[] elements;
  private int count;
  
  /**
   * @param totalNumberOfElementsInQuickFindDatastructure Total number of
   *        elements in the set.
   */
  public QuickFind(int totalNumberOfElementsInQuickFindDatastructure) {
    count = totalNumberOfElementsInQuickFindDatastructure;
    elements = new int[totalNumberOfElementsInQuickFindDatastructure];
    for (int i = 0; i < totalNumberOfElementsInQuickFindDatastructure; i++) {
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
    for (int i = 0; i < elements.length; i++) {
      if (elements[i] == element1Representative) {
        elements[i] = element2Representative;
      }
    }
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
    return elements[element];
  }

  /**
   * Finds whether 2 elements are in the same subset.
   *
   * @param element1 integer(element) whose location needs to be resolved.
   * @param element2 integer(element) whose location needs to be resolved.
   * @return true if the 2 elements are in the same subset, otherwise false.
   */
  public boolean connected(final int element1, final int element2) {
    return elements[element1] == elements[element2];
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
