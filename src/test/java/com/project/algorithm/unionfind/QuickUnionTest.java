package com.project.algorithm.unionfind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.project.algorithm.unionfind.QuickUnion;
import junitx.util.PrivateAccessor;
import org.junit.Test;

public class QuickUnionTest {
  
  @Test
  public void unionTest1() throws NoSuchFieldException {
    // single union operation
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    quickunionDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest2() throws NoSuchFieldException {
    // multiple union operations
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    quickunionDs.union(1, 10);
    quickunionDs.union(2, 3);
    quickunionDs.union(6, 7);
    quickunionDs.union(2, 6);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionDs, "elements");
    int[] expectedResult = new int[] {0, 10, 3, 7, 4, 5, 7, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest3() throws NoSuchFieldException {
    // repeated same union operations
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    quickunionDs.union(1, 10);
    quickunionDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickunionDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void countTest1() {
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    // not initializing array as union takes control of count
    quickunionDs.union(1, 10);
    assertEquals(10, quickunionDs.count());
    quickunionDs.union(2, 10);
    assertEquals(9, quickunionDs.count());
  }
  
  @Test
  public void findTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    PrivateAccessor.setField(quickunionDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertEquals(10, quickunionDs.find(1));
    assertEquals(10, quickunionDs.find(10));
    PrivateAccessor.setField(quickunionDs, "elements", new int[] {0,2,3,5,4,6,6,7,8,9,2});
    assertEquals(6, quickunionDs.find(1));
    assertEquals(6, quickunionDs.find(10));
    assertEquals(6, quickunionDs.find(2));
    assertEquals(6, quickunionDs.find(3));
  }
  
  @Test
  public void connectedTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    QuickUnion quickunionDs = new QuickUnion(sizeOfDs);
    PrivateAccessor.setField(quickunionDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertTrue(quickunionDs.connected(1, 10));
    for (int i = 2; i < 10; i++) {
      assertFalse(quickunionDs.connected(i, 10));
    }
  }
}
