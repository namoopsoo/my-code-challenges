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

```



