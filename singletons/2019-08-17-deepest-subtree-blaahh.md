
#### question
This is a pretty convoluted question, but given a binary tree, return the deepest (and effectively smallest I think) subtree which contains the deepest nodes in a given tree. So the deepest nodes in a given tree are the 1 or more nodes at the greatest depth.

#### my first accepted solution
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def subtreeWithAllDeepest(self, root: TreeNode) -> TreeNode:
        if root.left is None and root.right is None: return root
        
        memo = all_children(root)
        
        
        leaves = get_leaves(root, 0)
        # print(depths)
    
        max_depth = max(leaves, key=lambda x:x[1])[1]
        max_leaves = [x for x in leaves if x[1] == max_depth]
        deepest_leaves = [x[0] for x in max_leaves]
        
        if len(deepest_leaves) == 1:
            return find_subtree(root, deepest_leaves[0])
        
        candidates = [node for node in memo 
                      if len(set(deepest_leaves) & set(memo[node])
                            ) == len(max_leaves)]
        print('candidates:', candidates)
        
        best = sorted(candidates, key=lambda x:len(memo[x]))[0]
        
        print ('best: ', best)
        
        return find_subtree(root, best)
    
def find_subtree(t, x):
    if t.val == x: return t
    
    if t.left:
        left = find_subtree(t.left, x)
        if left: return left
        
    if t.right:
        right = find_subtree(t.right, x)
        if right: return right
        
    return None
        
    
def get_leaves(t, d):
    # returns leaves.
    if t.left is None and t.right is None:
        return [(t.val, d)]
    
    v = []
    if t.left:
        v.extend(get_leaves(t.left, d+1))
        
    if t.right:
        v.extend(get_leaves(t.right, d+1))
        
    return v    

def all_children(t):
    memo = {}
    
    search_children(t, memo)
    return memo
    # print('memo:', memo)
    
def search_children(t, memo):
    if t.left is None and t.right is None:
        return [t.val]
    vec = []
    if t.left:
        vec.extend(search_children(t.left, memo))
    if t.right:
        vec.extend(search_children(t.right, memo))
        
    memo[t.val] = vec
    
    return [t.val] + memo[t.val]


```
