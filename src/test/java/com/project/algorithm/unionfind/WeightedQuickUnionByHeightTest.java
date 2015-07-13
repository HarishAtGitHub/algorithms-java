package com.project.algorithm.unionfind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.project.algorithm.unionfind.WeightedQuickUnionByHeight;

import junitx.util.PrivateAccessor;

import org.junit.Test;

public class WeightedQuickUnionByHeightTest {
  
  @Test
  public void unionTest1() throws NoSuchFieldException {
    // single union operation
    // this is no different from the union in QuickUnion Ds.
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    quickunionByHeightDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionByHeightDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest2() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of equal sized trees
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    quickunionByHeightDs.union(1, 10);
    quickunionByHeightDs.union(2, 3);
    quickunionByHeightDs.union(2, 10);
    // the above will be like 
    // 2 -> 3 -> 10 <- 1
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionByHeightDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 5, 6, 7, 8, 9, 10};
    int[] height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertArrayEquals(expectedResult, actualResult);
    int acutalHeight = height[10]; // checking only root node as only root node matters for us
    int expectedHeight = 3;
    assertEquals(expectedHeight, acutalHeight);
  }
  
  @Test
  public void unionTest3() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of unequal height trees
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    quickunionByHeightDs.union(1, 10);
    quickunionByHeightDs.union(2, 3);
    quickunionByHeightDs.union(2, 10);
    // the above will be like 
    // 2 -> 3 -> 10 <- 1
    quickunionByHeightDs.union(6, 7);
    // the above is 6 -> 7
    quickunionByHeightDs.union(6, 2);
    // result should be like
    // 2-> 3 -> 10 <- 7 <- 6
    //           ^
    //           |
    //           1
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionByHeightDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 5, 7, 10, 8, 9, 10};
    int[] height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertArrayEquals(expectedResult, actualResult);
    int acutalHeight = height[10]; // checking only root node as only root node matters for us
    int expectedHeight = 3;
    assertEquals(expectedHeight, acutalHeight);
  }
  
  @Test
  public void unionTest4() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of equal height trees and see if height increases by 1
    int sizeOfDs = 11;
    int initialHeight = 1;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    int[] height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertEquals(initialHeight, height[1]);
    assertEquals(initialHeight, height[10]);
    quickunionByHeightDs.union(1, 10);
    height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertEquals(initialHeight, height[1]); // indicating that we don't care about childrens height
    assertEquals(initialHeight + 1, height[10]);
  }
  
  @Test
  public void unionTest5() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of unequal size trees
    // compare this with unionTest5() of WeightedQuickUnionBySizeTest
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    quickunionByHeightDs.union(1, 10);
    quickunionByHeightDs.union(2, 3);
    quickunionByHeightDs.union(2, 10);
    // the above will result in tree (say tree1)
    // 2 -> 3 -> 10 <- 1
    // now the above has size 4 and height 3
    int[] height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertEquals(3, height[10]);
    quickunionByHeightDs.union(5, 4);
    quickunionByHeightDs.union(6, 4);
    quickunionByHeightDs.union(7, 4);
    quickunionByHeightDs.union(8, 4);
    quickunionByHeightDs.union(9, 4);
    height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertEquals(2, height[4]);
    // the above will result in a tree (say tree2)
    // where all of 5, 6, 7, 8, 9 will point to 4
    // the above tree has size 6 and height 2.
    // so by our approach to reduce the tree parsing time in worst case
    // we should have made 4 the root node of tree2 to point
    // to root node 10 of tree1 .. thereby maintaining height as 3.
    quickunionByHeightDs.union(10, 4);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionByHeightDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 10, 4, 4, 4, 4, 4, 10};
    height = (int[])PrivateAccessor.getField(quickunionByHeightDs, "height");
    assertArrayEquals(expectedResult, actualResult);
    int acutalHeight = height[10]; // root node is 10 and not 4 as in WeightedQuickUnionBySize
    int expectedHeight = 3;
    assertEquals(expectedHeight, acutalHeight);
    // so we achieved what we wanted
    // but see the problem in WeightedQuickUnionBySizeTest unionTest5()
  }
  
  @Test
  public void unionTest6() throws NoSuchFieldException {
    // repeated same union operations
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    quickunionByHeightDs.union(1, 10);
    quickunionByHeightDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionByHeightDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(actualResult, expectedResult);
  }
  
  @Test
  public void countTest1() {
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    // not initializing array as union takes control of count
    quickunionByHeightDs.union(1, 10);
    assertEquals(10, quickunionByHeightDs.count());
    quickunionByHeightDs.union(2, 10);
    assertEquals(9, quickunionByHeightDs.count());
  }
  
  @Test
  public void findTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    PrivateAccessor.setField(quickunionByHeightDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertEquals(10, quickunionByHeightDs.find(1));
    assertEquals(10, quickunionByHeightDs.find(10));
    PrivateAccessor.setField(quickunionByHeightDs, "elements", new int[] {0,2,3,5,4,6,6,7,8,9,2});
    assertEquals(6, quickunionByHeightDs.find(1));
    assertEquals(6, quickunionByHeightDs.find(10));
    assertEquals(6, quickunionByHeightDs.find(2));
    assertEquals(6, quickunionByHeightDs.find(3));
  }
  
  @Test
  public void connectedTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionByHeight quickunionByHeightDs = new WeightedQuickUnionByHeight(sizeOfDs);
    PrivateAccessor.setField(quickunionByHeightDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertTrue(quickunionByHeightDs.connected(1, 10));
    for (int i = 2; i < 10; i++) {
      assertFalse(quickunionByHeightDs.connected(i, 10));
    }
  }
}
