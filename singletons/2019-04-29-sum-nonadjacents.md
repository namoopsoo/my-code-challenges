#### Q
Given a list of integers, write a func that returns the largest sum of non-adjacent numbers. Numbers can be `0` negative.

For example, `[2, 4, 6, 2, 5]` shoudl return `13` since we pick `2`, `6` and `5`.  `[5, 1, 1, 5]` should return `10` since we pick `5` and `5`,

#### idea 1
* An initial thought I had was to write a recursive func that starts at the head , and maintains an accumulating total,
passing the remaining elements based on a small look-ahead decision.
* Although this lookahead thing may be blind-sighted to some oddball edge-cases. 
```clojure

(defn sum-thing [total the-vec]
  (if (-> the-vec
      first
      nil?)
      total
      
      (let [the-first (first the-vec)
            the-second (second the-vec)]
            (if (> the-first the-second)
                (recur (+ total the-first) (rest (rest the-vec)))
                (recur (+ total the-second) (rest (rest (rest the-vec))))))
      )
      
      )

```
* This approach is quite greedy though, and it doesn't pick up the `6` in `[2 4 6 2 5]` , because `4` was chosen.
```clojure
conquer.core=> (def vec1 [2 4 6 2 5])
#'conquer.core/vec1
conquer.core=> (sum-thing 0 vec1)
9
conquer.core=> (sum-thing 0 [5 1 1 5])
10
conquer.core=> 
```
* It looks like in this next example, it would skip the  `23`  similarly because it took the `8`. 
```clojure
conquer.core=> (sum-thing 0 [5 1 1 5 6 3 8 23 4 20])
38
```
* But this idea is linear time however heh.  Any solution I think that sorts and finds all of the biggest numbers first , 
would necessarily have to be at least `O(n logn)` . 

#### idea 2, first find the largest numbers
* first perhaps enumerate the indices so we know which ones we are allowed and not allowed to take.
```clojure
conquer.core=> (sort-by first (map #(vector [%1 %2]) [3 4 11 6] (range 4)))
([[3 0]] [[4 1]] [[6 3]] [[11 2]])
conquer.core=> (reverse (sort-by first (map #(vector [%1 %2]) [3 4 11 6] (range 4))))
([[11 2]] [[6 3]] [[4 1]] [[3 0]])
```

```clojure
; (:require [clojure.set :as cljset])


(defn new? [position memory]
  (= #{}
     (cljset/intersection (set [position]) (set memory))  
     )
  )
  
(defn sorted-indexed [invec]
  (reverse (sort-by first (map #(vector %1 %2)
                          invec
                          (range (count invec))))))

(defn adjacents [i]
    (if (= 0 i) [1]
        [(inc i) (dec i)]))

(defn accumulate-through-sorted-vec
  [sorted-indexed-vec spent-indices-set accumulator-vec accumulated-sum]

  (prn "DEBUG" [sorted-indexed-vec spent-indices-set accumulator-vec accumulated-sum])
  ; if input vec is empty, return the accumulated stuffs.
  (if (empty? sorted-indexed-vec)
      [accumulator-vec accumulated-sum]

      ; otherwise...
      ; check if first index is in spent set. if not, add to accumulator
      ;     and to accumulating-sum also.
      (let [first-position (second (first sorted-indexed-vec))
            element (first (first sorted-indexed-vec))
            allowed-to-use-it (new? first-position spent-indices-set)
            _ (println "element " element)
            _ (println "first position " first-position)
            _ (println "allowed " allowed-to-use-it)
            ]
            (if allowed-to-use-it
                (recur (rest sorted-indexed-vec)
                        (cljset/union spent-indices-set (set (adjacents first-position)) )
                        (conj accumulator-vec element)
                        (+ accumulated-sum element))

                (recur (rest sorted-indexed-vec)
                        (cljset/union spent-indices-set (set (adjacents first-position)) )
                        accumulator-vec
                        accumulated-sum)
                ))))
  
(defn largest-sum [invec]
  (let [sorted-indexed-vec (sorted-indexed invec)]
    (accumulate-through-sorted-vec sorted-indexed-vec
                                    (set [])
                                    []
                                    0)))
    
```

* test run ... almost but not quite, 
```clojure
conquer.core=> (largest-sum [3 4 11 6] )
"DEBUG" [([11 2] [6 3] [4 1] [3 0]) #{} [] 0]
element  11
first position  2
allowed  true
"DEBUG" [([6 3] [4 1] [3 0]) #{1 3} [11] 11]
element  6
first position  3
allowed  false
"DEBUG" [([4 1] [3 0]) #{1 4 3 2} [11] 11]
element  4
first position  1
allowed  false
"DEBUG" [([3 0]) #{0 1 4 3 2} [11] 11]
element  3
first position  0
allowed  false
"DEBUG" [() #{0 1 4 3 2} [11] 11]
[[11] 11]
conquer.core=> 

```
* This test run looks good though , since `54` shows its getting indeed the largest numbers
```clojure
conquer.core=> (largest-sum [5 1 1 5 6 3 8 23 4 20])
"DEBUG" [([23 7] [20 9] [8 6] [6 4] [5 3] [5 0] [4 8] [3 5] [1 2] [1 1]) #{} [] 0]
element  23
first position  7
allowed  true
"DEBUG" [([20 9] [8 6] [6 4] [5 3] [5 0] [4 8] [3 5] [1 2] [1 1]) #{6 8} [23] 23]
element  20
first position  9
allowed  true
"DEBUG" [([8 6] [6 4] [5 3] [5 0] [4 8] [3 5] [1 2] [1 1]) #{6 10 8} [23 20] 43]
element  8
first position  6
allowed  false
"DEBUG" [([6 4] [5 3] [5 0] [4 8] [3 5] [1 2] [1 1]) #{7 6 5 10 8} [23 20] 43]
element  6
first position  4
allowed  true
"DEBUG" [([5 3] [5 0] [4 8] [3 5] [1 2] [1 1]) #{7 6 3 5 10 8} [23 20 6] 49]
element  5
first position  3
allowed  false
"DEBUG" [([5 0] [4 8] [3 5] [1 2] [1 1]) #{7 4 6 3 2 5 10 8} [23 20 6] 49]
element  5
first position  0
allowed  true
"DEBUG" [([4 8] [3 5] [1 2] [1 1]) #{7 1 4 6 3 2 5 10 8} [23 20 6 5] 54]
element  4
first position  8
allowed  false
"DEBUG" [([3 5] [1 2] [1 1]) #{7 1 4 6 3 2 9 5 10 8} [23 20 6 5] 54]
element  3
first position  5
allowed  false
"DEBUG" [([1 2] [1 1]) #{7 1 4 6 3 2 9 5 10 8} [23 20 6 5] 54]
element  1
first position  2
allowed  false
"DEBUG" [([1 1]) #{7 1 4 6 3 2 9 5 10 8} [23 20 6 5] 54]
element  1
first position  1
allowed  false
"DEBUG" [() #{0 7 1 4 6 3 2 9 5 10 8} [23 20 6 5] 54]
[[23 20 6 5] 54]
conquer.core=> 
```
