package chapter4;

import datastructures.BinaryNode;
import org.junit.Assert;
import org.junit.Test;

public class E4_6Test {
  @Test
  public void nextSucccesor() {
    BinaryNode<Integer> root = new BinaryNode<>(5);
    BinaryNode<Integer> l1Left = new BinaryNode<>(3);
    BinaryNode<Integer> l1right = new BinaryNode<>(7);
    BinaryNode<Integer> l2lleft = new BinaryNode<>(2);
    BinaryNode<Integer> l2lright = new BinaryNode<>(4);
    BinaryNode<Integer> l2rleft = new BinaryNode<>(6);
    BinaryNode<Integer> l2rright = new BinaryNode<>(8);

    root.left = l1Left;
    root.right = l1right;
    l1Left.parent = root;
    l1right.parent = root;
    l1Left.left = l2lleft;
    l1Left.right = l2lright;
    l2lleft.parent = l1Left;
    l2lright.parent = l1Left;
    l1right.left = l2rleft;
    l1right.right = l2rright;
    l2rleft.parent = l1right;
    l2rright.parent = l1right;

    Assert.assertEquals(6, new E4_6().nextSucccesor(root).data.intValue());
    Assert.assertEquals(7, new E4_6().nextSucccesor(l2rleft).data.intValue());
    Assert.assertEquals(5, new E4_6().nextSucccesor(l2lright).data.intValue());
    Assert.assertEquals(3, new E4_6().nextSucccesor(l2lleft).data.intValue());

    root.right = null;
    Assert.assertEquals(null, new E4_6().nextSucccesor(root));
  }
}
