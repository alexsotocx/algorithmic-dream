package binary_tree_test

import (
	"testing"

	"github.com/alexsotocx/algorithms/go/data_structures/trees/binary_tree"
	"github.com/alexsotocx/algorithms/go/interfaces"
)

type Integer struct {
	value int
}

func (i Integer) Value() interface{} {
	return i.value
}

func (i Integer) Compare(a interfaces.Comparable) int8 {
	if i.value == a.(Integer).value {
		return 0
	} else if i.value > a.(Integer).value {
		return 1
	}
	return -1
}

func TestInsert(t *testing.T) {
	tree := binary_tree.BinaryTree{}
	tree.Insert(&Integer{value: 1})
	tree.Insert(&Integer{value: 2})
	tree.Insert(&Integer{value: 3})
	tree.Insert(&Integer{value: 4})
	tree.Insert(&Integer{value: 5})

	if tree.Size != 5 {
		t.Errorf("Expected %d, got  %d\n", 1, tree.Size)
	}

	expectInOrder(&tree, []interface{}{4, 2, 1, 3, 5}, t)
	expectPreOrder(&tree, []interface{}{1, 2, 4, 3, 5}, t)
	expectPostOrder(&tree, []interface{}{4, 2, 5, 3, 1}, t)
}

func TestFind(t *testing.T) {
	tree := binary_tree.BinaryTree{}
	tree.Insert(&Integer{value: 1})
	tree.Insert(&Integer{value: 2})
	tree.Insert(&Integer{value: 3})
	tree.Insert(&Integer{value: 4})
	tree.Insert(&Integer{value: 5})

	if found, c := tree.Find(Integer{value: 5}); !(found && c.Value() == 5) {
		t.Error("It should find the element 5")
	}

	if found, c := tree.Find(Integer{value: 6}); found && c != nil {
		t.Error("It should not find the element 6")
	}
}

func TestDeleteRoot(t *testing.T) {
	tree := binary_tree.BinaryTree{}
	tree.Insert(&Integer{value: 1})
	tree.Insert(&Integer{value: 2})
	tree.Insert(&Integer{value: 3})
	tree.Insert(&Integer{value: 4})
	tree.Insert(&Integer{value: 5})
	tree.Insert(&Integer{value: 6})

	d := Integer{value: 1}
	if found, _ := tree.Find(d); !found {
		t.Error("It should find the element 1")
	}

	tree.Delete(d)

	if found, _ := tree.Find(d); found {
		t.Error("It should not find the element 1")
	}

	expectPreOrder(&tree, []interface{}{6, 2, 4, 3, 5}, t)
}

func TestDeleteParentFromBranch(t *testing.T) {
	tree := binary_tree.BinaryTree{}
	tree.Insert(&Integer{value: 1})
	tree.Insert(&Integer{value: 2})
	tree.Insert(&Integer{value: 3})
	tree.Insert(&Integer{value: 4})
	tree.Insert(&Integer{value: 5})
	tree.Insert(&Integer{value: 6})

	d := Integer{value: 2}

	if found, _ := tree.Find(d); !found {
		t.Error("It should find the element 2")
	}

	tree.Delete(d)

	if found, _ := tree.Find(d); found {
		t.Error("It should not find the element 2")
	}

	expectPreOrder(&tree, []interface{}{1, 6, 4, 3, 5}, t)
}

func TestDeleteLeaft(t *testing.T) {
	tree := binary_tree.BinaryTree{}
	tree.Insert(&Integer{value: 1})
	tree.Insert(&Integer{value: 2})
	tree.Insert(&Integer{value: 3})
	tree.Insert(&Integer{value: 4})
	tree.Insert(&Integer{value: 5})
	tree.Insert(&Integer{value: 6})

	d := Integer{value: 3}

	if found, _ := tree.Find(d); !found {
		t.Error("It should find the element 3")
	}

	tree.Delete(d)

	if found, _ := tree.Find(d); found {
		t.Error("It should not find the element 3")
	}

	expectPreOrder(&tree, []interface{}{1, 2, 4, 6, 5}, t)
}

func expectInOrder(tree *binary_tree.BinaryTree, expected []interface{}, t *testing.T) {
	i := 0
	tree.InOrder(func(data interfaces.Comparable) {
		if expected[i] != data.Value() {
			t.Errorf("Expected %d, got  %d\n", expected[i], data)
		}
		i++
	})
}

func expectPreOrder(tree *binary_tree.BinaryTree, expected []interface{}, t *testing.T) {
	i := 0
	tree.PreOrder(func(data interfaces.Comparable) {
		if expected[i] != data.Value() {
			t.Errorf("Expected %d, got  %d\n", expected[i], data.Value())
		}
		i++
	})
}

func expectPostOrder(tree *binary_tree.BinaryTree, expected []interface{}, t *testing.T) {
	i := 0
	tree.PosOrder(func(data interfaces.Comparable) {
		if expected[i] != data.Value() {
			t.Errorf("Expected %d, got  %d\n", expected[i], data.Value())
		}
		i++
	})
}
