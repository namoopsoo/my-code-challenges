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
* ok looks good, 
```clojure
conquer.core=> (prod-except-i [1 2 3 4 5])
(120 60 40 30 24)
```

#### Second case without division, 
```clojure
(defn without-i [vec i]
  (let [
        after-i (inc i)]
        (concat (subvec vec 0 i)
                (subvec vec after-i)))
  )

(defn nodiv-product-except-i [vec]
  (let [size (count vec)]
    (map #(
          reduce * (without-i vec %)
            )
            (range size))))

```
* Test `without-i`
```clojure
conquer.core=> (without-i [4 5 6 7 8 0] 0)
(5 6 7 8 0)
conquer.core=> (without-i [4 5 6 7 8 0] 1)
(4 6 7 8 0)
conquer.core=> (without-i [4 5 6 7 8 0] 10)

IndexOutOfBoundsException   clojure.lang.RT.subvec (RT.java:1573)
conquer.core=> (without-i [4 5 6 7 8 0] 6)

IndexOutOfBoundsException   clojure.lang.RT.subvec (RT.java:1573)
conquer.core=> (without-i [4 5 6 7 8 0] 5)
(4 5 6 7 8)
conquer.core=> 
```
* Test `nodiv-product-except-i ` . Seems ok
```clojure
conquer.core=> (nodiv-product-except-i [1 2 3 4 5])
(120 60 40 30 24)
conquer.core=> (nodiv-product-except-i [3 2 1])
(2 3 6)
conquer.core=> 

```



