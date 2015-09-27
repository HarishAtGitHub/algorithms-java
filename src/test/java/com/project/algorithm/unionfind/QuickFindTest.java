package com.project.algorithm.unionfind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.project.algorithm.unionfind.QuickFind;
import junitx.util.PrivateAccessor;
import org.junit.Test;

public class QuickFindTest {
  
  @Test
  public void unionTest1() throws NoSuchFieldException {
    // single union
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    quickfindDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickfindDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest2() throws NoSuchFieldException {
    // multiple union operations
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    quickfindDs.union(1, 10);
    quickfindDs.union(2, 3);
    quickfindDs.union(6, 7);
    quickfindDs.union(2, 6);
    int[] actualResult = (int[])PrivateAccessor.getField(quickfindDs, "elements");
    int[] expectedResult = new int[] {0, 10, 7, 7, 4, 5, 7, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void unionTest3() throws NoSuchFieldException {
    // repeated same union operations
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    quickfindDs.union(1, 10);
    quickfindDs.union(1, 10);
    int[] actualResult = (int[])PrivateAccessor.getField(quickfindDs, "elements");
    int[] expectedResult = new int[] {0, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertArrayEquals(expectedResult, actualResult);
  }
  
  @Test
  public void countTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    // not initializing array as union takes control of count
    quickfindDs.union(1, 10);
    assertEquals(10, quickfindDs.count());
    quickfindDs.union(2, 10);
    assertEquals(9, quickfindDs.count());
  }
  
  @Test
  public void findTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    PrivateAccessor.setField(quickfindDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertEquals(10, quickfindDs.find(1));
    assertEquals(10, quickfindDs.find(10));
    PrivateAccessor.setField(quickfindDs, "elements", new int[] {0,2,2,2,4,5,6,7,8,9,2});
    assertEquals(2, quickfindDs.find(1));
    assertEquals(2, quickfindDs.find(10));
    assertEquals(2, quickfindDs.find(2));
    assertEquals(2, quickfindDs.find(3));
  }
  
  @Test
  public void connectedTest1() throws NoSuchFieldException {
    int sizeOfDs = 11;
    QuickFind quickfindDs = new QuickFind(sizeOfDs);
    PrivateAccessor.setField(quickfindDs, "elements", new int[] {0,10,2,3,4,5,6,7,8,9,10});
    assertTrue(quickfindDs.connected(1, 10));
    for (int i = 2; i < 10; i++) {
      assertFalse(quickfindDs.connected(i, 10));
    }
  }
}
