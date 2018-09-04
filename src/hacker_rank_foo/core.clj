(ns hacker-rank-foo.core)


(comment 
  (ns solution (:gen-class))
)
 
 
 
(defn find-p [m grid]
  ; (println "find-p, grid, " grid)
  ; (println "grid [0 0] " (get-in grid [0 0]))

  (or 
    (and (= \p (get-in grid [0 0])) [0 0])
    (and (= \p (get-in grid [0 (- m 1)])) [0 (- m 1)])
    (and (= \p (get-in grid [(- m 1) (- m 1)])) [(- m 1) (- m 1)])
    (and (= \p (get-in grid [(- m 1) 0])) [(- m 1) 0])
    ))


(defn get-center [m]
    (let [h (int (/ m 2))]
        [h h]))
 


(defn make-path [start end]
    (let [x1 (first start)
          y1 (last start)
          x2 (first end)
          y2 (last end)
          vert-distance (- y2 y1)
          horz-distance (- x2 x1) 
          ;_ (println "vert-distance, " vert-distance)
          ;_ (println "horz-distance, " horz-distance)
          vert-dir (if (pos? vert-distance)  "DOWN" "UP")
          horz-dir (if (pos? horz-distance) "RIGHT" "LEFT")
          verts (map (fn [x] vert-dir) (range (Math/abs vert-distance)))
          horzs (map (fn [x] horz-dir) (range (Math/abs horz-distance)))
          ]
        (concat verts horzs)))


(defn displayPathtoPrincess [m grid]
    ; (println "m " m)
    ; (println "grid " grid)
    (let [p-coords (find-p m grid)
          center (get-center m)
          ; _ (println "center " center ", p-coords " p-coords)
          paths (make-path center p-coords)
          ; _ (println "paths... " paths)
          out-str (clojure.string/join "\n" paths )
          ]
      out-str
      )
    )


(defn make-empty-arr [n] (vec (map (fn [x] (map (fn [x] "-") (range 0 n))) (range 0 n)) ) )

(defn repl-vec-at-i-j [grid i j x]
    (println (assoc (vec (nth grid i)) j x))
    (vec (assoc grid i (vec (assoc (vec (nth grid i)) j x)))))

(defn make-n-grid-with-p [m coordinates newval]
    (let [empty-grid
          (make-empty-arr m)](repl-vec-at-i-j empty-grid
                                              (first coordinates)
                                              (last coordinates)
                                              newval
                                              )))
(defn to-vecs [seqs]
  (vec (map #(vec %) seqs)))

(defn -main []
  (let [m (Integer. (read-line))
        ; _ (println "m, " m)
        ; grid (slurp *in*) 
        
        grid (to-vecs
               (doall (take m (map #(seq (.toCharArray %)) (repeatedly read-line)))))
               ]
    ; (println "gird... " grid)
    (println (displayPathtoPrincess m grid)))
    )

