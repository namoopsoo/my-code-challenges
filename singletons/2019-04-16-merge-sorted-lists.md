#### Q
return a new sorted merged list from K sorted lists, each with size N.


####
```clojure
; original thought.. hmm
(defn merge-sorted-lists [list1 list2 list3]
  (let [K 3 ; (count lists)
        N (count list1)
        pieces (map () (range N))]
    ))
```
```clojure
; next thinking something more recursive like this
(defn all-empty [lists]

)

(defn mergeit [newlist  lists]
  (if (reduce #(and %1 %2) lists) newlist
    (let [[lists-remove-min-though smallest] (apply rem-min-head lists)
          newlist-updated (conj newlist smallest)]
          (recur newlist-updated lists-remove-min-though)))
)



(defn get-min-from-lists-heads [& lists]
  "For the given lists, whats the index of the list with the smallest element"
  (let [N (count lists)]
    (last (first (sort-by
                  (comp first first)
                  (map noop lists (range N)))))
  ))
  
  
  
(defn rem-min-head [& lists]
  "remove the smallest element from the heads of the given lists"
  (let [N (count lists)
        lists-vec (into [] lists)
        chosen-i (apply get-min-from-lists-heads lists-vec)
        range-vec (into [] (range N))
        normal-ones (without-i range-vec chosen-i)
        list-chosen-i (get lists-vec chosen-i)
        removed-element (first (get lists-vec chosen-i))
        all-but-first (into [] (rest (get lists-vec chosen-i)))
        other-lists (map #(get lists-vec %) normal-ones)
        combined (conj other-lists
            all-but-first)
        ]
      [combined removed-element]
  ))

(defn without-i [vec i]
  (let [
        after-i (inc i)]
        (concat (subvec vec 0 i)
                (subvec vec after-i)))
  )
  
  
  
```
```clojure
conquer.core=> (def vecs2 [[5 6 3 6] [3 4 5 0] [7 8 2]])
#'conquer.core/vecs2
conquer.core=> 

conquer.core=> (apply get-min-from-lists-heads vecs2)
([3 4 5 0] 1)
```
```clojure
conquer.core=> (apply rem-min-head  vecs)
lists-vec [[1 2 3 4] [5 6 3 6] [3 4 5 0]]
chosen i 0
normal ones (1 2)
lists-vec [[1 2 3 4] [5 6 3 6] [3 4 5 0]] , list chosen i  [1 2 3 4]
removed element 1
allbut first  (2 3 4)
other lists ([5 6 3 6] [3 4 5 0])
combined ((2 3 4) [5 6 3 6] [3 4 5 0])
((2 3 4) [5 6 3 6] [3 4 5 0])
```
