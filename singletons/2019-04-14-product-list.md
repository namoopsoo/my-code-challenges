#### question
Given an array of integers, return a new array such that each element at index i of the new array, is the product,
of all the numbers in the original array except the one at i. 

For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], 
the expected output would be [2, 3, 6]

Second case: without ability to use division.

#### For the first case, where you can use division, 
```clojure
(defn prod-except-i [vec]
  (let [product (reduce * vec)]
    (map #(/ product %) vec)))

```
