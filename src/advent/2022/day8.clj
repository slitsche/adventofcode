(ns advent.2022.day8
  (:require [clojure.string :as str]))

(defn parse-line [agg s]
  (->> (char-array s)
       (map #(Integer/parseInt (str %)))
       (vec)
       (conj agg)))

(defn parse [s]
  (->> (str/split-lines s)
       (reduce parse-line [])))

(defn neighbours [[x y] dir grid]
  (let [i (count grid)
        j (count (get grid 0))]
    (cond
      (= dir :left) (for [a (range 0 x)] [a y] )
      (= dir :up)   (for [a (range 0 y)] [x a])
      (= dir :right) (for [a (range (inc x) i)] [a y])
      (= dir :down)  (for [a (range (inc y) j)] [x a]))))

(defn visible-dir [tree dir grid]
  (->>
   (neighbours tree dir grid)
   (map #(get-in grid %))
   (map #(> (get-in grid tree) %))
   (every? identity)))

(defn treevisible? [tree grid]
  (or (visible-dir tree :up grid)
      (visible-dir tree :left grid)
      (visible-dir tree :right grid)
      (visible-dir tree :down grid)))

(defn visible-trees [grid]
  (let [i (count grid)
        j (count (get grid 0))]
    (->> (for [x (range i)
               y (range j)]
           (treevisible? [x y] grid))
         (filter true?)
         count)))

(def grid (parse (slurp "input/2022/day8.txt")))

(visible-trees grid) ;; 1809
