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

; next thinking something more recursive like this
(defn mergeit [lists newlist]
  (let [[lists-remove-min-though smallest] (rem-min-head lists)
        newlist-updated (insert-tolist newlist smallest)]
        (mergeit lists-remove-min-though newlist-updated))
)

(defn get-min-from-lists-heads [lists]
  (first (sort-by (comp first first) (enumerate-those-lists lists)))
  )
  
(defn rem-min-head [lists])
```
