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

