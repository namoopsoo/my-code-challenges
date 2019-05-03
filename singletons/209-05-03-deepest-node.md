### Q 
Count nodes in a binary tree

#### A breadth first search approach , to initially just print a tree...
```clojure
(def tree {:id 1 :d 0 :l {:id 2 :d 1
                      :l {:id 3 :d 2
                          :l {:id 4 :d 3}}
                      :r {:id 5 :d 2
                          :r {:id 6 :d 3}}}
                  :r {:id 7 :d 1
                      :l {:id 8 :d 2
                          :l {:id 9 :d 3
                              :r {:id 10 :d 4
                                  :l {
                                      :id 11 :d 5
                                      :r {:id 12 :d 6}}
                                  :r {:id 13 :d 5
                                      :l {:id 14 :d 6}}}}}
                      :r {:id 15 :d 2
                          :l {:id 16 :d 3
                              :l {:id 17 :d 4}
                              :r {:id 18 :d 4
                                  :l {:id 19 :d 5
                                      :r {:id 20 :d 6}}}}}}

            })

(defn print-tree-breadth [tree]
  (prn "i am " (get tree :id) :d (get tree :d)) 
  (print :l (get-in tree [:l :id]) " ")
  (print :r (get-in tree [:r :id]) " ")
  (if (not (nil? (get tree :l)))
      (print-tree-breadth (get tree :l)))
  (if (not (nil? (get tree :r)))
      (print-tree-breadth (get tree :r))))
```
* not quite there yet...
```clojure
conquer.core=> (print-tree-breadth tree)
"i am " 1 :d 0
:l 2  :r 7  "i am " 2 :d 1
:l 3  :r 5  "i am " 3 :d 2
:l 4  :r nil  "i am " 4 :d 3
:l nil  :r nil  "i am " 5 :d 2
:l nil  :r 6  "i am " 6 :d 3
:l nil  :r nil  "i am " 7 :d 1
:l 8  :r 15  "i am " 8 :d 2
:l 9  :r nil  "i am " 9 :d 3
:l nil  :r 10  "i am " 10 :d 4
:l 11  :r 13  "i am " 11 :d 5
:l nil  :r 12  "i am " 12 :d 6
:l nil  :r nil  "i am " 13 :d 5
:l 14  :r nil  "i am " 14 :d 6
:l nil  :r nil  "i am " 15 :d 2
:l 16  :r nil  "i am " 16 :d 3
:l 17  :r 18  "i am " 17 :d 4
:l nil  :r nil  "i am " 18 :d 4
:l 19  :r nil  "i am " 19 :d 5
:l nil  :r 20  "i am " 20 :d 6
:l nil  :r nil  nil
```

