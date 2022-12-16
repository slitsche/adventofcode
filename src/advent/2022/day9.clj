(ns advent.2022.day9
  (:require [clojure.string :as str]))

(defn parse [s]
  (->> (str/split-lines s)
       (map parse-line )
       flatten))

(defn parse-line [l]
  (let [[dir sc] (str/split l #" ")
        c (Integer/parseInt sc)]
    (case dir
      "R" (repeat c :right)
      "U" (repeat c :up)
      "L" (repeat c :left)
      "D" (repeat c :down))))

(defn vdiff [h t]
  (let [[hx hy] h
        [tx ty] t]
    [(- hx tx) (- hy ty)]))

(defn vadd [v d]
  (let [[vx vy] v
        [dx dy] d]
    [(+ vx dx) (+ vy dy)]))

(defn move? [v]
  (->> (map abs v)
       (some #(> % 1))))

;; calculate new direction (vector) the tail should move
(defn new-dir [v]
  (let [v1 (map abs v)]
    (map (fn [n d]
           (/ n (if (= 0 d) 1 d)))
         v v1)))

(defn next-knot [newh t]
  (let [diff (vdiff newh t)]
    (if (move? diff)
      [newh (vadd t (new-dir diff))]
      [newh t])))

(defn motion [dir [h t]]
  (let [[x y] h]
    (->
     (case dir
       :right [(inc x) y]
       :up    [x (inc y)]
       :down  [x (dec y)]
       :left  [(dec x) y])
     (next-knot t))))

(defn solution1 [s]
  (->>  (parse s)
    ;; compute a list of "Rope" positions
    (reduce (fn [a i] (conj a (motion i (first a))))
            '([[0 0][0 0]]))
    ;; get distinct tail positions
    (reduce (fn [a i] (conj a (last i))) #{})
    count))

(solution1 (slurp "input/2022/day9.txt"))
;; 6197

(defn solution2 [s]
  (->>  (parse s)
    (reduce (fn [a i] (conj a (rope-motion i (first a))))
            '([[0 0] [0 0] [0 0] [0 0] [0 0] [0 0] [0 0] [0 0] [0 0] [0 0]]))
    (reduce (fn [a i] (conj a (last i))) #{})
    count))

(defn rope-motion [dir rope]
  (reduce (fn [a i] (if (vector? (last a))
                      (->> (next-knot (last a) i)
                           last
                           (conj a))
                      (motion dir [a i])))
          rope))

(solution2 (slurp "input/2022/day9.txt"))
;; 2562
