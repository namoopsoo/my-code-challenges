
#### Question
Express the permutations of elements in a list.

```clojure

(defn without [vec elem]
    (for [x vec :when (not (= x elem))] x))


(defn perms [freevec choices]
  (if (= 1 (count freevec))
      ; choices + freevec[0]
      (conj choices (first freevec))
      
      ; otherwise
      (for [elem freevec]
            (perms 
                  ; rest => freevec - elem
                  (without freevec elem)
                  (conj choices elem)
                  ))
      )
)

```

* ==> first stab
```clojure
user=> (perms [1 2 3] [])
(([1 2 3] [1 3 2]) ([2 1 3] [2 3 1]) ([3 1 2] [3 2 1]))
```

* validate size...
```clojure
(defn factorial [n]
    (apply * 
        (without (range (inc n)) 0)
        ))
        
user=> (pprint (perms [1 2 3 4] []))
((([1 2 3 4] [1 2 4 3]) ([1 3 2 4] [1 3 4 2]) ([1 4 2 3] [1 4 3 2]))
 (([2 1 3 4] [2 1 4 3]) ([2 3 1 4] [2 3 4 1]) ([2 4 1 3] [2 4 3 1]))
 (([3 1 2 4] [3 1 4 2]) ([3 2 1 4] [3 2 4 1]) ([3 4 1 2] [3 4 2 1]))
 (([4 1 2 3] [4 1 3 2]) ([4 2 1 3] [4 2 3 1]) ([4 3 1 2] [4 3 2 1])))
nil

user=> (count (perms [1 2 3 4] []))
4
```

#### fix how the sequences are combined.
* Slight change, adding use of `(vec )` to try to combine... 
* So `vec` sort of should work to at least exhause a sequence into a vector..
```clojure
(defn perms [freevec choices]
  (if (= 1 (count freevec))
      ; choices + freevec[0]
      (conj choices (first freevec))
      
      ; otherwise
      (vec
        (for [elem freevec]
            (perms 
                  ; rest => freevec - elem
                  (without freevec elem)
                  (conj choices elem)
                  ))
            )))
```
* => not quite...   I guess its not 
```clojure
user=> (perms [1 2 3] [])
[[[1 2 3] [1 3 2]] [[2 1 3] [2 3 1]] [[3 1 2] [3 2 1]]]
```
