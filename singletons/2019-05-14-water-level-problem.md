### Q 
you are given an array of non-negative integers that represents a two-dimensional elevation map where each element
is a unit-width wall and the integer is he height. Suppose it will rain and all the spots between the walls
get filled up with rain water.

Compute how many units of water remain trapped on the map in O(n) time and O(1) space .

For example, given the input `[2, 1, 2]`, we can hold `1`.
Given `[3, 0, 1, 3, 0, 5]` , we can hold `8`.

```
; each pass
; - check if new number is greater or equal to input stores
;     - if so, dump it and store it.
;     - else: add units of volume
; - and create a new store if number is unseen (after dumping earlier)

```

```python

def process(data):
    totals = {}
    
    storage = []  # or perhaps {}
    
    for i, height in enumerate(data):
        # update or cash existing storage
        update_storage(storage, totals, i, height)
        
        # allocate new storages
        allocate_new_storages(storage, i, height)
  
def update_storage(storage, totals, i, height):
    for key, store in storage:
        if height >= key:
            # 1) okay pop that key
            # 2) and transfer contents to totals
        else:
            # accumulate key - height
            store.append([i, key - height])
```

