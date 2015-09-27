package com.project.algorithm.unionfind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.project.algorithm.unionfind.WeightedQuickUnionBySize;

import junitx.util.PrivateAccessor;

import org.junit.Test;

public class WeightedQuickUnionBySizeTest {
  
  @Test
  public void unionTest1() throws NoSuchFieldException {
    // single union operation
    // this is no different from the union in QuickUnion Ds.
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    quickunionBySizeDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest2() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of equal sized trees
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    quickunionBySizeDs.union(1, 10);
    quickunionBySizeDs.union(2, 3);
    quickunionBySizeDs.union(2, 10);
    // the above will be like 
    // 2 -> 3 -> 10 <- 1
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 5, 6, 7, 8, 9, 10};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertArrayEquals(expectedResult, actualResult);
    int acutalSize = size[10]; // checking only root node as only root node matters for us
    int expectedSize = 4;
    assertEquals(expectedSize, acutalSize);
  }
  
  @Test
  public void unionTest3() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of unequal size trees
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    quickunionBySizeDs.union(1, 10);
    quickunionBySizeDs.union(2, 3);
    quickunionBySizeDs.union(2, 10);
    // the above will be like 
    // 2 -> 3 -> 10 <- 1
    quickunionBySizeDs.union(6, 7);
    // the above is 6 -> 7
    quickunionBySizeDs.union(2, 6);
    // result should be like
    // 2-> 3 -> 10 <- 7 <- 6
    //           ^
    //           |
    //           1
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 5, 7, 10, 8, 9, 10};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertArrayEquals(expectedResult, actualResult);
    int acutalSize = size[10]; // checking only root node as only root node matters for us
    int expectedSize = 6;
    assertEquals(expectedSize, acutalSize);
  }
  
  @Test
  public void unionTest4() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of equal size trees and see if size increases 
    // as expected.
    int sizeOfDs = 11;
    int initialSize = 1;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertEquals(initialSize, size[1]);
    assertEquals(initialSize, size[10]);
    quickunionBySizeDs.union(1, 10);
    size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertEquals(initialSize, size[1]); // indicating that we don't care about childrens height
    assertEquals(initialSize + initialSize, size[10]);
  }
  
  @Test
  public void unionTest5() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of unequal size trees
    // this is a corner case where the idea of combining by size defeats the
    // main purpose of reducing parsing time when doing find.
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    quickunionBySizeDs.union(1, 10);
    quickunionBySizeDs.union(2, 3);
    quickunionBySizeDs.union(2, 10);
    // the above will result in tree (say tree1)
    // 2 -> 3 -> 10 <- 1
    // now the above has size 4 and height 3
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertEquals(4, size[10]);
    quickunionBySizeDs.union(5, 4);
    quickunionBySizeDs.union(6, 4);
    quickunionBySizeDs.union(7, 4);
    quickunionBySizeDs.union(8, 4);
    quickunionBySizeDs.union(9, 4);
    size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertEquals(6, size[4]);
    // the above will result in a tree (say tree2)
    // where all of 5, 6, 7, 8, 9 will point to 4
    // the above tree has size 6 and height 2.
    // so by our approach to reduce the tree parsing time in worst case
    // we should have made 4 the root node of tree2 to point
    // to root node 10 of tree1 .. thereby maintaining height as 3.
    // but because we are doing it by size root node of tree1 which is 10
    // is made to point to root node of tree2 which is 4 , thereby increasing
    // resulting tree height by 1 and thereby increasing tree parse time in worst case.
    // FIXME: the idea of doing it by size a bad idea ?
    quickunionBySizeDs.union(10, 4);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 4, 4, 4, 4, 4, 4};
    size = (int[])PrivateAccessor.getField(quickunionBySizeDs, "size");
    assertArrayEquals(expectedResult, actualResult);
    int acutalSize = size[4]; // checking only root node as only root node matters for us
    int expectedSize = 10;
    assertEquals(expectedSize, acutalSize);
    // the problem is that the above tree has height which is 4 (1 more than 3).
    // the right approach would be to have the height reduced as much as possible.
    // in the WeightedQuickUnionByHeight this would have resulting tree of
    // height only 3.
    // compare it with unionTest5() of WeightedQuickUnionByHeightTest
  }
  
  @Test
  public void unionTest6() throws NoSuchFieldException {
    // repeated same union operations
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    quickunionBySizeDs.union(1, 10);
    quickunionBySizeDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void countTest1() {
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    // not initializing array as union takes control of count
    quickunionBySizeDs.union(1, 10);
    assertEquals(10, quickunionBySizeDs.count());
    quickunionBySizeDs.union(2, 10);
    assertEquals(9, quickunionBySizeDs.count());
  }
  
  @Test
  public void findTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    PrivateAccessor.setField(quickunionBySizeDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertEquals(10, quickunionBySizeDs.find(1));
    assertEquals(10, quickunionBySizeDs.find(10), 10);
    PrivateAccessor.setField(quickunionBySizeDs, "elements", new int[] {0,2,3,5,4,6,6,7,8,9,2});
    assertEquals(6, quickunionBySizeDs.find(1), 6);
    assertEquals(6, quickunionBySizeDs.find(10), 6);
    assertEquals(6, quickunionBySizeDs.find(2), 6);
    assertEquals(6, quickunionBySizeDs.find(3), 6);
  }
  
  @Test
  public void connectedTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionBySize quickunionBySizeDs = new WeightedQuickUnionBySize(sizeOfDs);
    PrivateAccessor.setField(quickunionBySizeDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertTrue(quickunionBySizeDs.connected(1, 10));
    for (int i = 2; i < 10; i++) {
      assertFalse(quickunionBySizeDs.connected(i, 10));
    }
  }
}
