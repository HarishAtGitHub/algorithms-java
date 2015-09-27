package com.project.algorithm.unionfind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.project.algorithm.unionfind.WeightedQuickUnionBySizeWithPathCompression;

import junitx.util.PrivateAccessor;

import org.junit.Test;

public class WeightedQuickUnionBySizeWithPathCompressionTest {
  
  @Test
  public void unionTest1() throws NoSuchFieldException {
    // single union operation of leaf less trees - DEFAULT IS NO FLATTENING CASE
    // this is no different from the union in QuickUnion Ds.
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    // here flattening makes no sense as the resulting tree is itself of height 2
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest2() throws NoSuchFieldException {
    // multiple union operations - FLATTENING CASE
    // to check the union of equal sized trees and it also checks flattening
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    quickunionBySizeWithPcDs.union(2, 3);
    quickunionBySizeWithPcDs.union(2, 10);
    // the above will be like 
    // 2 -> 10 <- 1
    //       ^
    //       |
    //       3
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    // the result is checked after flattening
    int[] expectedResult = new int[] {0, 10, 10, 10, 4, 5, 6, 7, 8, 9, 10};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertArrayEquals(expectedResult, actualResult);
    int acutalSize = size[10]; // checking only root node as only root node matters for us
    int expectedSize = 4;
    assertEquals(expectedSize, acutalSize);
  }
  
  @Test
  public void unionTest3() throws NoSuchFieldException {
    // multiple union operations - NO FLATTENING CASE
    // to check the union of equal size trees where flattening will not work
    // as the unioned nodes are it self root nodes themselves
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    quickunionBySizeWithPcDs.union(2, 3);
    quickunionBySizeWithPcDs.union(3, 10);
    // the above will be like
    // 2 -> 3 -> 10 <- 1 
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 4, 5, 6, 7, 8, 9, 10};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertArrayEquals(expectedResult, actualResult);
  }
 
  @Test
  public void unionTest4() throws NoSuchFieldException {
    // multiple union operations - Excessive FLATTENING CASE 
    // (here flattening will make a lot of sense)
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    quickunionBySizeWithPcDs.union(2, 3);
    quickunionBySizeWithPcDs.union(3, 10);
    // the above will be like
    // 2 -> 3 -> 10 <- 1

    quickunionBySizeWithPcDs.union(4, 7);
    quickunionBySizeWithPcDs.union(5, 6);
    quickunionBySizeWithPcDs.union(6, 7);
    // the above will be like
    // 5 -> 6 -> 7 <- 4

    // now we are going to do union so that entire tree becomes flat, here we go ...
    quickunionBySizeWithPcDs.union(2, 5);
    // the above will be like
    // 2, 6, 4, 5, 10 will all point to 7
    // 1 will point to 10 
    // resulting tree height will be 3  
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    int[] expectedResult = new int[] {0, 10, 7, 7, 7, 7, 7, 7, 8, 9, 7};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertArrayEquals(expectedResult, actualResult);
  }
   
  @Test
  public void unionTest5() throws NoSuchFieldException {
    // multiple union operations - ZERO FLATTENING CASE 
    // (here flattening will NOT HELP)
    // to check the union of equal size trees where flattening will not work
    // as the unioned nodes are it self root nodes themselves
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    quickunionBySizeWithPcDs.union(2, 3);
    quickunionBySizeWithPcDs.union(3, 10);
    // the above will be like
    // 2 -> 3 -> 10 <- 1

    quickunionBySizeWithPcDs.union(4, 7);
    quickunionBySizeWithPcDs.union(5, 6);
    quickunionBySizeWithPcDs.union(6, 7);
    // the above will be like
    // 5 -> 6 -> 7 <- 4

    // now we are going to do union so that entire tree becomes flat, here we go ...
    quickunionBySizeWithPcDs.union(10, 7);
    // the above will be like
    //     _________7_________
    //     |        |        |
    //   __10__     6        4
    //   |    |     |
    //   3    1     5
    //   |
    //   2
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 10, 7, 6, 7, 7, 8, 9, 7};
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertArrayEquals(expectedResult, actualResult);
  }

  @Test
  public void unionTest6() throws NoSuchFieldException {
    // multiple union operations
    // to check the union of equal size trees and see if size increases 
    // as expected.
    int sizeOfDs = 11;
    int initialSize = 1;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    int[] size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertEquals(initialSize, size[1]);
    assertEquals(initialSize, size[10]);
    quickunionBySizeWithPcDs.union(1, 10);
    size = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "size");
    assertEquals(initialSize, size[1]); // indicating that we don't care about childrens height
    assertEquals(initialSize + initialSize, size[10]);
  }
  
  @Test
  public void unionTest7() throws NoSuchFieldException {
    // repeated same union operations
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    quickunionBySizeWithPcDs.union(1, 10);
    quickunionBySizeWithPcDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionBySizeWithPcDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void countTest1() {
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    // not initializing array as union takes control of count
    quickunionBySizeWithPcDs.union(1, 10);
    assertEquals(10, quickunionBySizeWithPcDs.count());
    quickunionBySizeWithPcDs.union(2, 10);
    assertEquals(9, quickunionBySizeWithPcDs.count());
  }
  
  @Test
  public void findTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    PrivateAccessor.setField(quickunionBySizeWithPcDs, "elements", new 
        int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertEquals(10, quickunionBySizeWithPcDs.find(1));
    assertEquals(10, quickunionBySizeWithPcDs.find(10), 10);
    PrivateAccessor.setField(quickunionBySizeWithPcDs, "elements", new 
        int[] {0,2,3,5,4,6,6,7,8,9,2});
    assertEquals(6, quickunionBySizeWithPcDs.find(1), 6);
    assertEquals(6, quickunionBySizeWithPcDs.find(10), 6);
    assertEquals(6, quickunionBySizeWithPcDs.find(2), 6);
    assertEquals(6, quickunionBySizeWithPcDs.find(3), 6);
  }
  
  @Test
  public void connectedTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    WeightedQuickUnionBySizeWithPathCompression quickunionBySizeWithPcDs = new 
        WeightedQuickUnionBySizeWithPathCompression(sizeOfDs);
    PrivateAccessor.setField(quickunionBySizeWithPcDs, "elements", new 
        int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertTrue(quickunionBySizeWithPcDs.connected(1, 10));
    for (int i = 2; i < 10; i++) {
      assertFalse(quickunionBySizeWithPcDs.connected(i, 10));
    }
  }
}
