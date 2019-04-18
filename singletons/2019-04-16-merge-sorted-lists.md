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
        lists-vec (into [] lists)
        _ (println "lists-vec" lists-vec)
        chosen-i (apply get-min-from-lists-heads lists-vec)
        _ (println "chosen i" chosen-i)
        range-vec (into [] (range N))
        normal-ones (without-i range-vec chosen-i)
        _ (println "normal ones" normal-ones)
        list-chosen-i (get lists-vec chosen-i)
        _ (println "lists-vec" lists-vec ", list chosen i " list-chosen-i)
        removed-element (first (get lists-vec chosen-i))
        _ (println "removed element" removed-element)
        all-but-first (rest (get lists-vec chosen-i))
        _ (println "allbut first " all-but-first)
        other-lists (map #(get lists-vec %) normal-ones)
        _ (println "other lists" other-lists)
        combined (conj other-lists
            all-but-first)
        _ (println "combined" combined)
        ]
      combined
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
