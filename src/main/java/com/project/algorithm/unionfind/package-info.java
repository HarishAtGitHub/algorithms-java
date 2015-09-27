/**
 * UnionFind Algorithm API and its implementations.
 */
package com.project.algorithm.unionfind;
// for learning Union Find, the order to follow for better understanding
// of how the algorithms evolved is
// 1. QuickFind (find is fast but union is very slow as all the associated nodes will have to change
//               and that too with a parse of the entire array using
//               for (int i = 0; i < elements.length; i++) { which is damn slow
//               so we cannot use this algorithm.)
//               whenever entire array parse is required for each operation we
//               should try to avoid that algorithm.
// 2. QuickUnion (No parse of entire array so safe, as here it is only pointers.
//                always point left tree to right(here). .actually union
//                without find is just O(1) but find is what is slowing down the union in 
//                worst case as Find is slow
//                when the tree is tall and linear(worst case) and it becomes like
//                entire array parse. so if we could make tree
//                short this problem will go away. so this entire array parse worst
//                case problem will also go away.Note: To the
//                question of "even entire array parse case happens in quickunion ?"
//                the answer is "There in QuickFind entire array parse
//                happens in every possible case. But here entire array parse happens only
//                in worst case.")
// 3. WeightedQuickUnionByHeight (in QuickUnion if we make trees short
//                                we can make find operation faster.
//                                So to achieve this make the shorter tree 
//                                point to taller tree)
// 4. WeightedQuickUnionBySize(Why do we need this ?Be careful to read all comments: make 
//                             the smaller size tree point to larger size tree)
// 5. Path compression by halving(in 3 as per our approach
//                                we are only worried about height or 
//                                size of the root node.. so if it is so
//                                then why should we worry about maintaining links 
//                                and waste time while parsing. why not
//                                make every children of a tree(A) too point to the
//                                root node of the other tree(B) to which this tree(A)
//                                is to be attached to.This gave rise to Path Compresssion
//                                technique.With completely flattened trees
//                                we will have QuickUnion with very fast find.
//                                Note: Please don't do the mistake
//                                entire array parse with for loop that we committed
//                                in QuickFind.)
                          