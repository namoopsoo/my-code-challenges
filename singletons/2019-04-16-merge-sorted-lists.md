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
(defn mergeit [newlist & lists]
  (let [[lists-remove-min-though smallest] (rem-min-head lists)
        newlist-updated (insert-tolist newlist smallest)]
        (recur lists-remove-min-though newlist-updated))
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
        chosen-i (apply get-min-from-lists-heads lists)
        normal-ones (without-i (range N) chosen-i)
        removed-element (first (get lists chosen-i))
        all-but-first (rest (get lists chosen-i))
        
        ]
      ; okay 
      (conj (map #(get lists %) normal-ones)
            all-but-first)
  )
)

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
