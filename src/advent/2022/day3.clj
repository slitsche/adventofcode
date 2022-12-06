(ns advent.2022.day3
  (:require [clojure.string :as str]))

;; failed for exactly one item per rucksack

(defn wrong-item [rucksack]
  (let [middle (/ (count rucksack) 2)
        compartments (split-at middle rucksack)]
    (->>
     (map set compartments)
     (apply clojure.set/intersection)
     first)))


(defn priority [item]
  (let [c (int item)]
    (if (> c 96)
      (- c 96)
      (+ 26 (- c 64)))))

(defn rucksack-prio [rucksack]
  (-> (wrong-item rucksack)
      (priority)))

(def rucksacks (str/split-lines (slurp "input.txt")))

;; solution part I
(reduce (fn [a i] (+ a (rucksack-prio i)))
        0
        rucksacks)


;;;;  Part 2

(defn items [r]
  (chars (char-array r)))

(defn find-badge [rs]
  (->> (map items rs)
       (map set)
       (apply clojure.set/intersection)
       (first)))

(defn team-prio [rs]
  (-> (find-badge rs)
      (priority)))

(def teams (partition 3 rucksacks))

;; solution
(reduce (fn [a i] (+ a (team-prio i)))
        0
        teams)
