
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
