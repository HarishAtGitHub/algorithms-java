package com.project.algorithm.unionfind;

/**
 * API for UnionFind Algorithms.
 * UnionFind Algorithm is an algorithm to
 * keep track of a set of elements partitioned
 * into a number of disjoint subsets(non overlapping sets i.e.,sets whose
 * intersection is an empty set)&#46;
 * <br>
 * <br>
 * The Algorithm mainly does 2 things<br>
 * 1&#46; Union : Joining two subsets into one single subset&#46;<br>
 * 2&#46; Find  : Finding the subset containing the element&#46;<br>
 * <br>
 * A subset is referred by its representative&#46;
 * The representative of a subset is generally
 * one of the elements in that set&#46;<br>
 * But it is upto to implemenation to decide on this representative&#46;<br>
 * [Reference:<br>
 *  1&#46; <a href="https://en.wikipedia.org/wiki/Disjoint_sets">
             https://en.wikipedia.org/wiki/Disjoint_sets
            </a><br>
 *  2&#46; <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">
             https://en.wikipedia.org/wiki/Disjoint-set_data_structure
            </a><br>
 *  3&#46; <a href="http://www.algorithmist.com/index.php/Union_Find">
             http://www.algorithmist.com/index.php/Union_Find
            </a><br>
 *  ].
 * @author Harish Kayarohanam
 */
public abstract class UnionFind {
  protected int[] elements;
  protected int count;

  protected UnionFind() {
  }

  protected UnionFind(final int totalNumberOfElementsInDatastructure) {
    count = totalNumberOfElementsInDatastructure;
    elements = new int[totalNumberOfElementsInDatastructure];
    for (int i = 0; i < totalNumberOfElementsInDatastructure; i++) {
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
  protected abstract void union(int element1, int element2);

  /**
   * Finds the subset containing the given element.
   *
   * @param element integer.
   * @return identifier or representative of the
   *         subset containing the given element.
   */
  protected abstract int find(int element);

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
